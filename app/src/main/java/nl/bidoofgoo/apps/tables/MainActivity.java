package nl.bidoofgoo.apps.tables;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import nl.bidoofgoo.apps.tables.Misc.ButtonClick;
import nl.bidoofgoo.apps.tables.Misc.DatabaseConnectie;
import nl.bidoofgoo.apps.tables.Misc.Function;
import nl.bidoofgoo.apps.tables.Misc.Variables;

public class MainActivity extends AppCompatActivity {

    // Mediaplayer that handles music, static so it applies fully throughout the app
    public static  MediaPlayer mediaPlayer = new MediaPlayer();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Create screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseConnectie.setupDatabase();
        Variables.loadResetKeyFromPreferences(this);

        // Instanciate all buttons
        Button play = (Button) findViewById(R.id.play);
        Button highScores = (Button) findViewById(R.id.highScoresButton);

        // Play music if not playing already
        if (!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.dreamscape);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

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

    }
}
