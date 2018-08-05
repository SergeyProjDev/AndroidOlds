package com.sergey.pass;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt = (EditText) findViewById(R.id.editText); //password
        edt1 = (EditText) findViewById(R.id.editText2); //login
        edt.requestFocus(); //фокус
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE); //клавиатура +

    }


    EditText edt;
    EditText edt1;
    public void onButtonClick(View v){
        String login = edt1.getText().toString();
        String password = edt.getText().toString();


        if ((password.equals(getString(R.string.s1))) && (login.equals(getString(R.string.s2))))
        {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS); //клавиатура -
            Button btn = (Button) findViewById(R.id.button);


            Intent intent = new Intent(getApplicationContext(), Passwords.class);
            startActivity(intent);

        }
        else
        {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Ошибка в логине или пароле");
            builder1.setCancelable(true);
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }
}
