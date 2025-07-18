package com.juegoarcade.objetos;

import java.awt.*;

public class RestosEspaciales extends ObjetoEspacial {
    public RestosEspaciales(int x) {
        super("/imagenes/restosespaciales.0.png", 0, -20);
        this.x = x;
        this.velocidad = 4;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}