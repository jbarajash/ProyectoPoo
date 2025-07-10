/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Enfermedad extends Inconveniente {
    private Astronauta astronauta;
    private int progreso = 0;

    public Enfermedad(String nombre, int gravedad, Astronauta astronauta) {
        super(nombre, gravedad);
        this.astronauta = astronauta;
    }

    @Override
    public void afectarMision(Mision mision) {
        if (progreso < gravedad) {
            astronauta.enfermar(1);
            progreso++;
            mision.registrarEvento(astronauta.getNombre() + " empeora por enfermedad " + nombre);
        }
    }
}