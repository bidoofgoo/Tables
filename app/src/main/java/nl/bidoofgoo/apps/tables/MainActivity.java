package nl.bidoofgoo.apps.tables;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static  MediaPlayer mediaPlayer = new MediaPlayer();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Button> buttons = new ArrayList<Button>();

        Button tafel = (Button) findViewById(R.id.buttonTafel);
        Button uitdaging = (Button) findViewById(R.id.buttonUitdaging);
        Button eindeloos = (Button) findViewById(R.id.buttonEindeloos);

        if (!mediaPlayer.isPlaying()){
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.dreamscape);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
        }

        buttons.add(tafel);
        buttons.add(uitdaging);

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
                Intent tafelScherm = new Intent(MainActivity.this, SommenActivity.class);
                tafelScherm.putExtra("type", "oefenen");
                startActivity(tafelScherm);
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
