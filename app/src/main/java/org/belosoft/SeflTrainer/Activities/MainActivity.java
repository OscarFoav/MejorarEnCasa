package org.belosoft.SeflTrainer.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.belosoft.SeflTrainer.R;
import org.belosoft.SeflTrainer.Utils.Util;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    // Realm
    private Realm realm;

    public CharSequence tituloOpcionAbierta;

    // lectura de Preferences
    private SharedPreferences prefs;
    public String user;
    public String age;
    public String weight;

    // para salir de la app pulsando 2 veces retroceso
    private static final int INTERVALO = 2000; // 2 segundos de plazo
    private long tiempoPrimerClick;

    // imageViews fondo menu
    private ImageView imageViewFlexiones;
    private ImageView imageViewAbdominales;
    private ImageView imageViewFondos;
    private ImageView imageViewSentadillas;
    private ImageView imageViewDominadas;
    private ImageView imageViewGemelos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // leer Preferences
        prefs = getSharedPreferences("Preferences", Context.MODE_PRIVATE);
        user = Util.getUserPreferences(prefs);
        age = Util.getAgePreferences(prefs);
        weight = Util.getWeightPreferences(prefs);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                // titulo navdrawer cerrado
                getSupportActionBar().setTitle(R.string.app_name);
            }

            public void onDrawerOpened(View view) {
                // titulo navdrawer abierto
                getSupportActionBar().setTitle(tituloOpcionAbierta);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // mostrar navegados al abrir/volver de activity
        // drawer.openDrawer(Gravity.START);

        // inicializar
        init();
    }

    private void init() {

        imageViewFlexiones = (ImageView) findViewById(R.id.imageButtonFlexiones);
        imageViewAbdominales = (ImageView) findViewById(R.id.imageButtonAbdominales);
        imageViewFondos = (ImageView) findViewById(R.id.imageButtonFondos);
        imageViewSentadillas = (ImageView) findViewById(R.id.imageButtonSentadillas);
        imageViewDominadas = (ImageView) findViewById(R.id.imageButtonDominadas);
        imageViewGemelos = (ImageView) findViewById(R.id.imageButtonGemelos);

        imageViewFlexiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFlexiones();
            }
        });

        imageViewAbdominales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAbdominales();
            }
        });

        imageViewFondos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFondos();
            }
        });

        imageViewSentadillas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSentadillas();
            }
        });

        imageViewDominadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDominadas();
            }
        });

        imageViewGemelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGemelos();
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()) {
            super.onBackPressed();
            this.finish();
            return;
        } else {
            toastMECCorto(getResources().getString(R.string.salir));
        }
        tiempoPrimerClick = System.currentTimeMillis();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cerrar_sesion:
                changeUserProfile();
                return true;
            case R.id.menu_borrar_datos_usuario:
                // borrar la BD, no hay vuelta atras

                final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);

                LayoutInflater inflater = this.getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_go_login_activity, null);

                dialogBuilder.setTitle(getResources().getString(R.string.go_login_activity_title));
                dialogBuilder.setMessage(getResources().getString(R.string.go_login_activity_message));
                dialogBuilder.setView(dialogView);

                dialogBuilder.setPositiveButton(getResources().getString(R.string.aceptar)
                        , new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                removeSharedPreferences_UserData();
                                setUpRealmConfig();
                                realm = Realm.getDefaultInstance();
                                realm.beginTransaction();
                                realm.deleteAll();
                                realm.commitTransaction();
                                realm.close();
                                changeUserProfile();
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setUpRealmConfig() {
        // configurar Realm config
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    private void changeUserProfile() {
        // abrir Login
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("opcion", 1);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removeSharedPreferences_UserData() {
        // borrar los valores de la Shared
        prefs.edit().clear().apply();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_flexiones) {
            // Handle the flexiones action
            openFlexiones();
        } else if (id == R.id.nav_abdominales) {
            // Handle the abdominales action
            openAbdominales();
        } else if (id == R.id.nav_fondos) {
            // Handle the fondos action
            openFondos();
        } else if (id == R.id.nav_sentadillas) {
            // Handle the sentadillas action
            openSentadillas();
        } else if (id == R.id.nav_dominadas) {
            // Handle the dominadas action
            openFondos();
        } else if (id == R.id.nav_gemelos) {
            // Handle the gemelos action
            openGemelos();
        } else if (id == R.id.nav_test_resistencia) {
            // Handle the gemelos action
            openStressTest();
        } else if (id == R.id.nav_about) {
            // Handle the gemelos action
            tituloOpcionAbierta = getResources().getString(R.string.action_acerca_de);
            Intent i = new Intent(this, AcercaDeActivity.class);
            startActivity(i);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openFlexiones() {
        tituloOpcionAbierta = getResources().getString(R.string.action_flexiones);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_FLEXIONES);
        startActivity(i);
    }

    public void openAbdominales() {
        tituloOpcionAbierta = getResources().getString(R.string.action_abdominales);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_ABDOMINALES);
        startActivity(i);
    }

    public void openFondos() {
        tituloOpcionAbierta = getResources().getString(R.string.action_fondos);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_FONDOS);
        startActivity(i);
    }

    public void openSentadillas() {
        tituloOpcionAbierta = getResources().getString(R.string.action_sentadillas);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_SENTADILLAS);
        startActivity(i);
    }

    public void openDominadas() {
        tituloOpcionAbierta = getResources().getString(R.string.action_dominadas);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_DOMINADAS);
        startActivity(i);
    }

    public void openGemelos() {
        tituloOpcionAbierta = getResources().getString(R.string.action_gemelos);
        Intent i = new Intent(this, PlantillaSeries.class);
        i.putExtra("serieType", Util.STRING_GEMELOS);
        startActivity(i);
    }

    public String toastMECCorto(String texto) {
        // presentacion de Toast personalizado para la app
        Toast toastInterno = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate((R.layout.toast_layout), (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView) layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto + "  ");
        toastInterno.setDuration(Toast.LENGTH_SHORT);
        toastInterno.setView(layout);
        toastInterno.show();
        return null;
    }

    public String toastMECLargo(String texto) {
        // presentacion de Toast personalizado para la app
        Toast toastInterno = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate((R.layout.toast_layout), (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView) layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto + "  ");
        toastInterno.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        toastInterno.setDuration(Toast.LENGTH_LONG);
        toastInterno.setView(layout);
        toastInterno.show();
        return null;
    }

    private void openStressTest() {
        tituloOpcionAbierta = getResources().getString(R.string.action_test_resistencia);
        Intent i = new Intent(this, TestResistencia.class);
        startActivity(i);
    }


}
