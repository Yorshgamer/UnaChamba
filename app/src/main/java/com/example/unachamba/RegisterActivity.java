package com.example.unachamba;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unachamba.Connection.ConnetionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RegisterActivity extends AppCompatActivity {
EditText nomapellidos, email, telefono, usuario, pass;
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

        nomapellidos=findViewById(R.id.txtnomapellidos);
        email=findViewById(R.id.txtemail);
        telefono=findViewById(R.id.txttelefono);
        usuario=findViewById(R.id.txtusuario);
        pass =findViewById(R.id.txtclave);
        registrar=findViewById(R.id.btnregistrar);
        ingresar=findViewById(R.id.lbliniciarsesion);

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegistrarUsuario();
            }
        });
    }
    public void RegistrarUsuario(){
    try {
        if(con==null){
            Toast.makeText(this, "Verifique su conexión", Toast.LENGTH_SHORT).show();
            return;
        }else {
            PreparedStatement stm = con.prepareStatement("INSERT INTO Usuarios VALUES(?,?,?,?,?)");
            stm.setString(1,nomapellidos.getText().toString());
            stm.setString(1,email.getText().toString());
            stm.setString(1,telefono.getText().toString());
            stm.setString(1,usuario.getText().toString());
            stm.setString(1,pass.getText().toString());
            stm.executeUpdate();

            Toast.makeText(RegisterActivity.this, "Registro Agregado Correctamente", Toast.LENGTH_SHORT).show();

            nomapellidos.setText("");
            email.setText("");
            telefono.setText("");
            usuario.setText("");
            pass.setText("");
        }
        }catch (Exception e){
        Log.e("Error de conexión",e.getMessage());

    }
    }
}