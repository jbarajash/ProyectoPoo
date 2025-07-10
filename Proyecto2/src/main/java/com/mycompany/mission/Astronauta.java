/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class Astronauta {
    private String nombre;
    private String rol;
    private int salud;
    private String estado = "Activo";
    private String cedula;
    private int posX;
    private int posY;
    private int experiencia;

    /** Nivel calculado a partir de la experiencia acumulada. */
    public int getNivel() {
        return experiencia / 5;
    }

    public Astronauta(String nombre, String rol, int salud, String cedula) {
        this.nombre = nombre;
        this.rol = rol;
        this.salud = salud;
        this.cedula = cedula;
        this.posX = 0;
        this.posY = 0;
        this.experiencia = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public int getSalud() {
        return salud;
    }

    public String getEstado() {
        return estado;
    }

    public String getCedula() {
        return cedula;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void enfermar(int gravedad) {
        this.estado = "Enfermo";
        this.salud -= gravedad;
    }

    public void curar(int mejora) {
        this.salud = Math.min(100, this.salud + mejora);
        this.estado = "Activo";
    }

    public void ganarExperiencia(int puntos) {
        this.experiencia += puntos;
    }

    public void realizarTarea(Tarea tarea) {
        tarea.ejecutar(this);
        if (tarea.isRequiereRecursos()) {
            salud -= 1;
        }
    }

    public void realizarTarea(Tarea tarea, Mision mision) {
        int nivelAnterior = getNivel();

        if (tarea instanceof TomarMuestras && mision != null && !mision.isEnPlaneta()) {
            mision.registrarEvento(nombre + " no puede tomar muestras fuera del planeta");
            return;
        }

        // Ejecutar la tarea otorgando experiencia
        tarea.ejecutar(this);

        if (tarea.isRequiereRecursos()) {
            salud -= 1;
            if (mision != null) {
                for (Recurso r : mision.getRecursos()) {
                    if (r.getTipo().equals("Comida")) {
                        try {
                            r.consumir(1);
                            mision.registrarEvento(nombre + " consumi\u00f3 comida");
                        } catch (IllegalArgumentException e) {
                            mision.registrarEvento("No hay comida suficiente para " + nombre);
                        }
                        break;
                    }
                }
            }
        }

        if (mision != null && tarea instanceof RepararModulo) {
            for (Recurso r : mision.getRecursos()) {
                if (r.getTipo().equals("Escudo")) {
                    r.reabastecer(1);
                    mision.registrarEvento(nombre + " repara el escudo");
                    break;
                }
            }
        }

        if (mision != null) {
            mision.registrarEvento(nombre + " ejecut\u00f3 " + tarea.getDescripcion());
            if (getNivel() > nivelAnterior) {
                mision.registrarEvento(nombre + " alcanz\u00f3 nivel " + getNivel());
            }
        }
    }

    /**
     * Mueve al astronauta una distancia relativa.\n
     * Este m\u00e9todo se podr\u00e1 conectar con la interfaz gr\u00e1fica en el futuro.
     */
    public void mover(int dx, int dy) {
        this.posX += dx;
        this.posY += dy;
    }

    public String reportarEstado() {
        return nombre + " (" + rol + ") - Salud: " + salud +
               " - Estado: " + estado +
               " - NIV: " + getNivel() +
               " - EXP: " + experiencia;
    }

    @Override
    public String toString() {
        return nombre;
    }
}