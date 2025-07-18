package com.juegoarcade.objetos;

import java.awt.*;

public class Oxigeno extends ObjetoEspacial {
    public Oxigeno(int x) {
        super("/imagenes/Oxigeno.png", 0, 10);
        this.x = x;
        this.velocidad = 3;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}