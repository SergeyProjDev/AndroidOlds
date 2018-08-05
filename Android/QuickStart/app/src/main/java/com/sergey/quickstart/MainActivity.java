package com.sergey.quickstart;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;


public abstract class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }




















    public void showPopur(View v) {   //меню
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu0:
                        //события для click1
                        Toast.makeText(getApplicationContext(), "Вы нажали 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu1:
                        //события для click2
                        Toast.makeText(getApplicationContext(), "Вы нажали 2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu2:
                        //события для click3
                        Toast.makeText(getApplicationContext(), "Вы нажали 3", Toast.LENGTH_SHORT).show();
                        break;
                }


                return true;
            }
        });
        popup.inflate(R.menu.actions);
        popup.show();
    }
    public void goHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
