/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Medico extends Astronauta {
    public Medico(String nombre, int salud, String cedula) {
        super(nombre, "Medico", salud, cedula);
    }

    public void curar(Astronauta a, Mision mision) {
        a.curar(10);
        if (mision != null) {
            mision.registrarEvento(getNombre() + " ha curado a " + a.getNombre());
        }
    }
}
