package com.daniel.hnd2.beans;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponsePersonajes implements Serializable {

    private int resultado;
    private ArrayList<PersonajeBean> personajes;

    public ResponsePersonajes() {
    }

    public ResponsePersonajes(int resultado, ArrayList<PersonajeBean> personajes) {
        this.resultado = resultado;
        this.personajes = personajes;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ArrayList<PersonajeBean> getPersonajes() {
        return personajes;
    }

    public void setPersonajes(ArrayList<PersonajeBean> personajes) {
        this.personajes = personajes;
    }

    public static ResponsePersonajes fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponsePersonajes.class);

        }else{

            return new ResponsePersonajes();

        }
    }

    public String toJson(){

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
