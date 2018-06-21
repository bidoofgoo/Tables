package nl.bidoofgoo.apps.tables.Misc.Variables;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PlaysEndless {


    // aantal keer gespeelt
    private static int endlessPlays = 0;


    public static int getValue() {
        return endlessPlays;
    }

    // endlessPlays + 1
    public static void upValue(Context context){
        setValue(endlessPlays + 1, context);
    }

    // Deze functie laadt de value van endlessPlayss uit sharedpreferences en slaat deze op
    public static void loadValueFromPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        endlessPlays = sp.getInt("key", 0);
    }

    // Deze functie zet het aantal keys in de sharedpreferences
    private static void saveValue(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key", endlessPlays);
        editor.commit();
    }

    // Deze functie wijzigd de endlessPlays
    private static void setValue(int endlessPlays, Context context) {
        PlaysEndless.endlessPlays = endlessPlays;
        if (PlaysEndless.endlessPlays < 0){
            PlaysEndless.endlessPlays = 0;
        }
        saveValue(context);
    }
}
