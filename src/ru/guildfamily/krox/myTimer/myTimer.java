package ru.guildfamily.krox.myTimer;

import android.os.CountDownTimer;

import java.util.Timer;

/**
 * Created by Dan) on 31.10.2015.
 */
public abstract class myTimer {
    private int time = 0;
    private int game_time = 0;
    private CountDownTimer countDownTimer;
    public myTimer(int x) {
        time = x;
        game_time = x;
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time-=1000;
            }

            @Override
            public void onFinish() {
                time = game_time;
                finish();
            }
        };
    }

    public void onStart() {
        countDownTimer.start();
    }
    public void onPause() {
        countDownTimer.cancel();
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time-=1000;
            }

            @Override
            public void onFinish() {
                time = game_time;
                finish();
            }
        };
    }

    public abstract void finish();

    public void cancel() {

        countDownTimer.cancel();

    }
}
