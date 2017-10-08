package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;
import org.belosoft.mejorarencasa.Utils.Util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class SplashActivity extends AppCompatActivity {

    // Realm
    private Realm realm;
    private static AtomicInteger DefaultValuesID = new AtomicInteger();
    private static AtomicInteger HistoricalID = new AtomicInteger();
    private static AtomicInteger UserID = new AtomicInteger();
    private RealmResults<DefaultValues> defaultValues;
    private RealmResults<Users> useres;
    private RealmResults<Historical> historicals;

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
