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
        // Recurso temporal para proteger la nave
        mision.getRecursos().add(new Recurso("Escudo", gravedad));
    }
}
