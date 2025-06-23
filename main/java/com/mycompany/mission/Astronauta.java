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

    public Astronauta(String nombre, String rol, int salud, String cedula) {
        this.nombre = nombre;
        this.rol = rol;
        this.salud = salud;
        this.cedula = cedula;
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

    public void enfermar(int gravedad) {
        this.estado = "Enfermo";
        this.salud -= gravedad;
    }

    public void realizarTarea(Tarea tarea) {
        tarea.ejecutar(this);
        if (tarea.isRequiereRecursos()) {
            salud -= 1;
        }
    }

    public String reportarEstado() {
        return nombre + " (" + rol + ") - Salud: " + salud + " - Estado: " + estado;
    }
}