package com.daniel.hnd2.test;

import com.daniel.hnd2.R;
import com.daniel.hnd2.beans.ObjetoBean;
import com.daniel.hnd2.beans.PersonajeBean;
import com.daniel.hnd2.beans.TipBean;

import java.util.ArrayList;


public class Modelo {

    public static ArrayList<ObjetoBean> getObjetos(){

        ArrayList<ObjetoBean> objetos = new ArrayList<>();

        objetos.add(new ObjetoBean(R.drawable.ametralladora,"Ametralladora", "Arma principal de nuestro personaje. Dispara de manera continuada con gran potencia para derribar a las naves enemigas."));
        objetos.add(new ObjetoBean(R.drawable.pistolalaser,"Pistola de Rayos Láser", "Pistola que emite rayos creados desde una base de moléculas neutro."));

        return objetos;

    }

    public static ArrayList<PersonajeBean> getPersonajes(){

        ArrayList<PersonajeBean> personajes = new ArrayList<>();

        personajes.add(new PersonajeBean("Nave x-wing", "Nave principal del videojuego, nuestro personaje", "Disparos desde sus cuatro alas", "Cañones en cada una de sus cuatro alas", R.drawable.xwing));
        personajes.add(new PersonajeBean("Nave de Star Trek", "El enemigo 'jefe' del videojuego, aparece después de derrotar a un número determinado de enemigos", "Rayos desde sus dos cañones", "Cañón", R.drawable.navestartrek));
        personajes.add(new PersonajeBean("Nave Piranha", "Nave del videojuego WipEout HD", "Diferentes disparos desde su parte delantera", "", R.drawable.piranhaprincipal));

        return personajes;

    }

    public static ArrayList<TipBean> getTips(){

        ArrayList<TipBean> tips = new ArrayList<>();

        tips.add(new TipBean("Nuevas naves", "En la última versión del videojuego ya están disponibles las 3 nuevas naves."));
        tips.add(new TipBean("Actualización 1.1", "Mejora en la fluidez del juego y arreglo de algunos bugs que se encontraron en la anterior versión."));

        return tips;

    }
}
