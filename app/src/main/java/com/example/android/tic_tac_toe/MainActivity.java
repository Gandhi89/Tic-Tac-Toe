package com.example.android.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int activePlayer = 0; // 0 = yellow , 1 = red
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 0 = yellow , 1 = red , 2 = empty space
    int[][] winingCombination = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    boolean hasWinner = false;

    ImageView iv1, iv2, iv3, iv4, iv5, iv6, iv7, iv8, iv9;
    TextView winner;
    Button newGame;
    android.support.v7.widget.GridLayout gridLayout;
    boolean draw = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridLayout = findViewById(R.id.gridLayout);
        winner = findViewById(R.id.winningText);
        newGame = findViewById(R.id.NewGame);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);
        iv4 = findViewById(R.id.iv4);
        iv5 = findViewById(R.id.iv5);
        iv6 = findViewById(R.id.iv6);
        iv7 = findViewById(R.id.iv7);
        iv8 = findViewById(R.id.iv8);
        iv9 = findViewById(R.id.iv9);
    }

    public void reset(View view) {
        activePlayer = 0; // 0 = yellow , 1 = red
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }

        hasWinner = false;

        winner.setVisibility(View.GONE);
        newGame.setVisibility(View.GONE);
        gridLayout.setAlpha(1.0f);
        iv1.setImageDrawable(null);
        iv2.setImageDrawable(null);
        iv3.setImageDrawable(null);
        iv4.setImageDrawable(null);
        iv5.setImageDrawable(null);
        iv6.setImageDrawable(null);
        iv7.setImageDrawable(null);
        iv8.setImageDrawable(null);
        iv9.setImageDrawable(null);

    }

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        if (!hasWinner) {

            int tag = Integer.parseInt(counter.getTag().toString());

            if (gameState[tag] == 2) {

                counter.setTranslationY(-1000f);

                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);

                    counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

                    activePlayer = 1;

                    gameState[tag] = 0;

                } else {
                    counter.setImageResource(R.drawable.red);

                    counter.animate().translationYBy(1000f).rotation(360f).setDuration(300);

                    activePlayer = 0;

                    gameState[tag] = 1;
                }

                for (int[] combination : winingCombination) {

                    if (gameState[combination[0]] == gameState[combination[1]] &&
                            gameState[combination[1]] == gameState[combination[2]]
                            && gameState[combination[0]] != 2) {


                        Log.d(TAG, "dropIn: winner -> " + gameState[combination[0]]);

                        gridLayout.setAlpha(0.2f);
                        if (gameState[combination[0]] == 0) {
                            winner.setText("Yellow wins the match");
                        } else {
                            winner.setText("Red wins the match");
                        }
                        winner.setVisibility(View.VISIBLE);
                        newGame.setVisibility(View.VISIBLE);
                        hasWinner = true;
                    }
                }
                draw = true;
                if (!hasWinner) {
                    for (int i = 0; i < gameState.length; i++) {
                        if (gameState[i] == 2) {
                            draw = false;
                        }
                    }
                    if (draw) {
                        gridLayout.setAlpha(0.2f);
                        winner.setText("match is draw");
                        winner.setVisibility(View.VISIBLE);
                        newGame.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }
}
