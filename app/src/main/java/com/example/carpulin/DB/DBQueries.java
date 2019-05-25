package com.example.carpulin.DB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.ViajeModelo;

import java.util.ArrayList;
import java.util.List;

public class DBQueries {
    public static boolean LoginConductor(String username, String password, Context context, boolean comingback){ //comingback es para iniciar automaticamente sesión
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT password FROM conductor WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).compareTo(password)==0) {
                db.close();
                return true;
            } else if (!comingback)Toast.makeText(context, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        } else if (!comingback)Toast.makeText(context, "No se encuentra registrado como Conductor", Toast.LENGTH_SHORT).show();
        db.close();
        return false;
    }

    public static boolean LoginPasajero(String username, String password, Context context, boolean comingback){ //comingback es para iniciar automaticamente sesión
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT password FROM pasajero WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            if (cursor.getString(0).compareTo(password)==0) {
                db.close();
                return true;
            } else if (!comingback)Toast.makeText(context, "Contraseña Incorrecta", Toast.LENGTH_SHORT).show();
        } else if(!comingback)Toast.makeText(context, "No se encuentra registrado como Pasajero", Toast.LENGTH_SHORT).show();
        db.close();
        return false;
    }

    public static boolean isConductorRegistrado(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT * FROM conductor WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static boolean isPasajeroRegistrado(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT * FROM pasajero WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static Conductor getConductor(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT username, nombre, password, correo, telefono, rut, sexo FROM conductor WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Conductor conductor = new Conductor(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            return conductor;
        }
        return null;
    }

    public static Pasajero getPasajero(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT username, nombre, password, correo, telefono, rut, sexo FROM pasajero WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Pasajero pasajero = new Pasajero(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6));
            return pasajero;
        }
        return null;
    }

    public static boolean isViajeIdOcupado(String ID, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT id FROM viaje WHERE id = '" + ID +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static List<ViajeModelo> getViajes(String origen, String destino, String fecha, Context context) {
        List<ViajeModelo> viajes = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();

        List<String> queries = new ArrayList<>();
        queries.add("SELECT * FROM viaje WHERE origen = '" + origen + "' AND destino = '" + destino + "'"); //tipoviaje 0
        queries.add("SELECT * FROM viaje WHERE origen = '" + origen + "' AND parada4 = '" + destino + "'"); //tipoviaje 1
        queries.add("SELECT * FROM viaje WHERE origen = '" + origen + "' AND parada3 = '" + destino + "'"); //2
        queries.add("SELECT * FROM viaje WHERE origen = '" + origen + "' AND parada2 = '" + destino + "'"); //3
        queries.add("SELECT * FROM viaje WHERE origen = '" + origen + "' AND parada1 = '" + destino + "'"); //4
        queries.add("SELECT * FROM viaje WHERE parada1 = '" + origen + "' AND destino = '" + destino + "'"); //5
        queries.add("SELECT * FROM viaje WHERE parada1 = '" + origen + "' AND parada4 = '" + destino + "'"); //6
        queries.add("SELECT * FROM viaje WHERE parada1 = '" + origen + "' AND parada3 = '" + destino + "'"); //7
        queries.add("SELECT * FROM viaje WHERE parada1 = '" + origen + "' AND parada2 = '" + destino + "'"); //8
        queries.add("SELECT * FROM viaje WHERE parada2 = '" + origen + "' AND destino = '" + destino + "'"); //9
        queries.add("SELECT * FROM viaje WHERE parada2 = '" + origen + "' AND parada4 = '" + destino + "'"); //10
        queries.add("SELECT * FROM viaje WHERE parada2 = '" + origen + "' AND parada3 = '" + destino + "'"); //11
        queries.add("SELECT * FROM viaje WHERE parada3 = '" + origen + "' AND destino = '" + destino + "'"); //12
        queries.add("SELECT * FROM viaje WHERE parada3 = '" + origen + "' AND parada4 = '" + destino + "'"); //13
        queries.add("SELECT * FROM viaje WHERE parada4 = '" + origen + "' AND destino = '" + destino + "'"); //14

        for(int i=0; i<queries.size();i++){
        Cursor cursor = db.rawQuery(queries.get(i), null);
        if (cursor.moveToFirst()) {
            do {
                viajes.add(new ViajeModelo(cursor.getString(0), //id
                        cursor.getString(29), //29=conductor
                        cursor.getString(1), //1=origen
                        cursor.getString(2), //2=destino
                        cursor.getString(3), //3=fechainicio
                        cursor.getString(4), //4=horainicio
                        cursor.getInt(24), //5=plazas
                        R.drawable.user,
                        cursor.getString(8), // parada1
                        cursor.getString(9), // parada2
                        cursor.getString(10), // parada3
                        cursor.getString(11), //parada4
                        i, //para calcular precio
                        cursor.getInt(20), //valor origen a parada1
                        cursor.getInt(21), //valor parada1 a parada2 u destino
                        cursor.getInt(22),
                        cursor.getInt(23),
                        cursor.getInt(5))); //valor parada 4 a destino
            } while (cursor.moveToNext());
        }
    }
        return viajes;
    }

    public static Viaje getfullViaje(String id, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT * FROM viaje WHERE id = '" + id +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Viaje viaje = new Viaje(cursor.getString(0), //id
                    cursor.getString(1), //origen
                    cursor.getString(2), //destino
                    cursor.getString(3), //fechainicio
                    cursor.getString(4), //horainicio
                    cursor.getInt(5), //valortotal
                    cursor.getString(6), //fechallegada
                    cursor.getString(7), //horallegada
                    cursor.getString(8), //parada1
                    cursor.getString(9), //parada2
                    cursor.getString(10), //parada3
                    cursor.getString(11), //parada4
                    cursor.getString(12), //fecha1
                    cursor.getString(13), //fecha2
                    cursor.getString(14), //fecha3
                    cursor.getString(15), //fecha4
                    cursor.getString(16), //hora1
                    cursor.getString(17), //hora2
                    cursor.getString(18), //hora3
                    cursor.getString(19), //hora4
                    cursor.getInt(20), //valor1
                    cursor.getInt(21), //valor2
                    cursor.getInt(22), //valor3
                    cursor.getInt(23), //valor4
                    cursor.getInt(24), //plazas1
                    cursor.getInt(25), //plazas2
                    cursor.getInt(26), //plazas3
                    cursor.getInt(27), //plazas4
                    cursor.getInt(28), //plazas5
                    cursor.getString(29) //conductor
                    );
            return viaje;
        }
        return null;
    }
}
