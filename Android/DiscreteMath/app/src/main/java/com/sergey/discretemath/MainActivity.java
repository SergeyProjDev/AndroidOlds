package com.sergey.discretemath;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public static String[] n = new String[16];

    public void showAboutPage(View v){
        Intent intent = new Intent(getApplicationContext(), Information.class);
        startActivity(intent);
    }


    public void readData (View v){
            EditText n1 = (EditText) findViewById(R.id.editText1);
            EditText n2 = (EditText) findViewById(R.id.editText2);
            EditText n3 = (EditText) findViewById(R.id.editText3);
            EditText n4 = (EditText) findViewById(R.id.editText4);
            EditText n5 = (EditText) findViewById(R.id.editText5);
            EditText n6 = (EditText) findViewById(R.id.editText6);
            EditText n7 = (EditText) findViewById(R.id.editText7);
            EditText n8 = (EditText) findViewById(R.id.editText8);
            EditText n9 = (EditText) findViewById(R.id.editText9);
            EditText n10 = (EditText) findViewById(R.id.editText10);
            EditText n11 = (EditText) findViewById(R.id.editText11);
            EditText n12 = (EditText) findViewById(R.id.editText12);
            EditText n13 = (EditText) findViewById(R.id.editText13);
            EditText n14 = (EditText) findViewById(R.id.editText14);
            EditText n15 = (EditText) findViewById(R.id.editText15);
            EditText n16 = (EditText) findViewById(R.id.editText16);

            n[0]  = n1.getText().toString();
            n[1]  = n2.getText().toString();
            n[2]  = n3.getText().toString();
            n[3]  = n4.getText().toString();
            n[4]  = n5.getText().toString();
            n[5]  = n6.getText().toString();
            n[6]  = n7.getText().toString();
            n[7]  = n8.getText().toString();
            n[8]  = n9.getText().toString();
            n[9] = n10.getText().toString();
            n[10] = n11.getText().toString();
            n[11] = n12.getText().toString();
            n[12] = n13.getText().toString();
            n[13] = n14.getText().toString();
            n[14] = n15.getText().toString();
            n[15] = n16.getText().toString();

            boolean readyToGo = true;
            for (int i=0; i<16; i++){
                if (n[i].equals("1") || (n[i].equals("0") || (n[i].equals("-")))) {}
                    else  {Toast.makeText(this, "Были введены недопустимые символы", Toast.LENGTH_SHORT).show(); readyToGo = false;}
            }
            if (readyToGo) {
                Intent intent = new Intent(getApplicationContext(), Counting.class);
                startActivity(intent);
            }
    }

    public void cleanInp (View v){
        EditText n1 = (EditText) findViewById(R.id.editText1);
        EditText n2 = (EditText) findViewById(R.id.editText2);
        EditText n3 = (EditText) findViewById(R.id.editText3);
        EditText n4 = (EditText) findViewById(R.id.editText4);
        EditText n5 = (EditText) findViewById(R.id.editText5);
        EditText n6 = (EditText) findViewById(R.id.editText6);
        EditText n7 = (EditText) findViewById(R.id.editText7);
        EditText n8 = (EditText) findViewById(R.id.editText8);
        EditText n9 = (EditText) findViewById(R.id.editText9);
        EditText n10 = (EditText) findViewById(R.id.editText10);
        EditText n11 = (EditText) findViewById(R.id.editText11);
        EditText n12 = (EditText) findViewById(R.id.editText12);
        EditText n13 = (EditText) findViewById(R.id.editText13);
        EditText n14 = (EditText) findViewById(R.id.editText14);
        EditText n15 = (EditText) findViewById(R.id.editText15);
        EditText n16 = (EditText) findViewById(R.id.editText16);
        n1 .setText("");
        n2 .setText("");
        n3 .setText("");
        n4 .setText("");
        n5 .setText("");
        n6 .setText("");
        n7 .setText("");
        n8 .setText("");
        n9 .setText("");
        n10.setText("");
        n11.setText("");
        n12.setText("");
        n13.setText("");
        n14.setText("");
        n15.setText("");
        n16.setText("");
    }

}
