package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import org.belosoft.mejorarencasa.R;
import org.belosoft.mejorarencasa.Utils.Util;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                final View viewInflated = LayoutInflater.from(this).inflate(R.layout.alert_dialog, null);
                builder.setTitle(getResources().getString(R.string.alert_dialog_aceptar_title) + " " + user);
                builder.setMessage(getResources().getString(R.string.alert_dialog_aceptar_message));
                builder.setPositiveButton(getResources().getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeSharedPreferences_UserData();
                        setUpRealmConfig();
                        realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.deleteAll();
                        realm.commitTransaction();
                        realm.close();
                        changeUserProfile();
                        toastMECCorto(user + " " + getResources().getString(R.string.alert_dialog_borrado));
                        //Toast.makeText(MainActivity.this, user + " " + getResources().getString(R.string.alert_dialog_borrado) , Toast.LENGTH_LONG ).show();
                    }
                })
                        .setNegativeButton(getResources().getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                toastMECCorto(getResources().getString(R.string.alert_dialog_cancelado));
                                //Toast.makeText(MainActivity.this, getResources().getString(R.string.alert_dialog_cancelado), Toast.LENGTH_LONG ).show();
                            }
                        }) ;
                builder.create();
                builder.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
            tituloOpcionAbierta = getResources().getString(R.string.action_flexiones);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_FLEXIONES);
            startActivity(i);
        } else if (id == R.id.nav_abdominales) {
            // Handle the abdominales action
            tituloOpcionAbierta = getResources().getString(R.string.action_abdominales);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_ABDOMINALES);
            startActivity(i);
        } else if (id == R.id.nav_fondos) {
            // Handle the fondos action
            tituloOpcionAbierta = getResources().getString(R.string.action_fondos);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_FONDOS);
            startActivity(i);
        } else if (id == R.id.nav_sentadillas) {
            // Handle the sentadillas action
            tituloOpcionAbierta = getResources().getString(R.string.action_sentadillas);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_SENTADILLAS);
            startActivity(i);
        } else if (id == R.id.nav_dominadas) {
            // Handle the dominadas action
            tituloOpcionAbierta = getResources().getString(R.string.action_dominadas);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_DOMINADAS);
            startActivity(i);
        } else if (id == R.id.nav_gemelos) {
            // Handle the gemelos action
            tituloOpcionAbierta = getResources().getString(R.string.action_gemelos);
            Intent i = new Intent(this, PlantillaSeries.class);
            i.putExtra("serieType", Util.STRING_GEMELOS);
            startActivity(i);
        } else if (id == R.id.nav_test_resistencia) {
            // Handle the gemelos action
            tituloOpcionAbierta = getResources().getString(R.string.action_test_resistencia);
            Intent i = new Intent(this, TestResistencia.class);
            startActivity(i);
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

    // configurar Realm config
    private void setUpRealmConfig() {
        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
    }
}
