package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class category1 extends AppCompatActivity {

    //global variables used in this activity
    ArrayList<String> checkedOptions = new ArrayList<>(4);
    ArrayList<String> answers = new ArrayList<>(5);
    ArrayList<String> rightAnswers = new ArrayList<>(3);
    int wakeUp;

    //global variables passed between activities
    int[] categoryCompleted = new int[4];
    String[] playerInfo = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category1);

        //grabs playerInfo, CategoryCompleted, question, answers and right answers from
        //MainActivity.java to create most of the info displayed in the screen
        answers = getIntent().getStringArrayListExtra("getAnswers");
        rightAnswers = getIntent().getStringArrayListExtra("getRightAnswers");
        wakeUp = getIntent().getIntExtra("warning", 0);
        playerInfo = getIntent().getStringArrayExtra("getPlayerInfo");
        categoryCompleted = getIntent().getIntArrayExtra("getCategoriesCompleted");

        //sets the player name and current points
        TextView setPlayerName = this.findViewById(R.id.setPlayerName);
        setPlayerName.setText(playerInfo[0]);

        TextView currentScore = this.findViewById(R.id.pointsValue);
        currentScore.setText(playerInfo[1]);

        //displays the squares at the top right with the right colours (categories cleared)
        //note:doesn't display the impossible possibilities a.k.a. less code here
        if (categoryCompleted[1] == 1) {
            View blueSquare = findViewById(R.id.blueSquare);
            blueSquare.setBackgroundResource(R.drawable.border_cat1);
        }
        if (categoryCompleted[2] == 1) {
            View yellowSquare = findViewById(R.id.yellowSquare);
            yellowSquare.setBackgroundResource(R.drawable.border_cat2);
        }
        if (categoryCompleted[3] == 1) {
            View brownSquare = findViewById(R.id.brownSquare);
            brownSquare.setBackgroundResource(R.drawable.border_cat3);
        }

        //let's make some history! (calls method that has the questions and answers about History)
        questionHistory();

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //methods to call to display the question with the proper shuffled answers and respective image
    public void questionHistory() {

        displayQuestion();
        displayAnswers();
        displayImage();

    }

    //displays the selected question
    private void displayQuestion() {

        TextView questionView = this.findViewById(R.id.questionText);
        questionView.setText(answers.get(0));

    }

    //displays the answers of a question from the info stored in answers
    private void displayAnswers() {


        //collects the answers and displays them
        TextView answerViewA = this.findViewById(R.id.labelA);
        answerViewA.setText(answers.get(1));
        TextView answerViewB = this.findViewById(R.id.labelB);
        answerViewB.setText(answers.get(2));
        TextView answerViewC = this.findViewById(R.id.labelC);
        answerViewC.setText(answers.get(3));
        TextView answerViewD = this.findViewById(R.id.labelD);
        answerViewD.setText(answers.get(4));

    }

    //displays the image of the selected question
    private void displayImage() {

        ImageView hintPic = this.findViewById(R.id.hintPic);
        if (playerInfo[2].equals("0")) {
            hintPic.setBackgroundResource(R.drawable.aroeiraskull);
        }
        if (playerInfo[2].equals("1")) {
            hintPic.setBackgroundResource(R.drawable.dianatemple);
        }
        if (playerInfo[2].equals("2")) {
            hintPic.setBackgroundResource(R.drawable.portugal);
        }
        if (playerInfo[2].equals("3")) {
            //Yes, Wallace is cool. And so is Gromit
            hintPic.setBackgroundResource(R.drawable.tea);
        }

    }

    //checks answers selected by the player and adds them to an ArrayList
    public void checkAnswer(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.labelA:
                if (checked)
                    checkedOptions.add(answers.get(1));
                else
                    checkedOptions.remove(answers.get(1));
                break;
            case R.id.labelB:
                if (checked)
                    checkedOptions.add(answers.get(2));
                else
                    checkedOptions.remove(answers.get(2));
                break;
            case R.id.labelC:
                if (checked)
                    checkedOptions.add(answers.get(3));
                else
                    checkedOptions.remove(answers.get(3));
                break;
            case R.id.labelD:
                if (checked)
                    checkedOptions.add(answers.get(4));
                else
                    checkedOptions.remove(answers.get(4));
                break;

        }

    }

    //checks the size of each array to later compare them
    //Step 1 - if the size is equal, we can then sort them alphabetically
    //Step 2 - with them sorted, we can check if both sorts are equal or not
    public void submitAnswer(View view) {

        //just in case someone decides to check zero options (and also to give a good laugh)
        if (checkedOptions.isEmpty()) {
            wakeUp += 1;
            if (wakeUp == 1) {
                Toast.makeText(this, getString(R.string.wakeUpPlayer), Toast.LENGTH_SHORT).show();
            }
            if (wakeUp == 2) {
                Toast.makeText(this, getString(R.string.angryDevil), Toast.LENGTH_SHORT).show();
            }
            if (wakeUp == 3) {
                Toast.makeText(this, getString(R.string.devilShot), Toast.LENGTH_SHORT).show();
            }
            if (wakeUp == 4) {
                Intent gameOver = new Intent(this, gameover.class);
                startActivity(gameOver);
                return;
            }

            //Restarting the category to prevent errors
            Intent restart = new Intent(this, category1.class);
            restart.putStringArrayListExtra("getAnswers", answers);
            restart.putStringArrayListExtra("getRightAnswers", rightAnswers);
            restart.putExtra("warning", wakeUp);
            restart.putExtra("getPlayerInfo", playerInfo);
            restart.putExtra("getCategoriesCompleted", categoryCompleted);
            restart.addFlags(restart.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(restart);
        } else {
            Collections.sort(checkedOptions);
            Collections.sort(rightAnswers);
            if (checkedOptions.equals(rightAnswers)) {
                correct();
            } else {
                wrong();
            }
        }
    }

    //adds points to the total score stored in playerInfo[1]
    public void correct() {

        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();

        int score = Integer.parseInt(playerInfo[1]);
        score += 25;
        playerInfo[1] = Integer.toString(score);

        categoryCompleted[0] = 1;

        goToCategories();

    }

    //removes points from the total score stored in playerInfo[1]
    //also prevents the score to go below 0 (probably not even needed but here anyway)
    //and if points are equal to zero, calls the game over screen
    public void wrong() {

        int score = Integer.parseInt(playerInfo[1]);
        score -= 10;

        if (score < 0) {
            score = 0;
        }

        playerInfo[1] = Integer.toString(score);

        TextView currentScore = this.findViewById(R.id.pointsValue);
        currentScore.setText(playerInfo[1]);

        if (score == 0) {
            Intent gameOver = new Intent(this, gameover.class);
            startActivity(gameOver);
        } else {
            Toast.makeText(this, getString(R.string.wrong), Toast.LENGTH_SHORT).show();
            goToCategories();
        }

    }

    //method to go to the main menu (Main2Activity)
    //also doesn't forget about the necessary variables needed everywhere
    private void goToCategories() {
        Intent categoryOptions = new Intent(this, Main2Activity.class);
        categoryOptions.putExtra("warning", wakeUp);
        categoryOptions.putExtra("getPlayerInfo", playerInfo);
        categoryOptions.putExtra("getCategoriesCompleted", categoryCompleted);
        startActivity(categoryOptions);
    }

}