package com.example.unachamba.Connection;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnetionBD {
    private String ip = "19jakdashk3";
    private String usuario = "sa";
    private String password = "jkkjfs";
    private String baseDatos = "sdfkjjk";


    @SuppressLint("NewApi")
    public Connection connect() {
        Connection connection = null;
        String connectionURL = null;

        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + this.ip + ":1433/" + this.baseDatos + ";user=" + this.usuario + ";password=" + this.password + ";";
            connection = DriverManager.getConnection(connectionURL);

        } catch (ClassNotFoundException e) {
            Log.e("Error de conexión SQL: ", "Controlador no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            Log.e("Error de conexión SQL: ", "Error de SQL: " + e.getMessage());
        } catch (Exception e) {
            Log.e("Error de conexión SQL: ", "Error desconocido: " + e.getMessage());
        }
        return connection;
    }
    
}
