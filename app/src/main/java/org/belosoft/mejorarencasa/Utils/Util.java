package org.belosoft.mejorarencasa.Utils;

import android.content.SharedPreferences;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by OscarPC on 05/10/2017.
 */

public class Util {

    public static String STRING_FLEXIONES = "Flexiones";
    public static String STRING_ABDOMINALES = "Abdominales";
    public static String STRING_FONDOS = "Fondos";
    public static String STRING_SENTADILLAS = "Sentadillas";
    public static String STRING_DOMINADAS = "Dominadas";
    public static String STRING_GEMELOS = "Gemelos";

    public static float FLEXIONES = 0.12f;
    public static float ABDOMINALES = 0.12f;
    public static float FONDOS = 0.12f;
    public static float SENTADILLAS = 0.12f;
    public static float DOMINADAS = 0.12f;
    public static float GEMELOS = 0.12f;

    public static int REPETICIONES_FLEXIONES_INIT = 5;
    public static int REPETICIONES_ABDOMINALES_INIT = 10;
    public static int REPETICIONES_FONDOS_INIT = 5;
    public static int REPETICIONES_SENTADILLAS_INIT = 10;
    public static int REPETICIONES_DOMINADAS_INIT = 1;
    public static int REPETICIONES_GEMELOS_INIT = 10;

    public static int SECONDS_LEAPS = 60;


    public static String getUserPreferences(SharedPreferences preferences) {
        return preferences.getString("user", "");
    }

    public static String getPassPreferences(SharedPreferences preferences) {
        return preferences.getString("pass", "");
    }

    public static String getAgePreferences(SharedPreferences preferences) {
        return preferences.getString("age", "");
    }

    public static String getWeightPreferences(SharedPreferences preferences) {
        return preferences.getString("weight", "");
    }

    public static boolean getCheckBoxPreferences(SharedPreferences preferences) {
        return preferences.getBoolean("remember", false);
    }

    public static void removeSharedPreferences(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user");
        editor.remove("pass");
        editor.remove("age");
        editor.remove("weight");
        editor.commit();
        editor.apply();
    }

    public static float calcCalories(String tipoSerie, int numeroRepeticiones) {
        // calculo de calorias para cada tipo de serie
        float unitCalories = 0.0f;
        float totalCalories = 0.0f;
        switch (tipoSerie) {
            case "Flexiones":
                unitCalories = FLEXIONES;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Abdominales":
                unitCalories = ABDOMINALES;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Fondos":
                unitCalories = FONDOS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Sentadillas":
                unitCalories = SENTADILLAS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Dominadas":
                unitCalories = DOMINADAS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Gemelos":
                unitCalories = GEMELOS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            default:
                break;
        }
        return totalCalories;
    }

}
