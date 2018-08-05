package com.sergey.discretemath;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class Counting extends AppCompatActivity
{




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counting);
        showM0andM1();
    }






    String [] x1 = {"0","0","0","0","0","0","0","0","1","1","1","1","1","1","1","1"};
    String [] x2 = {"0","0","0","0","1","1","1","1","0","0","0","0","1","1","1","1"};
    String [] x3 = {"0","0","1","1","0","0","1","1","0","0","1","1","0","0","1","1"};
    String [] x4 = {"0","1","0","1","0","1","0","1","0","1","0","1","0","1","0","1"};

    List<String> arrKub1 = new ArrayList<String>();
    List<String> arrKub2 = new ArrayList<String>();
    List<String> arrKub3 = new ArrayList<String>();
    List<String> arrM0 = new ArrayList<String>();
    List<String> arrM1 = new ArrayList<String>();
    List<String> arrAnswers = new ArrayList<String>();








    @SuppressLint("SetTextI18n")
    public void showM0andM1()
    {
        EditText M0  = (EditText) findViewById(R.id.editText19);
        EditText M1  = (EditText) findViewById(R.id.editText18);
        EditText f1  = (EditText) findViewById(R.id.editText1);
        EditText f2  = (EditText) findViewById(R.id.editText2);
        EditText f3  = (EditText) findViewById(R.id.editText3);
        EditText DNF = (EditText) findViewById(R.id.editText28);

        for (int i = 0; i < 16; i++) {
            if (MainActivity.n[i].equals("0")) { //М0
                M0.setText(M0.getText() + x1[i] + x2[i] + x3[i] + x4[i] + "\n");
                arrM0.add(x1[i] + x2[i] + x3[i] + x4[i]);
            }
            if (MainActivity.n[i].equals("1")) { //М1
                arrM1.add(x1[i] + x2[i] + x3[i] + x4[i]);
            }
        }
        for (int i = 0; i < arrM1.size(); i++) {
            arrKub1.add("-" +  String.valueOf(arrM1.get(i).charAt(1)) +  String.valueOf(arrM1.get(i).charAt(2)) +  String.valueOf(arrM1.get(i).charAt(3)));
            arrKub1.add( String.valueOf(arrM1.get(i).charAt(0)) + "-" +  String.valueOf(arrM1.get(i).charAt(2)) +  String.valueOf(arrM1.get(i).charAt(3)));
            arrKub1.add( String.valueOf(arrM1.get(i).charAt(0)) +  String.valueOf(arrM1.get(i).charAt(1)) + "-" +  String.valueOf(arrM1.get(i).charAt(3)));
            arrKub1.add( String.valueOf(arrM1.get(i).charAt(0)) +  String.valueOf(arrM1.get(i).charAt(1)) +  String.valueOf(arrM1.get(i).charAt(2)) + "-");
        }






        for (int i = 0; i < arrM1.size(); i++){  //элементы М1, перешедшие в Куб1 (по 4) (плюсики)
            boolean flag = false;
            for (int j = 0; j < 4; j++) {
                int c = j + i*4;
                if (theSameToM0Kub1(arrKub1.get(c)))
                    flag = true;
            }
            if (flag) arrM1.set(i, arrM1.get(i)+ " +");
        }

        arrKub1 = new ArrayList<String>(new LinkedHashSet<String>(arrKub1)); //удалить одинаковые


        for (int i = 0; i < arrM1.size(); i++){ //вывод М1
            M1.setText(M1.getText() + arrM1.get(i) + "\n");
        }


        for (int i = 0; i < arrKub1.size(); i++){ //arrKub1 готов
            if (!theSameToM0Kub1(arrKub1.get(i))) {
                arrKub1.remove(i);
                i--;
            }
        }







        for (int i = 0; i < arrKub1.size(); i++) {
            arrKub2.add("-" +  String.valueOf(arrKub1.get(i).charAt(1)) +  String.valueOf(arrKub1.get(i).charAt(2)) +  String.valueOf(arrKub1.get(i).charAt(3)));
            arrKub2.add( String.valueOf(arrKub1.get(i).charAt(0)) + "-" +  String.valueOf(arrKub1.get(i).charAt(2)) +  String.valueOf(arrKub1.get(i).charAt(3)));
            arrKub2.add( String.valueOf(arrKub1.get(i).charAt(0)) +  String.valueOf(arrKub1.get(i).charAt(1)) + "-" +  String.valueOf(arrKub1.get(i).charAt(3)));
            arrKub2.add( String.valueOf(arrKub1.get(i).charAt(0)) +  String.valueOf(arrKub1.get(i).charAt(1)) +  String.valueOf(arrKub1.get(i).charAt(2)) + "-");
        }

        for (int i = 0; i < arrKub2.size(); i++) { //удалить с одним тире
            if (countOfTire(arrKub2.get(i))) {
                arrKub2.remove(i);
                i = 0;
            }
        }

        for (int i = 0; i < (arrKub2.size()/3); i ++){  //элементы arrKub1, перешедшие в Куб2 (по 3) (плюсики)
            boolean flag = false;
            for (int j = 0; j < 3; j++) {
                int c = j + (i*3);
                if (theSameToM0Kub2(arrKub2.get(c)))
                    flag = true;
            }
            if (flag) arrKub1.set(i, arrKub1.get(i)+ " +");
        }

        for (int i = 0; i < arrKub1.size(); i++){  //вывод arrKub1
            f1.setText(f1.getText() + arrKub1.get(i) + "\n");
        }


        arrKub2 = new ArrayList<String>(new LinkedHashSet<String>(arrKub2)); //удалить одинаковые

        for (int i = 0; i < arrKub2.size(); i++) {
            if (theSameToM0Kub2(arrKub2.get(i)))
                f2.setText(f2.getText() + arrKub2.get(i) + "\n");
            else {
                arrKub2.remove(i);
                i--;
            }
        }








        for (int i = 0; i < arrKub2.size(); i++) {
            arrKub3.add("-" +  String.valueOf(arrKub2.get(i).charAt(1)) +  String.valueOf(arrKub2.get(i).charAt(2)) +  String.valueOf(arrKub2.get(i).charAt(3)));
            arrKub3.add( String.valueOf(arrKub2.get(i).charAt(0)) + "-" +  String.valueOf(arrKub2.get(i).charAt(2)) +  String.valueOf(arrKub2.get(i).charAt(3)));
            arrKub3.add( String.valueOf(arrKub2.get(i).charAt(0)) +  String.valueOf(arrKub2.get(i).charAt(1)) + "-" +  String.valueOf(arrKub2.get(i).charAt(3)));
            arrKub3.add( String.valueOf(arrKub2.get(i).charAt(0)) +  String.valueOf(arrKub2.get(i).charAt(1)) +  String.valueOf(arrKub2.get(i).charAt(2)) + "-");
        }
        for (int i = 0; i < arrKub3.size(); i++) { //удалить с двумя тире
            if (countOfTire1(arrKub3.get(i))) {
                arrKub3.remove(i);
                i = 0;
            }
        }


        for (int i = 0; i < arrKub2.size(); i++){
            boolean flag = false;
            for (int j = 0; j < 2; j++) {
                int c = j + i*2;
                if (theSameToM0Kub3(arrKub3.get(c)))
                    flag = true;
            }
            if (flag) arrKub2.set(i, arrKub2.get(i)+ " +");
        }



        f2.setText("");
        for (int i = 0; i < arrKub2.size(); i++){  //вывод arrKub2
            f2.setText(f2.getText() + arrKub2.get(i) + "\n");
        }

        for (int i = 0; i < arrKub3.size(); i++) {
            if (!theSameToM0Kub3(arrKub3.get(i)))
            {
                arrKub3.remove(i);
                i--;
            }
        }

        arrKub3 = new ArrayList<String>(new LinkedHashSet<String>(arrKub3)); //удалить одинаковые

        for (int i = 0; i < arrKub3.size(); i++){
            f3.setText(f3.getText() + arrKub3.get(i) + "\n");
        }











        for (int i=0; i<arrM1.size(); i++){
            if(arrM1.get(i).indexOf('+') < 0)
                arrAnswers.add(arrM1.get(i));
        }
        for (int i=0; i<arrKub1.size(); i++){
            if(arrKub1.get(i).indexOf('+') < 0)
                arrAnswers.add(arrKub1.get(i));
        }
        for (int i=0; i<arrKub2.size(); i++){
            if(arrKub2.get(i).indexOf('+') < 0)
                arrAnswers.add(arrKub2.get(i));
        }
        for (int i=0; i<arrKub3.size(); i++){
            if(arrKub3.get(i).indexOf('+') < 0)
                arrAnswers.add(arrKub3.get(i));
        }
        String fullAns = "";
        for (int i=0; i<arrAnswers.size(); i++){
            String ans = " V ";
            for (int j=0; j<4; j++) {
                if (String.valueOf(arrAnswers.get(i).charAt(j)).equals("-")) continue;
                if (String.valueOf(arrAnswers.get(i).charAt(j)).equals("1")) {
                    int a = j+1;
                    ans += "x" + symbal(a);
                }
                if (String.valueOf(arrAnswers.get(i).charAt(j)).equals("0")) {
                    int a = j+1;
                    ans += "x̅" + symbal(a);
                }
            }
            fullAns += ans;
        }
        fullAns = getString(R.string.DNF_sokr) + fullAns.substring(2, fullAns.length());
        DNF.setText(fullAns);
    }











    private String symbal (int aa){
        String index = "";
        if (String.valueOf(aa).equals("1")) index = "₁";
        if (String.valueOf(aa).equals("2")) index = "₂";
        if (String.valueOf(aa).equals("3")) index = "₃";
        if (String.valueOf(aa).equals("4")) index = "₄";
        return index;
    }


    private boolean theSameToM0Kub1 (String str) {
        char[] arr = str.toCharArray();
        for (int i = 0; i < arrM0.size(); i++) {
            byte a = 0;
            for (int j = 0; j < 4; j++) {
                if (!String.valueOf(arr[j]).equals("-")) {
                    if (String.valueOf(arr[j]).equals(String.valueOf(arrM0.get(i).charAt(j)))) {
                        a++;
                        if (a == 3) return false; //одинаковые
                    }
                }
            }
        }
        return true;
    }
    private boolean theSameToM0Kub2(String str1) {
        char[] arr = str1.toCharArray();
        int count;
        for (int i = 0; i < arrM0.size(); i++)
        {
            count = 0;
            for (int j = 0; j < 4; j++)
            {
                if ((arrM0.get(i).charAt(j) != '-')  && (arrM0.get(i).charAt(j) == arr[j])) count++;
            }
            if (count == 2) return false;
        }
        return true;
    }
    private boolean theSameToM0Kub3(String str1) {
        char[] arr = str1.toCharArray();
        int count;
        for (int i = 0; i < arrM0.size(); i++)
        {
            count = 0;
            for (int j = 0; j < 4; j++)
            {
                if ((arrM0.get(i).charAt(j) != '-')  && (arrM0.get(i).charAt(j) == arr[j])) count++;
            }
            if (count == 1) return false;
        }
        return true;
    }

    private boolean countOfTire (String str){
        if (arrKub1.contains(str)) return true;
        else return false;
    }
    private boolean countOfTire1 (String str){
        if (arrKub2.contains(str)) return true;
        else return false;
    }



}
