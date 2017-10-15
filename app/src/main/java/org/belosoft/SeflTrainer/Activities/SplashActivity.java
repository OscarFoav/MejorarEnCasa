package org.belosoft.SeflTrainer.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import org.belosoft.SeflTrainer.Models.DefaultValues;
import org.belosoft.SeflTrainer.Models.Historical;
import org.belosoft.SeflTrainer.Models.Users;
import org.belosoft.SeflTrainer.Utils.Util;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;

public class SplashActivity extends AppCompatActivity {

    // Preferences y variables temporales
    private SharedPreferences prefs;
    public String user;
    public String age;
    public String weight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // carga inicial
        inicializacion();

        Intent intentLogin = new Intent(this, LoginActivity.class);
        Intent intentMain = new Intent(this, MainActivity.class);

        if (!TextUtils.isEmpty(Util.getUserPreferences(prefs)) &&
                !TextUtils.isEmpty(Util.getPassPreferences(prefs))) {
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        finish();
    }

    public void inicializacion() {
        // carga inicial

        // acceso a Preferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        user = Util.getUserPreferences(prefs);
        age = Util.getAgePreferences(prefs);
        weight = Util.getWeightPreferences(prefs);



    }



    //** CRUD actions **//


}
