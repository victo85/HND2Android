package com.daniel.hnd2;

import android.content.Context;
import android.content.SharedPreferences;

import com.daniel.hnd2.beans.UsuarioBean;

/**
 * Created by ALUMNOS on 07/02/2017.
 */
public class Preferencias {
    private Context context;
    private final String PREFERENCIAS = "preferencias";
    private final String LOGIN = "login";
    private final String USUARIO = "usuario";

    public Preferencias(Context context) {
        this.context = context;
    }

    public void setUsuario(UsuarioBean usuario){ /* Método al que se le pasa un usuario para almacenarlo en preferencias */
        String usuarioJson = usuario.toJson();

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(USUARIO, usuarioJson);
        editor.commit();
    }

    public UsuarioBean getUsuario(){ /* Método que devuelve el usuario almacenado en preferencias */
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS,
                Context.MODE_PRIVATE);

        String usuarioJson = sharedPreferences.getString(USUARIO,"");
        return UsuarioBean.fromJson(usuarioJson);

    }

    public void setLogin(boolean login){ /* Método para indicarle a las preferencias si estamos logueados o no */
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(LOGIN,login);

        editor.commit();
    }

    public boolean isLogin(){ /* Método que nos devuelve como valor boleeano si el usuario se encuentra logueado o no */
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFERENCIAS,
                Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(LOGIN, false);
    }
}
