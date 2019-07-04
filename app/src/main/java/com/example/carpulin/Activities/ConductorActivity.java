package com.example.carpulin.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carpulin.DB.DBQueries;
import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.R;

public class ConductorActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Conductor conductor;
    private TextView HeaderConductor_username;
    private TextView HeaderConductor_nombre;
    private ImageView HeaderConductor_foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conductor);
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

        conductor=(Conductor)getIntent().getSerializableExtra("conductor_entidad");

        View headView = navigationView.getHeaderView(0);
        HeaderConductor_nombre = (TextView)headView.findViewById(R.id.HeaderConductor_nombre);
        HeaderConductor_username = (TextView)headView.findViewById(R.id.HeaderConductor_username);
        HeaderConductor_foto = (ImageView)headView.findViewById(R.id.HeaderConductor_foto);
        HeaderConductor_username.setText(conductor.getUsername());
        HeaderConductor_nombre.setText(conductor.getNombre());
        HeaderConductor_foto.setImageResource(R.drawable.user);



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
        getMenuInflater().inflate(R.menu.conductor, menu);
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

        if (id == R.id.ConductorActivity_perfil){
            Intent PerfilConductorActivity = new Intent(this, PerfilConductorActivity.class);
            PerfilConductorActivity.putExtra("conductor_entidad", conductor);
            startActivity(PerfilConductorActivity);
        }
        else if (id == R.id.ConductorActivity_crearviaje){
            Intent CrearViajeActivity = new Intent(this, CrearViajeActivity.class);
            CrearViajeActivity.putExtra("conductor_entidad", conductor);
            startActivity(CrearViajeActivity);
        }
        else if (id == R.id.ConductorActivity_verviajes) {
            Intent MisViajesActivity = new Intent(this, MisViajesActivity.class);
            MisViajesActivity.putExtra("conductor_entidad", conductor);
            startActivity(MisViajesActivity);
        }
        else if (id == R.id.ConductorActivity_verreservas) {
            Intent ReservasSolicitadasActivity = new Intent(this, ReservasSolicitadasActivity.class);
            ReservasSolicitadasActivity.putExtra("conductor_entidad", conductor);
            startActivity(ReservasSolicitadasActivity);
        }
        else if (id == R.id.ConductorActivity_salir){
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
