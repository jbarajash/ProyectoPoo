/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission.game;

/**
 *
 * @author Camila León
 */
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Ventana de inicio con el título del juego y un botón para comenzar.
 */
public class StartWindow extends JFrame {

    public StartWindow() {
        setTitle("Horizontes lejanos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(null);
        panel.setBackground(new Color(10, 17, 40));
        add(panel);

        JLabel title = new JLabel("Horizontes lejanos", SwingConstants.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Georgia", Font.BOLD, 26));
        title.setBounds(0, 20, 600, 40);
        panel.add(title);

        JLabel imgLabel = new JLabel();
        imgLabel.setBounds(150, 80, 300, 220);
        try {
            String path = System.getProperty("user.dir") + File.separator +
                          "src" + File.separator + "logo_nave.jpg";
            BufferedImage img = ImageIO.read(new File(path));
            Image scaled = img.getScaledInstance(imgLabel.getWidth(),
                                                imgLabel.getHeight(),
                                                Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
        } catch (IOException ex) {
            // Si falta la imagen, se ignora y sigue sin icono
        }
        panel.add(imgLabel);

        JButton start = new JButton("Iniciar");
        start.setBounds(250, 340, 100, 40);
        start.addActionListener(e -> {
            dispose();
            new GameFrame();
        });
        panel.add(start);
    }
}
