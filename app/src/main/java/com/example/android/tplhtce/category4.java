package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class category4 extends AppCompatActivity {

    //global variables used in this activity
    int pressedAnswer;
    int rightAnswerImageButton;
    int wakeUp;

    //global variables passed between activities
    String question;
    ArrayList<Integer> answers = new ArrayList<>(4);
    int[] categoryCompleted = new int[4];
    String[] playerInfo = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category4);

        //grabs playerInfo, CategoryCompleted, question, answers, warning joke and right answers from
        //MainActivity.java to create most of the info displayed in the screen
        //rightAnswer is converted from a String to an int because we need the resource identifier instead of a phrase (string)
        question = getIntent().getStringExtra("getQuestion");
        wakeUp = getIntent().getIntExtra("warning", 0);
        answers = getIntent().getIntegerArrayListExtra("getAnswers");
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
        if (categoryCompleted[2] == 1) {
            View yellowSquare = findViewById(R.id.yellowSquare);
            yellowSquare.setBackgroundResource(R.drawable.border_cat2);
        }

        //BELIEVE IN THE HEART OF THE CARDS! (calls method that has the questions and answers about Magic: The Gathering)
        questionMTG();

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //chooses the right question with the proper answers and calls the methods to display
    public void questionMTG() {

        displayQuestion();
        displayAnswers();

    }

    //displays the selected question
    private void displayQuestion() {

        TextView questionView = this.findViewById(R.id.questionText);
        questionView.setText(question);

    }

    //displays the answers of a question from the info stored in answers
    private void displayAnswers() {

        //gets the views to display the images (possible answers)
        ImageButton answerViewA = this.findViewById(R.id.labelA);
        ImageButton answerViewB = this.findViewById(R.id.labelB);
        ImageButton answerViewC = this.findViewById(R.id.labelC);
        ImageButton answerViewD = this.findViewById(R.id.labelD);

        //displays the correct images according the questions
        answerViewA.setImageResource(answers.get(0));
        answerViewB.setImageResource(answers.get(1));
        answerViewC.setImageResource(answers.get(2));
        answerViewD.setImageResource(answers.get(3));

    }

    public void checkAnswerA(View view) {

        pressedAnswer = answers.get(0);
        submitAnswer();

    }

    public void checkAnswerB(View view) {

        pressedAnswer = answers.get(1);
        submitAnswer();

    }

    public void checkAnswerC(View view) {

        pressedAnswer = answers.get(2);
        submitAnswer();

    }

    public void checkAnswerD(View view) {

        pressedAnswer = answers.get(3);
        submitAnswer();

    }

    //checks if the ImageButton selected has the same resource identifier as the right answer
    //depending of the results, it goes to the correct or wrong method
    public void submitAnswer() {

        ArrayList<Integer> correctAnswers = new ArrayList<>();
        correctAnswers.add(R.drawable.blacklotus);
        correctAnswers.add(R.drawable.bfm);
        correctAnswers.add(R.drawable.sistersofthestonedeath);
        correctAnswers.add(R.drawable.urzastower);

        if (playerInfo[2].equals("0")){
            rightAnswerImageButton = correctAnswers.get(0);
        }
        if (playerInfo[2].equals("1")){
            rightAnswerImageButton = correctAnswers.get(1);
        }
        if (playerInfo[2].equals("2")){
            rightAnswerImageButton = correctAnswers.get(2);
        }
        if (playerInfo[2].equals("3")){
            rightAnswerImageButton = correctAnswers.get(3);
        }

        if (pressedAnswer == rightAnswerImageButton) {
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

        categoryCompleted[3] = 1;

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
