package com.example.root.lista;

/**
 * Created by root on 22/02/17.
 */

public class Articulo {

    private String name;
    private String total;
    private String cantidad;

    public Articulo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Articulo(String name, String total, String cantidad) {
        this.name = name;
        this.total = total;
        this.cantidad = cantidad;
    }
}
