package ru.coyul.loginhelloapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ru.coyul.loginhelloapp.data.DataBaseHandler;


public class LoginActivity extends Activity {

    Button signInBtn;
    EditText txtUserName;
    EditText txtPassword;
    DataBaseHandler dataBaseHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInBtn = (Button) findViewById(R.id.btnSignIn);
        txtUserName = (EditText) findViewById(R.id.txtName);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = txtUserName.getText().toString();
                String password = txtPassword.getText().toString();
                String message = "Неверный пароль!";

                dataBaseHandler = new DataBaseHandler(LoginActivity.this);
                String dataBasePassword = dataBaseHandler.getUser(userName);
                if (password.equals(dataBasePassword)) {
                    savePreferences(userName);
                    Intent intentWelcome = new Intent(getApplicationContext(),WelcomeActivity.class);
                    startActivity(intentWelcome);
                }

                else {
                    if (password.equals("NoSuchUser")) message = "Такого пользователя не существует!";
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setMessage(message)
                            .setCancelable(false)
                            .setNegativeButton("ОК", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                    txtPassword.setText("");
                                }
                            });
                    builder.create().show();
                }
            }
        };

        signInBtn.setOnClickListener(onClickListener);
    }

    private void savePreferences(String userName) {
        SharedPreferences settings = getSharedPreferences(StartActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("HasLoggedIn", true);
        editor.putString("userName", userName);
        editor.commit();
    }



}
