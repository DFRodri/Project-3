package com.example.android.tplhtce;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class winner extends AppCompatActivity {

    //global variables used in this activity
    String[] playerInfo = new String[3];
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        //grabs playerInfo and CategoryCompleted info from MainActivity.java
        playerInfo = getIntent().getStringArrayExtra("getPlayerInfo");

        //plays the *cough* not really *cough* victory song!
        mp = MediaPlayer.create(this,R.raw.mwahaha);
        mp.start();
        //NOTE - there isn't an easy way to stop the audio on purpose
        //adding one would kill the audio too early and ruin the fun
        //overlapping it when you rotate the screen is fun
        //drama created goes up over 9000!!
        //I know that chances to this specific action happen are scarce from the POV of a normal user but... I liked it while testing things
        //at least the audio is killed when you press restart (if you don't overlap it ofc; then things go into a weird mode on and it plays until the end)

        //calls the "winning" text display method
        displayWin();

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //displays the win text
    //NOTE FROM THE DEVIL: This is what happens when you deal with me
    public void displayWin() {

        int remainingBody = Integer.parseInt(playerInfo[1]);

        String winning;
        winning = "\n" + getString(R.string.winState1) + " " + playerInfo[0] + "!\n";
        winning += playerInfo[1] + "% " + getString(R.string.winState2) + "\n";

        //Did you know? Game Over means that the game is now terminated.

        if (remainingBody != 100) {
            winning += (100 - remainingBody) + "% " + getString(R.string.winState3) + "\n";
            winning += getString(R.string.winState4) + "\n";
        }

        TextView congratulations = this.findViewById(R.id.congratulations);
        congratulations.setText(winning);

    }

    //If the player wants to try again from zero
    //NOTE FROM THE DEVIL: Please, do it
    public void restart(View view) {

        //to release/stop the music player before restarting the quiz game once again
        if (mp != null) {
            mp.stop();
            mp.reset();
            mp.release();
        }

        Intent restart = new Intent(this, MainActivity.class);
        startActivity(restart);

    }

}
