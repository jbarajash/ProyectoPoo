package juegoarcade.objetos;

import java.awt.*;

public class BolaFuego extends ObjetoEspacial {
    public BolaFuego(int x) {
        super("/imagenes/BolaFuego.jpg", 0, -30);
        this.x = x;
        this.velocidad = 4;
    }

    @Override
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, 40, 40, null);
    }
}