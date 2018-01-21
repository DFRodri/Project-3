package com.example.android.tplhtce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //global variables used in this activity
    boolean noName = false;
    String displayPlayerName;
    String displayPlayerName2;//temp displayName to prevent a empty name to be displayed

    //global variables passed between activities
    int[] categoryCompleted = {0, 0, 0, 0};
    String[] playerInfo = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //easy (and somewhat fun?) way to prevent people from exploiting the way questions are handled
    @Override
    public void onBackPressed() {
        Toast.makeText(this, getString(R.string.noToCheaters), Toast.LENGTH_SHORT).show();
    }

    //changes the name at the score board to the name typed
    public void setName(View view) {

        EditText getPlayerName = this.findViewById(R.id.playerName);
        displayPlayerName = getPlayerName.getText().toString();

        TextView setPlayerName = this.findViewById(R.id.setPlayerName);
        setPlayerName.setText(displayPlayerName);


        //if the player decides to be a dick... call him a loser (let's have some fun, shall we?)
        if (getPlayerName.getText().toString().isEmpty()) {

            displayPlayerName2 = getString(R.string.loserName);
            displayPlayerName = displayPlayerName2;
            setPlayerName.setText(displayPlayerName);

            if (!noName) {

                Toast.makeText(this, getString(R.string.noName), Toast.LENGTH_SHORT).show();

                noName = true;

            }
        }

        //uses a string to hold info related to player to be easier to deal with data
        playerInfo[0] = displayPlayerName;
        playerInfo[1] = "0";

        //passes the categories info and player info
        Intent radioCategory = new Intent(this, Main2Activity.class);
        radioCategory.putExtra("getPlayerInfo", playerInfo);
        radioCategory.putExtra("getCategoriesCompleted", categoryCompleted);
        startActivity(radioCategory);

    }

}
