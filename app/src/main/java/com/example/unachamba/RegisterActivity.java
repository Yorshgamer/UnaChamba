package com.example.unachamba;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.unachamba.Connection.ConnetionBD;

import java.sql.Connection;

public class RegisterActivity extends AppCompatActivity {
EditText nomapellidos, email, telefono, usuario, pass;
Button resgitrar;
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
        resgitrar=findViewById(R.id.btnregistrar);
        ingresar=findViewById(R.id.lbliniciarsesion);
    }
}