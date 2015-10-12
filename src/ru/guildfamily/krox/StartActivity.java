package ru.guildfamily.krox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ru.guildfamily.krox.R;

/**
 * Created by Dan) on 10.10.2015.
 */
public class StartActivity extends Activity {

    public static final String APP_PREFERENCES = "settings"; //имя файла настроек
    public static final String APP_PREFERENCES_TIME= "time"; //параметр файла настроек, время таймера
    public static int time;
    private SharedPreferences settings; //класс для работы с настройками

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);

        Context context = this;
        //buttons
        Button buttonStart = (Button)(findViewById(R.id.buttonStart));
        Button buttonSettings = (Button)findViewById(R.id.buttonSettings);

        //Listeners
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //settings
                settings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                if (settings.contains(APP_PREFERENCES_TIME)) {
                    time = settings.getInt(APP_PREFERENCES_TIME, 0);
                } else {

                    Resources res = context.getResources();
                    time = res.getInteger(R.integer.default_time);  // по умолчанию
                }
                Intent intent = new Intent(StartActivity.this, KroxActivity.class);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });

        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}