package com.example.root.lista;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;


/**
 * Created by root on 14/02/17.
 */

public class ArticuloAdapter extends BaseAdapter {

    Context context;
    List<Articulo> data;

    public ArticuloAdapter(Context context, List<Articulo> data){
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v;
        if(convertView == null){
            v = View.inflate(context,R.layout.activity_items,null);
        }
        else v = convertView;

        Articulo u = (Articulo)getItem(position);

        TextView items = (TextView) v.findViewById(R.id.items);
        TextView cantidadItems = (TextView) v.findViewById(R.id.cantidadItems);
        TextView precioItems = (TextView) v.findViewById(R.id.precioItems);
        items.setText(u.getName());
        cantidadItems.setText(u.getCantidad());
        precioItems.setText(u.getTotal());

        return v;
    }
}
