package com.example.android.tplhtce;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class gameover extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        //Sega Rally's Game Over seems fun, right?
        MediaPlayer gameOver = MediaPlayer.create(this, R.raw.gameover);
        gameOver.start();


    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    public void restart(View view){

        Intent restart = new Intent(this, MainActivity.class);
        startActivity(restart);

    }

}
