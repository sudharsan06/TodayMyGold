package com.sample.todaymygold.FirebasePack;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sample.todaymygold.MainActivity;
import com.sample.todaymygold.POJO.GoldRatePojo;
import com.sample.todaymygold.Utils.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FirebaseHelper {

    private DatabaseReference databaseReference;
    public FirebaseHelper() {
        // Initialize the Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    //
    public void sendGoldRate(String date, String state, String city, int _22k_tdyRate, int _24k_tdyRate, int _22k_ytdRate, int _24k_ytdRate) {
        // Create a GoldRate object
        //String date, String state, String city, int _22k_gold_rate, int _24k_gold_rate, int yesterday22K_gold, int yesterday24K_gold
        /*GoldRatePojo goldRate = new GoldRatePojo(date, state, city, _22k_tdyRate, _24k_tdyRate, _22k_ytdRate, _24k_ytdRate);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());


        // Push data to Firebase Database
        databaseReference.child("todayMyGold").child(currentDate).setValue(goldRate)
                .addOnSuccessListener(aVoid -> {
                    Log.d(Constants.TAG, "Data sent successfully");
                })
                .addOnFailureListener(e -> {
                    // Failed to send data
                    Log.d(Constants.TAG, "Issue in sending data to firebase!");
                });*/
        //GoldRatePojo goldRate = new GoldRatePojo("2024-08-04", "Tamil Nadu", "Coimbatore", 6450, 7036, 6460, 7047);
        GoldRatePojo goldRate = new GoldRatePojo(date, state, city, _22k_tdyRate, _24k_tdyRate, _22k_ytdRate, _24k_ytdRate);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        databaseReference.child("todayMyGold").child(currentDate).setValue(goldRate)
                .addOnSuccessListener(aVoid -> System.out.println("Test data sent successfully!"))
                .addOnFailureListener(e -> e.printStackTrace());
    }


    public void fetchGoldRates(String currentDate) {


        DatabaseReference ref = databaseReference.child("todayMyGold").child(currentDate);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    GoldRatePojo goldRate = snapshot.getValue(GoldRatePojo.class);
                   // for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                        if (goldRate != null) {
                            // Use the GoldRate object
                            Log.d(Constants.TAG, "Date: " + goldRate.getDate());
                            Log.d(Constants.TAG, "State: " + goldRate.getState());
                            Log.d(Constants.TAG, "City: " + goldRate.getCity());
                            Log.d(Constants.TAG, "today_22k_G: " + goldRate.get_22k_gold_rate());
                            Log.d(Constants.TAG, "today_24k_G: " + goldRate.get_24k_gold_rate());
                            Log.d(Constants.TAG, "yesterday_22k_G: " + goldRate.getYesterday22K_gold());
                            Log.d(Constants.TAG, "yesterday_24k_G: " + goldRate.getYesterday24K_gold());

                            MainActivity.result = "Date: " + goldRate.getDate() + "\n" +
                                    "State: " + goldRate.getState() + "\n" +
                                    "City: " + goldRate.getCity() + "\n" +
                                    "today_22k_G: " + goldRate.get_22k_gold_rate() + "\n" +
                                    "today_24k_G: " + goldRate.get_24k_gold_rate() + "\n" +
                                    "yesterday_22k_G: " + goldRate.getYesterday22K_gold() + "\n" +
                                    "yesterday_24k_G: " + goldRate.getYesterday24K_gold() + "\n";
                        } else {
                            Log.d(Constants.TAG, "No data found!");
                        }
                   // }
                } else {
                    Log.d(Constants.TAG, "No data found!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }) ;


}

}
