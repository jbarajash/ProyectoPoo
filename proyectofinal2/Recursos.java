/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal2;

/**
 *
 * @author 57324
 */
public abstract class Recursos {
    //nombre del recurso
    String nombre;
    // unico o acumulable
    String tipo;
    // total del recurso en cuestion
    double cantidad;

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
}
