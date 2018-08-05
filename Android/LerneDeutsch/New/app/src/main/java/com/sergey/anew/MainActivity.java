package com.sergey.anew;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    Button word, next;
    ImageView flag1, flag2, city1, city2;

    List<String> words = new ArrayList<>();
    List<Integer> were = new ArrayList<>();
    boolean firstNative = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flag1 = findViewById(R.id.flag);
        flag2 = findViewById(R.id.flag2);
        city1 = findViewById(R.id.city);
        city2 = findViewById(R.id.city2);
        word = findViewById(R.id.button);
        next = findViewById(R.id.button2);

        loadStarter();
    }

    public void showSettingsActivity(View v){
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    private void loadStarter(){
        String settings = getSettingsFromTxt();
        loadSettingsToProgram(settings);
        getNextWord(null);
    }

    private String getSettingsFromTxt(){
        FileInputStream fin;
        String setting;
        try {
            getAvilivableFileOrCreate();
            fin = openFileInput("info.txt");
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            setting = new String(bytes);
            return setting;
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_LONG).show();
            return "error";
        }
    }
    private void getAvilivableFileOrCreate(){
        File root = new File(Environment.getExternalStorageDirectory(), "info.txt");
        if (!root.exists()) {
           root.mkdirs();
           setStartSettingsInTxt();
        }
    }
    private void setStartSettingsInTxt(){
        FileOutputStream fos;
        try {
            String text = "/storage/9016-4EF8/de.txt firstNative >deutsch";
            fos = openFileOutput("info.txt", MODE_PRIVATE);
            fos.write(text.getBytes());
            fos.close();
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    private void loadSettingsToProgram(String settings){
        String address = settings.substring(0, settings.indexOf(".txt")+4);
        getInternalTextFileToArray(address);

        if (settings.contains("firstNative")) setFirstNative(true);
        if (settings.contains("firstTranslate")) setFirstNative(false);

        if (settings.contains(">deutsch")) setGrahpicInterface("deutsct");
        if (settings.contains(">english")) setGrahpicInterface("english");
    }
    private void getInternalTextFileToArray(String address){
        try {
            File myFile = new File(address);
            FileInputStream fIn = new FileInputStream(myFile);
            BufferedReader myReader = new BufferedReader(new InputStreamReader(fIn));
            String fileString;
            while ((fileString = myReader.readLine()) != null) {
                words.add(fileString);
            }
            myReader.close();
        } catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
    }
    private void setFirstNative(boolean status){
        firstNative = status;
    }
    private void setGrahpicInterface(String name){
        Button btn1 = findViewById(R.id.button);
        Button btn2 = findViewById(R.id.button2);
        if (name.equals("english")) {
            flag1.setImageResource(R.drawable.england);
            flag2.setImageResource(R.drawable.england);
            btn1.setBackgroundResource(R.drawable.eng_background);
            city2.setVisibility(View.GONE);
            city1.setVisibility(View.VISIBLE);
            btn2.setBackgroundResource(R.drawable.en_next);
        }
        else { // if (name.equals("deutsct"))
            flag1.setImageResource(R.drawable.germany);
            flag2.setImageResource(R.drawable.germany);
            btn1.setBackgroundResource(R.drawable.de_background);
            city1.setVisibility(View.GONE); city2.setVisibility(View.VISIBLE);
            btn2.setBackgroundResource(R.drawable.de_next);
        }
    }
    public void getNextWord(View v){
        try{
            Random rand = new Random();
            int was = anyRandom(rand, 0, words.size());
            if (!were.contains(was)){
                were.add(was);
                String line = words.get(was);
                foreign = line.substring(0, line.indexOf('-'));
                translate = line.substring(line.indexOf('-') + 1, line.length());
                if (firstNative) word.setText(translate);
                else word.setText(foreign);
            }
            else {
                getNextWord(null);
            }
        }
        catch (Exception e){
            were.clear();
            getNextWord(null);
        }
    }
    public void translate(View v){
        if (firstNative) word.setText(foreign);
          else word.setText(translate);
    }
    private int anyRandom(Random random, int low, int high) {
        return random.nextInt(high) + low;
    }

    String foreign, translate;
}
