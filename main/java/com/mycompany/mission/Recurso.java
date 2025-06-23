/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Recurso {
    private String tipo;
    private double cantidad;

    public Recurso(String tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void consumir(double cantidad) {
        if (cantidad > this.cantidad) {
            throw new IllegalArgumentException("No hay suficiente " + tipo + " para consumir");
        }
        this.cantidad -= cantidad;
    }

    public void reabastecer(double cantidad) {
        this.cantidad += cantidad;
    }
}