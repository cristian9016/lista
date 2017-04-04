package com.example.root.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.root.lista.Adapters.ArticuloAdapter;
import com.example.root.lista.Java.Articulo;

import java.util.ArrayList;
import java.util.List;

public class Resumen extends AppCompatActivity {

    ArticuloAdapter adapter;
    List data = new ArrayList<Articulo>();
    List data1 = new ArrayList<Articulo>();
    ListView listaResumen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumen);

        listaResumen = (ListView) findViewById(R.id.list);
        inicioVectores();

        adapter = new ArticuloAdapter(this,data);
        listaResumen.setAdapter(adapter);

    }

    public void inicioVectores(){
        ArrayList<String> itemsNombre = (ArrayList<String>) getIntent().getSerializableExtra("itemsNombre");
        ArrayList<String> itemsCantidad = (ArrayList<String>) getIntent().getSerializableExtra("itemsCantidad");
        ArrayList<String> itemsTotal = (ArrayList<String>) getIntent().getSerializableExtra("itemsTotal");

        for(int i=0;i<itemsCantidad.size();i++){
            data.add(new Articulo(itemsNombre.get(i),itemsTotal.get(i),itemsCantidad.get(i)));
        }
    }

}
