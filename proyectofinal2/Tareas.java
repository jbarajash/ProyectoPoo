/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal2;

/**
 *
 * @author 57324
 */
public abstract class Tareas {
    String nombre;
    String descripcion;
    boolean Completada;
    //constructor
    public Tareas(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    //getters
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return Completada;
    }

    
    //setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setCompletada(boolean Completada) {
        this.Completada = Completada;
    }

    @Override
    public String toString() {
        return "Tarea:" + "nombre=" + nombre + ", descripcion=" + descripcion + ", Completada=" + Completada ;
    }

    

   
    
}
