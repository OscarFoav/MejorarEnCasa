package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.belosoft.mejorarencasa.Models.DefaultValues;
import org.belosoft.mejorarencasa.Models.Historical;
import org.belosoft.mejorarencasa.Models.Users;
import org.belosoft.mejorarencasa.R;
import org.belosoft.mejorarencasa.Utils.Util;

import java.util.ArrayList;

import java.util.concurrent.atomic.AtomicInteger;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;



public class Sentadillas extends AppCompatActivity {

    // Serie
    private static final String SERIE_TYPE = "Sentadillas";

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

    // control de repeticiones
    public int totalSeries = 5;  // por ahora no esta en la BD
    public int totalRepeticiones;
    public Button btnSerie1;
    public Button btnSerie2;
    public Button btnSerie3;
    public Button btnSerie4;
    public Button btnSerie5;
    public Button btn10SegundosMas;
    public Button btn10SegundosMenos;
    public Button btnTerminarCuentaAtras;
    public ProgressBar prbCuentaAtras;
    public TextView txvCuentaAtras;

    // control de la cuenta atras
    public CountDownTimer countDownTimer;
    public static final int TIEMPO_MAXIMO = 150;              // tiempos minimo y maximo para el descanso entre series
    public static final int TIEMPO_MINIMO = 30;
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

    // calorias por kilo y repetición
    public double caloriasKiloRepeticion = 0.081;

    // aviso acustico
    ToneGenerator toneG;

    // estas de debajo seran sustituidas por los valores de la BD
    public int repSerie1 = 0;
    public int repSerie2 = 0;
    public int repSerie3 = 0;
    public int repSerie4 = 0;
    public int repSerie5 = 0;
    public int numeroCuentaAtras = 60;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_flexiones);
        setContentView(R.layout.activity_plantilla_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // color de fondo
        View viewChangeBackground = findViewById(R.id.activityMainChangeBackground);
        // asignar el color
        viewChangeBackground.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

        // imagen asociada
        ImageView imageView = (ImageView) findViewById(R.id.imageViewSerie);
        imageView.setImageResource(R.drawable.sentadillas);

        // carga inicial
        inicializacion();

        // activar la fecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // mantener la pantalla encencida
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    public void inicializacion() {
        // carga inicial

        // acceso a Preferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        user = Util.getUserPreferences(prefs);
        age = Util.getAgePreferences(prefs);
        weight = Util.getWeightPreferences(prefs);


        // sonido-beep
        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

        // se asigna el mismo valor a tiempoRestante que el que tiene numeroCuentaAtras
        asignarTiempoRestanteIgualQueNumeroCuentaAtras();

        TextView textView1 = (TextView) findViewById(R.id.textViewPrimeraRepeticion);
        TextView textView2 = (TextView) findViewById(R.id.textViewSegundaRepeticion);
        TextView textView3 = (TextView) findViewById(R.id.textViewTerceraRepeticion);
        TextView textView4 = (TextView) findViewById(R.id.textViewCuartaRepeticion);
        TextView textView5 = (TextView) findViewById(R.id.textViewQuintaRepeticion);
        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;

        TextView txvCabeceraSeries = (TextView) findViewById(R.id.textViewCabeceraSerie);
        txvCabeceraSeries.setText(user);

        TextView txvNumeroSeries = (TextView) findViewById(R.id.textViewNumeroSeries);
        txvNumeroSeries.setText(getResources().getString(R.string.numero_series) + ": " + totalSeries);

        TextView txvNumeroRepeticiones = (TextView) findViewById(R.id.textViewNumeroRepeticiones);
        txvNumeroRepeticiones.setText(getResources().getString(R.string.numero_repeticiones) + ": " + totalRepeticiones);

        txvSerieTiempoReposo = (TextView) findViewById(R.id.textViewSerieTiempoReposo);
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) +
                ": " + numeroCuentaAtras +
                " " + getResources().getString(R.string.tiempo_de_descanso_despues));

        prbCuentaAtras = (ProgressBar) findViewById(R.id.prbCuantaAtras);
        prbCuentaAtras.setMax(numeroCuentaAtras);
        prbCuentaAtras.setProgress(numeroCuentaAtras);
        txvCuentaAtras = (TextView) findViewById(R.id.txvCuentaAtras);

        // inicialización de variables
        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtras));

        // asignacion de botones
        btnSerie1 = (Button) findViewById(R.id.btnPrimeraRepeticion);
        btnSerie2 = (Button) findViewById(R.id.btnSegundaRepeticion);
        btnSerie3 = (Button) findViewById(R.id.btnTerceraRepeticion);
        btnSerie4 = (Button) findViewById(R.id.btnCuartaRepeticion);
        btnSerie5 = (Button) findViewById(R.id.btnQuintaRepeticion);
        btn10SegundosMas = (Button) findViewById(R.id.btn10SegundosMas);
        btn10SegundosMenos = (Button) findViewById(R.id.btn10SegundosMenos);
        btnTerminarCuentaAtras = (Button) findViewById(R.id.btnTerminarCuentaAtras);

        // acceso a Realm
        setUpRealmConfig();
        realm = Realm.getDefaultInstance();
        DefaultValuesID = getIdByTable(realm, DefaultValues.class);
        HistoricalID = getIdByTable(realm, Historical.class);
        UserID = getIdByTable(realm, Users.class);

        // lectura de DefaultValues
        defaultValues = realm.where(DefaultValues.class).findAll();

        // lectura de Users
        useres = realm.where(Users.class)
                .equalTo("user_serie", user)
                .equalTo("series_name", SERIE_TYPE)
                .findAll();
        if (useres.size() == 0) {
            // si no existe, mensaje de error
            Toast.makeText(this, "No hay datos. Volver a la pantalla de login para introducir los datos", Toast.LENGTH_LONG).show();
        } else {
            // lectura Users
            readUser();
            textView1.setText(getResources().getText(R.string.primera_serie) + ": " + repSerie1 + " " + plurales(repSerie1));
            textView2.setText(getResources().getText(R.string.segunda_serie) + ": " + repSerie2 + " " + plurales(repSerie2));
            textView3.setText(getResources().getText(R.string.tercera_serie) + ": " + repSerie3 + " " + plurales(repSerie3));
            textView4.setText(getResources().getText(R.string.cuarta_serie) + ": " + repSerie4 + " " + plurales(repSerie4));
            textView5.setText(getResources().getText(R.string.quinta_serie) + ": " + repSerie5 + " " + plurales(repSerie5));
            totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;
            // total series no esta en la BD (¿todavia o nunca?)
            txvNumeroSeries.setText(getResources().getString(R.string.numero_series) + ": " + totalSeries);
            txvNumeroRepeticiones.setText(getResources().getString(R.string.numero_repeticiones) + ": " + totalRepeticiones);
        }
        //realm.close();


        // progress bar y text view cuenta atras visibles
        prbCuentaAtras.setVisibility(View.VISIBLE);
        txvCuentaAtras.setVisibility(View.VISIBLE);
        btn10SegundosMas.setVisibility(View.VISIBLE);
        btn10SegundosMenos.setVisibility(View.VISIBLE);
        btnTerminarCuentaAtras.setVisibility(View.VISIBLE);

        // // deshabilitar buttons 2 a 5
        btnSerie2.setEnabled(false);
        btnSerie3.setEnabled(false);
        btnSerie4.setEnabled(false);
        btnSerie5.setEnabled(false);

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

        btn10SegundosMas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int sumar = 10;
                // quinto boton
                if (numeroCuentaAtras == tiempoRestante) {
                    if (numeroCuentaAtras < TIEMPO_MAXIMO)
                        numeroCuentaAtras = numeroCuentaAtras + sumar;
                    actualizarTimer(sumar);
                } else {
                    if (numeroCuentaAtras < TIEMPO_MAXIMO)
                        numeroCuentaAtras = numeroCuentaAtras + sumar;
                    reiniciarTimer(numeroCuentaAtras, tiempoRestante + sumar);
                }
            }
        });

        btn10SegundosMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int restar = 10;
                // si los valores son iguales no se actualiza el timer
                if (numeroCuentaAtras == tiempoRestante) {
                    if (numeroCuentaAtras > TIEMPO_MINIMO)
                        numeroCuentaAtras = numeroCuentaAtras - restar;
                    actualizarTimer(-restar);
                } else {
                    if (numeroCuentaAtras > TIEMPO_MINIMO)
                        numeroCuentaAtras = numeroCuentaAtras - restar;
                    reiniciarTimer(numeroCuentaAtras, tiempoRestante - restar);
                }
            }
        });


        btnTerminarCuentaAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                cancelTimer();
                txvCuentaAtras.setText(numeroCuentaAtras + "");
                tiempoRestante = numeroCuentaAtras;
            }
        });

    }

    // configurar Realm config
    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    //** CRUD actions **//
    private void increaseRepetitionsNumber(int cantidad) {
        // se incremente cada repeticion el numero de veces indicado en cantidad
        //setUpRealmConfig();
        realm = Realm.getDefaultInstance();
        useres = realm.where(Users.class)
                .equalTo("user_serie", user)
                .equalTo("series_name", SERIE_TYPE)
                .findAll();
        if (useres.size() != 0) {
            // empezar upgrade
            realm.beginTransaction();
            useres.get(0).getId();
            useres.get(0).setRepetition_series_one(cantidad + repSerie1);
            useres.get(0).setRepetition_series_two(cantidad + repSerie2);
            useres.get(0).setRepetition_series_three(cantidad + repSerie3);
            useres.get(0).setRepetition_series_four(cantidad + repSerie4);
            useres.get(0).setRepetition_series_five(cantidad + repSerie5);
            // grabar al final
            realm.commitTransaction();
        } else {
            Toast.makeText(this, "Error saving", Toast.LENGTH_LONG).show();
        }
    }

    // lectura User / Serie
    private void readUser() {
        // textView1.setText(useres.get(0).getSeries_name().toString());
        repSerie1 = useres.get(0).getRepetition_series_one();
        repSerie2 = useres.get(0).getRepetition_series_two();
        repSerie3 = useres.get(0).getRepetition_series_three();
        repSerie4 = useres.get(0).getRepetition_series_four();
        repSerie5 = useres.get(0).getRepetition_series_five();
    }

    private <T extends RealmObject> AtomicInteger getIdByTable(Realm realm, Class<T> anyClass) {
        RealmResults<T> results = realm.where(anyClass).findAll();
        return (results.size() > 0) ? new AtomicInteger(results.max("id").intValue()) : new AtomicInteger();
    }

    public void CuentaAtras(final int contador, final int segundos) {
        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.VISIBLE);
        txvCuentaAtras.setVisibility(View.VISIBLE);

        prbCuentaAtras.setMax(contador);
        prbCuentaAtras.setProgress(segundos);
        txvCuentaAtras.setText(String.valueOf(segundos));

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
                    cancelarTimer();
                } else {
                    txvCuentaAtras.setText(String.valueOf(contador));
                    prbCuentaAtras.setProgress(contador);
                }
            }
        }.start();
    }

    public void reiniciarTimer(int numeroCuentaAtrasLocal, int tiempoRestanteLocal) {
        if (countDownTimer != null) countDownTimer.cancel();
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) + ": " + numeroCuentaAtrasLocal);
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtrasLocal));
        numeroCuentaAtras = numeroCuentaAtrasLocal;
        CuentaAtras(numeroCuentaAtrasLocal, tiempoRestanteLocal);
    }

    public void actualizarTimer(int valor) {
        asignarTiempoRestanteIgualQueNumeroCuentaAtras();
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) + ": " + numeroCuentaAtras);
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtras));
    }

    public void asignarTiempoRestanteIgualQueNumeroCuentaAtras() {
        tiempoRestante = numeroCuentaAtras;
    }

    public void cancelTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        cancelarTimer();
    }

    public void cancelarTimer() {
        txvCuentaAtras.setText("OK");
        toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 400);
        prbCuentaAtras.setProgress(numeroCuentaAtras);
        // activamos button en secuencia
        switch (boton) {
            case 1:
                btnSerie1.setEnabled(false);
                btnSerie2.setEnabled(true);
                boton = 2;
                break;
            case 2:
                btnSerie2.setEnabled(false);
                btnSerie3.setEnabled(true);
                boton = 3;
                break;
            case 3:
                btnSerie3.setEnabled(false);
                btnSerie4.setEnabled(true);
                boton = 4;
                break;
            case 4:
                btnSerie4.setEnabled(false);
                btnSerie5.setEnabled(true);
                boton = 5;
                break;
            case 5:
                btnSerie5.setEnabled(false);
                boton = 1;
                showFinalSerie();
                break;
            default:
                break;
        }
    }

    public void showFinalSerie() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final SeekBar seekBarNumberOfRepetitions ;             // numero de repeticiones en el dialogbox final de serie
        final TextView textViewSeekProgressValue;

        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.final_serie, null);


        seekBarNumberOfRepetitions = (SeekBar) dialogView.findViewById(R.id.seekBarRepetitionsNumber);
        textViewSeekProgressValue = (TextView) dialogView.findViewById(R.id.textViewSeekProgressValue);



        dialogBuilder.setTitle(getResources().getString(R.string.dialog_box_titulo_final_serie));
        dialogBuilder.setMessage("( " + getResources().getString(R.string.calorias_consumidas_aproximadas) +
                String.format(": %.1f", caloriasKiloRepeticion * totalRepeticiones) +
                ")\n" + getResources().getString(R.string.dialog_box_mensaje_final_serie));
        dialogBuilder.setView(dialogView);

        dialogBuilder.setPositiveButton(getResources().getString(R.string.aceptar)
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //si
                        String converter = textViewSeekProgressValue.getText().toString();
                        increaseRepetitionsNumber(Integer.parseInt(converter));
                        onBackPressed();
                    }
                });
        dialogBuilder.setNegativeButton(getResources().getString(R.string.cancelar)
                , new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        //no
                        onBackPressed();
                    }
                });
        dialogBuilder.create();
        dialogBuilder.show();

        seekBarNumberOfRepetitions.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress - 5;
                textViewSeekProgressValue.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private ArrayList<Serie> getAllSeries() {
        return new ArrayList<Serie>() {{
            add(new Serie("Primera Repeticion", R.id.btnPrimeraRepeticion));
            add(new Serie("Segunda Repeticion", R.id.btnSegundaRepeticion));
            add(new Serie("Tercera Repeticion", R.id.btnTerceraRepeticion));
            add(new Serie("Cuarta Repeticion", R.id.btnCuartaRepeticion));

        }};
    }

    private String plurales(int cantidad) {
        Resources res = this.getResources();
        String cadena;
        //-- zero = 0, one = 1, two = 2, few = n mod 100 in 3..10,  many = n mod 100 in 11..99, other = everything else
        if (cantidad == 1) {
            cadena = res.getQuantityString(R.plurals.repeticion, 1);
        } else {
            cadena = res.getQuantityString(R.plurals.repeticion, 2);
        }
        return cadena;
    }
}
