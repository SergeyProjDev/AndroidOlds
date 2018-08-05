package com.sergey.binarcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    TextView binary1;
    TextView rewerse1;
    TextView additional1;

    public void initialization(View v){
        binary1 = (TextView) findViewById(R.id.textView);
        rewerse1 = (TextView) findViewById(R.id.textView2);
        additional1 = (TextView) findViewById(R.id.textView3);
    }




    public void onButton1Click (View v){
        initialization(v);

        EditText enteredText = (EditText) findViewById(R.id.editText);
        try {
            int number = Integer.parseInt(enteredText.getText().toString());
            binary1.setText(toBinary(number));
            rewerse1.setText(toReverse(toBinary(number)));
            additional1.setText(toAdditional(toTen(toReverse(toBinary(number)))));
        }
        catch (Exception e){
            binary1.setText("Не пролучается");
            rewerse1.setText("Не пролучается");
            additional1.setText("Не пролучается");
        }
    }




    public static String leftPad(String originalString, int length, char padCharacter) {
        String paddedString = originalString;
        while (paddedString.length() < length)
        {
            paddedString = padCharacter + paddedString;
        }
        return paddedString;
    }



     public String toBinary(int aNumber){
         String reversedBinary = "";
         while (aNumber > 0)
         {
             if (aNumber % 2 == 0) reversedBinary += "0";
             else reversedBinary += "1";
             aNumber = aNumber / 2;
         }
         String binary = "";
         for(int i = reversedBinary.length() - 1; i >= 0; i--) {
             binary = binary + reversedBinary.charAt(i);
         }
         byte razryad1 = razryad(binary);
         if (razryad1 != 0) {
             binary = leftPad(binary, razryad1, '0');
         }
         //совершенный бинарный код (binary)
         return binary;
     }
     public String toReverse(String aBinary){
         char[] str = aBinary.toCharArray();
         String rewersed = "";
         for (int i = 0; i < aBinary.length(); i++)
         {
             if (str[i] == '1') rewersed += "0";
               else rewersed += "1";
         }
         return rewersed;
     }
     public String toTen (String bin) {
             int res = 0, a = 0, mult = 0;
             char[] symbols = bin.toCharArray();
             for(int len = symbols.length-1; len >= 0; len--)
             {
                 int temp = 0;
                 a = Character.getNumericValue(symbols[len]);
                 temp = a * pow(2, mult);
                 mult++;
                 res += temp;
             }
             return Integer.toString(res);
     }
     public int pow(int a, int b) {
        int result = 1;
        for (int i = 0; i < b; i++) {
            result *= a;
        }
        return result;
     }

     public String toAdditional(String aTen){
        int tened = Integer.parseInt(aTen);
        tened ++;
        String result = toBinary(tened);
        return result;
     }

     public byte razryad (String aBinary){
         byte razryad = 2;
         if (aBinary.length() <= 4) razryad = 4;
           else
              if (aBinary.length() <= 8) razryad = 8;
             else
                if (aBinary.length() <= 16) razryad = 16;
               else
                if (aBinary.length() <= 32) razryad = 32;
                 else
                  if (aBinary.length() <= 64) razryad = 64;
                   else {
                      return 0;
                   }
         return razryad;
     }

}
