/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class FugaOxigeno extends Inconveniente {
    public FugaOxigeno(String nombre, int gravedad) {
        super(nombre, gravedad);
    }

    @Override
    public void afectarMision(Mision mision) {
        Recurso escudo = null;
        for (Recurso recurso : mision.getRecursos()) {
            if (recurso.getTipo().equals("Oxígeno")) {
                recurso.consumir(gravedad);
                mision.registrarEvento(
                    "Fuga de oxígeno consume " + gravedad + " unidades");
            }
            if (recurso.getTipo().equals("Escudo")) {
                escudo = recurso;
            }
        }
        if (escudo != null && escudo.getCantidad() > 0) {
            escudo.consumir(1);
            mision.registrarEvento("Escudo reducido por la fuga de oxígeno");
        }
    }
}