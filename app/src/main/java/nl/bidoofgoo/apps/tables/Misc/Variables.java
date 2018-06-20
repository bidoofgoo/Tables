package nl.bidoofgoo.apps.tables.Misc;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Variables {

    // aantal reset keys
    private static int resetKey = 0;

    public static int getResetKey() {
        return resetKey;
    }

    public static void upKeys(Context context){
        setResetKey(resetKey + 1, context);
    }

    public static void downKeys(Context context){
        setResetKey(resetKey - 1, context);
    }

    // Deze functie laadt de value van resetkeys uit sharedpreferences en slaat deze op
    public static void loadResetKeyFromPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        resetKey = sp.getInt("key", 0);
    }

    // Deze functie zet het aantal keys in de sharedpreferences
    private static void saveResetKeys(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key", resetKey);
        editor.commit();
    }

    // Deze functie wijzigd de resetkey
    private static void setResetKey(int resetKey, Context context) {
        Variables.resetKey = resetKey;
        if (Variables.resetKey < 0){
            Variables.resetKey = 0;
        }
        saveResetKeys(context);
    }

}
