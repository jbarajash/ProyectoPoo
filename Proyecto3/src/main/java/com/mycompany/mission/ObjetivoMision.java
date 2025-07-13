/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class ObjetivoMision {
    private String descripcion;
    private boolean cumplido;

    public ObjetivoMision(String descripcion) {
        this.descripcion = descripcion;
        this.cumplido = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCumplido() {
        return cumplido;
    }

    public void verificar(Mision mision) {
        boolean completo = true;
        for (Tarea t : mision.getTareas()) {
            if (!t.isCompletada()) {
                completo = false;
                break;
            }
        }
        this.cumplido = completo;
    }
}