package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class category3 extends AppCompatActivity {

    //global variables used in this activity
    String pressedAnswer;

    //global variables passed between activities
    String rightAnswerCheck;
    ArrayList<String> answers = new ArrayList<>(5);
    int[] categoryCompleted = new int[4];
    String[] playerInfo = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category3);

        //grabs playerInfo, CategoryCompleted, question, answers and right answers from
        //MainActivity.java to create most of the info displayed in the screen
        answers = getIntent().getStringArrayListExtra("getAnswers");
        rightAnswerCheck = getIntent().getStringExtra("getRightAnswer");
        playerInfo = getIntent().getStringArrayExtra("getPlayerInfo");
        categoryCompleted = getIntent().getIntArrayExtra("getCategoriesCompleted");

        //sets the player name and current points
        TextView setPlayerName = this.findViewById(R.id.setPlayerName);
        setPlayerName.setText(playerInfo[0]);

        TextView currentScore = this.findViewById(R.id.pointsValue);
        currentScore.setText(playerInfo[1]);

        //displays the squares at the top right with the right colours (categories cleared)
        //note:doesn't display the impossible possibilities a.k.a. less code here
        if (categoryCompleted[0] == 1) {
            View orangeSquare = findViewById(R.id.orangeSquare);
            orangeSquare.setBackgroundResource(R.drawable.border_cat0);
        }
        if (categoryCompleted[1] == 1) {
            View blueSquare = findViewById(R.id.blueSquare);
            blueSquare.setBackgroundResource(R.drawable.border_cat1);
        }
        if (categoryCompleted[3] == 1) {
            View brownSquare = findViewById(R.id.brownSquare);
            brownSquare.setBackgroundResource(R.drawable.border_cat3);
        }

        //go Pikachu! Wait! Wrong reality! (calls method that has the questions and answers about animals)
        questionAnimals();

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //methods to call to display the question with the proper shuffled answers and respective image
    public void questionAnimals() {

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
            hintPic.setBackgroundResource(R.drawable.savanna);
        }
        if (playerInfo[2].equals("1")) {
            hintPic.setBackgroundResource(R.drawable.undersea);
        }
        if (playerInfo[2].equals("2")) {
            hintPic.setBackgroundResource(R.drawable.northpole);
        }
        if (playerInfo[2].equals("3")) {
            hintPic.setBackgroundResource(R.drawable.regenanimals);
        }

    }

    //checks answer selected by the player and adds it to a string to be compared later with the right answer
    public void checkAnswer(View view) {

        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.labelA:
                if (checked) {
                    pressedAnswer = answers.get(1);
                    break;
                }
            case R.id.labelB:
                if (checked) {
                    pressedAnswer = answers.get(2);
                    break;
                }
            case R.id.labelC:
                if (checked) {
                    pressedAnswer = answers.get(3);
                    break;
                }
            case R.id.labelD:
                if (checked) {
                    pressedAnswer = answers.get(4);
                    break;
                }

        }

    }

    //checks if the answer selected has the same string value as the right answer
    //depending of the results, it goes to the correct or wrong method
    public void submitAnswer(View view) {

        if (pressedAnswer.equals(rightAnswerCheck)) {
            correct();
        } else {
            wrong();
        }

    }

    //adds points to the total score stored in playerInfo[1]
    public void correct() {

        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();

        int score = Integer.parseInt(playerInfo[1]);
        score += 25;
        playerInfo[1] = Integer.toString(score);

        categoryCompleted[2] = 1;

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
        categoryOptions.putExtra("getPlayerInfo", playerInfo);
        categoryOptions.putExtra("getCategoriesCompleted", categoryCompleted);
        startActivity(categoryOptions);
    }

}
