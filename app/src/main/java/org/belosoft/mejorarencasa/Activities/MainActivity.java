package org.belosoft.mejorarencasa.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
        prefs = getSharedPreferences("Preferences",Context.MODE_PRIVATE);
        user = Util.getUserPreferences(prefs);
        age = Util.getAgePreferences(prefs);
        weight = Util.getWeightPreferences(prefs);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            public void onDrawerClosed(View view){
                // titulo navdrawer cerrado
                getSupportActionBar().setTitle(R.string.app_name);
            }
            public void onDrawerOpened(View view){
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
        if (tiempoPrimerClick + INTERVALO > System.currentTimeMillis()){
            super.onBackPressed();
            this.finish();
            return;
        } else {
            toastMEC(getResources().getString(R.string.salir));
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
        switch (item.getItemId()){
            case R.id.menu_logout:
                logOut();
                return  true;
            case R.id.menu_forget_logout:
                removeSharedPreferences();
                logOut();
                return  true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logOut(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void removeSharedPreferences() {
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
            tituloOpcionAbierta=getResources().getString(R.string.action_flexiones);
            Intent i = new Intent(this, Flexiones.class);
            startActivity(i);
        } else if (id == R.id.nav_abdominales) {
            // Handle the abdominales action
            tituloOpcionAbierta=getResources().getString(R.string.action_abdominales);
            Intent i = new Intent(this, Abdominales.class);
            startActivity(i);
        } else if (id == R.id.nav_fondos) {
            // Handle the fondos action
            tituloOpcionAbierta=getResources().getString(R.string.action_fondos);
            Intent i = new Intent(this, Fondos.class);
            startActivity(i);
        } else if (id == R.id.nav_sentadillas) {
            // Handle the sentadillas action
            tituloOpcionAbierta=getResources().getString(R.string.action_sentadillas);
            Intent i = new Intent(this, Sentadillas.class);
            startActivity(i);
        } else if (id == R.id.nav_dominadas) {
            // Handle the dominadas action
            tituloOpcionAbierta=getResources().getString(R.string.action_dominadas);
            Intent i = new Intent(this, Dominadas.class);
            startActivity(i);
        } else if (id == R.id.nav_gemelos) {
            // Handle the gemelos action
            tituloOpcionAbierta=getResources().getString(R.string.action_gemelos);
            Intent i = new Intent(this, Gemelos.class);
            startActivity(i);
        } else if (id == R.id.nav_share) {
            // Handle the gemelos action
            tituloOpcionAbierta=getResources().getString(R.string.action_configuracion);
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String toastMEC (String texto) {
        // presentacion de Toast personalizado para la app
        Toast toastInterno = new Toast(getApplicationContext());
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate((R.layout.toast_layout), (ViewGroup) findViewById(R.id.lytLayout));
        TextView txtMsg = (TextView) layout.findViewById(R.id.txtMensaje);
        txtMsg.setText(texto+"  ");
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

}
