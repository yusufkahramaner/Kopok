package com.nagdos.kopok;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    TextView zamanText;
    TextView skorText;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView [] imageArray;
    int skor;
    Handler handler;
    Runnable runnable;
    Button yenidenOyna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageView9 = findViewById(R.id.imageView9);

        imageArray = new ImageView[]{imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};

        yenidenOyna = findViewById(R.id.yenidenOyna);

        skor = 0;
        skorText = findViewById(R.id.skorText);
        zamanText = findViewById(R.id.zamanText);


        for(ImageView image:imageArray){
            image.setVisibility(View.INVISIBLE);
        }
    }

    public void skor(View view){
        skor++;
        skorText.setText("Skor : "+skor);

    }

    public void oyna(){
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long l) {
                zamanText.setText("Zaman : "+l/1000);
            }

            @Override
            public void onFinish() {
                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                handler.removeCallbacks(runnable);
                zamanText.setText("Zaman Doldu!");


                yenidenOyna.setText("Oyna");
                yenidenOyna.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(),"Üzgünüz! Zaman Doldu.",Toast.LENGTH_SHORT).show();
            }
        }.start();

        kopok();
    }

    public void kopok(){

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image:imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                Random r = new Random();
                int i = r.nextInt(8-0);
                imageArray[i].setVisibility(View.VISIBLE);
                handler.postDelayed(this,350);

            }

        };

        handler.post(runnable);
    }

    public void yenidenOyna(View view){
        new CountDownTimer(3000,1000){

            @Override
            public void onTick(long l) {
                yenidenOyna.setText("Son : "+l/1000);
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Yeni Oyun Başladı! Başarılar.",Toast.LENGTH_SHORT).show();
                skor = 0;
                skorText.setText("Skor : 0");
                zamanText.setText("Zaman : 30");
                yenidenOyna.setVisibility(View.INVISIBLE);
                oyna();

            }
        }.start();
    }
}
