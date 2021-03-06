package com.daniel.hnd2.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daniel.hnd2.Preferencias;
import com.daniel.hnd2.R;
import com.daniel.hnd2.beans.UsuarioBean;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editNombre, editApellidos, editUsuario, editPassword;
    private Button btn_registro;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); /* Coloca el toolbar en esta actividad */

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellidos = (EditText) findViewById(R.id.editApellidos);
        editUsuario = (EditText) findViewById(R.id.editUsuario);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btn_registro = (Button) findViewById(R.id.btn_registro);

        btn_registro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String usuario = editUsuario.getText().toString();
        String nombre = editNombre.getText().toString();
        String apellidos = editApellidos.getText().toString();
        String password = editPassword.getText().toString();


        if(usuario!=null && nombre!=null && apellidos!=null && password!=null &&
                !usuario.isEmpty() && !nombre.isEmpty() && !apellidos.isEmpty() && !password.isEmpty()){

            UsuarioBean usuarioBean = new UsuarioBean(nombre, apellidos, usuario, password);

            Preferencias preferencias = new Preferencias(RegistroActivity.this);
            preferencias.setUsuario(usuarioBean);

            Toast.makeText(RegistroActivity.this,
                    R.string.usuario_guardado,
                    Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(RegistroActivity.this,
                    R.string.datos_requeridos,
                    Toast.LENGTH_SHORT).show();
        }
    }
}
