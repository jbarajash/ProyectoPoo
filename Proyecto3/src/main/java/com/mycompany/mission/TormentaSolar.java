/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */

public class TormentaSolar extends Inconveniente {
    public TormentaSolar(String nombre, int gravedad) {
        super(nombre, gravedad);
    }

    @Override
    public void afectarMision(Mision mision) {
        // La tormenta daña levemente el escudo existente.
        for (Recurso r : mision.getRecursos()) {
            if (r.getTipo().equals("Escudo") && r.getCantidad() > 0) {
                r.consumir(1);
                mision.registrarEvento("Tormenta solar debilita el escudo");
                return;
            }
        }
        // si no hay escudo registrado solo se guarda el evento
        mision.registrarEvento("Tormenta solar sin escudo activo");
    }
}