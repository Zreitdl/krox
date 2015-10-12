package ru.guildfamily.krox;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by Dan) on 12.10.2015.
 */
public class SettingsActivity extends Activity {

    TextView textViewTimeSet;
    RadioButton button30;
    RadioButton button45;
    RadioButton button60;
    public static final String APP_PREFERENCES = "settings"; //имя файла настроек
    public static final String APP_PREFERENCES_TIME= "time"; //параметр файла настроек, время таймера
    public static int TIME;
    private SharedPreferences settings; //класс для работы с настройками


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        textViewTimeSet = (TextView)findViewById(R.id.textViewTimeSet);
       // textViewTimeSet.setText(getText(R.string.timeSet) + ":");

        button30 = (RadioButton)findViewById(R.id.radioButton30);
        button45 = (RadioButton)findViewById(R.id.radioButton45);
        button60 = (RadioButton)findViewById(R.id.radioButton60);
        button30.setChecked(false);
        button45.setChecked(false);
        button60.setChecked(false);

        //load settings
        settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        if (settings.contains(APP_PREFERENCES_TIME)) {
            TIME = settings.getInt(APP_PREFERENCES_TIME, 0);
        } else {
            SharedPreferences.Editor editor = settings.edit();
            Context context = this;
            Resources res = context.getResources();
            TIME = res.getInteger(R.integer.default_time);  // по умолчанию
            editor.putInt(APP_PREFERENCES_TIME, TIME);
            editor.apply();
        }

        //set RadioButton
        if (TIME == 45000) {
            button45.setChecked(true);
        } else if (TIME == 60000) {
            button60.setChecked(true);
        } else {
            TIME = 30000;
            button30.setChecked(true);
        }

        RadioGroup radioGroupTime = (RadioGroup)findViewById(R.id.radioGroupTime);
        radioGroupTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
//                    case -1:
//                        Toast.makeText(getApplicationContext(), "No choice", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.radioButton30:
                        TIME = 30000;
                        break;
                    case R.id.radioButton45:
                        TIME = 45000;
                        break;
                    case R.id.radioButton60:
                        TIME = 60000;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    protected void onPause() {
        super.onPause();
        //showToast("pause");
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(APP_PREFERENCES_TIME, TIME);
        editor.apply();
    }

    private void showToast(String phrase) {
        Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
    }
}