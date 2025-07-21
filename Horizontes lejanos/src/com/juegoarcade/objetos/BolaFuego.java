package com.juegoarcade.objetos;

import java.awt.*;

public class BolaFuego extends ObjetoEspacial {
    public BolaFuego(int x) {
        super("/imagenes/BolaFuego.png", 0, -30);
        this.x = x;
        this.velocidad = 4;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}
