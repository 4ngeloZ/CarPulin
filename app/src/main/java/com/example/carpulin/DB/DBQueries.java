package com.example.carpulin.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.carpulin.Entidades.Conductor;
import com.example.carpulin.Entidades.Pasajero;
import com.example.carpulin.Entidades.Viaje;
import com.example.carpulin.R;
import com.example.carpulin.ReservaModelo;
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
        String query = "SELECT username, nombre, password, correo, telefono, rut, sexo, preferencias FROM pasajero WHERE username = '" + username +"'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()){
            Pasajero pasajero = new Pasajero(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
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

    public static boolean isReservaIdOcupado(String ID, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT id FROM reserva WHERE id = '" + ID +"'";
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

            int p=0;
            if (i==0){
                p=cursor.getInt(24);
                if (p>cursor.getInt(25))p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
                if (p>cursor.getInt(28))p=cursor.getInt(28);
            }
            else if (i==1){
                p=cursor.getInt(24);
                if (p>cursor.getInt(25))p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
            }
            else if (i==2){
                p=cursor.getInt(24);
                if (p>cursor.getInt(25))p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
            }
            else if (i==3){
                p=cursor.getInt(24);
                if (p>cursor.getInt(25))p=cursor.getInt(25);
            }
            else if (i==4)p=cursor.getInt(24);
            else if (i==5){
                p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
                if (p>cursor.getInt(28))p=cursor.getInt(28);
            }
            else if (i==6){
                p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
            }
            else if (i==7){
                p=cursor.getInt(25);
                if (p>cursor.getInt(26))p=cursor.getInt(26);
            }
            else if (i==8)p=cursor.getInt(25);
            else if (i==9){
                p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
                if (p>cursor.getInt(28))p=cursor.getInt(28);
            }
            else if (i==10){
                p=cursor.getInt(26);
                if (p>cursor.getInt(27))p=cursor.getInt(27);
            }
            else if (i==11)p=cursor.getInt(26);
            else if (i==12){
                p=cursor.getInt(27);
                if (p>cursor.getInt(28))p=cursor.getInt(28);
            }
            else if (i==13)p=cursor.getInt(27);
            else if (i==14)p=cursor.getInt(28);

            do {
                viajes.add(new ViajeModelo(cursor.getString(0), //id
                        cursor.getString(29), //29=conductor
                        cursor.getString(1), //1=origen
                        cursor.getString(2), //2=destino
                        cursor.getString(3), //3=fechainicio
                        cursor.getString(4), //4=horainicio
                        p, //plazas
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

    public static List<ReservaModelo> getReservas(String username, Context context){
        List<ReservaModelo> reservas = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT reserva.id, viaje.id, reserva.username, viaje.origen, viaje.destino, reserva.origen, " +
                "reserva.destino, reserva.plazas, viaje.fechainicio, viaje.horainicio, reserva.valor, reserva.procesada " +
                "FROM reserva " +
                "INNER JOIN viaje ON reserva.idviaje = viaje.id " +
                "INNER JOIN conductor ON viaje.conductor = conductor.username " +
                "WHERE conductor.username = '" + username + "' AND reserva.procesada = 0";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                reservas.add(new ReservaModelo(cursor.getString(0), //id
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6),
                        cursor.getString(8),
                        cursor.getString(9),
                        cursor.getInt(7),
                        R.drawable.user,
                        cursor.getInt(10)));
            } while (cursor.moveToNext());
        }
        return reservas;
    }

    public static void ReservaAceptada(String idReserva, String idViaje, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "UPDATE reserva SET procesada = 1 WHERE id = '" + idReserva + "'";
        db.execSQL(query);

        query = "SELECT plazas1, plazas2, plazas3, plazas4, plazas5 " +
                "FROM viaje " +
                "WHERE id = '" + idViaje + "'";
        Cursor cursor = db.rawQuery(query, null);

        int p1=0;
        int p2=0;
        int p3=0;
        int p4=0;
        int p5=0;

        if (cursor.moveToFirst()){
            p1=cursor.getInt(0);
            p2=cursor.getInt(1);
            p3=cursor.getInt(2);
            p4=cursor.getInt(3);
            p5=cursor.getInt(4);
        }

        query = "SELECT plazas1, plazas2, plazas3, plazas4, plazas5 " +
                "FROM reserva " +
                "WHERE id = '" + idReserva + "'";

        Cursor cursor2 = db.rawQuery(query, null);

        if (cursor2.moveToFirst()){
            p1-=cursor2.getInt(0);
            p2-=cursor2.getInt(1);
            p3-=cursor2.getInt(2);
            p4-=cursor2.getInt(3);
            p5-=cursor2.getInt(4);
        }

        ContentValues cv = new ContentValues();
        cv.put("plazas1", p1);
        cv.put("plazas2", p2);
        cv.put("plazas3", p3);
        cv.put("plazas4", p4);
        cv.put("plazas5", p5);

        db.update("viaje", cv, "id = '" + idViaje +"'", null);

        /*Toast.makeText(context, Integer.toString(p1) + " " +
                Integer.toString(p2) + " " +
                Integer.toString(p3) + " " +
                Integer.toString(p4) + " " +
                Integer.toString(p5) + " ",Toast.LENGTH_SHORT).show();*/

    }

    public static void ReservaRechazada(String id, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "UPDATE reserva SET procesada = 2 WHERE id = '" + id + "'";
        db.execSQL(query);
    }

    public static void ProbandoReserva(String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "SELECT * FROM reserva WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        int i=0;
        if (cursor.moveToFirst()){
            i++;
            while(cursor.moveToNext())i++;
        }
        if(i==0) Toast.makeText(context, "No hay reservas con ese usuario", Toast.LENGTH_SHORT).show();
        else Toast.makeText(context, "Cantidad de reservas: " + Integer.toString(i), Toast.LENGTH_SHORT).show();
    }

    public static boolean actualizarCorreoPasajero(String nuevocorreo, String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "UPDATE pasajero SET correo = '" + nuevocorreo + "' WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static boolean actualizarTelefonoPasajero(String nuevotelefono, String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "UPDATE pasajero SET telefono = '" + nuevotelefono + "' WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }

    public static boolean actualizarPreferenciasPasajero(String nuevaspreferencias, String username, Context context){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(context, "db", null, 1);
        SQLiteDatabase db = admin.getWritableDatabase();
        String query = "UPDATE pasajero SET preferencias = '" + nuevaspreferencias + "' WHERE username = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }
}
