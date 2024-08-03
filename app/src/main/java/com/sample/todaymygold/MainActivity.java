package com.sample.todaymygold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     TextView tvAdmin;
    private Handler handler = new Handler();
    private Runnable longPressRunnable;
    private boolean isLongPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvAdmin = findViewById(R.id.tvAdmin);
        tvAdmin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLongPressed = false;
                        longPressRunnable = new Runnable() {
                            @Override
                            public void run() {
                                isLongPressed = true;
                                openSecondActivity();
                            }
                        };

                        handler.postDelayed(longPressRunnable, 10000); // 10 seconds
                        return true;

                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacks(longPressRunnable);
                        if (!isLongPressed) {
                            // Short press detected; you can handle it here if needed
                        }
                        return true;
                }
                return false;
            }
        });

    }
    private void openSecondActivity() {
        Intent intent = new Intent(MainActivity.this, AdminPageActivity.class);
        startActivity(intent);
    }
}