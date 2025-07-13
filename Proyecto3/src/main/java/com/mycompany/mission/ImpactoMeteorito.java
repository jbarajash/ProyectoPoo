/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission;

/**
 *
 * @author Camila León
 */
public class ImpactoMeteorito extends Inconveniente {
    public ImpactoMeteorito(String nombre, int gravedad) {
        super(nombre, gravedad);
    }

    @Override
    public void afectarMision(Mision mision) {
        Recurso escudo = null;
        for (Recurso r : mision.getRecursos()) {
            if (r.getTipo().equals("Escudo")) {
                escudo = r;
                break;
            }
        }
        if (escudo != null && escudo.getCantidad() > 0) {
            escudo.consumir(gravedad);
            mision.registrarEvento("Escudo amortigua impacto de meteorito");
        }

        mision.getTareas().add(new RepararModulo("Reparar daño por meteorito"));
        mision.registrarEvento(
            "Impacto de meteorito requiere reparaciones");
    }
}