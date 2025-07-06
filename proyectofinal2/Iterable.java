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
public class Iterable {   
    // esl listado de todas las tareas para elegir al azar 
    public static ArrayList<Tareas> totalTareas=new ArrayList<>(); 
    public static ArrayList<Inconvenientes> totalIconvenientes=new ArrayList<>();
    public static ArrayList<Astronauta> totalAstronautas = new ArrayList<>();
    public static ArrayList<Recursos> totalRecursos= new ArrayList<>();
    public ArrayList<Tareas> getTotalTareas() {
        return totalTareas;
    }
    public ArrayList<Inconvenientes> getTotalIconvenientes() {
        return totalIconvenientes;
    }

    public static ArrayList<Astronauta> getTotalAstronautas() {
        return totalAstronautas;
    }
    
    
}
