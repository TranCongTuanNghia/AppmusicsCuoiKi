package com.example.appmusics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {
    private EditText txtUserName,txtPassword;
    Button btn_sign_in,btn_register;
    SqliteHelp sqliteHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        sqliteHelp = new SqliteHelp(this);
        txtUserName=(EditText) findViewById(R.id.txtUserName);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        Button btn_sign_in=(Button) findViewById(R.id.btn_sign_in);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                String UserName=txtUserName.getText().toString();
                String Pass=txtPassword.getText().toString();
                User currentUser = sqliteHelp.Authenticate(new User(null, UserName,null,null, Pass));
                    if (currentUser != null) {
                        Intent Profileintent=new Intent(SignInActivity.this,ProfileActivity.class);
                        startActivity(Profileintent);
                        Toast.makeText(SignInActivity.this, "Successfully Login!", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(SignInActivity.this, "Failed to login , please try again", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        Button btn_register=(Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registerintent=new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(Registerintent);
            }
        });
    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = txtUserName.getText().toString();
        String Pass = txtPassword.getText().toString();

        //Handling validation for Email field
        if (UserName.isEmpty()) {
            valid = false;
            txtUserName.setError("Please enter valid UserName!");
        } else {
            valid = true;
            txtPassword.setError(null);
        }

        //Handling validation for Password field
        if (Pass.isEmpty()) {
            valid = false;
            txtPassword.setError("Please enter valid password!");
        } else {
            if (Pass.length() > 5) {
                valid = true;
                txtPassword.setError(null);
            } else {
                valid = false;
                txtPassword.setError("Password is to short!");
            }
        }

        return valid;
    }
}
