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
    protected int experiencia;

    public Tarea(String descripcion) {
        this(descripcion, false, 1);
    }

    public Tarea(String descripcion, boolean requiereRecursos) {
        this(descripcion, requiereRecursos, 1);
    }

    public Tarea(String descripcion, boolean requiereRecursos, int experiencia) {
        this.descripcion = descripcion;
        this.requiereRecursos = requiereRecursos;
        this.completada = false;
        this.experiencia = experiencia;
    }

    public boolean isRequiereRecursos() {
        return requiereRecursos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void ejecutar(Astronauta astronauta) {
        completada = true;
        astronauta.ganarExperiencia(experiencia);
    }
}