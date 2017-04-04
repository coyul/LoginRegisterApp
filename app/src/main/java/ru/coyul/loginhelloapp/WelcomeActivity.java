package ru.coyul.loginhelloapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        textView = (TextView) findViewById(R.id.helloView);
        textView.setText("Привет, " + getSharedPreferences(StartActivity.PREFS_NAME, 0).getString("userName", "") + "!");
    }
}
