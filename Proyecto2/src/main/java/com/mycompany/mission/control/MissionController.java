package com.mycompany.mission.control;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.mission.*;
import java.util.Random;

/**
 * Encapsula la lógica de la misión y notifica a la GUI mediante la
 * interfaz VentanaCallback.
 */
public class MissionController {

    public interface VentanaCallback {
        void log(String msg);
        void refrescar();
    }

    private final Mision mision;
    private final VentanaCallback gui;

    public MissionController(VentanaCallback gui) {
        this.gui = gui;
        this.mision = crearEscenarioBase();
        gui.log(mision.iniciarMision());
    }

    public Mision getMision() {
        return mision;
    }

    /** Asigna y ejecuta una tarea, notificando a la GUI. */
    public void realizarTarea(Astronauta a, Tarea t) {
        mision.asignarTarea(t);
        a.realizarTarea(t, mision);
        gui.log(a.getNombre() + " realizó " + t.getDescripcion());
        gui.refrescar();
    }

    /** Resuelve un inconveniente y lo registra. */
    public void resolverInconveniente(Inconveniente inc) {
        mision.resolverInconveniente(inc);
        gui.log("Inconveniente '" + inc.getNombre() + "' resuelto");
        gui.refrescar();
    }

    /**
     * Genera y aplica un inconveniente aleatorio.
     */
    public Inconveniente eventoAleatorio() {
        int pick = new Random().nextInt(4);
        Inconveniente inc = switch (pick) {
            case 0 -> new FugaOxigeno("Fuga espontánea", 5);
            case 1 -> new TormentaSolar("Tormenta solar", 3);
            case 2 -> new ImpactoMeteorito("Micro-meteorito", 1);
            default -> new Fuego("Incendio en módulo", 2);
        };
        mision.resolverInconveniente(inc);
        gui.log("Ocurre inconveniente: " + inc.getNombre());
        gui.refrescar();
        return inc;
    }

    private Mision crearEscenarioBase() {
        Mision m = new Mision("Marte");
        m.getRecursos().add(new Recurso("Oxígeno", 100));
        m.getRecursos().add(new Comida(200));
        m.getRecursos().add(new Recurso("Combustible", 500));
        m.getRecursos().add(new Recurso("Escudo", 5));

        m.getAstronautas().add(new Astronauta("Juan",   "Piloto",     100, "A1"));
        m.getAstronautas().add(new Medico    ("Luis",               100, "A3"));
        m.getAstronautas().add(new Astronauta("María",  "Científica", 100, "A2"));
        return m;
    }
}