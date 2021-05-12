package Practica01;

import java.io.Serializable;

public class Producto implements Serializable{
    private int id;
    private String nombre;
    private float precio;
    private int existencias;

    public Producto(int id, String nombre, float precio, int existencias){
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.existencias = existencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExistencias() {
        return existencias;
    }

    public void setExistencias(int existencias) {
        this.existencias = existencias;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getInfo(){
        return Integer.toString(getId()) + " " + getNombre() + " " +
        Float.toString(getPrecio()) + " " + Integer.toString(getExistencias());
    }
}
