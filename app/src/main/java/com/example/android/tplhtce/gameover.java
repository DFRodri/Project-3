package com.example.android.tplhtce;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class gameover extends AppCompatActivity {

    //global variable used in this activity
    private MediaPlayer mp;

    //to prevent songs to overlap with rotations
    //more info here: https://developer.android.com/guide/components/activities/activity-lifecycle.html
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //forces the song to stop on reload. Having it playing twice is annoying
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
            mp = null;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        //Sega Rally's Game Over theme seems fun, right?
        mp = MediaPlayer.create(this, R.raw.gameover);
        mp.start();

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //If the player wants to try again from zero
    //NOTE FROM THE DEVIL: Please, do it
    public void restart(View view) {

        //forces the song to stop
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
        }

        Intent restart = new Intent(this, MainActivity.class);
        startActivity(restart);

    }

}
