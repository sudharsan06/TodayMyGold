package com.sample.todaymygold;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.sample.todaymygold.FirebasePack.FirebaseHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminPageActivity extends AppCompatActivity {

    EditText editTextText, editTextText2, editTextText3, editTextText4, editTextText5, editTextText6;
    Button button;
    FirebaseHelper firebaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextText = findViewById(R.id.editTextText);   //State
        editTextText2 = findViewById(R.id.editTextText2); //City
        editTextText3 = findViewById(R.id.editTextText3); //22k_tdyRate
        editTextText4 = findViewById(R.id.editTextText4); //24k_tdyRate
        editTextText5 = findViewById(R.id.editTextText5); //22k_ytdRate
        editTextText6 = findViewById(R.id.editTextText6); //24k_ytdRate
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
               // String date = editTextText.getText().toString();
                String state = editTextText.getText().toString();
                String city = editTextText2.getText().toString();
                int _22k_tdyRate = Integer.parseInt(editTextText3.getText().toString());
                int _24k_tdyRate = Integer.parseInt(editTextText4.getText().toString());
                int _22k_ytdRate = Integer.parseInt(editTextText5.getText().toString());
                int _24k_ytdRate = Integer.parseInt(editTextText6.getText().toString());
                firebaseHelper = new FirebaseHelper();
                firebaseHelper.sendGoldRate(currentDate, state, city, _22k_tdyRate, _24k_tdyRate, _22k_ytdRate, _24k_ytdRate);

               /* firebaseHelper = new FirebaseHelper();
                firebaseHelper.sendGoldRate("2024-08-04", "Tamil Nadu", "Coimbatore", 6450, 7036, 6460, 7047);*/

            }
        });



    }
}