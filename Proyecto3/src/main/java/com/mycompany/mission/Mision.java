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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// ObjetivoMision está en el mismo paquete

public class Mision {
    private String destino;
    private List<Recurso> recursos = new ArrayList<>();
    private List<Astronauta> astronautas = new ArrayList<>();
    private List<Tarea> tareas = new ArrayList<>();
    private List<Inconveniente> inconvenientes = new ArrayList<>();
    private List<String> bitacora = new ArrayList<>();
    private List<ObjetivoMision> objetivos = new ArrayList<>();
    /** Indica si la tripulación se encuentra actualmente en el planeta. */
    private boolean enPlaneta = true;

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

    public List<String> getBitacora() {
        return bitacora;
    }

    public List<ObjetivoMision> getObjetivos() {
        return objetivos;
    }

    public boolean isEnPlaneta() {
        return enPlaneta;
    }

    public void setEnPlaneta(boolean enPlaneta) {
        this.enPlaneta = enPlaneta;
        registrarEvento("La misi\u00f3n ahora est\u00e1 " + (enPlaneta ? "en el planeta" : "en \u00f3rbita"));
    }

    public void registrarEvento(String mensaje) {
        bitacora.add(mensaje);
    }

    public String iniciarMision() {
        String msg = "Misi\u00f3n hacia " + destino + " iniciada";
        registrarEvento(msg);
        return msg;
    }

    public void asignarTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public void resolverInconveniente(Inconveniente inc) {
        inc.afectarMision(this);
        inconvenientes.add(inc);
        registrarEvento("Inconveniente '" + inc.nombre + "' resuelto");
    }

    public int calcularPuntaje() {
        int completadas = 0;
        for (Tarea t : tareas) {
            if (t.isCompletada()) {
                completadas++;
            }
        }
        int puntaje = completadas * 10;
        int saludTotal = 0;
        for (Astronauta a : astronautas) {
            saludTotal += a.getSalud();
        }
        if (!astronautas.isEmpty()) {
            puntaje += saludTotal / astronautas.size();
        }
        double recursosTotal = 0;
        for (Recurso r : recursos) {
            recursosTotal += r.getCantidad();
        }
        puntaje += (int) recursosTotal;
        return puntaje;
    }

    public void evaluarObjetivos() {
        for (ObjetivoMision o : objetivos) {
            o.verificar(this);
        }
    }

    /** Guarda en un archivo la bitácora y el puntaje para
     *  mantener un historial entre ejecuciones. */
    public void guardarHistorial(String archivo) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println("=== Mision a " + destino + " ===");
            for (String e : bitacora) {
                pw.println(e);
            }
            pw.println("Puntaje: " + calcularPuntaje());
            pw.println();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}