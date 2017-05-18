package com.daniel.hnd2.beans;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponseObjetos implements Serializable{

    private int resultado;
    private ArrayList<ObjetoBean> objetos;

    public ResponseObjetos() {
    }

    public ResponseObjetos(int resultado, ArrayList<ObjetoBean> objetos) {
        this.resultado = resultado;
        this.objetos = objetos;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ArrayList<ObjetoBean> getObjetos() {
        return objetos;
    }

    public void setObjetos(ArrayList<ObjetoBean> objetos) {
        this.objetos = objetos;
    }

    public static ResponseObjetos fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseObjetos.class);

        }else{

            return new ResponseObjetos();

        }
    }

    public String toJson(){

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
