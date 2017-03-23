package com.coolweather.android.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import com.coolweather.android.R;
import com.coolweather.android.util.DoubleClickExit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getString("weather", null) != null) {
            Intent intent = new Intent(this, WeatherActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        if (!DoubleClickExit.check()) {
            Snackbar.make(MainActivity.this.getWindow().getDecorView().findViewById(android.R.id.content), "再按一次退出app!", Snackbar.LENGTH_SHORT).show();
        } else {
            super.onBackPressed();
        }
    }

}