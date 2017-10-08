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
        if (historicals.size() == 0) createHistorical(userLocal, "");
        realm.close();
    }

    private void createNewDefaultValues() {
        String push_ups;
        String repetition_number_push_ups;
        String level_first_push_ups;
        String level_second_push_ups;
        String abs;
        String repetition_number_abs;
        String level_first_abs;
        String level_second_abs;
        String dips;
        String repetition_number_dips;
        String level_first_dips;
        String level_second_dips;
        String squats;
        String repetition_number_squats;
        String level_first_squats;
        String level_second_squats;
        String pull_ups;
        String repetition_number_pull_ups;
        String level_first_pull_ups;
        String level_second_pull_ups;
        String calves;
        String repetition_number_calves;
        String level_first_calves;
        String level_second_calves;
        // asignando valores
        push_ups = "Push-Ups";
        repetition_number_push_ups = "5";
        level_first_push_ups = "100";
        level_second_push_ups = "150";
        abs = "Abs";
        repetition_number_abs = "5";
        level_first_abs = "200";
        level_second_abs = "300";
        dips = "Dips";
        repetition_number_dips = "5";
        level_first_dips = "150";
        level_second_dips = "200";
        squats = "Squats";
        repetition_number_squats = "5";
        level_first_squats = "200";
        level_second_squats = "300";
        pull_ups = "Pull-Ups";
        repetition_number_pull_ups = "5";
        level_first_pull_ups = "20";
        level_second_pull_ups = "30";
        calves = "Calves";
        repetition_number_calves = "5";
        level_first_calves = "200";
        level_second_calves = "300";
        // grabacion del registro
        realm.beginTransaction();
        DefaultValues defaultValues = new DefaultValues(
                push_ups
                , repetition_number_push_ups
                , level_first_push_ups
                , level_second_push_ups
                , abs
                , repetition_number_abs
                , level_first_abs
                , level_second_abs
                , dips
                , repetition_number_dips
                , level_first_dips
                , level_second_dips
                , squats
                , repetition_number_squats
                , level_first_squats
                , level_second_squats
                , pull_ups
                , repetition_number_pull_ups
                , level_first_pull_ups
                , level_second_pull_ups
                , calves
                , repetition_number_calves
                , level_first_calves
                , level_second_calves);
        realm.copyToRealm(defaultValues);
        realm.commitTransaction();
    }

    private void createUsers(String userLocal, int ageLocal, int weightLocal) {
        String user_serie;
        int user_age;
        int user_weight;
        String series_name;
        int repetition_series_one;
        int repetition_series_two;
        int repetition_series_three;
        int repetition_series_four;
        int repetition_series_five;
        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Flexiones";
        repetition_series_one = 10;
        repetition_series_two = 10;
        repetition_series_three = 10;
        repetition_series_four = 10;
        repetition_series_five = 10;
        // grabacion del registro
        realm.beginTransaction();
        Users useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Abdominales";
        repetition_series_one = 20;
        repetition_series_two = 20;
        repetition_series_three = 20;
        repetition_series_four = 20;
        repetition_series_five = 20;
        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Fondos";
        repetition_series_one = 15;
        repetition_series_two = 15;
        repetition_series_three = 15;
        repetition_series_four = 15;
        repetition_series_five = 15;
        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Sentadillas";
        repetition_series_one = 20;
        repetition_series_two = 20;
        repetition_series_three = 20;
        repetition_series_four = 20;
        repetition_series_five = 20;
        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Dominadas";
        repetition_series_one = 2;
        repetition_series_two = 2;
        repetition_series_three = 2;
        repetition_series_four = 2;
        repetition_series_five = 2;
        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

        // asignando valores
        user_serie = userLocal;
        user_age = ageLocal;
        user_weight = weightLocal;
        series_name = "Gemelos";
        repetition_series_one = 20;
        repetition_series_two = 20;
        repetition_series_three = 20;
        repetition_series_four = 20;
        repetition_series_five = 20;
        // grabacion del registro
        realm.beginTransaction();
        useres = new Users(
                user_serie,
                user_age,
                user_weight,
                series_name,
                repetition_series_one,
                repetition_series_two,
                repetition_series_three,
                repetition_series_four,
                repetition_series_five);
        realm.copyToRealm(useres);
        realm.commitTransaction();

    }

    private void createHistorical(String userLocal, String serieLocal) {
        String historical_column_user;
        String historical_column_type_series;
        float historical_column_calories;
        String historical_column_series_number;
        String historical_column_repetition_series_one;
        String historical_column_repetition_series_two;
        String historical_column_repetition_series_three;
        String historical_column_repetition_series_four;
        String historical_column_repetition_series_five;
        Date createAt;
        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Flexiones";
        historical_column_calories = 0.081f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "10";
        historical_column_repetition_series_two = "10";
        historical_column_repetition_series_three = "10";
        historical_column_repetition_series_four = "10";
        historical_column_repetition_series_five = "10";
        // grabacion del registro
        realm.beginTransaction();
        Historical historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Abdominales";
        historical_column_calories = 1f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "10";
        historical_column_repetition_series_two = "10";
        historical_column_repetition_series_three = "10";
        historical_column_repetition_series_four = "10";
        historical_column_repetition_series_five = "10";
        // grabacion del registro
        realm.beginTransaction();
        historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Fondos";
        historical_column_calories = 1f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "20";
        historical_column_repetition_series_two = "20";
        historical_column_repetition_series_three = "20";
        historical_column_repetition_series_four = "20";
        historical_column_repetition_series_five = "20";
        // grabacion del registro
        realm.beginTransaction();
        historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Sentadillas";
        historical_column_calories = 1f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "15";
        historical_column_repetition_series_two = "15";
        historical_column_repetition_series_three = "15";
        historical_column_repetition_series_four = "15";
        historical_column_repetition_series_five = "15";
        // grabacion del registro
        realm.beginTransaction();
        historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Dominadas";
        historical_column_calories = 1f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "2";
        historical_column_repetition_series_two = "2";
        historical_column_repetition_series_three = "2";
        historical_column_repetition_series_four = "2";
        historical_column_repetition_series_five = "2";
        // grabacion del registro
        realm.beginTransaction();
        historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

        // asignando valores Flexiones
        historical_column_user = userLocal;
        historical_column_type_series = "Gemelos";
        historical_column_calories = 1f;
        historical_column_series_number = "5";
        historical_column_repetition_series_one = "25";
        historical_column_repetition_series_two = "25";
        historical_column_repetition_series_three = "25";
        historical_column_repetition_series_four = "25";
        historical_column_repetition_series_five = "25";
        // grabacion del registro
        realm.beginTransaction();
        historical = new Historical(
                historical_column_user,
                historical_column_type_series,
                historical_column_calories,
                historical_column_series_number,
                historical_column_repetition_series_one,
                historical_column_repetition_series_two,
                historical_column_repetition_series_three,
                historical_column_repetition_series_four,
                historical_column_repetition_series_five
        );
        realm.copyToRealm(historical);
        realm.commitTransaction();

    }

    // configurar Realm config
    private void setUpRealmConfig() {
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

