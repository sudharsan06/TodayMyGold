package com.sample.todaymygold;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sample.todaymygold.FirebasePack.FirebaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextView tvAdmin;
    Button btn_temp_fetch_data;
    private Handler handler = new Handler();
    private Runnable longPressRunnable;
    private boolean isLongPressed = false;
    public static String result = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseHelper firebaseHelper = new FirebaseHelper();


        tvAdmin = findViewById(R.id.tvAdmin);
        btn_temp_fetch_data = findViewById(R.id.btn_temp_fetch_data);
        btn_temp_fetch_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                firebaseHelper.fetchGoldRates(currentDate);

                if(MainActivity.result!=null && !MainActivity.result.isEmpty())
                    tvAdmin.setText(MainActivity.result);


            }
        });

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