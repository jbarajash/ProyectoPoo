/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission.game;

/**
 *
 * @author Camila León
 */
import javax.swing.SwingUtilities;

/** Punto de entrada para el modo de juego con ventana de inicio. */

public class GameLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartWindow().setVisible(true));
    }
}
