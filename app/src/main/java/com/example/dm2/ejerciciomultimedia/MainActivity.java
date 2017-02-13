package com.example.dm2.ejerciciomultimedia;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    Button buttonIniciar,buttonPausar,buttonDetener,buttonContinuar,buttonCircular;
    int posicion=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonIniciar=(Button)findViewById(R.id.buttonIniciar);
        buttonPausar=(Button)findViewById(R.id.buttonPausar);
        buttonDetener=(Button)findViewById(R.id.buttonDetener);
        buttonContinuar=(Button)findViewById(R.id.buttonContinuar);
        buttonCircular=(Button)findViewById(R.id.buttonCircular);
    }

    public void destruir(){
        if(mp!=null)
            mp.release();
    }

    public void pulsado(View v) {

        if(v.getId() == R.id.buttonIniciar){
            destruir();
            mp=MediaPlayer.create(this,R.raw.audio);
            mp.start();
            String op=buttonCircular.getText().toString();
            if(op.equals("No reproducir en forma circular")){
                mp.setLooping(false);
            }else{
                mp.setLooping(true);
            }
        }
        if(v.getId() == R.id.buttonPausar){
            if(mp!=null && mp.isPlaying()){
                posicion=mp.getCurrentPosition();
                mp.pause();
            }
        }
        if(v.getId() == R.id.buttonDetener){
            detener();
        }
        if(v.getId() == R.id.buttonContinuar){
            if(mp!=null && mp.isPlaying()==false){
                mp.seekTo(posicion);
                mp.start();
            }
        }
        if(v.getId() == R.id.buttonCircular){
            detener();
            String op=buttonCircular.getText().toString();
            if(op.equals("No reproducir en forma circular")){
                buttonCircular.setText("reproducir en forma circular");
            }else{
                buttonCircular.setText("No reproducir en forma circular");
            }
        }
    }

    public void detener(){
        if(mp!=null){
            mp.stop();
            posicion=0;
        }
    }
}
