package com.example.congtri.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    Button btnLogin;
    EditText edtUserName;
    EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.buttonLogin);
        edtUserName = (EditText) findViewById(R.id.editTextUserName);
        edtPassword = (EditText) findViewById(R.id.editTextPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setMessage("Authenticating...");
//        progressDialog.show();

        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();
        SharedPreferences prePass = getSharedPreferences("usernameAndPassword", MODE_PRIVATE);


        /* Get user and pass */
        String user = prePass.getString("username", null);
        String pass = prePass.getString("password", null);
        boolean firstLogin = prePass.getBoolean("firstLogin", true);

        if(firstLogin == true){
            Toast.makeText(getApplicationContext(), "This is the first time login", Toast.LENGTH_LONG).show();
            /* Create new password */
            switchSigupScreen();
        }
        else {
            /* Login check */
            if(username == user && password == pass){
                Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_LONG).show();
                switchScreenApp();
            }
            else {
                Toast.makeText(getApplicationContext(), "Login fail", Toast.LENGTH_LONG).show();
                return;
            }
        }

        // TODO: Implement your own authentication logic here.
//        new android.os.Handler().postDelayed(
//                new Runnable() {
//                    public void run() {
//                        // On complete call either onLoginSuccess or onLoginFailed
//                        // onLoginSuccess();
//                        // onLoginFailed();
//                        progressDialog.dismiss();
//                    }
//                }, 3000);

    }

    public boolean vaildateLoginInfomations() {

        String username = edtUserName.getText().toString();
        String password = edtPassword.getText().toString();

        boolean vaild = true;

        if (username.isEmpty()) {
            edtUserName.setError("Enter username");
            vaild = false;
        } else {
            edtUserName.setError(null);
        }

        if (password.isEmpty()) {
            edtPassword.setError("Enter password");
            vaild = false;
        } else {
            edtPassword.setError(null);
        }

        return vaild;
    }

    public void makeNewUsernameAndPassword(String user, String pass)
    {
        if(user.isEmpty() || pass.isEmpty()){
            edtPassword.setError("Please type new password");
            edtPassword.getText().clear();
            edtUserName.setError("Please type new user name");
            edtUserName.getText().clear();
        }
        else
        {
            String username = edtUserName.getText().toString();
            String password = edtPassword.getText().toString();

            SharedPreferences prePass = getSharedPreferences("usernameAndPassword", MODE_PRIVATE);
            SharedPreferences.Editor edit = prePass.edit();

            edit.putString("username", username);
            edit.putString("password", password);
            edit.putBoolean("firstLogin", true);
            edit.commit();

            switchScreenApp();
        }
    }

    public void switchScreenApp() {
        Intent screenMain = new Intent(LoginActivity.this, ControlActivity.class);
        startActivity(screenMain);
    }

    public void switchSigupScreen(){
        Intent screenSignUp = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(screenSignUp);
    }
}
