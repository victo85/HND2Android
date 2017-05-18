package com.daniel.hnd2.beans;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ResponseTips implements Serializable{

    private int resultado;
    private ArrayList<TipBean> tips;

    public ResponseTips() {
    }

    public ResponseTips(int resultado, ArrayList<TipBean> tips) {
        this.resultado = resultado;
        this.tips = tips;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }

    public ArrayList<TipBean> getTips() {
        return tips;
    }

    public void setTips(ArrayList<TipBean> tips) {
        this.tips = tips;
    }

    public static ResponseTips fromJson(String json){

        if(json != null && !json.isEmpty()){

            Gson gson = new Gson();
            return gson.fromJson(json, ResponseTips.class);

        }else{

            return new ResponseTips();

        }
    }

    public String toJson(){

        Gson gson = new Gson();
        return gson.toJson(this);

    }
}

