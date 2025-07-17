/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package juegoarcade.objetos;

/**
 *
 * @author 57324
 */
import java.awt.*;

public class Jeringa extends ObjetoEspacial {
    public Jeringa(int x) {
        super("/imagenes/jeringa.png", 0, 15);
        this.x = x;
        this.velocidad = 3;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}
