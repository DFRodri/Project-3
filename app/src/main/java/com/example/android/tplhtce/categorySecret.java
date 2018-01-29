package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class categorySecret extends AppCompatActivity {

    //007 here, this is the combination to get a win
    int[] rightAnswer = {2, 6, 3, 7, 3, 5, 4, 4, 1, 8};

    //other global variables passed between activities
    String[] playerInfo = new String[3];

    //the following global variables are used to make rotation make its magic
    private static final String ANSWER_ORDER = "answerOrder";

    //besides making rotation do its magic, they're also global variables used in this activity
    private int answerOrder;
    private int[] answer = new int[10];

    //saves the important stuff to use when the screen rotates, before the onDestroy() happens
    //more info here: https://developer.android.com/guide/components/activities/activity-lifecycle.html
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //saves the number of keys pressed until now
        savedInstanceState.putInt("answerOrder", answerOrder);

        //saves the sequence of keys pressed until now into a string to be able to call it later properly
        //each string key is different because of the if() being used, different strings of the same name can be saved and reloaded due to that
        //it's close to an array, what we need here, but it only has one single index and works with constant updates
        //at least from what I know and understand, my knowledge is sort of limited in this particular case
        for (int i = 0; i < answer.length; i++)
        {
            String ANSWER = "answerSaved" + Integer.toString(i);
            savedInstanceState.putInt(ANSWER, answer[i]);
        }
        //NOTE: I know that putIntArray() exists but it asked me the impossible (a String key where I can't have one...)
        //so a practical solution was found and adapted as needed

    }

    //reloads the important saved stuff when the screen rotated, after the onDestroy() happens
    //more info here: https://developer.android.com/guide/components/activities/activity-lifecycle.html
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        //recalls the number of keys pressed until now
        answerOrder = savedInstanceState.getInt("answerOrder");

        //reloads "the sequence of keys pressed until now" saved before
        //uses the same concept of the onSavedInstanceState()
        for (int i = 0; i < answer.length; i++)
        {
            String ANSWER = "answerSaved" + Integer.toString(i);
            answer[i] = savedInstanceState.getInt(ANSWER, answer[i]);
        }
        //this is here because we need to make the activity load a TextView according the number saved in answerOrder
        //if we don't, we will get the string referenced in the xml when we rotate our smartphone
        //until a key is pressed and it decides to update everything properly
        submitAnswer();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ignoremeplease);

        //this is just here because we need the name of the player saved in playerInfo[0]
        //and the points saved in playerInfo[1]
        playerInfo = getIntent().getStringArrayExtra("getPlayerInfo");

    }

    //getSecret<number> - One method for every key pressed

    //step 1 - saves the key pressed in the place our counter says; counter starts with 0
    //step 2 - increments our counter by one to make possible "step 1"
    //step 3 - calls submitAnswer() to update the text
    //when it reaches 10, the game ends, display the final score along the name of the player, and adds a restart button
    //step 3 also beautifies a bit the screen here and there
    public void getSecretOne(View view) {

        answer[answerOrder] = 1;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretTwo(View view) {

        answer[answerOrder] = 2;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretThree(View view) {

        answer[answerOrder] = 3;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretFour(View view) {

        answer[answerOrder] = 4;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretFive(View view) {

        answer[answerOrder] = 5;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretSix(View view) {

        answer[answerOrder] = 6;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretSeven(View view) {

        answer[answerOrder] = 7;
        answerOrder += 1;

        submitAnswer();

    }

    public void getSecretEight(View view) {

        answer[answerOrder] = 8;
        answerOrder += 1;

        submitAnswer();

    }

    //Changes the info displayed on the screen (text) and checks for the solution decided
    //Also controls the restart button
    public void submitAnswer() {

        TextView textOne = this.findViewById(R.id.text1);
        TextView textTwo = this.findViewById(R.id.text2);

        if (answerOrder == 1) {
            textOne.setText(R.string.secretEins);
            textTwo.setText(R.string.secretZwei);
        }
        if (answerOrder == 2) {
            textOne.setText(R.string.secretDrei);
            textTwo.setText(R.string.secretVier);
        }
        if (answerOrder == 3) {
            textOne.setText(R.string.secretFunf);
            textTwo.setText(R.string.secretSechs);
        }
        if (answerOrder == 4) {
            textOne.setText(R.string.secretSieben);
            textTwo.setText(R.string.secretAcht);
        }
        if (answerOrder == 5) {
            textOne.setText(R.string.secretNeun);
            textTwo.setText(R.string.secretZehn);
        }
        if (answerOrder == 6) {
            textOne.setText(R.string.secretElf);
            textTwo.setText(R.string.secretZwolf);
        }
        if (answerOrder == 7) {
            textOne.setText(R.string.secretDreizehn);
            textTwo.setText(R.string.secretVierzehn);
        }
        if (answerOrder == 8) {
            textOne.setText(R.string.secretFunfzehn);
            textTwo.setText(R.string.secretSechzehn);
        }
        if (answerOrder == 9) {
            textOne.setText(R.string.secretSiebzehn);
            textTwo.setText(R.string.secretAchtzehn);
        }
        if (answerOrder == 10) {

            //Makes every useless button invisible to make the screen cleaner (and also a bit prettier, right?)
            Button one = this.findViewById(R.id.first);
            one.setVisibility(View.INVISIBLE);
            Button two = this.findViewById(R.id.second);
            two.setVisibility(View.INVISIBLE);
            Button three = this.findViewById(R.id.third);
            three.setVisibility(View.INVISIBLE);
            Button four = this.findViewById(R.id.fourth);
            four.setVisibility(View.INVISIBLE);
            Button five = this.findViewById(R.id.fifth);
            five.setVisibility(View.INVISIBLE);
            Button six = this.findViewById(R.id.sixth);
            six.setVisibility(View.INVISIBLE);
            Button seven = this.findViewById(R.id.seventh);
            seven.setVisibility(View.INVISIBLE);
            Button eight = this.findViewById(R.id.eighth);
            eight.setVisibility(View.INVISIBLE);

            //Winning toasts are here... if you win
            //NOTE: Yes, there is german in here but not for that reason, silly.
            //It's here because of Major Alvega, an old Portuguese tv show (google it; be aware that some results may be in Portuguese)
            //No, not the comics. Although it's highly based on it!
            if (Arrays.equals(answer, rightAnswer)) {

                textOne.setText(R.string.secretSemiFinal);
                textTwo.setText(R.string.secretFinal);

                if (playerInfo[1].equals("0")) {
                    Toast.makeText(this, getString(R.string.finalScore0) + " " + playerInfo[0] + "!\n" + getString(R.string.finalScore1) + " " + getString(R.string.finalScore3), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, getString(R.string.finalScore0) + " " + playerInfo[0] + "!\n" + getString(R.string.finalScore1) + " " + playerInfo[1] + " " + getString(R.string.finalScore2), Toast.LENGTH_LONG).show();
                }

            } else {

                textOne.setText(R.string.secretSemiError);
                textTwo.setText(R.string.secretError);

            }

            Button restart = this.findViewById(R.id.restart);
            restart.setVisibility(View.VISIBLE);
        }

    }

    //If the player wants to try again from zero
    public void getSecretRestart(View view) {

        Intent restart = new Intent(this, MainActivity.class);
        startActivity(restart);

    }
}
