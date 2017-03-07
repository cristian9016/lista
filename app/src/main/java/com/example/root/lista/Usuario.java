package com.example.root.lista;

/**
 * Created by root on 14/02/17.
 */

public class Usuario {

    private String nombre;
    private String password;
    private String imagen;

    public Usuario() {
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario(String name,String pass,String img){
        nombre = name;
        password = pass;
        imagen = img;
    }
}
