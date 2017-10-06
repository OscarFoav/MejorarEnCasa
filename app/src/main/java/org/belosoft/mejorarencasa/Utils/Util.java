package org.belosoft.mejorarencasa.Utils;

import android.content.SharedPreferences;
import android.widget.Switch;

/**
 * Created by OscarPC on 05/10/2017.
 */

public class Util {

    public static String getUserPreferences (SharedPreferences preferences) {
        return preferences.getString("user", "");
    }

    public static String getPassPreferences (SharedPreferences preferences) {
        return preferences.getString("pass", "");
    }

    public static String getAgePreferences (SharedPreferences preferences) {
        return preferences.getString("age", "");
    }

    public static String getWeightPreferences (SharedPreferences preferences) {
        return preferences.getString("weight", "");
    }

    public static boolean getCheckBoxPreferences (SharedPreferences preferences){
        return preferences.getBoolean("remember", false);
    }

}
