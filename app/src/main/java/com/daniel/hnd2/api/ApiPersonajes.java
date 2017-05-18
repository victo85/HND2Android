package com.daniel.hnd2.api;

import android.util.Log;

import com.daniel.hnd2.beans.PersonajeBean;
import com.daniel.hnd2.beans.ResponsePersonaje;
import com.daniel.hnd2.beans.ResponsePersonajes;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ApiPersonajes {

    private final String URL = "http://danielplata.esy.es/APIandroid/personajes/";
    private OkHttpClient client;

    public ApiPersonajes() {

        client = new OkHttpClient();

    }

    public ArrayList<PersonajeBean> getPersonajes(){

        try {

            Request request = new Request.Builder()
                    .url(URL)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponsePersonajes responsePersonajes = ResponsePersonajes.fromJson(json);

            return responsePersonajes.getPersonajes();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public PersonajeBean getPersonaje(int id){
        try {

            Request request = new Request.Builder()
                    .url(URL + id)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponsePersonaje responsePersonaje = ResponsePersonaje.fromJson(json);

            return responsePersonaje.getPersonaje();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
