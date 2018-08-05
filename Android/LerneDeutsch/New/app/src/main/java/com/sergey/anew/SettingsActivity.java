package com.sergey.anew;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SettingsActivity extends AppCompatActivity {

    RadioButton rbForeign, rbNative, rbEnglish, rbDeutsch;
    EditText address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        address = findViewById(R.id.adress);
        rbForeign = findViewById(R.id.radioButton1);
        rbNative = findViewById(R.id.radioButton2);
        rbEnglish = findViewById(R.id.radioButton3);
        rbDeutsch = findViewById(R.id.radioButton4);

        loadStarter();
    }

    private void loadStarter() {
        String settings = getOldSettings();
        loadSettingsToProgram(settings);
    }

    public void saveSettings (View v){
        String settings = getNewSettingsString();
        setNewSettings(settings);
        Toast.makeText(this, "Settings were updated", Toast.LENGTH_SHORT).show();
        startMainActivity();
    }

    private String getOldSettings() {
        FileInputStream fin;
        String data;
        try {
            fin = openFileInput("info.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            data = new String(bytes);
            return data;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            return "";
        }
    }
    private void loadSettingsToProgram(String setting){
        String myFile = setting.substring(0, setting.indexOf(".txt")+4);
        address.setText(myFile);

        if (setting.contains("firstNative")) rbNative.setChecked(true);
        if (setting.contains("firstTranslate")) rbForeign.setChecked(true);

        if (setting.contains(">deutsch")) rbDeutsch.setChecked(true);
        if (setting.contains(">english")) rbEnglish.setChecked(true);

    }
    private String getNewSettingsString(){
        String string;

        string = address.getText().toString();
        if (rbNative.isChecked()) string += " firstNative";
           else string += " firstTranslate";
        if (rbDeutsch.isChecked()) string += " >deutsch";
           else string += " >english";

           return string;
    }
    private void setNewSettings(String setting){
        FileOutputStream fos;
        try {
            fos = openFileOutput("info.txt", MODE_PRIVATE);
            fos.write(setting.getBytes());
            fos.close();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void startMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}