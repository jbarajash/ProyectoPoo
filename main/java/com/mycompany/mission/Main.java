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
    public static void main(String[] args)throws java.io.UnsupportedEncodingException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        
        Mision mision = new Mision("Marte");

        // Añadir recursos iniciales
        mision.getRecursos().add(new Recurso("Oxígeno", 100));
        mision.getRecursos().add(new Comida(200));
        mision.getRecursos().add(new Recurso("Combustible", 500));

        // Crear y registrar astronautas
        Astronauta piloto = new Astronauta("Juan", "Piloto", 100, "A1");
        Astronauta cientifica = new Astronauta("María", "Científica", 100, "A2");
        mision.getAstronautas().add(piloto);
        mision.getAstronautas().add(cientifica);

        // Inicio de la misión
        System.out.println(mision.iniciarMision());

        // Asignar y ejecutar una tarea
        Tarea tomarFotos = new TomarFotos("Fotografiar superficie marciana");
        mision.asignarTarea(tomarFotos);
        cientifica.realizarTarea(tomarFotos);

        // Ocurre un inconveniente
        Inconveniente fuga = new FugaOxigeno("Fuga en módulo", 10);
        mision.resolverInconveniente(fuga);

        // Astronauta enferma
        Inconveniente enfermedad = new Enfermedad("Gripe espacial", 5, piloto);
        mision.resolverInconveniente(enfermedad);

        // Reportar estado de la tripulación
        for (Astronauta a : mision.getAstronautas()) {
            System.out.println(a.reportarEstado());
        }

        // Reportar cantidades restantes de recursos
        for (Recurso r : mision.getRecursos()) {
            System.out.println(r.getTipo() + ": " + r.getCantidad());
        }
    }
}