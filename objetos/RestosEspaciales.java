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

public class RestosEspaciales extends ObjetoEspacial {
    public RestosEspaciales(int x) {
        super("/imagenes/restosespaciales.png", 0, -20);
        this.x = x;
        this.velocidad = 4;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}
