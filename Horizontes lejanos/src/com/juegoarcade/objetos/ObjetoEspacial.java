package com.juegoarcade.objetos;

import javax.swing.*;
import java.awt.*;

public abstract class ObjetoEspacial {
    protected int x, y;
    protected double efecto;
    protected int velocidad;
    protected Image imagen;

    public ObjetoEspacial(String nombreImagen, int y, double efecto) {
        this.imagen = new ImageIcon(getClass().getResource(nombreImagen)).getImage();
        this.y = y;
        this.efecto = efecto;
    }

    public void mover() {
        y += velocidad;
    }

    public int getY() {
        return y;
    }

    public double getEfecto() {
        return efecto;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public abstract void dibujar(Graphics g);
}
