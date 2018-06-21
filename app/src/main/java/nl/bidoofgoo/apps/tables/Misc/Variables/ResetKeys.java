package nl.bidoofgoo.apps.tables.Misc.Variables;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ResetKeys{

    // aantal reset keys
    private static int resetKey = 0;


    public static int getValue() {
        return resetKey;
    }

    // resetkey + 1
    public static void upValue(Context context){
        setValue(resetKey + 1, context);
    }

    // resetkey -1
    public static void downValue(Context context){
        setValue(resetKey - 1, context);
    }

    // Deze functie laadt de value van resetkeys uit sharedpreferences en slaat deze op
    public static void loadValueFromPreferences(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        resetKey = sp.getInt("key", 0);
    }

    // Deze functie zet het aantal keys in de sharedpreferences
    private static void saveValue(Context context){
        SharedPreferences sp = context.getSharedPreferences("keys", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("key", resetKey);
        editor.commit();
    }

    // Deze functie wijzigd de resetkey
    private static void setValue(int resetKey, Context context) {
        ResetKeys.resetKey = resetKey;
        if (ResetKeys.resetKey < 0){
            ResetKeys.resetKey = 0;
        }
        saveValue(context);
    }

}
