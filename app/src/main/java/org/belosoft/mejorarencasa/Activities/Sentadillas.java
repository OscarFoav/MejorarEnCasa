package org.belosoft.mejorarencasa.Activities;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.belosoft.mejorarencasa.R;

public class Sentadillas extends AppCompatActivity {

    // variables
    public int totalSeries = 4 ;
    public int totalRepeticiones;
    Button btnSerie1;
    Button btnSerie2;
    Button btnSerie3;
    Button btnSerie4;
    Button btnSerie5;
    ProgressBar prbCuentaAtras;
    TextView txvCuentaAtras;
    CountDownTimer countDownTimer;

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
        setContentView(R.layout.activity_sentadillas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // activar la fecha ir atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        totalRepeticiones = repSerie1 + repSerie2 + repSerie3 + repSerie4 + repSerie5;

        TextView txvFlexionesSeriesRepeticiones = (TextView) findViewById(R.id.txvDominadasSeriesRepeticiones);
        txvFlexionesSeriesRepeticiones.setText("Series: " + totalSeries + "  / Repeticiones: " + totalRepeticiones);

        TextView txvPSR = (TextView) findViewById(R.id.txtPrimeraSerieRepeticionesTabla);
        txvPSR.setText("" + repSerie1 + " " + getResources().getString(R.string.repeticiones));

        TextView txvSSR = (TextView) findViewById(R.id.txtSegundaSerieRepeticionesTabla);
        txvSSR.setText("" + repSerie2 + " " + getResources().getString(R.string.repeticiones));

        TextView txvTSR = (TextView) findViewById(R.id.txtTerceraSerieRepeticionesTabla);
        txvTSR.setText("" + repSerie3 + " " + getResources().getString(R.string.repeticiones));

        TextView txvCSR = (TextView) findViewById(R.id.txtCuartaSerieRepeticionesTabla);
        txvCSR.setText("" + repSerie4 + " " + getResources().getString(R.string.repeticiones));

        TextView txvQSR = (TextView) findViewById(R.id.txtQuintaSerieRepeticionesTabla);
        txvQSR.setText("" + repSerie5 + " " + getResources().getString(R.string.repeticiones));

        prbCuentaAtras = (ProgressBar) findViewById(R.id.prbCuantaAtras);
        txvCuentaAtras = (TextView) findViewById(R.id.txvCuentaAtras);

        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.INVISIBLE);
        txvCuentaAtras.setVisibility(View.INVISIBLE);

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

        // deshabilitar buttons 2 a 5
        btnSerie2.setEnabled(false);
        btnSerie3.setEnabled(false);
        btnSerie4.setEnabled(false);
        btnSerie5.setEnabled(false);

        // listener de buttons
        btnSerie1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras, 1);
            }
        });


        btnSerie2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras, 2);
            }
        });


        btnSerie3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras, 3);
            }
        });


        btnSerie4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras, 4);
            }
        });


        btnSerie5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your handler code here
                CuentaAtras(numeroCuentaAtras, 5);
            }
        });


    }

    public void CuentaAtras(final int contador, final int boton) {
        // progress bar y text view cuenta atras invisibles
        prbCuentaAtras.setVisibility(View.VISIBLE);
        txvCuentaAtras.setVisibility(View.VISIBLE);

        // empezamos la cuenta atras
        countDownTimer = new CountDownTimer( contador * 1000, 500){
            // 500 means, onTick function will be called at every 500 milliseconds

            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                prbCuentaAtras.setProgress((int)seconds);
                txvCuentaAtras.setText(String.format("%02d", seconds%60));
                // format the textview to show the easily readable format

            }
            @Override
            public void onFinish() {
                if(txvCuentaAtras.getText().equals("00")){
                    txvCuentaAtras.setText("OK");
                    // activamos button en secuencia
                    if (boton == 1) {
                        btnSerie1.setEnabled(false);
                        btnSerie2.setEnabled(true);
                    }

                    if (boton == 2) {
                        btnSerie2.setEnabled(false);
                        btnSerie3.setEnabled(true);
                    }

                    if (boton == 3) {
                        btnSerie3.setEnabled(false);
                        btnSerie4.setEnabled(true);
                    }

                    if (boton == 4) {
                        btnSerie4.setEnabled(false);
                        btnSerie5.setEnabled(true);
                    }

                    if (boton == 5) {
                        btnSerie5.setEnabled(false);
                        // final de serie
                    }
                }
                else{
                    txvCuentaAtras.setText("60");
                    prbCuentaAtras.setProgress(60 * contador);
                }
            }
        }.start();

        // Animation an = new RotateAnimation(180.0f, 270.0f, 0f, 90f);
        // an.setFillAfter(true);
        // prbCuentaAtras.startAnimation(an);
    }

}
