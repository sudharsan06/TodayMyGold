package com.sample.todaymygold;
import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.RemoteViews;

import com.sample.todaymygold.POJO.GoldRatePojo;
import com.sample.todaymygold.Utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UpdateGoldWidgetService extends IntentService {

    private static final String API_URL = "https://raw.githubusercontent.com/sudharsan06/TodayMyGold/main/small_gold.json";
    private static ArrayList<GoldRatePojo> goldRatePojoArrayList = new ArrayList<>();
    public UpdateGoldWidgetService() {
        super("UpdateGoldWidgetService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            String message = parseJson(result.toString());

            // Update the widget
            Integer priceDiff = goldRatePojoArrayList.get(0).getYesterday24K_gold() - goldRatePojoArrayList.get(0).get_24k_gold_rate();





            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            RemoteViews views = new RemoteViews(getPackageName(), R.layout.widget_layout);
            views.setTextViewText(R.id.widget_text, Constants.rupee_symbol+" "+goldRatePojoArrayList.get(0).get_22k_gold_rate());
            views.setTextViewText(R.id.tv_price_24k, Constants.rupee_symbol+" "+goldRatePojoArrayList.get(0).get_24k_gold_rate());
            views.setTextViewText(R.id.tv_price_diff, priceDiff+"");
            if(priceDiff > 0)
                views.setTextViewCompoundDrawables(R.id.tv_price_diff,0,0,R.drawable.ic_arrow_up,0);
            else if(priceDiff < 0)
                views.setTextViewCompoundDrawables(R.id.tv_price_diff,0,0,R.drawable.ic_arrow_down,0);
            else
                views.setTextViewCompoundDrawables(R.id.tv_price_diff,0,0,R.drawable.ic_no_change_16,0);

            // Update all widgets
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, GoldWidgetProvider.class));
            appWidgetManager.updateAppWidget(appWidgetIds, views);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String parseJson(String json) {
        // Simple JSON parsing example; adjust based on actual JSON structure
        try {
            JSONObject jsonObject = new JSONObject(json);
            goldRatePojoArrayList.clear();
            GoldRatePojo goldRatePojo = new GoldRatePojo();
            goldRatePojo.setDate(jsonObject.getString("date"));
            goldRatePojo.setState(jsonObject.getString("state"));
            goldRatePojo.setCity(jsonObject.getString("city"));
            goldRatePojo.set_22k_gold_rate(jsonObject.getInt("22k_gold_rate"));
            goldRatePojo.set_24k_gold_rate(jsonObject.getInt("24k_gold_rate"));
            goldRatePojo.setYesterday22K_gold(jsonObject.getInt("yesterday22K_gold"));
            goldRatePojo.setYesterday24K_gold(jsonObject.getInt("yesterday24K_gold"));
            goldRatePojoArrayList.add(goldRatePojo);


            return jsonObject.getString("state"); // Change "message" based on your JSON
        } catch (JSONException e) {
            e.printStackTrace();
            return "Error";
        }
    }
}