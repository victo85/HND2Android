package com.daniel.hnd2.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.hnd2.R;
import com.daniel.hnd2.adapters.PersonajesAdapter;
import com.daniel.hnd2.api.ApiPersonajes;
import com.daniel.hnd2.beans.PersonajeBean;
import com.daniel.hnd2.fragments.PersonajesFragment;


public class PersonajeActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgPersonaje;
    private TextView txtNombre, txtDescripcion, txtPoder, txtArma;
    private ImageButton btn_compartir, btn_twitter, btn_facebook, btn_whatsapp;

    private String detalle;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); /* Coloca el toolbar en esta actividad */

        PersonajeBean personajeBean = (PersonajeBean) getIntent().getSerializableExtra(PersonajesFragment.PERSONAJE_KEY);

        imgPersonaje = (ImageView) findViewById(R.id.imgPersonaje);
        txtNombre = (TextView) findViewById(R.id.txtNombre);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
        txtPoder = (TextView) findViewById(R.id.txtPoder);
        txtArma = (TextView) findViewById(R.id.txtArma);
        btn_compartir = (ImageButton) findViewById(R.id.btn_compartir);
        btn_twitter = (ImageButton) findViewById(R.id.btn_twitter);
        btn_facebook = (ImageButton) findViewById(R.id.btn_facebook);
        btn_whatsapp = (ImageButton) findViewById(R.id.btn_whatsapp);

        btn_compartir.setOnClickListener(this);
        btn_twitter.setOnClickListener(this);
        btn_facebook.setOnClickListener(this);
        btn_whatsapp.setOnClickListener(this);

        detalle = "Nombre: " + personajeBean.getNombre() + ", Descripcion: " + personajeBean.getDescripcion() + ", Poder: " + personajeBean.getPoder() + ", Arma: " + personajeBean.getArma();

        Hilo hilo = new Hilo();
        hilo.execute();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_compartir:
                Intent intentCompartir = new Intent(Intent.ACTION_SEND);
                intentCompartir.setType("text/plain");
                intentCompartir.putExtra(Intent.EXTRA_TEXT, detalle);
                startActivity(Intent.createChooser(intentCompartir, getString(R.string.compartir_en)));
                break;

            case R.id.btn_twitter:
                Intent intentTwitter = new Intent(Intent.ACTION_SEND);
                intentTwitter.setType("text/plain");
                intentTwitter.putExtra(Intent.EXTRA_TEXT, detalle);
                intentTwitter.setPackage("com.twitter.android");
                startActivity(intentTwitter);
                break;

            case R.id.btn_facebook:
                Intent intentFacebook = new Intent(Intent.ACTION_SEND);
                intentFacebook.setType("text/plain");
                intentFacebook.putExtra(Intent.EXTRA_TEXT, detalle);
                intentFacebook.setPackage("com.facebook.katana");
                startActivity(intentFacebook);
                break;

            case R.id.btn_whatsapp:
                Intent intentWhatsapp = new Intent(Intent.ACTION_SEND);
                intentWhatsapp.setType("text/plain");
                intentWhatsapp.putExtra(Intent.EXTRA_TEXT, detalle);
                intentWhatsapp.setPackage("com.whatsapp");
                startActivity(intentWhatsapp);
                break;
        }
    }

    private class Hilo extends AsyncTask<Integer, Void, PersonajeBean> {

        @Override
        protected PersonajeBean doInBackground(Integer... args) {

            int id = args[0];
            ApiPersonajes apiPersonajes = new ApiPersonajes();
            PersonajeBean personajeBean = apiPersonajes.getPersonaje(id);

            return personajeBean;
        }

        @Override
        protected void onPostExecute(PersonajeBean personajeBean) {
            super.onPostExecute(personajeBean);

            if(personajeBean == null){

                Toast.makeText(PersonajeActivity.this, "No se pudo realizar la petición", Toast.LENGTH_SHORT).show();

            }else{

                txtNombre.setText(personajeBean.getNombre());
                txtDescripcion.setText(personajeBean.getDescripcion());
                txtPoder.setText(personajeBean.getPoder());
                txtArma.setText(personajeBean.getArma());
                imgPersonaje.setImageDrawable(ContextCompat.getDrawable(PersonajeActivity.this,personajeBean.getImgPersonaje()));

            }

        }
    }

}
