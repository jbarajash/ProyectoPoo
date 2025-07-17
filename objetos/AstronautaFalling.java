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

public class AstronautaFalling extends ObjetoEspacial {
    public AstronautaFalling(int x) {
        super("/imagenes/astronauta_falling.png", 0, -25);
        this.x = x;
        this.velocidad = 3;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}
