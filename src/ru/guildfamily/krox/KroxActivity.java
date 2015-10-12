package ru.guildfamily.krox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;
import android.view.MotionEvent;
import android.widget.Toast;

public class KroxActivity extends Activity {
    private GestureDetector gestureDetector;
    public int count  = 0;
    private boolean isFirstSwipe = true;
    public int GAME_TIME;
    private boolean onWorking = true;  // Эта штука для того, чтобы не считались свайпы во время создания Алерт диалога
    private TextView textViewInfo;
    private TextView textViewScore;
    private SharedPreferences mSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GAME_TIME = getIntent().getExtras().getInt("time");

        //загружаем textView
        textViewInfo = (TextView)findViewById(R.id.textViewInfo);
        textViewScore = (TextView)findViewById(R.id.textViewScore);
        textViewInfo.setText(getText(R.string.info));
        textViewScore.setText(getText(R.string.count) + ": 0");

        //Swipes Detecting
        gestureDetector = initGestureDetector();
        View view = findViewById(R.id.LinearLayout);
        view.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

            }
        });
    }


    //распознаем движение на экране
    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                                   float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        return false;
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        //showToast("Up Swipe");
                    } else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                       // showToast("Left Swipe");
                        if (onWorking) {
                            if (isFirstSwipe) {
                                //SetOnTimer
                                setOnTimer();
                                isFirstSwipe = false;
                                textViewInfo.setText("Александр Сергеевич Пушкин");
                            } else {
                                count++;
                                String text = getString(R.string.count) + ": " + Integer.toString(count);
                                textViewScore.setText(text);
                            }
                        }
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        //showToast("Right Swipe");
                    }
                } catch (Exception e) {
                } //for now, ignore
                return false;
            }
        });
    }

    public void setOnTimer() {
        new CountDownTimer(GAME_TIME, 1000) {
            public void onTick(long millisUntilFinished) {
                //nothing
                //System.out.println("tick");
            }
            public void onFinish() {
                showScore();
                //textViewInfo.setText(getText(R.string.info));
            }
        }.start();
    }

    public void showScore() {
        onWorking = false;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(KroxActivity.this);

        // set title
        alertDialogBuilder.setTitle(R.string.timeIsUp);

        // set dialog message
        alertDialogBuilder
                .setCancelable(false)
                .setMessage(getText(R.string.count) + ": " + Integer.toString(count))
                .setNeutralButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        count = 0;
                        textViewInfo.setText(getText(R.string.info));
                        isFirstSwipe = true;
                        textViewScore.setText(getText(R.string.count) + ": 0");
                        onWorking = true;
                    }
                });
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void showToast(String phrase) {
        Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
    }
}
