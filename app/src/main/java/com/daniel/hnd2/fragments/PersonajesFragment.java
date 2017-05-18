package com.daniel.hnd2.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daniel.hnd2.R;
import com.daniel.hnd2.activities.PersonajeActivity;
import com.daniel.hnd2.adapters.PersonajesAdapter;
import com.daniel.hnd2.api.ApiPersonajes;
import com.daniel.hnd2.beans.PersonajeBean;
import com.daniel.hnd2.test.Modelo;

import java.util.ArrayList;

public class PersonajesFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listPersonajes;
    private ArrayList<PersonajeBean> personajes;
    public static final String PERSONAJE_KEY="PERSONAJE_KEY";

    public PersonajesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_personajes, container, false);

        listPersonajes = (ListView) view.findViewById(R.id.listPersonajes);
        personajes = Modelo.getPersonajes();

        PersonajesAdapter adapter = new PersonajesAdapter(getActivity(), R.layout.item, personajes);
        listPersonajes.setAdapter(adapter);
        listPersonajes.setOnItemClickListener(this);

        Hilo hilo = new Hilo();
        hilo.execute();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public static PersonajesFragment newInstance() {
        return new PersonajesFragment();
    } /* Método utilizado para crear una instancia del fragment */

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
        PersonajeBean personajeBean = personajes.get(posicion);
        Intent intent = new Intent(getActivity(), PersonajeActivity.class);
        intent.putExtra(PERSONAJE_KEY, personajeBean);

        startActivity(intent);
    }

    private class Hilo extends AsyncTask<Void, Void, ArrayList<PersonajeBean>> {


        @Override
        protected ArrayList<PersonajeBean> doInBackground(Void... voids) {

            ApiPersonajes apiPersonajes = new ApiPersonajes();

            return apiPersonajes.getPersonajes();
        }

        @Override
        protected void onPostExecute(ArrayList<PersonajeBean> personajeBeen) {
            super.onPostExecute(personajeBeen);

            if(personajeBeen != null){
                personajes.clear();
                personajes.addAll(personajeBeen);

                PersonajesAdapter adapter = (PersonajesAdapter) listPersonajes.getAdapter();
                adapter.notifyDataSetChanged();
            }else{
                Toast.makeText(getActivity(), "No se ha realizado la petición", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
