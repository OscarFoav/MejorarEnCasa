package org.belosoft.mejorarencasa.Activities;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.belosoft.mejorarencasa.Adapters.MyAdapter;
import org.belosoft.mejorarencasa.R;

import java.util.ArrayList;
import java.util.List;

public class Flexiones extends AppCompatActivity {

    public int totalSeries = 5;
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

    ToneGenerator toneG;


    // estas de debajo seran sustituidas por los valores de la BD
    public int repSerie1 = 11;
    public int repSerie2 = 11;
    public int repSerie3 = 11;
    public int repSerie4 = 11;
    public int repSerie5 = 11;
    public int numeroCuentaAtras = 60;

    // modificacion a cardview y recyclerview
    // acceso a Serie .java
    private List<Serie> series;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private MyAdapter mAdapter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_flexiones);
        setContentView(R.layout.activity_plantilla_series);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // activar la fecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // mantener la pantalla encencida
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // sonido-beep
        toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

        TextView textView1 = (TextView) findViewById(R.id.textViewPrimeraRepeticion);
        textView1.setText(getResources().getText(R.string.primera_serie) + ": " + repSerie1 + " " + getResources().getString(R.string.repeticiones));

        TextView textView2 = (TextView) findViewById(R.id.textViewSegundaRepeticion);
        textView2.setText(getResources().getText(R.string.segunda_serie) + ": " + repSerie2 + " " + getResources().getString(R.string.repeticiones));

        TextView textView3 = (TextView) findViewById(R.id.textViewTerceraRepeticion);
        textView3.setText(getResources().getText(R.string.tercera_serie) + ": " + repSerie3 + " " + getResources().getString(R.string.repeticiones));

        TextView textView4 = (TextView) findViewById(R.id.textViewCuartaRepeticion);
        textView4.setText(getResources().getText(R.string.cuarta_serie) + ": " + repSerie4 + " " + getResources().getString(R.string.repeticiones));

        TextView textView5 = (TextView) findViewById(R.id.textViewQuintaRepeticion);
        textView5.setText(getResources().getText(R.string.quinta_serie) + ": " + repSerie5 + " " + getResources().getString(R.string.repeticiones));

        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;

        TextView txvCabeceraSeries = (TextView) findViewById(R.id.txvCabeceraSerie);
        txvCabeceraSeries.setText(getResources().getString(R.string.categoria_flexiones));

        TextView txvNumeroSeries = (TextView) findViewById(R.id.txvNumeroSeries);
        txvNumeroSeries.setText(getResources().getString(R.string.series) + ": " + totalSeries);

        TextView txvNumeroRepeticiones = (TextView) findViewById(R.id.txvNumeroRepeticiones);
        txvNumeroRepeticiones.setText(getResources().getString(R.string.repeticiones) + ": " + totalRepeticiones);

        txvSerieTiempoReposo = (TextView) findViewById(R.id.txvSerieTiempoReposo);
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_reposo) + ": " + numeroCuentaAtras);

        // modificacion a cardview y recyclerview
        series = this.getAllSeries();

        mLayoutManager = new LinearLayoutManager(this);

        prbCuentaAtras = (ProgressBar) findViewById(R.id.prbCuantaAtras);
        prbCuentaAtras.setMax(numeroCuentaAtras);
        prbCuentaAtras.setProgress(numeroCuentaAtras);
        txvCuentaAtras = (TextView) findViewById(R.id.txvCuentaAtras);

        // // inicialización de variables
        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtras));
        // if (numeroCuentaAtras > 99) {
        //     txvCuentaAtras.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
        // } else {
        //     txvCuentaAtras.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
        // }

        // // asignacion de botones
        btnSerie1 = (Button) findViewById(R.id.btnPrimeraRepeticion);
        btnSerie2 = (Button) findViewById(R.id.btnSegundaRepeticion);
        btnSerie3 = (Button) findViewById(R.id.btnTerceraRepeticion);
        btnSerie4 = (Button) findViewById(R.id.btnCuartaRepeticion);
        btnSerie5 = (Button) findViewById(R.id.btnQuintaRepeticion);
        btn10SegundosMas = (Button) findViewById(R.id.btn10SegundosMas);
        btn10SegundosMenos = (Button) findViewById(R.id.btn10SegundosMenos);
        btnTerminarCuentaAtras = (Button) findViewById(R.id.btnTerminarCuentaAtras);

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
                if ((numeroCuentaAtras) == tiempoRestante) {
                    if (numeroCuentaAtras < TIEMPO_MAXIMO) numeroCuentaAtras = numeroCuentaAtras + sumar;
                    actualizarTimer(sumar);
                }  else {
                    if (numeroCuentaAtras < TIEMPO_MAXIMO) numeroCuentaAtras = numeroCuentaAtras + sumar;
                    reiniciarTimer(numeroCuentaAtras, tiempoRestante + sumar);
                }
            }
        });

        btn10SegundosMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int restar = 10;
                // si los valores son iguales no se actualiza el timer
                if ((numeroCuentaAtras) == tiempoRestante) {
                    if (numeroCuentaAtras > TIEMPO_MINIMO) numeroCuentaAtras = numeroCuentaAtras - restar;
                    actualizarTimer(-restar);
                } else {
                    if (numeroCuentaAtras > TIEMPO_MINIMO) numeroCuentaAtras = numeroCuentaAtras - restar;
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
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_reposo) + ": " + numeroCuentaAtrasLocal);
        CuentaAtras(numeroCuentaAtrasLocal, tiempoRestanteLocal);
    }

    public void actualizarTimer(int valor){
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_reposo) + ": " + valor);
        txvCuentaAtras.setText(String.valueOf(valor));
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

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.final_serie, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle(getResources().getString(R.string.titulo_final_serie));
        dialogBuilder.setMessage("( " + getResources().getString(R.string.calorias_consumidas_aproximadas) +
                String.format(": %.1f", caloriasKiloRepeticion * totalRepeticiones) +
                ")\n" + getResources().getString(R.string.mensaje_final_serie));

        dialogBuilder.setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //si
                repSerie1 += 1;
                repSerie2 += 1;
                repSerie3 += 1;
                repSerie4 += 1;
                repSerie5 += 1;

                onBackPressed();
            }
        });
        dialogBuilder.setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //no
                onBackPressed();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }

    private ArrayList<Serie> getAllSeries() {
        return new ArrayList<Serie>() {{
            add(new Serie("Primera Repeticion", R.id.btnPrimeraRepeticion));
            add(new Serie("Segunda Repeticion", R.id.btnSegundaRepeticion));
            add(new Serie("Tercera Repeticion", R.id.btnTerceraRepeticion));
            add(new Serie("Cuarta Repeticion", R.id.btnCuartaRepeticion));

        }};
    }
}
