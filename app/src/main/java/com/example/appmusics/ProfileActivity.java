package com.example.appmusics;

import android.accounts.Account;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private TextView txtUserName;
    SqliteHelp sqliteHelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button btn_onListView= (Button) findViewById(R.id.btn_ListView);
        btn_onListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent onListView=new Intent(ProfileActivity.this,ListViewActivity.class);
                startActivity(onListView);
            }
        });
    }
}