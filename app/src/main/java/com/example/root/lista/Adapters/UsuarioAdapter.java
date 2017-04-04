package com.example.root.lista.Adapters;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.root.lista.R;
import com.example.root.lista.Java.Usuario;

import java.util.List;


/**
 * Created by root on 14/02/17.
 */

public class UsuarioAdapter extends BaseAdapter {

    Context context;
    List<Usuario> data;

    public UsuarioAdapter(Context context, List<Usuario> data){
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
            v = View.inflate(context, R.layout.activity_usuarios,null);
        }
        else v = convertView;

        Usuario u = (Usuario)getItem(position);

        TextView name = (TextView) v.findViewById(R.id.name);
        TextView pass = (TextView) v.findViewById(R.id.pass);
        ImageView image = (ImageView) v.findViewById(R.id.img);
        TextView cantidad = (TextView) v.findViewById(R.id.cantidad);
        cantidad.setText("0");
        byte[] decodedBytes = Base64.decode(u.getImagen(),0);
        Bitmap imagen = BitmapFactory.decodeByteArray(decodedBytes,0,decodedBytes.length);
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(null,imagen);
        roundedDrawable.setCornerRadius(imagen.getHeight());
        name.setText(u.getNombre());
        pass.setText(u.getPassword());
        image.setImageDrawable(roundedDrawable);

        return v;
    }
}
