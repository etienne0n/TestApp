package com.github.etienne0n.testapp;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";
    private static final String[] PLAYERS = {PLAYER_X, PLAYER_O};
    private boolean currentPlayer = false; // false -> Player X, true -> Player O
    private TextView[][] idArray = new TextView[3][3];

    private static int gameCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView statusText = findViewById(R.id.statusText);
        TextView playerXWins = findViewById(R.id.playerXWins);
        TextView playerOWins = findViewById(R.id.playerOWins);



        initArray();

        int[]xAndOWins = new int[2];


        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                TextView current = idArray[y][x];
                current.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){

                        if(current.getText().equals("")){
                            gameCounter++;
                            current.setText(currentPlayer());
                            if(won(currentPlayer())){
                                clearField();
                                xAndOWins[currentPlayer ? 1 : 0]++;
                                playerXWins.setText("Player X : " + xAndOWins[0]);
                                playerOWins.setText("Player O : " + xAndOWins[1]);
                                statusText.setText("Spieler " + currentPlayer() + " ist an der Reihe!");
                                //currentPlayer = false;
                            }else if(gameCounter == 9){
                                clearField();
                                nextPlayer();
                                statusText.setText("Unentschieden. Spieler " + currentPlayer() + " ist an der Reihe!");
                            } else{
                                nextPlayer();
                                statusText.setText("Spieler " + currentPlayer() + " ist an der Reihe!");
                            }

                        }
                    }
                });
            }
        }

    }

    private void clearField() {
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++){
                idArray[y][x].setText("");
                gameCounter = 0;
            }
        }
    }

    private void initArray() {
        idArray[0][0] = findViewById(R.id.f0);
        idArray[0][1] = findViewById(R.id.f1);
        idArray[0][2] = findViewById(R.id.f2);
        idArray[1][0] = findViewById(R.id.f3);
        idArray[1][1] = findViewById(R.id.f4);
        idArray[1][2] = findViewById(R.id.f5);
        idArray[2][0] = findViewById(R.id.f6);
        idArray[2][1] = findViewById(R.id.f7);
        idArray[2][2] = findViewById(R.id.f8);

    }

    private boolean won(String currentPlayer) {

        for(int y = 0; y < 3; y++){
            // rows
            if((    idArray[y][0].getText().equals(currentPlayer) &&
                    idArray[y][1].getText().equals(currentPlayer) &&
                    idArray[y][2].getText().equals(currentPlayer))

                    ||
                    // columns
                    (idArray[0][y].getText().equals(currentPlayer) &&
                    idArray[1][y].getText().equals(currentPlayer) &&
                    idArray[2][y].getText().equals(currentPlayer))

           ){

               return true;
           }

        }
        // diagonal 1
        if((    idArray[0][0].getText().equals(currentPlayer) &&
                idArray[1][1].getText().equals(currentPlayer) &&
                idArray[2][2].getText().equals(currentPlayer))

                ||
                // diagonal 2
                (idArray[2][0].getText().equals(currentPlayer) &&
                        idArray[1][1].getText().equals(currentPlayer) &&
                        idArray[0][2].getText().equals(currentPlayer))

        ){

            return true;
        }

        return false;
    }

    private String currentPlayer(){
        return PLAYERS[currentPlayer ? 1 : 0];

    }

    private void nextPlayer(){
        currentPlayer = !currentPlayer;
    }




}
