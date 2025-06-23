/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Tarea {
    protected String descripcion;
    protected boolean requiereRecursos;
    protected boolean completada;

    public Tarea(String descripcion) {
        this(descripcion, false);
    }

    public Tarea(String descripcion, boolean requiereRecursos) {
        this.descripcion = descripcion;
        this.requiereRecursos = requiereRecursos;
        this.completada = false;
    }

    public boolean isRequiereRecursos() {
        return requiereRecursos;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void ejecutar(Astronauta astronauta) {
        completada = true;
    }
}