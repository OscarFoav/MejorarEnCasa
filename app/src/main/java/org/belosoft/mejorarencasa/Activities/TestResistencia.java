package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;
import org.belosoft.mejorarencasa.R;
import org.belosoft.mejorarencasa.Utils.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

import static org.belosoft.mejorarencasa.R.id.parent;
import static org.belosoft.mejorarencasa.R.id.transition_scene_layoutid_cache;

public class TestResistencia extends AppCompatActivity {

    // Realm
    private Realm realm;
    private static AtomicInteger DefaultValuesID = new AtomicInteger();
    private static AtomicInteger HistoricalID = new AtomicInteger();
    private static AtomicInteger UserID = new AtomicInteger();
    private RealmResults<DefaultValues> defaultValues;
    private RealmResults<Users> useres;
    private RealmResults<Historical> historicals;
    private Users users;

    // Preferences y variables temporales
    private SharedPreferences prefs;
    public String user;
    public String age;
    public String weight;

    private Button btnSerie1;
    private Button btnSerie2;
    private Button btnSerie3;
    private Button btnSerie4;
    private Button btnSerie5;
    private Button btnSerie6;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private Spinner spinner4;
    private Spinner spinner5;
    private Spinner spinner6;
    private Object itemSpinner1;
    private Object itemSpinner2;
    private Object itemSpinner3;
    private Object itemSpinner4;
    private Object itemSpinner5;
    private Object itemSpinner6;
    private int valueSpinner1 = Util.REPETICIONES_FLEXIONES_INIT;
    private int valueSpinner2 = Util.REPETICIONES_ABDOMINALES_INIT;
    private int valueSpinner3 = Util.REPETICIONES_FONDOS_INIT;
    private int valueSpinner4 = Util.REPETICIONES_SENTADILLAS_INIT;
    private int valueSpinner5 = Util.REPETICIONES_DOMINADAS_INIT;
    private int valueSpinner6 = Util.REPETICIONES_GEMELOS_INIT;

    private List<Integer> listSpinner;
    private ArrayAdapter<Integer> adapterSpinner;

    public ProgressBar prbCuentaAtras;
    public TextView txvCuentaAtras;

    // control de la cuenta atras
    public CountDownTimer countDownTimer;
    public int tiempoRestante = 0;              // para cambiar la cuenta atrás sobre la marcha

    //public int incremento;  // puede valer +10 o -10
    TextView txvSerieTiempoReposo;
    private int boton = 0;     // controla cual es el boton activo, empieza por el primero

    // flags
    private int flgBotonOnSerie1 = 0; // controla si está activo el boton 1 y evita que se pulse 2 veces
    private int flgBotonOnSerie2 = 0;
    private int flgBotonOnSerie3 = 0;
    private int flgBotonOnSerie4 = 0;
    private int flgBotonOnSerie5 = 0;
    private int flgBotonOnSerie6 = 0;

    public int numeroCuentaAtras = 5; //Util.SECONDS_LEAPS;


    ToneGenerator toneG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_resistencia);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // activar la fecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // color de fondo
        View viewChangeBackground = findViewById(R.id.activityMainChangeBackground);
        viewChangeBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

        inicializacion();

    }

    public void inicializacion() {

        // botones
        btnSerie1 = (Button) findViewById(R.id.btnPrimeraRepeticion);
        btnSerie2 = (Button) findViewById(R.id.btnSegundaRepeticion);
        btnSerie3 = (Button) findViewById(R.id.btnTerceraRepeticion);
        btnSerie4 = (Button) findViewById(R.id.btnCuartaRepeticion);
        btnSerie5 = (Button) findViewById(R.id.btnQuintaRepeticion);
        btnSerie6 = (Button) findViewById(R.id.btnSextaRepeticion);
        // spinners
        spinner1 = (Spinner) findViewById(R.id.spinnerPrimeraRepeticion);
        spinner2 = (Spinner) findViewById(R.id.spinnerSegunaRepeticion);
        spinner3 = (Spinner) findViewById(R.id.spinnerTerceraRepeticion);
        spinner4 = (Spinner) findViewById(R.id.spinnerCuartaRepeticion);
        spinner5 = (Spinner) findViewById(R.id.spinnerQuintaRepeticion);
        spinner6 = (Spinner) findViewById(R.id.spinnerSextaRepeticion);

        listSpinner = new ArrayList<Integer>();
        listSpinner.add(1);
        listSpinner.add(2);
        listSpinner.add(3);
        listSpinner.add(4);
        listSpinner.add(5);
        listSpinner.add(6);
        listSpinner.add(7);
        listSpinner.add(8);
        listSpinner.add(9);
        listSpinner.add(10);
        listSpinner.add(11);
        listSpinner.add(12);
        listSpinner.add(13);
        listSpinner.add(14);
        listSpinner.add(15);
        listSpinner.add(16);
        listSpinner.add(17);
        listSpinner.add(18);
        listSpinner.add(19);
        listSpinner.add(20);
        listSpinner.add(23);
        listSpinner.add(22);
        listSpinner.add(23);
        listSpinner.add(24);
        listSpinner.add(25);
        listSpinner.add(26);
        listSpinner.add(27);
        listSpinner.add(28);
        listSpinner.add(29);
        listSpinner.add(30);
        listSpinner.add(31);
        listSpinner.add(32);
        listSpinner.add(33);
        listSpinner.add(34);
        listSpinner.add(35);
        listSpinner.add(36);
        listSpinner.add(37);
        listSpinner.add(38);
        listSpinner.add(39);
        listSpinner.add(40);
        listSpinner.add(41);
        listSpinner.add(42);
        listSpinner.add(43);
        listSpinner.add(44);
        listSpinner.add(45);
        listSpinner.add(46);
        listSpinner.add(47);
        listSpinner.add(48);
        listSpinner.add(49);
        listSpinner.add(50);
        adapterSpinner = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, listSpinner);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapterSpinner);
        spinner2.setAdapter(adapterSpinner);
        spinner3.setAdapter(adapterSpinner);
        spinner4.setAdapter(adapterSpinner);
        spinner5.setAdapter(adapterSpinner);
        spinner6.setAdapter(adapterSpinner);

        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        user = Util.getUserPreferences(prefs);
        age = Util.getAgePreferences(prefs);
        weight = Util.getWeightPreferences(prefs);

        // acceso a Realm
        setUpRealmConfig();
        realm = Realm.getDefaultInstance();
        DefaultValuesID = getIdByTable(realm, DefaultValues.class);
        HistoricalID = getIdByTable(realm, Historical.class);
        UserID = getIdByTable(realm, Users.class);

        // deshabilitar buttons y spinners 2 a 6
        btnSerie2.setEnabled(false);
        btnSerie3.setEnabled(false);
        btnSerie4.setEnabled(false);
        btnSerie5.setEnabled(false);
        btnSerie6.setEnabled(false);
        spinner2.setEnabled(false);
        spinner3.setEnabled(false);
        spinner4.setEnabled(false);
        spinner5.setEnabled(false);
        spinner6.setEnabled(false);

        // progressbar y texto
        prbCuentaAtras = (ProgressBar) findViewById(R.id.prbCuantaAtras);
        prbCuentaAtras.setMax(numeroCuentaAtras);
        prbCuentaAtras.setProgress(numeroCuentaAtras);
        txvCuentaAtras = (TextView) findViewById(R.id.txvCuentaAtras);

        // listener de buttons
        btnSerie1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // primer boton
                boton = 1;
                if (flgBotonOnSerie1 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie1 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        btnSerie2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // segundo boton
                if (flgBotonOnSerie2 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie2 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        btnSerie3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (flgBotonOnSerie3 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie3 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        btnSerie4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // tercer boton
                if (flgBotonOnSerie4 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie4 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        btnSerie5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // cuarto boton
                if (flgBotonOnSerie5 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie5 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        btnSerie6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // cuarto boton
                if (flgBotonOnSerie6 == 0) {
                    CuentaAtras(numeroCuentaAtras, numeroCuentaAtras);
                    flgBotonOnSerie6 = 1;
                    tiempoRestante = numeroCuentaAtras;
                }
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner1 = spinner1.getItemAtPosition(position);
                valueSpinner1 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                valueSpinner1 = Util.REPETICIONES_FLEXIONES_INIT;
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner2 = spinner2.getItemAtPosition(position);
                valueSpinner2 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                valueSpinner2 = Util.REPETICIONES_ABDOMINALES_INIT;
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner3 = spinner3.getItemAtPosition(position);
                valueSpinner3 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemSpinner3 = Util.REPETICIONES_FONDOS_INIT;
            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner4 = spinner4.getItemAtPosition(position);
                valueSpinner4 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemSpinner4 = Util.REPETICIONES_SENTADILLAS_INIT;
            }
        });

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner5 = spinner5.getItemAtPosition(position);
                valueSpinner5 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemSpinner1 = Util.REPETICIONES_DOMINADAS_INIT;
            }
        });

        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                itemSpinner6 = spinner6.getItemAtPosition(position);
                valueSpinner6 = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                itemSpinner6 = Util.REPETICIONES_GEMELOS_INIT;
            }
        });

    }

    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    public void CuentaAtras(final int contador, final int segundos) {
        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.VISIBLE);
        txvCuentaAtras.setVisibility(View.VISIBLE);

        prbCuentaAtras.setMax(contador);
        prbCuentaAtras.setProgress(segundos);
        txvCuentaAtras.setText(String.valueOf(segundos));

        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
        // empezamos la cuenta atras
        countDownTimer = new CountDownTimer(segundos * 1000, 500) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                tiempoRestante = (int) seconds;                     // convertimos long a int
                prbCuentaAtras.setProgress((int) seconds);
                txvCuentaAtras.setText(String.format("%02d", seconds % contador));
            }

            @Override
            public void onFinish() {
                if (txvCuentaAtras.getText().equals(numeroCuentaAtras + "")) {
                    terminarTimer();
                } else {
                    txvCuentaAtras.setText(String.valueOf(contador));
                    prbCuentaAtras.setProgress(contador);
                    terminarTimer();
                }
            }
        }.start();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }

    public void terminarTimer() {
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtras));
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 400);
        prbCuentaAtras.setProgress(numeroCuentaAtras);
        // activamos button en secuencia
        switch (boton) {
            case 1:
                btnSerie1.setEnabled(false);
                btnSerie2.setEnabled(true);
                spinner1.setEnabled(false);
                spinner2.setEnabled(true);
                boton = 2;
                break;
            case 2:
                btnSerie2.setEnabled(false);
                btnSerie3.setEnabled(true);
                spinner2.setEnabled(false);
                spinner3.setEnabled(true);
                boton = 3;
                break;
            case 3:
                btnSerie3.setEnabled(false);
                btnSerie4.setEnabled(true);
                spinner3.setEnabled(false);
                spinner4.setEnabled(true);
                boton = 4;
                break;
            case 4:
                btnSerie4.setEnabled(false);
                btnSerie5.setEnabled(true);
                spinner4.setEnabled(false);
                spinner5.setEnabled(true);
                boton = 5;
                break;
            case 5:
                btnSerie5.setEnabled(false);
                btnSerie6.setEnabled(true);
                spinner5.setEnabled(false);
                spinner6.setEnabled(true);
                boton = 6;
                break;
            case 6:
                btnSerie6.setEnabled(false);
                spinner6.setEnabled(false);
                boton = 1;
                showFinalSerie();
                break;
            default:
                break;
        }
    }

    public void showFinalSerie() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.final_test_resistencia, null);

        dialogBuilder.setTitle("Test de Resistencia");
        dialogBuilder.setMessage("El test de resistencia ha terminado.");
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton(getResources().getString(R.string.aceptar)
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        saveUsers();
                        onBackPressed();
                    }
                });
        dialogBuilder.create();
        dialogBuilder.show();
    }

    private void saveUsers() {
        //setUpRealmConfig();
        realm = Realm.getDefaultInstance();
        modifyUsers(Util.STRING_FLEXIONES, valueSpinner1);
        modifyUsers(Util.STRING_ABDOMINALES, valueSpinner2);
        modifyUsers(Util.STRING_FONDOS, valueSpinner3);
        modifyUsers(Util.STRING_SENTADILLAS, valueSpinner4);
        modifyUsers(Util.STRING_DOMINADAS, valueSpinner5);
        modifyUsers(Util.STRING_GEMELOS, valueSpinner6);
    }

    private void modifyUsers(String serieType, int valueSpinnerLocal) {
        useres = realm.where(Users.class)
                .equalTo("user_serie", user)
                .equalTo("series_name", serieType)
                .findAll();
        if (useres.size() != 0) {
            // empezar upgrade
            realm.beginTransaction();
            useres.get(0).setRepetition_series_one(valueSpinnerLocal);
            useres.get(0).setRepetition_series_two(valueSpinnerLocal);
            useres.get(0).setRepetition_series_three(valueSpinnerLocal);
            useres.get(0).setRepetition_series_four(valueSpinnerLocal);
            useres.get(0).setRepetition_series_five(valueSpinnerLocal);
            useres.get(0).setSeconds_leaps(Util.SECONDS_LEAPS);
            useres.get(0).setCreateAt(new Date());
            // grabar al final
            realm.commitTransaction();
        } else {
            Toast.makeText(this, "Error saving", Toast.LENGTH_LONG).show();
        }

    }

}
