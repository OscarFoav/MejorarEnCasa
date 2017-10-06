package org.belosoft.mejorarencasa.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.belosoft.mejorarencasa.R;

public class Abdominales extends AppCompatActivity {

    // variables
    public int totalSeries = 5 ;
    public int totalRepeticiones;
    Button btnSerie1;
    Button btnSerie2;
    Button btnSerie3;
    Button btnSerie4;
    Button btnSerie5;
    Button btn10SegundosMas;
    Button btn10SegundosMenos;
    Button btnTerminarCuentaAtras;
    ProgressBar prbCuentaAtras;
    TextView txvCuentaAtras;
    CountDownTimer countDownTimer;
    //public int incremento;  // puede valer +10 o -10
    TextView txvSerieTiempoReposo ;
    int boton = 1;     // controla cual es el boton activo, empieza por el primero

    // estas de debajo seran sustituidas por los valores de la BD
    public int repSerie1 = 25;
    public int repSerie2 = 25;
    public int repSerie3 = 25;
    public int repSerie4 = 25;
    public int repSerie5 = 25;
    public int numeroCuentaAtras = 60;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abdominales);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // activar la fecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;

        TextView txvCabeceraSeries = (TextView) findViewById(R.id.textViewCabeceraSerie);
        txvCabeceraSeries.setText(getResources().getString(R.string.categoria_abdominales));

        TextView txvNumeroSeries = (TextView) findViewById(R.id.textViewNumeroSeries);
        txvNumeroSeries.setText(getResources().getString(R.string.numero_series) + ": " + totalSeries);

        TextView txvNumeroRepeticiones = (TextView) findViewById(R.id.textViewNumeroRepeticiones);
        txvNumeroRepeticiones.setText(getResources().getString(R.string.numero_repeticiones) + ": " + totalRepeticiones);

        txvSerieTiempoReposo = (TextView) findViewById(R.id.textViewSerieTiempoReposo);
        txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) + ": " + numeroCuentaAtras);

        TextView txvPSR = (TextView) findViewById(R.id.txtPrimeraSerieRepeticionesTabla);
        txvPSR.setText("" + repSerie1 + " " + getResources().getString(R.string.numero_repeticiones));

        TextView txvSSR = (TextView) findViewById(R.id.txtSegundaSerieRepeticionesTabla);
        txvSSR.setText("" + repSerie2 + " " + getResources().getString(R.string.numero_repeticiones));

        TextView txvTSR = (TextView) findViewById(R.id.txtTerceraSerieRepeticionesTabla);
        txvTSR.setText("" + repSerie3 + " " + getResources().getString(R.string.numero_repeticiones));

        TextView txvCSR = (TextView) findViewById(R.id.txtCuartaSerieRepeticionesTabla);
        txvCSR.setText("" + repSerie4 + " " + getResources().getString(R.string.numero_repeticiones));

        TextView txvQSR = (TextView) findViewById(R.id.txtQuintaSerieRepeticionesTabla);
        txvQSR.setText("" + repSerie5 + " " + getResources().getString(R.string.numero_repeticiones));

        prbCuentaAtras = (ProgressBar) findViewById(R.id.prbCuantaAtras);
        txvCuentaAtras = (TextView) findViewById(R.id.txvCuentaAtras);

        // inicialización de variables
        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;
        txvCuentaAtras.setText(String.valueOf(numeroCuentaAtras));
        if (numeroCuentaAtras > 99) {
            txvCuentaAtras.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 35);
        } else {
            txvCuentaAtras.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 50);
        }

        // asignacion de botones
        btnSerie1 = (Button) findViewById(R.id.btnPrimeraSerieHechaTabla);
        btnSerie2 = (Button) findViewById(R.id.btnSegundaSerieHechaTabla);
        btnSerie3 = (Button) findViewById(R.id.btnTerceraSerieHechaTabla);
        btnSerie4 = (Button) findViewById(R.id.btnCuartaSerieHechaTabla);
        btnSerie5 = (Button) findViewById(R.id.btnQuintaSerieHechaTabla);
        btn10SegundosMas = (Button) findViewById(R.id.btn10SegundosMas);
        btn10SegundosMenos = (Button) findViewById(R.id.btn10SegundosMenos);
        btnTerminarCuentaAtras = (Button) findViewById(R.id.btnTerminarCuentaAtras);

        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.INVISIBLE);
        txvCuentaAtras.setVisibility(View.INVISIBLE);
        // btn10SegundosMas.setVisibility(View.INVISIBLE);
        // btn10SegundosMenos.setVisibility(View.INVISIBLE);

        // deshabilitar buttons 2 a 5
        btnSerie2.setEnabled(false);
        btnSerie3.setEnabled(false);
        btnSerie4.setEnabled(false);
        btnSerie5.setEnabled(false);

        // listener de buttons
        btnSerie1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras);
            }
        });


        btnSerie2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras);
            }
        });


        btnSerie3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras);
            }
        });


        btnSerie4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras);
            }
        });


        btnSerie5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras);
            }
        });

        btn10SegundosMas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if (numeroCuentaAtras < 150) numeroCuentaAtras += +10;
                txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) + ": " + numeroCuentaAtras);
            }
        });


        btn10SegundosMenos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                if (numeroCuentaAtras > 30) numeroCuentaAtras -= 10;
                txvSerieTiempoReposo.setText(getResources().getString(R.string.tiempo_de_descanso) + ": " + numeroCuentaAtras);
            }
        });

        btnTerminarCuentaAtras.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                cancelTimer();
                txvCuentaAtras.setText("00");
            }
        });


    }


    public void CuentaAtras(final int contador) {
        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.VISIBLE);
        txvCuentaAtras.setVisibility(View.VISIBLE);

        prbCuentaAtras.setMax(contador);
        prbCuentaAtras.setProgress(contador);
        txvCuentaAtras.setText(String.valueOf(contador));

        // empezamos la cuenta atras
        countDownTimer = new CountDownTimer(contador * 1000, 500) {
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                prbCuentaAtras.setProgress((int) seconds);
                txvCuentaAtras.setText(String.format("%02d", seconds % contador));
                // format the textview to show the easily readable format

                //if (incremento == 10) {
                //    seconds = seconds + 10;
                //    incremento = 0;
                //} else if (incremento == -10) {
                //    seconds = seconds - 10;
                //    incremento = 0;
                //}
                //;

            }

            @Override
            public void onFinish() {
                if (txvCuentaAtras.getText().equals("00")) {
                    cancelarTimer();
                } else {
                    txvCuentaAtras.setText(String.valueOf(contador));
                    prbCuentaAtras.setProgress(contador);
                }
            }
        }.start();
    }

    public void cancelTimer() {
        if (countDownTimer != null) countDownTimer.cancel();
        cancelarTimer();
    }

    public void cancelarTimer() {
        txvCuentaAtras.setText("OK");
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

    public void showFinalSerie(){

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.final_serie, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle(getResources().getString(R.string.titulo_final_serie));
        dialogBuilder.setMessage(getResources().getString(R.string.mensaje_final_serie));

        dialogBuilder.setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton){
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
            public void onClick(DialogInterface dialog, int whichButton){
                //no
                onBackPressed();
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


}
