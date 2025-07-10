/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Fuego extends Inconveniente {
    public Fuego(String nombre, int gravedad) {
        super(nombre, gravedad);
    }

    @Override
    public void afectarMision(Mision mision) {
        for (Astronauta astro : mision.getAstronautas()) {
            // daño a cada astronauta según la gravedad del fuego
            for (int i = 0; i < gravedad; i++) {
                astro.realizarTarea(new RepararModulo("Extinguir fuego"), mision);
            }
        }
    }
}