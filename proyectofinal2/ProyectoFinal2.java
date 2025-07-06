/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal2;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author 57324
 */

public class ProyectoFinal2 {
    public static void main(String[] args) {
      Scanner input= new Scanner(System.in);
      //inicio random
      Random rand=new Random();
      // se crea la mision
      Mision marte= new Mision("marte");
      //guardar una lista con todas las tareas que hay
      AnalizarDatos analizarDatos = new AnalizarDatos("Analizar Datos","");
      TomarFotos tomarFotos = new TomarFotos("Tomar Fotos ","");
      TomarMuestras tomarMuestras= new TomarMuestras("Tomar Muestras ","");
      Explorar explorar= new Explorar("Explorar ","");
      Iterable.totalTareas.add(tomarFotos);
      Iterable.totalTareas.add(analizarDatos);
      Iterable.totalTareas.add(tomarMuestras);
      Iterable.totalTareas.add(explorar);
      // guardar listado total de los posibles tripulantes
      Ingeniero ingeniero = new Ingeniero("Jorge");
      Cientifico cientifico= new Cientifico("Violeta");
      Medico medico= new Medico("Ruby");
      Piloto piloto= new Piloto("Aurora");
      Explorador explorador = new Explorador("Alexander");
      Soldado soldado = new Soldado("Alison");
      Iterable.totalAstronautas.add(ingeniero);
      Iterable.totalAstronautas.add(cientifico);
      Iterable.totalAstronautas.add(medico);
      Iterable.totalAstronautas.add(piloto);
      Iterable.totalAstronautas.add(explorador);
      Iterable.totalAstronautas.add(soldado);
      // guardar listado total de los posibles recursos de la mision
      Botiquin botiquin = new Botiquin();
      Pistola pistola = new Pistola();
      Microscopio microscopio = new Microscopio();
      Comida comida = new Comida();
      Combustible combustible = new Combustible();
      Oxigeno oxigeno = new Oxigeno();
      Computadora computadora = new Computadora();
      Maleta maleta= new Maleta();
      Mapa mapa = new Mapa();
      Iterable.totalRecursos.add(botiquin);
      Iterable.totalRecursos.add(pistola);
      Iterable.totalRecursos.add(microscopio);
      Iterable.totalRecursos.add(comida);
      Iterable.totalRecursos.add(combustible);
      Iterable.totalRecursos.add(oxigeno);
      Iterable.totalRecursos.add(computadora);
      Iterable.totalRecursos.add(maleta);
      Iterable.totalRecursos.add(mapa);
      // asigna 4 tareas al azar a la mision
        for (int i=0;i<4;i++){
        // crea un indice random entre el total de las tareas de iterable    
         int indiceRandom=rand.nextInt(Iterable.totalTareas.size());
        // añade a la mision la tarea del total de tareas que tiene el indice random
         marte.añadirTareas(Iterable.totalTareas.get(indiceRandom), i);  
        } 
      //jugabilidad
      // introduccion
        System.out.println("""
                           Tras la catastrofe que azoto la Tierra, la humanidad depende de esta mision a Marte para garantizar su futuro.
                           Ahora que el comandante ha muerto, tu asumes el mando: antes de partir, deberas prepararte eligiendo cuidadosamente
                           a la tripulacion y asignando los recursos que llevaran. Cada eleccion sera crucial para sobrevivir y completar las
                           tareas asignadas: explorar el terreno marciano, recolectar valiosas muestras y analizar datos cientificos que
                           podrian cambiar el destino de la Tierra. El exito de la mision y el regreso seguro del equipo dependen de tu
                           capacidad de liderazgo y planificacion.""");
        System.out.println("Presiona cualquier tecla (y ENTER) para continuar...");
        input.nextLine(); // espera a que el usuario escriba algo y presione ENTER
        
        System.out.println("para iniciar la mision es importante que conozcas las tareas que debes realizar");
        // imprime las tareas que debe cumplir para esta mision
        for(Tareas tarea:marte.tareas){
            System.out.println(tarea.toString());
        }
        System.out.println("Presiona cualquier tecla (y ENTER) para continuar...");
        input.nextLine();
        System.out.println("Lo primero que debes hacer es elegir Los tripulantes (hay 4 asientos en la nave) de la mision(recuerda que cada tripulante debe comer y respirar)");
        System.out.println("estas son tus opciones");
        //imprime los astronautas para mostrarlos
        for(Astronauta astronauta:Iterable.totalAstronautas){
            System.out.println(astronauta.toString());
        }
        // solicita la eleccion de los 4 astronautas de la mision
        for(int i=0;i<4;i++){
            System.out.println("ingrese la cedula del astronauta que desea agreagar");
            marte.añadirTripulacion(Iterable.totalAstronautas.get(input.nextInt()-1),i);
            marte.tripulacion[i].setEstado("activo");
        }
        System.out.println("gran eleccion, su tripulacion es :");
        //imprime la tripulacion seleccionada
        for(Astronauta astro:marte.tripulacion){
            System.out.println(astro.toString());
        }
    }
}
