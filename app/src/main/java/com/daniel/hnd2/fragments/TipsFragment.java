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
import com.daniel.hnd2.activities.TipActivity;
import com.daniel.hnd2.adapters.TipsAdapter;
import com.daniel.hnd2.api.ApiTips;
import com.daniel.hnd2.beans.TipBean;
import com.daniel.hnd2.test.Modelo;

import java.util.ArrayList;

public class TipsFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ArrayList<TipBean> tips;
    private ListView listTips;
    public static final String TIP_KEY="TIP_KEY";

    public TipsFragment() {
        // Required empty public constructor
    }

    public static TipsFragment newInstance(){
        return new TipsFragment();
    } /* Método utilizado para crear una instancia del fragment */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tips, container, false);

        listTips = (ListView) view.findViewById(R.id.listTips);
        tips = Modelo.getTips();

        TipsAdapter adapter = new TipsAdapter(getActivity(), R.layout.item_tip, tips);
        listTips.setAdapter(adapter);
        listTips.setOnItemClickListener(this);

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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int posicion, long id) {
        TipBean tipBean = tips.get(posicion);
        Intent intent = new Intent(getActivity(), TipActivity.class);
        intent.putExtra(TIP_KEY, tipBean);

        startActivity(intent);
    }
    private class Hilo extends AsyncTask<Void, Void, ArrayList<TipBean>> {


        @Override
        protected ArrayList<TipBean> doInBackground(Void... voids) {

            ApiTips apiTips = new ApiTips();

            return apiTips.getTips();
        }

        @Override
        protected void onPostExecute(ArrayList<TipBean> tipBeen) {
            super.onPostExecute(tipBeen);

            if (tipBeen != null) {
                tips.clear();
                tips.addAll(tipBeen);

                TipsAdapter adapter = (TipsAdapter) listTips.getAdapter();
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(), "No se ha realizado la petición", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
