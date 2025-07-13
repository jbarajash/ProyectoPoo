/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */

public class Main {
    public static void main(String[] args) {
        Mision mision = new Mision("Marte");

        // Añadir recursos iniciales
        mision.getRecursos().add(new Recurso("Oxígeno", 100));
        mision.getRecursos().add(new Comida(200));
        mision.getRecursos().add(new Recurso("Combustible", 500));
        mision.getRecursos().add(new Recurso("Escudo", 5));

        // Crear y registrar astronautas
        Astronauta piloto = new Astronauta("Juan", "Piloto", 100, "A1");
        Medico medico = new Medico("Luis", 100, "A3");
        Astronauta cientifica = new Astronauta("María", "Científica", 100, "A2");
        mision.getAstronautas().add(piloto);
        mision.getAstronautas().add(medico);
        mision.getAstronautas().add(cientifica);

        // Definir objetivo
        mision.getObjetivos().add(new ObjetivoMision("Completar todas las tareas"));

        // Inicio de la misión
        System.out.println(mision.iniciarMision());

        // En órbita no se permiten muestras
        mision.setEnPlaneta(false);
        Tarea tomarMuestra = new TomarMuestras("Recolectar suelo marciano");
        mision.asignarTarea(tomarMuestra);
        cientifica.realizarTarea(tomarMuestra, mision);

        // Llegada al planeta
        mision.setEnPlaneta(true);
        cientifica.realizarTarea(tomarMuestra, mision);

        // Asignar y ejecutar una tarea
        Tarea tomarFotos = new TomarFotos("Fotografiar superficie marciana");
        mision.asignarTarea(tomarFotos);
        cientifica.realizarTarea(tomarFotos, mision);

        // Ocurre un inconveniente
        Inconveniente fuga = new FugaOxigeno("Fuga en módulo", 10);
        mision.resolverInconveniente(fuga);

        // Astronauta enferma
        Inconveniente enfermedad = new Enfermedad("Gripe espacial", 5, piloto);
        mision.resolverInconveniente(enfermedad);
        medico.curar(piloto, mision);

        // Evaluar objetivos y puntaje
        mision.evaluarObjetivos();
        int puntaje = mision.calcularPuntaje();
        System.out.println("Puntaje final: " + puntaje);

        // Mostrar bitácora
        for (String evento : mision.getBitacora()) {
            System.out.println("LOG: " + evento);
        }

        // Reportar estado de la tripulación
        for (Astronauta a : mision.getAstronautas()) {
            System.out.println(a.reportarEstado());
        }

        // Reportar cantidades restantes de recursos
        for (Recurso r : mision.getRecursos()) {
            System.out.println(r.getTipo() + ": " + r.getCantidad());
        }

        mision.guardarHistorial("historial.txt");
    }
}