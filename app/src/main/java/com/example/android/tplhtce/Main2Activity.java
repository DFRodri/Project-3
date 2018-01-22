package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class Main2Activity extends AppCompatActivity {

    //global variables used in this activity
    //Cat was here. Cat was hungry.

    //those are also passed between activities
    int wakeUp;
    ArrayList<String> answers = new ArrayList<>(5);
    ArrayList<Integer> answersD = new ArrayList<>(4);
    ArrayList<String> rightAnswers = new ArrayList<>(3);
    int[] categoryCompleted = new int[4];
    String[] playerInfo = new String[3];
    String rightAnswerButton;
    String rightAnswerCheck;
    String questionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //grabs playerInfo and CategoryCompleted info from MainActivity.java
        playerInfo = getIntent().getStringArrayExtra("getPlayerInfo");
        categoryCompleted = getIntent().getIntArrayExtra("getCategoriesCompleted");

        //sets the player name and current points
        TextView setPlayerName = this.findViewById(R.id.setPlayerName);
        setPlayerName.setText(playerInfo[0]);

        TextView currentScore = this.findViewById(R.id.pointsValue);
        currentScore.setText(playerInfo[1]);

        //the variable used to play with the warnings in category 1 and 3 of our little game
        wakeUp = getIntent().getIntExtra("warning", 0);

        //used to display the categories at the top right side of the screen
        //0 = nothing changes, 1 = changes the color of the square
        if (categoryCompleted[0] == 1) {
            View orangeSquare = findViewById(R.id.orangeSquare);
            orangeSquare.setBackgroundResource(R.drawable.border_cat0);

            TextView categoryOne = this.findViewById(R.id.categoryA);
            categoryOne.setText(getString(R.string.done));
        }
        if (categoryCompleted[1] == 1) {
            View blueSquare = findViewById(R.id.blueSquare);
            blueSquare.setBackgroundResource(R.drawable.border_cat1);

            TextView categoryTwo = this.findViewById(R.id.categoryB);
            categoryTwo.setText(getString(R.string.done));
        }
        if (categoryCompleted[2] == 1) {
            View yellowSquare = findViewById(R.id.yellowSquare);
            yellowSquare.setBackgroundResource(R.drawable.border_cat2);

            TextView categoryThree = this.findViewById(R.id.categoryC);
            categoryThree.setText(getString(R.string.done));
        }
        if (categoryCompleted[3] == 1) {
            View brownSquare = findViewById(R.id.brownSquare);
            brownSquare.setBackgroundResource(R.drawable.border_cat3);

            TextView categoryFour = this.findViewById(R.id.categoryD);
            categoryFour.setText(getString(R.string.done));
        }

        //when 4 squares change color, we get a win!
        if ((categoryCompleted[0] == 1) && (categoryCompleted[1] == 1) && (categoryCompleted[2] == 1) && (categoryCompleted[3] == 1)) {
            goToWinnerScreen();
        }

        //decides on the question to display
        //solution opted is the most balanced I could create/find
        //random sucks, most of the time, even using a big number data to make results be out of the normal way of how random works,
        //it will always tend to ignore two possible results
        //shuffle using a XOR isn't terrible but also fails, one possible solution will always give you an "error"
        //(not exactly an error but something that you don't expect to see)
        ArrayList<String> random = new ArrayList<>();
        random.add("0");
        random.add("1");
        random.add("2");
        random.add("3");
        Collections.shuffle(random);
        playerInfo[2] = random.get(0);

    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //the following 4 getCategory methods are made to be one method for each button/category of the next 4 activities
    //each method grabs a set of question plus four possible answers and shuffles the answers based on the value decided in playerInfo[2]
    //to make it a bit harder to guess the right one in the first attempts
    //Then it passes that along the player name, current points, and categories cleared (or not) to another activity
    //NOTE: I know that this could be done here without any extra activity (you can code buttons in pure java after all) but knowledge is short to do it properly as it should for yesterday


    public void getCategory0(View view) {
        if (categoryCompleted[0] != 1) {

            if (playerInfo[2].equals("0")) {
                answers.add(getString(R.string.q1_1));
                answers.add(getString(R.string.q1_1A));//correct
                answers.add(getString(R.string.q1_1B));
                answers.add(getString(R.string.q1_1C));
                answers.add(getString(R.string.q1_1D));

                //correct answer added to another ArrayList to be compared later with answers chosen by the player
                rightAnswers.add(answers.get(1));

            }

            if (playerInfo[2].equals("1")) {
                answers.add(getString(R.string.q1_2));
                answers.add(getString(R.string.q1_2A));//correct
                answers.add(getString(R.string.q1_2B));
                answers.add(getString(R.string.q1_2C));
                answers.add(getString(R.string.q1_2D));//correct

                //correct answers added to another ArrayList to be compared later with answers chosen by the player
                rightAnswers.add(answers.get(1));
                rightAnswers.add(answers.get(4));

            }

            if (playerInfo[2].equals("2")) {
                answers.add(getString(R.string.q1_3));
                answers.add(getString(R.string.q1_3A));//correct
                answers.add(getString(R.string.q1_3B));//correct
                answers.add(getString(R.string.q1_3C));
                answers.add(getString(R.string.q1_3D));

                //correct answers added to another ArrayList to be compared later with answers chosen by the player
                rightAnswers.add(answers.get(1));
                rightAnswers.add(answers.get(2));

            }

            if (playerInfo[2].equals("3")) {
                answers.add(getString(R.string.q1_4));
                answers.add(getString(R.string.q1_4A));//correct
                answers.add(getString(R.string.q1_4B));
                answers.add(getString(R.string.q1_4C));//correct
                answers.add(getString(R.string.q1_4D));//correct

                //correct answers added to another ArrayList to be compared later with answers chosen by the player
                rightAnswers.add(answers.get(1));
                rightAnswers.add(answers.get(3));
                rightAnswers.add(answers.get(4));

            }

            //shuffles the portion(subList) of the variable answers(ArrayList) that holds the answers
            //this also makes code in each of the following activities shorter
            //PRO-TIP: This make me dump all the worries about Collections.shuffle() and global variables when you rotate your smartphone
            Collections.shuffle(answers.subList(1, 5));

            //Time to drop the ball into the next category with this long pass full of putSomethingOnTheOtherSide
            Intent category0 = new Intent(this, category1.class);
            category0.putExtra("warning",wakeUp);
            category0.putStringArrayListExtra("getAnswers", answers);
            category0.putStringArrayListExtra("getRightAnswers", rightAnswers);
            category0.putExtra("getPlayerInfo", playerInfo);
            category0.putExtra("getCategoriesCompleted", categoryCompleted);
            startActivity(category0);
        }
    }

    public void getCategory1(View view) {
        if (categoryCompleted[1] != 1) {

            if (playerInfo[2].equals("0")) {
                answers.add(getString(R.string.q2_1));
                answers.add(getString(R.string.q2_1A));
                answers.add(getString(R.string.q2_1B));//correct
                answers.add(getString(R.string.q2_1C));
                answers.add(getString(R.string.q2_1D));

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerButton = answers.get(2);

            }

            if (playerInfo[2].equals("1")) {
                answers.add(getString(R.string.q2_2));
                answers.add(getString(R.string.q2_2A));
                answers.add(getString(R.string.q2_2B));//correct
                answers.add(getString(R.string.q2_2C));
                answers.add(getString(R.string.q2_2D));

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerButton = answers.get(2);

            }

            if (playerInfo[2].equals("2")) {
                answers.add(getString(R.string.q2_3));
                answers.add(getString(R.string.q2_3A));
                answers.add(getString(R.string.q2_3B));
                answers.add(getString(R.string.q2_3C));
                answers.add(getString(R.string.q2_3D));//correct

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerButton = answers.get(4);

            }

            if (playerInfo[2].equals("3")) {
                answers.add(getString(R.string.q2_4));
                answers.add(getString(R.string.q2_4A));
                answers.add(getString(R.string.q2_4B));
                answers.add(getString(R.string.q2_4C));
                answers.add(getString(R.string.q2_4D));//correct

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerButton = answers.get(4);

            }

            //shuffles the portion(subList) of the variable answers(ArrayList) that holds the answers
            //this also makes code in each of the following activities shorter
            //PRO-TIP: This make me dump all the worries about Collections.shuffle() and global variables when you rotate your smartphone
            Collections.shuffle(answers.subList(1, 5));

            //Time to drop the ball into the next category with this long pass full of putSomethingOnTheOtherSide
            Intent category1 = new Intent(this, category2.class);
            category1.putExtra("warning",wakeUp);
            category1.putStringArrayListExtra("getAnswers", answers);
            category1.putExtra("getRightAnswer", rightAnswerButton);
            category1.putExtra("getPlayerInfo", playerInfo);
            category1.putExtra("getCategoriesCompleted", categoryCompleted);
            startActivity(category1);
        }
    }

    public void getCategory2(View view) {
        if (categoryCompleted[2] != 1) {

            if (playerInfo[2].equals("0")) {
                answers.add(getString(R.string.q3_1));
                answers.add(getString(R.string.q3_1A));//correct
                answers.add(getString(R.string.q3_1B));
                answers.add(getString(R.string.q3_1C));
                answers.add(getString(R.string.q3_1D));

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerCheck = answers.get(1);

            }

            if (playerInfo[2].equals("1")) {
                answers.add(getString(R.string.q3_2));
                answers.add(getString(R.string.q3_2A));
                answers.add(getString(R.string.q3_2B));
                answers.add(getString(R.string.q3_2C));
                answers.add(getString(R.string.q3_2D));//correct

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerCheck = answers.get(4);

            }

            if (playerInfo[2].equals("2")) {
                answers.add(getString(R.string.q3_3));
                answers.add(getString(R.string.q3_3A));//correct
                answers.add(getString(R.string.q3_3B));
                answers.add(getString(R.string.q3_3C));
                answers.add(getString(R.string.q3_3D));

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerCheck = answers.get(1);

            }

            if (playerInfo[2].equals("3")) {
                answers.add(getString(R.string.q3_4));
                answers.add(getString(R.string.q3_4A));//correct
                answers.add(getString(R.string.q3_4B));
                answers.add(getString(R.string.q3_4C));
                answers.add(getString(R.string.q3_4D));

                //correct answer added to a String to be compared later with answer pressed by the player
                rightAnswerCheck = answers.get(1);

            }

            //shuffles the portion(subList) of the variable answers(ArrayList) that holds the answers
            //this also makes code in each of the following activities shorter
            //PRO-TIP: This make me dump all the worries about Collections.shuffle() and global variables when you rotate your smartphone
            Collections.shuffle(answers.subList(1, 5));

            //Time to drop the ball into the next category with this long pass full of putSomethingOnTheOtherSide
            Intent category2 = new Intent(this, category3.class);
            category2.putExtra("warning",wakeUp);
            category2.putStringArrayListExtra("getAnswers", answers);
            category2.putExtra("getRightAnswer", rightAnswerCheck);
            category2.putExtra("getPlayerInfo", playerInfo);
            category2.putExtra("getCategoriesCompleted", categoryCompleted);
            startActivity(category2);
        }
    }

    public void getCategory3(View view) {
        if (categoryCompleted[3] != 1) {

            //Special note about the way this is achieved, it's made with the idea that we can "store images" in arrayLists
            //because we can store their resource identifiers and further compare them
            //NOTE: rightAnswer isn't here to prevent issues that I can't solve (lol :( )
            if (playerInfo[2].equals("0")) {
                questionD = getString(R.string.q4_1);
                answersD.add(R.drawable.blacklotus);//correct
                answersD.add(R.drawable.platinumangel);
                answersD.add(R.drawable.serrasangel);
                answersD.add(R.drawable.nalathindragon);

            }

            if (playerInfo[2].equals("1")) {
                questionD = getString(R.string.q4_2);
                answersD.add(R.drawable.bfm);//correct
                answersD.add(R.drawable.emrakulthethornsaeon);
                answersD.add(R.drawable.worldgorgerdragon);
                answersD.add(R.drawable.phyrexiandreadnought);

            }

            if (playerInfo[2].equals("2")) {
                questionD = getString(R.string.q4_3);
                answersD.add(R.drawable.deathsshadow);
                answersD.add(R.drawable.demonofdeathsgate);
                answersD.add(R.drawable.sistersofthestonedeath);//correct
                answersD.add(R.drawable.nyxathid);

            }

            if (playerInfo[2].equals("3")) {
                questionD = getString(R.string.q4_4);
                answersD.add(R.drawable.urzastower);//correct
                answersD.add(R.drawable.mishrasbauble);
                answersD.add(R.drawable.akromasmemorial);
                answersD.add(R.drawable.ancientden);

            }

            //shuffles the portion(subList) of the variable answers(ArrayList) that holds the answers
            //this also makes code in each of the following activities shorter
            //PRO-TIP: This make me dump all the worries about Collections.shuffle() and global variables when you rotate your smartphone
            Collections.shuffle(answersD);

            //Time to drop the ball into the next category with this long pass full of putSomethingOnTheOtherSide
            Intent category3 = new Intent(this, category4.class);
            category3.putExtra("warning",wakeUp);
            category3.putExtra("getQuestion", questionD);
            category3.putIntegerArrayListExtra("getAnswers", answersD);
            category3.putExtra("getPlayerInfo", playerInfo);
            category3.putExtra("getCategoriesCompleted", categoryCompleted);
            startActivity(category3);
        }
    }

    //If the player wants to try again from zero
    //NOTE FROM THE DEVIL: Please, do it
    public void restart(View view) {

        categoryCompleted[0] = 0;
        categoryCompleted[1] = 0;
        categoryCompleted[2] = 0;
        categoryCompleted[3] = 0;
        playerInfo[1] = "0";

        Intent restart = new Intent(this,Main2Activity.class);
        restart.putExtra("getPlayerInfo", playerInfo);
        restart.putExtra("getCategoriesCompleted", categoryCompleted);
        restart.addFlags(restart.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(restart);
    }

    //"It's a win, baby! Yeah!" - Austin Powers (sort of)
    public void goToWinnerScreen() {
        Intent win = new Intent(this, winner.class);
        win.putExtra("getPlayerInfo", playerInfo);
        startActivity(win);
    }

}