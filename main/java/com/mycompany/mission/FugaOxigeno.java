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
        for (Recurso recurso : mision.getRecursos()) {
            if (recurso.getTipo().equals("Oxígeno")) {
                recurso.consumir(gravedad);
            }
        }
    }
}
