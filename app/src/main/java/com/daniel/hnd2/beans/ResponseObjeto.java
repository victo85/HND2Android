package com.daniel.hnd2.beans;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponseObjeto implements Serializable{

    private int resultado;
    private ObjetoBean objeto;

    public ResponseObjeto() {
    }

    public ResponseObjeto(int resultado, ObjetoBean objeto) {
        this.resultado = resultado;
        this.objeto = objeto;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ObjetoBean getObjeto() {
        return objeto;
    }

    public void setObjeto(ObjetoBean objeto) {
        this.objeto = objeto;
    }

    public static ResponseObjeto fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseObjeto.class);

        }else{

            return new ResponseObjeto();

        }
    }

    public String toJson(){

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
