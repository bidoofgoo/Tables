package nl.bidoofgoo.apps.tables;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.DatabaseConnectie;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Misc.Variables.HighscoreEndless;
import nl.bidoofgoo.apps.tables.Misc.Variables.PlaysEndless;
import nl.bidoofgoo.apps.tables.Misc.Variables.ResetKeys;

public class MainActivity extends AppCompatActivityMusic {


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseConnectie.setupDatabase();
        ResetKeys.loadValueFromPreferences(this);
        HighscoreEndless.loadValueFromPreferences(this);
        PlaysEndless.loadValueFromPreferences(this);

        // Instanciate all buttons
        Button play = (Button) findViewById(R.id.play);
        Button highScores = (Button) findViewById(R.id.highScoresButton);
        Button statistiek = findViewById(R.id.statsButton);

        ButtonClick.setButtonClickFunction(highScores, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent highScores = new Intent(MainActivity.this, LeaderBoardActivity.class);
                startActivity(highScores);
            }
        });

        // Button events
        ButtonClick.setButtonClickFunction(play, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent playScherm = new Intent(MainActivity.this, SelectMode.class);
                startActivity(playScherm);
            }
        });

<<<<<<< HEAD
<<<<<<< HEAD
    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer.start();
=======
        ButtonClick.setButtonClickFunction(statistiek, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent playScherm = new Intent(MainActivity.this, Statistiek.class);
                startActivity(playScherm);
            }
        });

>>>>>>> 45bf4cb2c31ded0896ca202bb03cc3634bbbd1b3
=======
>>>>>>> parent of 448bce1... Test
    }
}
