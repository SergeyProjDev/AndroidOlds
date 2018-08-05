package com.sergey.myapplication;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.main1screen);
    }

    public int shag = 0;
    boolean ans1;
    boolean ans2, ans3, ans4, ans5;


    public void onButtonClick (View v){
        shag += 1;
        TextView showTxt = (TextView) findViewById(R.id.textView);
        Button variant1 = (Button) findViewById(R.id.ans1);
        Button variant2 = (Button) findViewById(R.id.ans2);
        Button next = (Button) findViewById(R.id.button2);
        ImageView sprite = (ImageView) findViewById(R.id.sprite);






        if (shag == 0) {
            getWindow().setBackgroundDrawableResource(R.drawable.main1screen);
            showTxt.setText("   Нажмите \"Вперед\", чтоб начать игру.");
        }
        if (shag == 1) {
            getWindow().setBackgroundDrawableResource(R.drawable.u1);
            showTxt.setText("   Обычное теплое утро, люди торопятся куда-то, проходя мимо.");
        }
        if (shag == 2)
            showTxt.setText("   А я не тороплюсь, все равно опоздаю, как всегда.");
        if (shag == 3)
            showTxt.setText("   Сегодня намечается сложный день, надо сдать лабораторную работу по ООП.");
        if (shag == 4)
            showTxt.setText("   …");
        if (shag == 5)
            showTxt.setText("   Вроде успеваю к началу пары.");
        if (shag == 6)
            showTxt.setText("   Пожалуй, лучше ускорю шаг...");
        if (shag == 7) {
            showTxt.setText("   Даже не знаю, сесть на лифт или пойти пешком.");
            variant1.setText("Пойти пешком.");
            variant2.setText("Поехать на лифте");
            variant1.setEnabled(true); variant1.setVisibility(View.VISIBLE);
            variant2.setEnabled(true); variant2.setVisibility(View.VISIBLE);
            next.setEnabled(false);
        }
        if (!ans1) {
            if (shag == 8) {
                showTxt.setText("   Так и сделаю.");
                variant1.setEnabled(false);
                variant1.setVisibility(View.INVISIBLE);
                variant2.setEnabled(false);
                variant2.setVisibility(View.INVISIBLE);
                next.setEnabled(true);
            }
            if (shag == 8) {
                getWindow().setBackgroundDrawableResource(R.drawable.lift);
                showTxt.setText("   Подъехал лифт…");
            }
            if (shag == 9)
                showTxt.setText("   Из-за огромного количества студентов пришлось пропустить его.");
            if (shag == 10)
                showTxt.setText("   Ничего, поеду на следующем.");
            if (shag == 11)
                showTxt.setText("   Спустя две-три минуты ожидания, лифт все-же приехал.");
            if (shag == 12)
                showTxt.setText("   Зайдя в него, обнаружилось что прошло уже десять минут пары.");
            if (shag == 13)
                showTxt.setText("   …");
            if (shag == 14)
                showTxt.setText("   Вот я на этаже. Подхожу к кафедре.");
            if (shag == 15)
                showTxt.setText("   Увы и ах, дверь закрыта и на кафедру меня не пустят.");
            if (shag == 16)
                showTxt.setText("   Игра закончена.");
        }
         else
            {
            if (shag == 8) {
               showTxt.setText("   Так и сделаю. Впереди одинадцать этажей счастья…");
               getWindow().setBackgroundDrawableResource(R.drawable.stairs);
               variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
               variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
               next.setEnabled(true);
            }
            if (shag == 9)
                showTxt.setText("   Мимо проходят измученные студенты и преподователи.");
            if (shag == 10) {
                sprite.setImageResource(R.drawable.spr1prepod2);
                sprite.setVisibility(View.VISIBLE);
                showTxt.setText("   Все торопятся поскорее занять свои места в аудиториях и  приступить к работе.");
            }
            if (shag == 11)
                showTxt.setText("   Я: «Здравствуйте, Антон Юрьевич»");
            if (shag == 12)
                showTxt.setText("   Антон Юрьевич: «Да-а-а-а, здравствуйте» ");
            if (shag == 13)
                showTxt.setText("   Интересно, он меня вообще помнит?)");
            if (shag == 14) {
                sprite.setVisibility(View.INVISIBLE);
                showTxt.setText("   ");
            }
            if (shag == 15)
                showTxt.setText("   Осталось всего несколько этажей…..");
            if (shag == 16) {
                showTxt.setText("   О на ступеньках валяется письменная ручка, но я вроде на бомжа не похож.");
                variant1.setText("Поднять ручку.");
                variant2.setText("Не поднимать.");
                variant1.setEnabled(true); variant1.setVisibility(View.VISIBLE);
                variant2.setEnabled(true); variant2.setVisibility(View.VISIBLE);
                next.setEnabled(false);
            }
            if (shag == 17) {
                showTxt.setText("   Все, иду дальше.");
                variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                next.setEnabled(true);
            }
            if (shag == 18)
                showTxt.setText("   А вот и она, моя любимая кафедра «ТММиСАПР»");
            if (shag == 19)
                showTxt.setText("   Опоздал всего на две минуты, но дверь открыта, захожу.");
            if (shag == 20) {
                getWindow().setBackgroundDrawableResource(R.drawable.income);
                sprite.setImageResource(R.drawable.spr1teacher);
                sprite.setVisibility(View.VISIBLE);
                showTxt.setText("   Владимир Иванович: «Здравствуйте! Почти не опоздали»");
            }
            if (shag == 21)
                showTxt.setText("   Я: «Драааавствуйте»");
            if (shag == 22)
                showTxt.setText("   Владимир Иванович: «Придумайте причину своего опоздания, студент»");
            if (shag == 23)
                showTxt.setText("   Я: …");
            if (shag == 24) {
                variant1.setText("Проспал, извините.");
                variant2.setText("У трамвая пробило колесо.");
                variant1.setEnabled(true); variant1.setVisibility(View.VISIBLE);
                variant2.setEnabled(true); variant2.setVisibility(View.VISIBLE);
                next.setEnabled(false);
            }
            if (!ans3) {
                if (shag == 25) {
                    variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                    variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                    next.setEnabled(true);
                    sprite.setImageResource(R.drawable.spr1teacher1);
                    showTxt.setText("   Владимир Иванович: «Вы попытались пошутить?!»");
                }
                if (shag == 26) showTxt.setText("   Владимир Иванович: «Похоже что вы не знаете кто на кафедре хорошо шутит!»");
                if (shag == 27) showTxt.setText("   Я: «А кто?»");
                if (shag == 28) showTxt.setText("   Владимир Иванович: «Тот кто преподователь!!!»");
            }
            if (ans3) {
                if (shag == 25) {
                    variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                    variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                    next.setEnabled(true);
                    showTxt.setText("   Владимир Иванович: «Могли бы и что-то пооригинальнее придумать…»");
                }
                if (shag == 26) showTxt.setText("   Владимир Иванович: «Могли бы проявить креативность, вроде ж сообразительный человек»");
                if (shag == 27) showTxt.setText("   Владимир Иванович: «Ой!!!!!»");
                if (shag == 28) showTxt.setText("   Владимир Иванович: «Прошу прощения, не человек, а студент!»");
            }
            if (shag == 29) {
                getWindow().setBackgroundDrawableResource(R.drawable.class1room);
                sprite.setVisibility(View.INVISIBLE);
                showTxt.setText("   Владимир Иванович: «Займите свое место в аудитории»");
            }
            if (shag == 30) showTxt.setText("   Так, куда бы сесть…");
            if (shag == 31) showTxt.setText("   На том компе мышка не работает, а на том клавиатура.");
            if (shag == 32) showTxt.setText("   А на этом вообще «Ошибка при обмене данных с ядром»");
            if (shag == 33) showTxt.setText("   Владимир Иванович: «Садитесь уже!»");
            if (shag == 34) showTxt.setText("   Я сел за машину.");
            if (shag == 35) showTxt.setText("   Монитор так убаюкивающе светит в лицо, может поспать…");
            if (shag == 36) showTxt.setText("   А мне пришлось всю ночь сидеть за лабораторкой...");
            if (shag == 37) showTxt.setText("   Имею я право отдохнуть...?");
            if (shag == 38) {
                variant1.setText("Лечь поспать.");
                variant2.setText("Сидеть у компа.");
                variant1.setEnabled(true); variant1.setVisibility(View.VISIBLE);
                variant2.setEnabled(true); variant2.setVisibility(View.VISIBLE);
                next.setEnabled(false);
            }
            if ((!ans4) && (shag == 39)) shag = 43;
            if (ans4) {
                if (shag == 39) {
                    variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                    variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                    next.setEnabled(true);
                    showTxt.setText("   Дааа, поспать бы мне сейчас не мешало.");
                }
                if (shag == 40) showTxt.setText("   Тем более ночь была безсонной, с этой тупой лабораторкой.");
                if (shag == 41) showTxt.setText("   …");
                if (!ans3) {
                    if (shag == 42) {
                        sprite.setImageResource(R.drawable.spr1teacher3);
                        sprite.setVisibility(View.VISIBLE);
                        showTxt.setText("   Владимир Иванович: «Вы что спите?!?!»");
                    }
                    if (shag == 43)  showTxt.setText("   Владимир Иванович: «Вы не только шутки шутите, а еще и спите на парах?!»");
                    if (shag == 44)  showTxt.setText("   Владимир Иванович: «Моя пара окончена для Вас!!!»");
                    if (shag == 44)  showTxt.setText("   Владимир Иванович: «Немедленно покиньте аудиторию!»");
                    if (shag >= 45)  showTxt.setText("   Игра закончена.");
                }
            }
                if (ans3) {
                    if (shag == 42)  {
                        sprite.setImageResource(R.drawable.spr1teacher2);
                        sprite.setVisibility(View.VISIBLE);
                        showTxt.setText("   Владимир Иванович: «Разве вы дома не выспались?»");
                    }
                    if (shag == 43)  showTxt.setText("   Владимир Иванович: «Ану быстро садитесь за работу»");
                }
                    if (shag == 44) {
                        variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                        variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                        next.setEnabled(true);
                        sprite.setVisibility(View.INVISIBLE);
                        showTxt.setText("   Я подошел к компу, пароль был не введен.");
                    }
                    if (shag == 45)  showTxt.setText("   Без помощи преподователя зайти в систему было невозможно.");
                    if (shag == 46)  showTxt.setText("   Я: «Введите пароль, прошу»");
                    if (shag == 47)  showTxt.setText("   Владимир Иванович: «Смотрите, за окном крокодил»");
                    if (shag == 48)  showTxt.setText("   Я отвернулся и посмотрел в окно.");
                    if (shag == 49) {
                        showTxt.setText("   Было слышно стук клавишь.");
                        sprite.setImageResource(R.drawable.spr1krokodil);
                        sprite.setVisibility(View.VISIBLE);
                        getWindow().setBackgroundDrawableResource(R.drawable.window);
                    }
                    if (shag == 50) showTxt.setText("   Я повернулся.");
                    if (shag == 51) {
                        sprite.setVisibility(View.INVISIBLE);
                        getWindow().setBackgroundDrawableResource(R.drawable.teach1comp);
                        showTxt.setText("   Владимир Иванович: «Какого цвета был крокодил?»");
                    }
                    if (shag == 52) {
                        variant1.setText("Розовый.");
                        variant2.setText("Красный.");
                        variant1.setEnabled(true); variant1.setVisibility(View.VISIBLE);
                        variant2.setEnabled(true); variant2.setVisibility(View.VISIBLE);
                        next.setEnabled(false);
                    }
                    if (!ans5)  {
                        if (shag == 53) {
                            sprite.setVisibility(View.VISIBLE);
                            sprite.setImageResource(R.drawable.spr1teacher3);
                            showTxt.setText("   Владимир Иванович: «Вы сказали красный?»");
                            variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                            variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                            next.setEnabled(true);
                        }
                        if (shag == 54)  showTxt.setText("   Я: «Дааа»");
                        if (shag == 55)  showTxt.setText("   Владимир Иванович: «Вы меня обманули! Он был розовый»");
                        if (shag == 56)  showTxt.setText("   Владимир Иванович: «В моей аудитории не место обманщикам!»");
                        if (shag == 57)  showTxt.setText("   Владимир Иванович: «Уходите отсюда, вы воруете у нашей кафедры!»");
                        if (shag >= 58)  showTxt.setText("   Игра закончена.");
                    }
                    else {
                        if (shag == 53) {
                            sprite.setVisibility(View.VISIBLE);
                            sprite.setImageResource(R.drawable.spr1teacher);
                            showTxt.setText("   Владимир Иванович: «Сразу видно, что вы честный студент»");
                            variant1.setEnabled(false); variant1.setVisibility(View.INVISIBLE);
                            variant2.setEnabled(false); variant2.setVisibility(View.INVISIBLE);
                            next.setEnabled(true);
                        }
                        if (shag == 54)  showTxt.setText("   Я: «Спасибо, что ввели пароль»");
                        if (shag == 55)  showTxt.setText("   Владимир Иванович отошел.");
                        if (shag == 56)  showTxt.setText("   Так, нужно сдать лабораторную, которую я делал всю ночь.");
                        if (shag == 57)  showTxt.setText("   …");
                        if (shag == 58)  showTxt.setText("   После десяти минут работы над кодом, приложение заработало.");
                        if (shag == 59)  showTxt.setText("   Ну что ж, надо попробовать сдать…");
                        if (shag == 60)  showTxt.setText("   Я: «Владимир Иванович, примите мою лабораторную работу»");
                        if (shag == 61)  showTxt.setText("   Владимир Иванович: «Ну, давайте посмотрим…»");
                        if (shag == 62)  showTxt.setText("   …");
                        if (shag == 63)  showTxt.setText("   Владимир Иванович: «Ваша программа работает»");
                        if (!ans3)
                            if (shag >= 64)
                              showTxt.setText("   Владимир Иванович: «Но Вы сегодня много шутили, поэтому я не приму вашу работу» (Игра закончена)");
                        if (ans4) if (shag >= 64)
                            showTxt.setText("   Владимир Иванович: «Вы пытались спать на моей паре, а не приму вашу работу сегодня» (Игра закончена)");
                        if ((ans3) && (!ans4)){
                            if (shag == 65)  showTxt.setText("   Владимир Иванович: «Давайте ручку я распишусь»");
                            if ((shag == 66)&& (ans2)) showTxt.setText("   Как хорошо что я поднял ручку по пути на ВЦ. ");
                              else  showTxt.setText("   Я: «Я… я забыл… ручку дома»");
                            if ((shag == 67)&& (ans2)) showTxt.setText("   Владимир Иванович расписался и со зданной лабой я отправился домой. ");
                            else  showTxt.setText("   Владимир Иванович: «Приходите лучше завтра»");
                            if ((shag == 68)&& (ans2)) showTxt.setText("   ПОБЕДА! ВЫ - БЕЗСМЕРТНЫЙ СТУДЕНТ, ПОЗДРАВЛЯЮ, ТАКИХ КАК ВЫ НЕ СУЩЕСТВУЕТ!!!");
                            else  showTxt.setText("   Игра закончена.»");

                        }

                    }








        }






}




    public void onAnswer1 (View v){
        if (shag == 7) ans1 = true;
        if (shag == 16) ans2 = true;
        if (shag == 24) ans3 = true;
        if (shag == 38) ans4 = true;
        if (shag == 52) ans5 = true;
        onButtonClick(v);
    }
    public void onAnswer2 (View v){
        if (shag == 7) ans1 = false;
        if (shag == 16) ans2 = false;
        if (shag == 24) ans3 = false;
        if (shag == 38) ans4 = false;
        if (shag == 52) ans5 = false;
        onButtonClick(v);
    }



}
