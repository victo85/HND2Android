package com.daniel.hnd2.beans;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponseTip implements Serializable{

    private int resultado;
    private TipBean tip;

    public ResponseTip() {
    }

    public ResponseTip(int resultado, TipBean tip) {
        this.resultado = resultado;
        this.tip = tip;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public TipBean getTip() {
        return tip;
    }

    public void setTip(TipBean tip) {
        this.tip = tip;
    }

    public static ResponseTip fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseTip.class);

        }else{

            return new ResponseTip();

        }
    }

    public String toJson(){

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}
