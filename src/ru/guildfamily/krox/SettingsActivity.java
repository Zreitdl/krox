package ru.guildfamily.krox;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

/**
 * Created by Dan) on 12.10.2015.
 */
public class SettingsActivity extends Activity {

    TextView textViewTimeSet;
    Button button30;
    Button button45;
    Button button60;
    public static final String APP_PREFERENCES = "settings"; //имя файла настроек
    public static final String APP_PREFERENCES_TIME= "time"; //параметр файла настроек, время таймера
    public static int TIME;
    private SharedPreferences settings; //класс для работы с настройками


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        textViewTimeSet = (TextView)findViewById(R.id.textViewTimeSet);
        textViewTimeSet.setText(getText(R.string.timeSet) + ":");

        button30 = (RadioButton)findViewById(R.id.radioButton30);
        button45 = (RadioButton)findViewById(R.id.radioButton45);
        button60 = (RadioButton)findViewById(R.id.radioButton60);
        button30.setActivated(false);
        button45.setActivated(false);
        button60.setActivated(false);

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
        if (TIME == 30000) {
            button30.setActivated(true);
        } else if (TIME == 45000) {
            button45.setActivated(true);
        } else if (TIME == 60000) {
            button60.setActivated(true);
        }

        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button30.setActivated(true);
                button45.setActivated(false);
                button60.setActivated(false);
            }
        });

        button45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button45.setActivated(true);
                button30.setActivated(false);
                button60.setActivated(false);
            }
        });

        button60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button60.setActivated(true);
                button45.setActivated(false);
                button30.setActivated(false);
            }
        });
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState); // что делает эта строчка?
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(APP_PREFERENCES_TIME, TIME);
        editor.apply();
    }

}