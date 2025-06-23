/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Comida extends Recurso {
    public double cantidad;

    public Comida(double cantidad) {
        super("Comida", cantidad);
        this.cantidad = cantidad;
    }

    @Override
    public void consumir(double cantidad) {
        super.consumir(cantidad);
        this.cantidad = super.getCantidad();
    }

    @Override
    public void reabastecer(double cantidad) {
        super.reabastecer(cantidad);
        this.cantidad = super.getCantidad();
    }
}
