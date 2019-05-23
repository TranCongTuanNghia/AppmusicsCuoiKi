package com.example.appmusics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {
    EditText txtUserName,txtFullName,txtEmail,txtPassword;
    Button btn_SignUp;
    SqliteHelp sqliteHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sqliteHelp = new SqliteHelp(this);
        txtUserName=(EditText) findViewById(R.id.txtUserName);
        txtFullName=(EditText) findViewById(R.id.txtFullName);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        Button btn_sign_up=(Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    String UserName = txtUserName.getText().toString();
                    String FullName = txtFullName.getText().toString();
                    String Email = txtEmail.getText().toString();
                    String Password = txtPassword.getText().toString();

                    if (!sqliteHelp.isUserNameExists(UserName)) {
                        sqliteHelp.addUser(new User(null, UserName,FullName, Email, Password));
                        Toast.makeText(SignUpActivity.this, "User created successfully! Please Login ", Toast.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Toast.LENGTH_LONG);
                    }else {
                        Toast.makeText(SignUpActivity.this, "User already exists with same Username ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
    public boolean validate() {
        boolean valid = false;

        //Get values from EditText fields
        String UserName = txtUserName.getText().toString();
        String FullName = txtFullName.getText().toString();
        String Email = txtEmail.getText().toString();
        String Password = txtPassword.getText().toString();

        //Handling validation for UserName field
        if (UserName.isEmpty()) {
            valid = false;
            txtUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                txtUserName.setError(null);
            } else {
                valid = false;
                txtUserName.setError("Username is to short!");
            }
        }

        if (FullName.isEmpty()) {
            valid = false;
            txtFullName.setError("Please enter valid FullName!");
        } else {
            if (FullName.length() > 5) {
                valid = true;
                txtFullName.setError(null);
            } else {
                valid = false;
                txtFullName.setError("FullName is to short!");
            }
        }

        //Handling validation for Email field
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            txtEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            txtEmail.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valid = false;
            txtPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
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
