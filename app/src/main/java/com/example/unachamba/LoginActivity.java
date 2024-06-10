package com.example.unachamba;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.unachamba.Connection.ConnetionBD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginActivity extends AppCompatActivity{
EditText usuario, Pass;
TextView lblRegistrar;
Button btnIngresar;
Connection con;

public LoginActivity(){
    ConnetionBD instanceConnetion = new ConnetionBD();
    con=instanceConnetion.connect();
}
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);

    usuario=(EditText) findViewById(R.id.txtUsuario);
    Pass=(EditText) findViewById(R.id.txtPass);
    lblRegistrar=(TextView) findViewById(R.id.lblRegistrar);
    btnIngresar=(Button) findViewById(R.id.button);

btnIngresar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        new LoginActivity.login().execute("");

    }
});

    lblRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new LoginActivity.login().execute("");
            Intent reg = new Intent(getApplicationContext(), RegisterActivity.class);
            startActivity(reg);
        }
    });
}
public class login extends AsyncTask<String,String,String>{
    String z=null;
    Boolean exito = false;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected String doInBackground(String... strings) {

        if (con==null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
                }
            });
            z="En conexion";
        }
        else {
            try {

                String sql="SELECT * FROM Usuarios WHERE userNombreUsuario = '"+usuario.getText()+"' AND userContrasena = '"+ Pass.getText()+ "'";
                Statement stm = con.createStatement();
                ResultSet rs= stm.executeQuery(sql);

                if (rs.next()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Acceso Exitoso", Toast.LENGTH_SHORT).show();
                            Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(menu);
                        }
                    });

                    usuario.setText("");
                    Pass.setText("");

                }
                else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LoginActivity.this, "Error en el usuario o contraseña", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }catch (Exception e){
                exito=false;
                Log.e("Error de conexión: ", e.getMessage());
            }
        }
        return z;
    }
}
}
