package com.example.root.lista;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.root.lista.Adapters.UsuarioAdapter;
import com.example.root.lista.Java.Articulo;
import com.example.root.lista.Java.Usuario;
import com.example.root.lista.Net.HttpAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements HttpAsyncTask.HttpInterface{

    ProgressDialog pd;
    ListView list;
    List data = new ArrayList<Usuario>();
    UsuarioAdapter adapter;
    CheckBox box;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);

        pd = new ProgressDialog(this);

        pd.setMessage("Cargando...");
        pd.show();
        if(isNetDisponible()){
                HttpAsyncTask http = new HttpAsyncTask(HttpAsyncTask.GET,this);
                http.execute(getString(R.string.url));
        }else{
            pd.hide();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("No Internet Connection").setTitle("Alert").setCancelable(false)
                    .setNeutralButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            finish();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();

        }

       }

    @Override
    public void setResponse(String response) {
        pd.hide();
        String[] datos = null;
        String[] pwd = null;
        Log.i("RESPONSE",response);

        try {
            JSONArray arra =  new JSONArray(response);
            Usuario u;
            for(int i=0; i<arra.length();i++){
                JSONObject obj = arra.getJSONObject(i);
                String nombre = obj.getString("user");
                String password = obj.getString("password");
                String imagen = obj.getString("imagen");
                u = new Usuario(nombre,password,imagen);
                data.add(u);
            }
            adapter = new UsuarioAdapter(this, data);
            list.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView acumulador = (TextView) view.findViewById(R.id.cantidad);
                int acu = Integer.parseInt(acumulador.getText().toString())+1;
                acumulador.setText(String.valueOf(acu));
            }
        });


    }

    public void remove(View v){

        RelativeLayout parentRow = (RelativeLayout) v.getParent();
        TextView t = (TextView) parentRow.getChildAt(0);
        int id = v.getId();
        int cantidad = Integer.parseInt(t.getText().toString());

        if(id == R.id.remove){
            if(cantidad!=0){
                cantidad = cantidad - 1;
                t.setText(String.valueOf(cantidad));
            }
        }

    }

    public boolean isNetDisponible(){
        ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo actNetInfo = connManager.getActiveNetworkInfo();
        return (actNetInfo != null && actNetInfo.isConnected() && actNetInfo.isConnected());
    }

    public void reset(View v){
        int a = list.getChildCount();
        for(int i=0;i<a;i++){
            View x = list.getChildAt(i);
            TextView cantidad = (TextView) x.findViewById(R.id.cantidad);
            cantidad.setText("0");
        }
    }

    public void next(View v){
        int a = list.getChildCount();

        Articulo item = new Articulo();

        Intent intent = new Intent(this,Resumen.class);

        ArrayList<String> itemsNombre = new ArrayList<>();
        ArrayList<String> itemsTotal = new ArrayList<>();
        ArrayList<String> itemsCantidad = new ArrayList<>();

        for(int i=0;i<a;i++){
            View x = list.getChildAt(i);
            TextView cantidad = (TextView) x.findViewById(R.id.cantidad);


            int cant = Integer.parseInt(cantidad.getText().toString());

            if(cant!=0){
                TextView nombre = (TextView)x.findViewById(R.id.name);
                TextView precio = (TextView) x.findViewById(R.id.pass);
                int valor = Integer.parseInt(precio.getText().toString());
                int total = cant*valor;
                itemsNombre.add(nombre.getText().toString());
                itemsTotal.add(String.valueOf(total));
                itemsCantidad.add(String.valueOf(cant));
            }
        }
        intent.putExtra("itemsNombre",itemsNombre);
        intent.putExtra("itemsCantidad",itemsCantidad);
        intent.putExtra("itemsTotal",itemsTotal);
        startActivity(intent);
    }

}
