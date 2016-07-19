package com.example.congtri.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button btnSignup;
    EditText edtUser, edtPass, edtRetypePass, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btnSignup = (Button) findViewById(R.id.buttonSignup);
        edtUser = (EditText) findViewById(R.id.editTextSignupName);
        edtPass = (EditText) findViewById(R.id.editTextPassword);
        edtRetypePass = (EditText) findViewById(R.id.editTextRetypePass);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeLoginInformation();
            }
        });

    }

    public void makeLoginInformation() {
        String name = edtUser.getText().toString();
        String pass = edtPass.getText().toString();
        String retypePass = edtRetypePass.getText().toString();
        String email = edtEmail.getText().toString();

        if (name.isEmpty() || pass.isEmpty() || retypePass.isEmpty() || email.isEmpty()) {
            if (name.isEmpty()) {
                edtUser.setError("Enter your new user name!");
            }

            if (pass.isEmpty()) {
                edtPass.setError("Enter your password !");
            }

            if (retypePass.isEmpty()) {
                edtRetypePass.setError("Enter your password !");
            }

            if (email.isEmpty()) {
                edtEmail.setError("Enter a valid email address");
            }
        } else if (pass.compareTo(retypePass) != 0) {
            edtPass.getText().clear();
            edtRetypePass.getText().clear();

            Toast.makeText(getApplicationContext(), "Passwords are not match !", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Create new username and password success !", Toast.LENGTH_LONG).show();
        }
    }
}
