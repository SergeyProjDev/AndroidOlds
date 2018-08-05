package com.sergey.database;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class StartActivity extends AppCompatActivity implements Serializable{

    String trueLogin;
    String truePassword;
    DataInfo dtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        dtInfo = deserializeObject();
        if (!(dtInfo.getAppLogin().equals("error")) && !(dtInfo.getAppPassword().equals("error"))){
            Encryptor encryptor = new Encryptor();
            trueLogin = encryptor.decryptWord(dtInfo.getAppLogin());
            truePassword = encryptor.decryptWord(dtInfo.getAppPassword());
                EditText login = findViewById(R.id.editText); login.setText(trueLogin);
                final EditText password = findViewById(R.id.editText2); password.requestFocus();

                password.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count){
                        if (password.getText().toString().equals(truePassword)) {
                            Button btn = findViewById(R.id.button5);
                            showDataBasePage(btn);
                        }
                    }
                    @Override
                    public void afterTextChanged(Editable s) { }
                });
        }
    }

    public void showDataBasePage(View v){
        try{
            EditText login = findViewById(R.id.editText);
            EditText password = findViewById(R.id.editText2);

            if ((dtInfo.getAppLogin().equals("error")) && (dtInfo.getAppPassword().equals("error"))){ //первый вход
                Encryptor encryptor = new Encryptor();
                dtInfo.setAppLogin(encryptor.encryptWord(login.getText().toString()));
                dtInfo.setAppPassword(encryptor.encryptWord(String.valueOf(password.getText())));
                serializeObject(dtInfo);
                Toast.makeText(getApplicationContext(), "Login: " + login.getText().toString() + "\nPassword: " + password.getText().toString(), Toast.LENGTH_LONG).show();
                Intent Intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(Intent);
            }
            else {
                if ((login.getText().toString().equals(trueLogin)) && (String.valueOf(password.getText()).equals(truePassword))) {
                    Intent Intent = new Intent(v.getContext(), MainActivity.class);
                    v.getContext().startActivity(Intent);
                }
                else {
                    Toast.makeText(this, "I don`t know you!", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(),Toast.LENGTH_LONG).show();
        }



    }

    int n = 0;
    public void hack (View v){
        n++;
        if (n == 25){
            Toast.makeText(this, "Showing...", Toast.LENGTH_SHORT).show();
            Intent Intent = new Intent(v.getContext(), MainActivity.class);
            v.getContext().startActivity(Intent);
        }
    }


    public final static String fileName = "data.ser";
    public void serializeObject (DataInfo dataInfo){
        try{
            FileOutputStream fos = getBaseContext().openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(dataInfo);
            os.close();
            fos.close();
        }
        catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    public DataInfo deserializeObject(){
        try{
            FileInputStream fis = getBaseContext().openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            DataInfo simpleClass = (DataInfo) is.readObject();
            is.close();
            fis.close();

            return simpleClass;
        }
        catch (Exception ex){
            Toast.makeText(this, "Create new user. Enter params and press Show Info!", Toast.LENGTH_LONG).show();
            DataInfo simpleClass = new DataInfo();
            simpleClass.setAppLogin("error");
            simpleClass.setAppPassword("error");
            return simpleClass;
        }

    }
}

class DataInfo implements Serializable {

    private String appLogin;
    private String appPassword;

    public String getAppPassword() {
        return appPassword;
    }

    public String getAppLogin() {
        return appLogin;
    }

    public void setAppLogin(String appLogin) {
        this.appLogin = appLogin;
    }

    public void setAppPassword(String appPassword) {
        this.appPassword = appPassword;
    }


}

class Encryptor
{
    private String alphabet = "abQBc8dek:lNMWE№RT2r0!stY(UIOo9pq4*PЙЦA+)SDйцЛ;укен@ДАгПшщОРЖзхВРъэ.УждлоФрпЫаЭЯв,ШыфКяЧчсмСМИТЬБЮиЩтЕ?ьбю-ЗFGНH=JХKfГ1ghi3jLZЪXCV7mnu6v wxy5z";
    private int key = 7;


    public String encryptWord(String word)
    {
        String result = "";
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++)
        {
            for (int j = 0; j < alphabet.length(); j++)
            {
                if (wordArr[i] == alphabet.charAt(j))
                {
                    int n;
                    if ((j + key) >= alphabet.length()) n = (j + key) - alphabet.length();
                    else n = j + key;
                    result += alphabet.charAt(n);
                }
            }
        }
        return result;
    }

    public String decryptWord(String word)
    {
        String result = "";
        char[] wordArr = word.toCharArray();
        for (int i = 0; i < wordArr.length; i++)
        {
            for (int j = 0; j < alphabet.length(); j++)
            {
                if (wordArr[i] == alphabet.charAt(j))
                {
                    int n;
                    if ((j - key) < 0) n = (j - key) + alphabet.length();
                    else n = j - key;
                    result += alphabet.charAt(n);
                }
            }
        }
        return result;
    }
}