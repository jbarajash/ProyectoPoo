package com.juegoarcade.core;
import Menu.Ventana;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame ventana = new JFrame("Horizontes Lejanos - El Juego");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.add(new JuegoPanel());
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
