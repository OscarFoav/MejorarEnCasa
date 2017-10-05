package org.belosoft.mejorarencasa.Utils;

import android.content.SharedPreferences;

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
}
