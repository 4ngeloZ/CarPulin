package com.example.carpulin.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.AdminSQLiteOpenHelper;
import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.R;

public class PasajeroActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Pasajero pasajero;
    private TextView HeaderPasajero_username;
    private TextView HeaderPasajero_nombre;
    private ImageView HeaderPasajero_foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasajero);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        pasajero=(Pasajero)getIntent().getSerializableExtra("pasajero_entidad");

        View headView = navigationView.getHeaderView(0);
        HeaderPasajero_nombre = (TextView)headView.findViewById(R.id.HeaderPasajero_username);
        HeaderPasajero_username = (TextView)headView.findViewById(R.id.HeaderPasajero_nombre);
        HeaderPasajero_foto = (ImageView)headView.findViewById(R.id.HeaderPasajero_foto);
        HeaderPasajero_username.setText(pasajero.getUsername());
        HeaderPasajero_nombre.setText(pasajero.getNombre());
        HeaderPasajero_foto.setImageResource(R.drawable.user);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pasajero, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.PasajeroActivity_perfil){
            Intent PerfilPasajeroActivity = new Intent(this, PerfilPasajeroActivity.class);
            PerfilPasajeroActivity.putExtra("pasajero_entidad", pasajero);
            startActivity(PerfilPasajeroActivity);
        }
        else if (id == R.id.PasajeroActivity_buscarviaje){
            Intent BuscarViajeActivity = new Intent(this, BuscarViajeActivity.class);
            BuscarViajeActivity.putExtra("pasajero_entidad", pasajero);
            startActivity(BuscarViajeActivity);
        }
        else if (id == R.id.PasajeroActivity_verreservas) {
            Intent MisReservasActivity = new Intent(this, MisReservasActivity.class);
            MisReservasActivity.putExtra("pasajero_entidad", pasajero);
            startActivity(MisReservasActivity);
            //Toast.makeText(this, Integer.toString(DBQueries.getMisReservas(pasajero.getUsername(),this).size()),Toast.LENGTH_SHORT).show();
        }
        else if (id == R.id.PasajeroActivity_salir){
            SharedPreferences sharedPreferences;
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("AutoLogin", false);
            editor.apply();
            Intent MainActivity = new Intent(this, com.example.carpulin.Activities.MainActivity.class);
            startActivity(MainActivity);
            this.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
