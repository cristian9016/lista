package com.example.root.lista;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class splashScreen extends AppCompatActivity {

    private static final int segundos=4;
    private static final int milis = segundos*1000;
    private ProgressBar progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progreso = (ProgressBar) findViewById(R.id.pbprogreso);
        iniciarAnimacion();
    }

    public void iniciarAnimacion(){
        new CountDownTimer(milis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progreso.setProgress(establecerProgreso(millisUntilFinished));
                progreso.setMax(3);
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(splashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    public int establecerProgreso(Long milisecs){
        return (int)((milis-milisecs)/1000);
    }
}
