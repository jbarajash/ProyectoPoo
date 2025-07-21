package com.juegoarcade.entidades;

import javax.swing.*;
import java.awt.*;

public class Jugador {
    private int x, y, ancho, alto;
    private double vida;
    private Image imagen;

    public Jugador() {
        this.x = 200;
        this.y = 550;
        this.ancho = 55;
        this.alto = 55;
        this.vida = 100;
        this.imagen = new ImageIcon(getClass().getResource("/imagenes/astronauta1.png")).getImage();
    }

    public void moverIzquierda() {
        if (x > 0) x -= 15;
    }

    public void moverDerecha() {
        if (x < 400 - ancho) x += 15;
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, ancho, alto, null);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }

    public void recibirDanio(double cantidad) {
        vida -= cantidad;
        if (vida < 0) vida = 0;
    }

    public void curar(double cantidad) {
        vida += cantidad;
        if (vida > 100) vida = 100;
    }

    public double getVida() {
        return vida;
    }

    public int getX() {
        return x;
    }
} 