package com.example.unachamba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Patterns;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.unachamba.Connection.ConnetionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterActivity extends AppCompatActivity {
EditText userNombres, userCorreoElectronico, userCelular, userNombreUsuario, userContrasena;
Button registrar;
TextView ingresar;
Connection con;

public RegisterActivity (){
    ConnetionBD instanceConnection = new ConnetionBD();
    con= instanceConnection.connect();
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        userNombres=findViewById(R.id.txtnomapellidos);
        userCorreoElectronico=findViewById(R.id.txtemail);
        userCelular=findViewById(R.id.txttelefono);
        userNombreUsuario=findViewById(R.id.txtusuario);
        userContrasena =findViewById(R.id.txtclave);
        registrar=findViewById(R.id.btnCancelar);
        ingresar=findViewById(R.id.lbliniciarsesion);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ingresar = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(ingresar);
                finish();
            }
        });
    }
    public void RegistrarUsuario(){
        try {
            // Validación de correo electrónico
            String email = userCorreoElectronico.getText().toString();
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validación de número de celular
            String celular = userCelular.getText().toString();
            if (!celular.matches("\\d{9}")) {
                Toast.makeText(this, "Ingrese un número de celular válido de 9 dígitos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (con == null) {
                Toast.makeText(this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
                return;
            } else {
                PreparedStatement stm = con.prepareStatement("INSERT INTO Usuarios VALUES(?,?,?,?,?)");
                stm.setString(1, userNombres.getText().toString());
                stm.setString(2, userCelular.getText().toString());
                stm.setString(3, userCorreoElectronico.getText().toString());
                stm.setString(4, userNombreUsuario.getText().toString());
                stm.setString(5, userContrasena.getText().toString());
                stm.executeUpdate();

                Toast.makeText(RegisterActivity.this, "Registro Agregado Correctamente", Toast.LENGTH_SHORT).show();

                userNombres.setText("");
                userCelular.setText("");
                userCorreoElectronico.setText("");
                userNombreUsuario.setText("");
                userContrasena.setText("");
            }
        } catch (Exception e) {
            Log.e("Error de conexión", e.getMessage());
        }
    }
}