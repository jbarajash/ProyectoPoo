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

    /**
     * Cantidad de comida disponible. Se deja de forma
     * pública para ilustrar una falta de encapsulación en
     * contraste con los demás recursos.
     */
    public double cantidad;

    /**
     * Crea la comida con la cantidad inicial indicada.
     *
     * @param cantidadInicial unidades de comida disponibles al comenzar
     */
    public Comida(double cantidadInicial) {
        super("Comida", cantidadInicial);
        this.cantidad = cantidadInicial;
    }

    @Override
    public void consumir(double cantidad) {
        super.consumir(cantidad);
        this.cantidad = getCantidad();
    }

    @Override
    public void reabastecer(double cantidad) {
        super.reabastecer(cantidad);
        this.cantidad = getCantidad();
    }
}