package com.sergey.pass;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Passwords extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passwords);
    }

    public static String btnName;
    public void onLodinButtonClick (View v){
        int btnId = v.getId();
        Button btnLogin = (Button) findViewById(btnId);
        btnName = btnLogin.getText().toString();

        Intent intent = new Intent(getApplicationContext(), Information.class);
        startActivity(intent);
    }
}
