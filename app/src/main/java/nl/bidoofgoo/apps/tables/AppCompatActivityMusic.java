package nl.bidoofgoo.apps.tables;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;

public class AppCompatActivityMusic extends AppCompatActivity {
    // Mediaplayer that handles music, static so it applies fully throughout the app
    public static MediaPlayer mediaPlayer;

    @Override
    protected void onResume() {
        super.onResume();
        // Play music if not playing already
        if (mediaPlayer == null){
            mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.dreamscape);
            mediaPlayer.setLooping(true);
        }

        if (!mediaPlayer.isPlaying()){
            mediaPlayer.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
    }
}
