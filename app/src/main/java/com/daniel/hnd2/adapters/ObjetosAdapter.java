package com.daniel.hnd2.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.hnd2.R;
import com.daniel.hnd2.beans.ObjetoBean;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ObjetosAdapter extends ArrayAdapter<ObjetoBean>{
    private Context context;
    private int resource;
    private List<ObjetoBean> objects;

    public ObjetosAdapter(Context context, int resource, List<ObjetoBean> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource = resource;
        this.objects=objects;
    }

    class ViewHolder{
        TextView txtItem;
        ImageView imgItem;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if(view==null){
            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(resource,null);
            viewHolder.txtItem = (TextView) view.findViewById(R.id.txtItem);
            viewHolder.imgItem = (ImageView) view.findViewById(R.id.imgItem);

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }

        ObjetoBean objetoBean = objects.get(position);

        viewHolder.txtItem.setText(objetoBean.getNombre());

        Picasso.with(context)
                .load(objetoBean.getImagenObjeto())
                .fit()
                .centerInside() /* Método para escalar la imagen y ajustarla */
                .into(viewHolder.imgItem);
        return view;
    }
}
