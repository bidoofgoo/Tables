package nl.bidoofgoo.apps.tables.Misc.Variables;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class HighscoreEndless {
    // hoogste Highscore
    private static int highscore = 0;


    public static int getValue() {
        return highscore;
    }


    // Deze functie laadt de value van highscores uit sharedpreferences en slaat deze op
    public static void loadValueFromPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        highscore = sp.getInt("highscore", 0);
    }

    // Deze functie zet de highscore in de sharedpreferences
    private static void saveValue(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("highscore", highscore);
        editor.commit();
    }

    // Deze functie wijzigd de highscore
    public static void setValue(int highscore, Context context) {
        HighscoreEndless.highscore = highscore;
        if (HighscoreEndless.highscore < 0){
            HighscoreEndless.highscore = 0;
        }
        saveValue(context);
    }
}
