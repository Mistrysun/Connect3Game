package com.example.android.connect3game;

import android.graphics.Typeface;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    int activePlayer = 0;

    int noOfGoes = 0;

    boolean gameActive = true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && gameActive) {

            noOfGoes++;

            gameState[tappedCounter] = activePlayer;

            counter.setTranslationY(-1500);

            if (activePlayer == 0) {

                counter.setImageResource(R.drawable.yellow);

                activePlayer = 1;

            } else {

                counter.setImageResource(R.drawable.red);

                activePlayer = 0;
            }


            counter.animate().translationYBy(1500).setDuration(300).rotation(3600);

            for (int[] winningPosition : winningPositions) {

                //gameState and winningPosition must match
                //e.g. [[0,3] && [0,4] && [0,5]  //e.g. [[1,0], [1,3], [1,6]  //last part of the if statement makes sure their are no empty spaces

                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {

                    //someone has won!

                    String winner = "";

                    if (activePlayer == 1) {

                        gameActive = false;
                        winner = "Yellow";


                    } else {

                        gameActive = false;
                        winner = "Red";
                    }

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(winner + " has won! "+ "\n" + "Payout £10");
                    winnerTextView.setVisibility(view.VISIBLE);

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(view.VISIBLE);

                } else if (noOfGoes == 9 && (gameActive = true)) {

                    gameActive = false;

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
                    winnerTextView.setText(" You both are SHIT!");
                    winnerTextView.setTextSize(30);
                    winnerTextView.setTypeface(null, Typeface.BOLD);
                    winnerTextView.setVisibility(view.VISIBLE);

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
                    playAgainButton.setVisibility(view.VISIBLE);
                }
            }
        }
    }

    public void playAgain (View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
        playAgainButton.setVisibility(View.INVISIBLE);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        winnerTextView.setTextSize(30);
        winnerTextView.setTypeface(null, Typeface.NORMAL);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for (int i=0; i<gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);
        }

        for (int i=0; i<gameState.length; i++) {

            gameState[i] = 2;

        }

        noOfGoes = 0;
        activePlayer = 0;
        gameActive = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
