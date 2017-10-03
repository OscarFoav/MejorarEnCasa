package org.belosoft.mejorarencasa.Activities;

import android.content.Intent;
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

import org.belosoft.mejorarencasa.Preferences.Preferencias;
import org.belosoft.mejorarencasa.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public CharSequence tituloOpcionAbierta;

    // para salir de la app pulsando 2 veces retroceso
    private static final int INTERVALO = 2000; // 2 segundos de plazo
    private long tiempoPrimerClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        // DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // if (drawer.isDrawerOpen(GravityCompat.START)) {
        //     drawer.closeDrawer(GravityCompat.START);
        // } else {
        //     super.onBackPressed();
        // }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
            Intent i = new Intent(this, Preferencias.class);
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
