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

        // Instanciate all buttons
        Button tafel = (Button) findViewById(R.id.buttonTafel);
        Button uitdaging = (Button) findViewById(R.id.buttonUitdaging);
        Button eindeloos = (Button) findViewById(R.id.buttonEindeloos);

        // Play music if not playing already
        if (!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.dreamscape);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        // Button events
        ButtonClick.setButtonClickFunction(uitdaging, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent tafelScherm = new Intent(MainActivity.this, SommenActivity.class);
                tafelScherm.putExtra("type", "uitdaging");
                startActivity(tafelScherm);
            }
        });

        ButtonClick.setButtonClickFunction(tafel, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent kiesTafel = new Intent(MainActivity.this, kiesTafelActivity.class);
                startActivity(kiesTafel);
            }
        });

        ButtonClick.setButtonClickFunction(eindeloos, getResources(), new Function() {
            @Override
            public void whatToDo() {
                // ga naar een nieuwe pagina
                Intent tafelScherm = new Intent(MainActivity.this, SommenActivity.class);
                tafelScherm.putExtra("type", "eindeloos");
                startActivity(tafelScherm);
            }
        });

    }
}
