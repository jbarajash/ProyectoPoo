package com.juegoarcade.objetos;

import java.awt.*;

public class Meteorito extends ObjetoEspacial {
    public Meteorito(int x) {
        super("/imagenes/meteorito.png", 0, -40);
        this.x = x;
        this.velocidad = 5;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}
