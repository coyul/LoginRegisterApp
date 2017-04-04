package ru.coyul.loginhelloapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    Button loginBtn;
    Button registerBtn;
    public static final String PREFS_NAME = "PrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        SharedPreferences settings = getSharedPreferences(StartActivity.PREFS_NAME, 0);
        boolean hasLoggedIn = settings.getBoolean("HasLoggedIn", false);
        if (hasLoggedIn) {
            startWelcomeActivity();
        }

        loginBtn = (Button) findViewById(R.id.btnLogin);
        registerBtn = (Button) findViewById(R.id.btnRegister);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btnLogin:
                        Intent intentLogin = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intentLogin);
                        break;
                    case R.id.btnRegister:
                        Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                        startActivity(intentRegister);
                        break;
                }
            }
        };

        loginBtn.setOnClickListener(onClickListener);
        registerBtn.setOnClickListener(onClickListener);
    }

    public void startWelcomeActivity() {
        Intent intentWelcome = new Intent(getApplicationContext(), WelcomeActivity.class);
        startActivity(intentWelcome);
    }
}
