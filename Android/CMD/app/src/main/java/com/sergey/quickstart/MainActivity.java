package com.sergey.quickstart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.io.File;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmd =  findViewById(R.id.editText);
        help =  findViewById(R.id.editText2);
        cmd.setSelection(1);
    }
    public EditText cmd, help;
    String command;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP)||(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) || (keyCode == KeyEvent.KEYCODE_BACK)){
                command = cmd.getText().toString();
                doIt(command);
                return true;
        }
        return false;
    }



    @SuppressLint({"MissingPermission", "SetTextI18n", "HardwareIds"})
    private void doIt(String aCommand){
        boolean rot = false;
        if (aCommand.contains("cls")){
            cmd.setText(">");
            cmd.setSelection(1);
            help.setText("");
            return;
        }
        if (aCommand.contains(">start")) {
          String comm = aCommand.substring(7, aCommand.length());
          File directory = new File("/storage/emulated/0/Android/data");
          File[] files = directory.listFiles();
            for (File file : files) {
                if (file.getName().contains(comm)) {
                    comm = file.getName();
                }
                if (aCommand.contains("?")) {
                    help.setText(help.getText() + file.getName() + "\n");
                }
            }
            try{
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(comm);
                startActivity(launchIntent);
                help.setText(help.getText()+"started program: "+comm + "\n");
            }
            catch (Exception e){
                help.setText(help.getText() + e.toString() +"\n");
            }

        }
        if (aCommand.contains(">battery")){
            String comm = aCommand.substring(8, aCommand.length());
            Intent batteryIntent = registerReceiver(null, new     IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = 0;
            if (batteryIntent != null) {
                level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            }
            int scale = 0;
            if (batteryIntent != null) {
                scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            }
            if (comm.contains("lvl"))help.setText(help.getText()+ "battery is: "+String.valueOf(((float)level / (float)scale) * 100.0f)+ "%\n");
            if (aCommand.contains("?")) {
                help.setText(help.getText() + ">battery lvl - percent level \n");
            }
        }
        if (aCommand.contains(">phone")){
            String comm = aCommand.substring(6, aCommand.length());
            TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
            if (comm.contains("country")) if (tm != null) {
                help.setText(help.getText() +"operators country: "+ tm.getNetworkCountryIso() + "\n");
            }
            if (comm.contains("operator")) if (tm != null) {
                help.setText(help.getText() +"operator name: "+ tm.getNetworkOperatorName () + "\n");
            }
            if (comm.contains("id")) if (tm != null) {
                help.setText(help.getText() +"phone id: "+ tm.getDeviceId() + "\n");
            }
            if (comm.contains("roaming")) if (tm != null) {
                help.setText(help.getText() +"in roaming: "+ tm.isNetworkRoaming () + "\n");
            }
            if (comm.contains("number")) if (tm != null) {
                help.setText(help.getText() +"phone number: "+ tm.getLine1Number() + "\n");
            }
            if (aCommand.contains("?")) {
                help.setText(help.getText() + ">phone country - country code in which the operator is registered \n>phone id - device id \n>phone operator - operator name \n>phone roaming - true if in roaming \n");
            }
        }
        if (aCommand.contains(">android")){
            String comm = aCommand.substring(8, aCommand.length());
            if (comm.contains("version")){
                String release = Build.VERSION.RELEASE;
                int sdkVersion = Build.VERSION.SDK_INT;
                help.setText(help.getText() + "Android SDK version: " + sdkVersion + ";\nAndroid version: " + release +"\n");
            }
            if (aCommand.contains("rotation")){
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
            }
            if (aCommand.contains("?")) {
                help.setText(help.getText() + ">android version - sdkVersion \n>android rotation - accelerometer");
            }

        }

    }

























    public void showPopur(final View v) {   //меню
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu0:
                        Intent Intent = new Intent(v.getContext(), CommandsHelp.class);
                        v.getContext().startActivity(Intent);
                        Toast.makeText(getApplicationContext(), "Starting commands...", LENGTH_SHORT).show();
                        break;
                    case R.id.menu1:
                        //события для click2
                        Toast.makeText(getApplicationContext(), "Вы нажали 2", LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        //события для click3
                        Toast.makeText(getApplicationContext(), "Вы нажали 3", LENGTH_SHORT).show();
                        break;
                }


                return true;
            }
        });
        popup.inflate(R.menu.actions);
        popup.show();
    }
    public void goHome(View view) {
        cmd.setText(">");
        cmd.setSelection(1);
        help.setText("");
    }
}




