package org.belosoft.SeflTrainer.Utils;

import android.content.SharedPreferences;

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

    public static float CALORIES_FLEXIONES = 0.12f;
    public static float CALORIES_ABDOMINALES = 0.12f;
    public static float CALORIES_FONDOS = 0.12f;
    public static float CALORIES_SENTADILLAS = 0.12f;
    public static float CALORIES_DOMINADAS = 0.12f;
    public static float CALORIES_GEMELOS = 0.12f;

    public static int REPETICIONES_FLEXIONES_INIT = 5;
    public static int REPETICIONES_ABDOMINALES_INIT = 10;
    public static int REPETICIONES_FONDOS_INIT = 5;
    public static int REPETICIONES_SENTADILLAS_INIT = 10;
    public static int REPETICIONES_DOMINADAS_INIT = 1;
    public static int REPETICIONES_GEMELOS_INIT = 10;

    public static int SECONDS_LEAPS = 60;
    public static int serie_repetitions = 5;

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
                unitCalories = CALORIES_FLEXIONES;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Abdominales":
                unitCalories = CALORIES_ABDOMINALES;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Fondos":
                unitCalories = CALORIES_FONDOS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Sentadillas":
                unitCalories = CALORIES_SENTADILLAS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Dominadas":
                unitCalories = CALORIES_DOMINADAS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            case "Gemelos":
                unitCalories = CALORIES_GEMELOS;
                totalCalories = unitCalories * numeroRepeticiones;
                break;
            default:
                break;
        }
        return totalCalories;
    }

}
