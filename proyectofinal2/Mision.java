/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal2;

import java.util.ArrayList;

/**
 *
 * @author 57324
 */
public class Mision {
    String destino;
    //un numero limitado de astronautas que puedes llevar a la mision, este es limitado pues la nave no tiene espacio infinito
    Astronauta[] tripulacion=new Astronauta[4];
    // pueden ser expulsados de la nave tantos como tripulacion puede haber
    Astronauta[] expulsados= new Astronauta[4];
    // el tiempo que ha durado la mision desde que fue iniciada 
    static int tiempo;
    // el numero de inconvenientes es virtualmente infinito , pues pueden ocurrir tantos como dias dure la mision
    ArrayList<Inconvenientes> inconvenientes= new ArrayList<>();
    // cada mision tiene 5 tareas para completar
    Tareas[] tareas= new Tareas[4];
    //hay un numero limitado de cosas que puedes llevar a la mision
    Recursos[] recursos=new Recursos[6];
    // constructor
    public Mision(String destino) {
        this.destino = destino;
    }
    //añadir la tripulacion a la nave antes de empezar la mision, recibiendo como parametro el astronauta en especifico que deseas agregar
    public void añadirTripulacion(Astronauta astronauta,int j){
        //la posicion coincide con el numero de la cedula -1
        tripulacion[j]=astronauta;        
    }
    //basicamente una copia del metodo de arriba
    public void añadirExpulsados(Astronauta astronauta){
        //la posicion coincide con el numero de la cedula -1
        expulsados[astronauta.cedula-1]=astronauta;        
    }
    // La clase mision es basicamente la base de control,el sigiente metodo asigna a un astronauta una tarea para realizar, i=cedula-1, j es el indicador de la tarea a realizar
    public void asignarTarea(int i,int j){
        tripulacion[i].tarea=tareas[j];
    }
    // añade a la lista de tareas las tareas que le envian
    public void añadirTareas(Tareas tarea,int i){
        tareas[i]=tarea;
    }
    //metodo para agregar a la mision los recursos elegidos, el indice sera entregado por un ciclo de guardado en el main
    public void añadirRecursos(Recursos recurso, int i){
        recursos[i]=recurso;
    }
    // expulsa a l tripulante, lo añade a la lista de expulsados
    public void expulsarTripulante(int i){
        tripulacion[i].fueExpulsado();
        añadirExpulsados(tripulacion[i]);
    }
    //getters
    public String getDestino() {
        return destino;
    }

    public Astronauta[] getTripulacion() {
        return tripulacion;
    }

    public Astronauta[] getExpulsados() {
        return expulsados;
    }

    public int getTiempo() {
        return tiempo;
    }

    public ArrayList<Inconvenientes> getInconvenientes() {
        return inconvenientes;
    }
    //setters

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setTripulacion(Astronauta[] tripulacion) {
        this.tripulacion = tripulacion;
    }

    public void setExpulsados(Astronauta[] expulsados) {
        this.expulsados = expulsados;
    }

    

    public void setInconvenientes(ArrayList<Inconvenientes> inconvenientes) {
        this.inconvenientes = inconvenientes;
    }
    
    
}
