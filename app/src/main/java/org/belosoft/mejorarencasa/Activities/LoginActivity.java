package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;
import org.belosoft.mejorarencasa.R;
import org.belosoft.mejorarencasa.Utils.Util;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class LoginActivity extends AppCompatActivity {

    private Realm realm;
    private static AtomicInteger DefaultValuesID = new AtomicInteger();
    private static AtomicInteger HistoricalID = new AtomicInteger();
    private static AtomicInteger UserID = new AtomicInteger();
    private RealmResults<DefaultValues> defaultValues;
    private RealmResults<Users> useres;
    private RealmResults<Historical> historicals;

    private SharedPreferences prefs;

    private String menu = "1";

    private EditText editTextUser;
    private EditText editTextPassword;
    private EditText editTextAge;
    private EditText editTextWeight;
    private Switch switchRemember;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // activar la fecha ir atrás
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindUI();

        // Shared Preferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);

        setCredentialsIfExist();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextUser.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();
                String age = editTextAge.getText().toString();
                String weight = editTextWeight.getText().toString();
                // si no hay ningun error en user o password, se vuelve al MainActivity
                if (login(user, password)) {
                    // vamos al MainActivity
                    goToMain();
                    // guardamos las Preferences
                    saveOnPreferences(user, password, age, weight);
                    // creacion de tablas en Realm
                    creacionRealm(user, Integer.parseInt(age), Integer.parseInt(weight));
                }
            }
        });
    }

    private void bindUI() {
        editTextUser = (EditText) findViewById(R.id.editTextUser);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        switchRemember = (Switch) findViewById(R.id.switchRemember);
        btnLogin = (Button) findViewById(R.id.btnLogin);
    }

    private void setCredentialsIfExist() {
        String user = Util.getUserPreferences(prefs);
        String password = Util.getPassPreferences(prefs);
        String age = Util.getAgePreferences(prefs);
        String weight = Util.getWeightPreferences(prefs);
        boolean remember = Util.getCheckBoxPreferences(prefs);
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)) {
            editTextUser.setText(user);
            editTextPassword.setText(password);
            editTextAge.setText(age);
            editTextWeight.setText(weight);
            switchRemember.setChecked(true);
        }
    }

    private boolean login(String user, String password) {
        if (!isValidUser(user)) {
            Toast.makeText(this, "User is empty, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else if (!isValidPassword(password)) {
            Toast.makeText(this, "Password too short, please try again", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    private void saveOnPreferences(String user, String password, String age, String weight) {
        if (switchRemember.isChecked()) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("user", user);
            editor.putString("pass", password);
            editor.putString("age", age);
            editor.putString("weight", weight);
            editor.commit();
            editor.apply();
        }
    }

    private boolean isValidUser(String user) {
        return !TextUtils.isEmpty(user);
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 4;
    }

    private void goToMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        // para que no se pueda volver al Login desde el MainActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void creacionRealm(String userLocal, int ageLocal, int weightLocal) {
        // llamada a Realm
        setUpRealmConfig();
        realm = Realm.getDefaultInstance();
        DefaultValuesID = getIdByTable(realm, DefaultValues.class);
        HistoricalID = getIdByTable(realm, Historical.class);
        UserID = getIdByTable(realm, Users.class);
        // lectura/creacion de DefaultValues
        defaultValues = realm.where(DefaultValues.class).findAll();
        if (defaultValues.size() == 0) createNewDefaultValues();
        // lectura/creacion de User
        useres = realm.where(Users.class).findAll();
        if (useres.size() == 0) createUsers(userLocal, ageLocal, weightLocal);
        // lectura/creacion de Historical
        historicals = realm.where(Historical.class).findAll();
        if (historicals.size() == 0) createHistorical(userLocal);
        realm.close();
    }

    private void createNewDefaultValues() {
        // grabacion del registro
        realm.beginTransaction();
        DefaultValues defaultValues = new DefaultValues(
                "Flexiones"
                , 5
                , 100
                , 200
                , "Abdominales"
                , 5
                , 200
                , 300
                , "Fondos"
                , 5
                , 150
                , 200
                , "Sentadillas"
                , 5
                , 200
                , 300
                , "Dominadas"
                , 5
                , 20
                , 30
                , "Gemelos"
                , 5
                , 200
                , 300);
        realm.copyToRealm(defaultValues);
        realm.commitTransaction();
    }

    private void createUsers(String userLocal, int ageLocal, int weightLocal) {
        // grabacion del registro
        realm.beginTransaction();
        Users useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Flexiones",
                60,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Abdominales",
                60,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Fondos",
                60,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Sentadillas",
                60,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Dominadas",
                60,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                userLocal,
                ageLocal,
                weightLocal,
                "Gemelos",
                60,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                new Date());
        realm.copyToRealm(useres);
        realm.commitTransaction();

    }

    private void createHistorical(String userLocal) {
        // asignando valores Flexiones
        realm.beginTransaction();
        Historical historical = new Historical(
                userLocal,
                "Flexiones",
                Util.FLEXIONES,
                60,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                Util.REPETICIONES_FLEXIONES_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Abdominales
        realm.beginTransaction();
        historical = new Historical(
                userLocal,
                "Abdominales",
                Util.ABDOMINALES,
                60,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                Util.REPETICIONES_ABDOMINALES_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Fondos
        realm.beginTransaction();
        historical = new Historical(
                userLocal,
                "Fondos",
                Util.FONDOS,
                60,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                Util.REPETICIONES_FONDOS_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Sentadillas
        realm.beginTransaction();
        historical = new Historical(
                userLocal,
                "Sentadillas",
                Util.SENTADILLAS,
                60,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                Util.REPETICIONES_SENTADILLAS_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Dominadas
        realm.beginTransaction();
        historical = new Historical(
                userLocal,
                "Dominadas",
                Util.DOMINADAS,
                60,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                Util.REPETICIONES_DOMINADAS_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Gemelos
        realm.beginTransaction();
        historical = new Historical(
                userLocal,
                "Gemelos",
                Util.GEMELOS,
                60,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                Util.REPETICIONES_GEMELOS_INIT,
                new Date()
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

    }

    private void setUpRealmConfig() {
        // configurar Realm config
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }

}

