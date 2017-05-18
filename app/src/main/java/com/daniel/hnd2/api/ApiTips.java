package com.daniel.hnd2.api;

import com.daniel.hnd2.beans.ObjetoBean;
import com.daniel.hnd2.beans.ResponseObjeto;
import com.daniel.hnd2.beans.ResponseObjetos;
import com.daniel.hnd2.beans.ResponseTip;
import com.daniel.hnd2.beans.ResponseTips;
import com.daniel.hnd2.beans.TipBean;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ALUMNOS on 16/05/2017.
 */

public class ApiTips {

    private final String URL = "http://danielplata.esy.es/APIandroid/tips/";
    private OkHttpClient client;

    public ApiTips() {

        client = new OkHttpClient();

    }

    public ArrayList<TipBean> getTips(){

        try {

            Request request = new Request.Builder()
                    .url(URL)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseTips responseTips = ResponseTips.fromJson(json);

            return responseTips.getTips();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public TipBean getTip(int id){
        try {

            Request request = new Request.Builder()
                    .url(URL + id)
                    .build();

            Response response = client.newCall(request).execute();
            String json = response.body().string();
            ResponseTip responseTip = ResponseTip.fromJson(json);

            return responseTip.getTip();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
