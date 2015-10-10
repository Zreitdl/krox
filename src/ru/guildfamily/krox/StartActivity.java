package ru.guildfamily.krox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ru.guildfamily.krox.R;

/**
 * Created by Dan) on 10.10.2015.
 */
public class StartActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        //buttons
        Button buttonStart =  (Button)(findViewById(R.id.buttonStart));

        //Listeners
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, KroxActivity.class);
                startActivity(intent);
            }
        });
    }
}