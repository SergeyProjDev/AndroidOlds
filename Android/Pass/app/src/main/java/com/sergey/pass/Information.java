package com.sergey.pass;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Information extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        showLogin = (TextView) findViewById(R.id.textView);
        showPassword = (TextView) findViewById(R.id.textView2);
        showInformation = (TextView) findViewById(R.id.textView3);

        if (Passwords.btnName.equals("hzzh@outlook.com")) showInfo("hzzh@outlook.com", "Serik2000", "Нет информации.");
        if (Passwords.btnName.equals("s1712@tmm-sapr.org")) showInfo("s1712@tmm-sapr.org", "Serik2000", "Нет информации.");
        if (Passwords.btnName.equals("myjukovka@gmail.com")) showInfo("myjukovka@gmail.com", "Serik1234", "Нет информации.");
        if (Passwords.btnName.equals("Autodesk")) showInfo("s1712@tmm-sapr.org", "ctht;f123","Нет информации.");
        if (Passwords.btnName.equals("ПриватБанк (студ)")) showInfo("5168757335252476", "Serik1234","9483 - код;    Срок действия: 07/21");
        if (Passwords.btnName.equals("Steam (основной)")) showInfo("kabachok231", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("Steam (второй)")) showInfo("baklazan231", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("Fusion360")) showInfo("s1712@tmm-sapr.org", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("ПриватБанк (Internet)")) showInfo("4731185614840970", "681","Срок действия: 07/26");
        if (Passwords.btnName.equals("BK")) showInfo("380994322254", "vk2000serik","Нет информации.");
        if (Passwords.btnName.equals("больница")) showInfo("hzzh@outlook.com", "Serik1234","Нет информации.");
        if (Passwords.btnName.equals("TeamViever")) showInfo("hzzh@outlook.com", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("Mi Band 2")) showInfo("380994322254", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("Клуб програмистов")) showInfo("hzzh@outlook.com", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("ВЦ")) showInfo("s1712", "ctht;f1234","Нет информации.");
        if (Passwords.btnName.equals("Facebook")) showInfo("380501783335", "яцык-лох","Нет информации.");

        if (Passwords.btnName.equals("")) showInfo("Your login", "Your password","YourInformation");
    }


    TextView showLogin;
    TextView showPassword;
    TextView showInformation;

    private void showInfo (String aLogin, String aPassword, String aInformation){
        showLogin.setText(aLogin);
        showPassword.setText(aPassword);
        showInformation.setText(aInformation);
    }

    public void copyLogin (View v){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", showLogin.getText().toString());
        clipboard.setPrimaryClip(clip);
        sayReady("Логин скопирован!");
    }
    public void copyPassword (View v){
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("label", showPassword.getText().toString());
        clipboard.setPrimaryClip(clip);
        sayReady("Пароль скопирован!");
    }
    private void sayReady(String aShow){
        Toast toast = Toast.makeText(getApplicationContext(), aShow, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 0);
        toast.show();
    }
}
