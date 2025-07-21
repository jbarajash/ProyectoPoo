package com.juegoarcade.objetos;

import java.awt.*;

public class AstronautaFalling extends ObjetoEspacial {
    public AstronautaFalling(int x) {
        super("/imagenes/astronauta.png", 0, -25);
        this.x = x;
        this.velocidad = 3;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 66, 66, null);
    }
}
