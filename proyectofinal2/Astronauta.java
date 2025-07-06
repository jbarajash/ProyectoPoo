/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal2;

/**
 *
 * @author 57324
 */
//abstracto pues, no existen los astronautas puros, deben tener una profesion
public abstract class Astronauta {
    String nombre;
    //estatico para poder usar esta referencia para trabajar con los arreglos
    static int numAstronautas;
    int cedula;
    int salud;
    //activo inactivo,enfermo
    String estado;
    // la tarea asignada al astronauta
    Tareas tarea;
    
    // constructor, por defecto un astronauta esta activo y tine la salud al maximo 
    public Astronauta(String nombre){
        numAstronautas+=1;
        this. nombre=nombre;
        this.cedula=numAstronautas;
        this.salud=100;
        this.estado="inactivo";
    } 
    // getters
    public String getNombre() {
        return nombre;
    }

    public int getCedula() {
        return cedula;
    }

    public int getSalud() {
        return salud;
    }

    public String getEstado() {
        return estado;
    }

    public static int getNumAstronautas() {
        return numAstronautas;
    }

    public Tareas getTarea() {
        return tarea;
    }
    
    // setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void setNumAstronautas(int numAstronautas) {
        Astronauta.numAstronautas = numAstronautas;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    //  to string metodo auxiliar para el reporte 

    @Override
    public String toString() {
        return "Astronauta{" + "nombre=" + nombre + ", cedula=" + cedula + ", salud=" + salud + ", estado=" + estado + ", tarea=" + tarea + '}';
    }
      
    public String reportarEstado(){
        return toString();
    }
    public void fueExpulsado(){
        this.estado = "inactivo";
    }
}
