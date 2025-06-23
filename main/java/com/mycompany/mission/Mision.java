/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
import java.util.ArrayList;
import java.util.List;

public class Mision {
    private String destino;
    private List<Recurso> recursos = new ArrayList<>();
    private List<Astronauta> astronautas = new ArrayList<>();
    private List<Tarea> tareas = new ArrayList<>();
    private List<Inconveniente> inconvenientes = new ArrayList<>();

    public Mision(String destino) {
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public List<Astronauta> getAstronautas() {
        return astronautas;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public List<Inconveniente> getInconvenientes() {
        return inconvenientes;
    }

    public String iniciarMision() {
        return "Misi\u00f3n hacia " + destino + " iniciada";
    }

    public void asignarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void resolverInconveniente(Inconveniente inc) {
        inc.afectarMision(this);
        inconvenientes.add(inc);
    }
}