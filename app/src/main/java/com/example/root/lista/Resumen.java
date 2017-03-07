package com.example.root.lista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout.simple_list_item_1;

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
        String[] g = {"cristian","laura"};
        ArrayAdapter adapter1 = new ArrayAdapter(this,simple_list_item_1,g);
        adapter = new ArticuloAdapter(this,data1);
        listaResumen.setAdapter(adapter1);
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
