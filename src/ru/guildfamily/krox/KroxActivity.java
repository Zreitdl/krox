package ru.guildfamily.krox;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;
import android.widget.TextView;
import android.view.MotionEvent;
import android.widget.Toast;

public class KroxActivity extends Activity {
    private GestureDetector gestureDetector;
    public int count  = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        gestureDetector = initGestureDetector();

        TextView textView = (TextView) findViewById(R.id.textView);
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
                        count++;
                        TextView textView = (TextView)findViewById(R.id.textView);
						String text = "—чет: " + Integer.toString(count);
                        textView.setText(text);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        //showToast("Right Swipe");
                    }
                } catch (Exception e) {
                } //for now, ignore
                return false;
            }

            private void showToast(String phrase) {
                Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
