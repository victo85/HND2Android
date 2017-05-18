package com.daniel.hnd2.api;

import com.daniel.hnd2.beans.ObjetoBean;
import com.daniel.hnd2.beans.PersonajeBean;
import com.daniel.hnd2.beans.ResponseObjeto;
import com.daniel.hnd2.beans.ResponseObjetos;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ApiObjetos {

    private final String URL = "http://danielplata.esy.es/APIandroid/objetos/";
    private OkHttpClient client;

    public ApiObjetos() {

        client = new OkHttpClient();

    }

    public ArrayList<ObjetoBean> getObjetos(){

        try {

            Request request = new Request.Builder()
                    .url(URL)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseObjetos responseObjetos = ResponseObjetos.fromJson(json);

            return responseObjetos.getObjetos();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ObjetoBean getObjeto(int id){
        try {

            Request request = new Request.Builder()
                    .url(URL + id)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseObjeto responseObjeto = ResponseObjeto.fromJson(json);

            return responseObjeto.getObjeto();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
