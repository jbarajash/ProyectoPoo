/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import juegoarcade.core.JuegoPanel;

public class Ventana extends JFrame {

    private JButton btnInstrucciones;
    private JButton btnJugar;

    public Ventana() {
        // Configurar la ventana
        setTitle("Mi Juego");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar en pantalla

        // Crear panel para los botones
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        // Crear botones
        btnInstrucciones = new JButton("Instrucciones");
        btnJugar = new JButton("Jugar");

        // Agregar listeners
        btnInstrucciones.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarInstrucciones();
            }
        });

        btnJugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuego();
            }
        });

        // Agregar botones al panel
        panel.add(btnInstrucciones);
        panel.add(btnJugar);

        // Agregar panel a la ventana
        add(panel);

        // Hacer visible la ventana
        setVisible(true);
    }

    private void mostrarInstrucciones() {
    JOptionPane.showMessageDialog(this, """
                                        Recoge ox\u00edgeno y jeringas para recuperar vida y mantenerte en buen estado, ya que estos \u00edtems son beneficiosos.
                                        Evita la basura espacial, los astronautas cayendo, los meteoritos y las bolas de fuego, pues son peligrosos y disminuir\u00e1n tu vida al impactarte.
                                        Mantente alerta y esquiva todos los obst\u00e1culos da\u00f1inos para sobrevivir el mayor tiempo posible.""",
            "Instrucciones",
            JOptionPane.INFORMATION_MESSAGE);
}


    private void iniciarJuego() {
        JOptionPane.showMessageDialog(this,
                "¡El juego va a comenzar!",
                "Jugar",
                JOptionPane.INFORMATION_MESSAGE);
        
        JFrame ventana = new JFrame("Horizontes Lejanos - El Juego");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        
        ventana.add(new JuegoPanel());
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    
}
