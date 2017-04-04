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
import ru.coyul.loginhelloapp.data.User;


public class RegisterActivity extends Activity {
    Button signUpBtn;
    EditText txtUserName;
    EditText txtPassword;
    DataBaseHandler dataBaseHandler;
    User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpBtn = (Button) findViewById(R.id.btnSignUp);
        txtUserName = (EditText) findViewById(R.id.txtNewName);
        txtPassword = (EditText) findViewById(R.id.txtNewPassword);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setName(txtUserName.getText().toString());
                user.setPassword(txtPassword.getText().toString());

                if (user.getPassword().length() > 4) {
                    dataBaseHandler = new DataBaseHandler(RegisterActivity.this);
                    dataBaseHandler.addUser(user);

                    savePreferences(user.getName());
                    Intent intentWelcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                    startActivity(intentWelcome);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    builder.setMessage("Длина пароля должна быть больше 4 символов!")
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

        signUpBtn.setOnClickListener(onClickListener);
    }

    private void savePreferences(String userName) {
        SharedPreferences settings = getSharedPreferences(StartActivity.PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("HasLoggedIn", true);
        editor.putString("userName", userName);
        editor.commit();
    }
}
