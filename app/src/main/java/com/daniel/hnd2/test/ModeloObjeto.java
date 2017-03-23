package com.daniel.hnd2.test;

import com.daniel.hnd2.R;
import com.daniel.hnd2.beans.ObjetoBean;
import com.daniel.hnd2.beans.PersonajeBean;

import java.util.ArrayList;


public class ModeloObjeto {

    public static ArrayList<ObjetoBean> getObjetos(){

        ArrayList<ObjetoBean> objetos = new ArrayList<>();

        objetos.add(new ObjetoBean(R.drawable.ametralladora,"Ametralladora", "Arma principal de nuestro personaje. Dispara de manera continuada con gran potencia para derribar a las naves enemigas."));
        objetos.add(new ObjetoBean(R.drawable.ametralladora,"Pistola de Rayos Láser", "Pistola que emite rayos creados desde una base de moléculas neutro."));

        return objetos;

    }
}
