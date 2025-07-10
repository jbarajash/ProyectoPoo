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
    private double capacidadInicial;

    public Recurso(String tipo, double cantidad) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.capacidadInicial = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getCapacidadInicial() {
        return capacidadInicial;
    }

    public void consumir(double cantidad) {
        if (cantidad > this.cantidad) {
            throw new IllegalArgumentException("No hay suficiente " + tipo + " para consumir");
        }
        this.cantidad -= cantidad;
    }

    /**
     * Aumenta el recurso sin superar su capacidad inicial.
     * Si la suma excede el máximo se ajusta al valor tope.
     */
    public void reabastecer(double cantidad) {
        this.cantidad = Math.min(capacidadInicial, this.cantidad + cantidad);
    }
}