package com.mycompany.mission.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class StartWindow extends JFrame {

    public StartWindow() {
        setTitle("Horizontes lejanos");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;

        // fondo
        String fondoPath = "src/fondo_espacio.jpg";
        BackgroundPanel panel = new BackgroundPanel(fondoPath);
        setContentPane(panel);
        panel.setLayout(null);

        int logoWidth = 350;
        int logoHeight = 280;
        int btnWidth = 140;
        int btnHeight = 50;
        int spacing = 20;

        // img titulo
        JLabel logoTitulo = new JLabel();
        try {
            BufferedImage tituloImg = ImageIO.read(new File("src/logo_titulo.png"));
            int tituloAnchoOriginal = tituloImg.getWidth();
            int tituloAltoOriginal = tituloImg.getHeight();
            int tituloAncho = screenWidth / 2;
            int tituloAlto = (tituloAltoOriginal * tituloAncho) / tituloAnchoOriginal;
            int tituloX = (screenWidth - tituloAncho) / 2;
            int tituloY = -180; 

            Image tituloEscalado = tituloImg.getScaledInstance(tituloAncho, tituloAlto, Image.SCALE_SMOOTH);
            logoTitulo.setIcon(new ImageIcon(tituloEscalado));
            logoTitulo.setBounds(tituloX, tituloY, tituloAncho, tituloAlto);
            panel.add(logoTitulo);
        } catch (IOException e) {
            System.err.println("No se pudo cargar el logo del título: " + e.getMessage());
        }

        // img logo
        JLabel imgLabel = new JLabel();
        int logoX = (screenWidth - logoWidth) / 2;
        int logoY = 500; 
        imgLabel.setBounds(logoX, logoY, logoWidth, logoHeight);
        try {
            BufferedImage img = ImageIO.read(new File("src/logo_nave.png"));
            Image scaled = img.getScaledInstance(logoWidth, logoHeight, Image.SCALE_SMOOTH);
            imgLabel.setIcon(new ImageIcon(scaled));
        } catch (IOException ex) {
            System.err.println("No se pudo cargar el logo del juego: " + ex.getMessage());
        }
        panel.add(imgLabel);

        // boton
        Color baseColor = new Color(0, 120, 200);
        Color hoverColor = new Color(0, 160, 255);

        JButton start = new JButton("Iniciar") {
            private Color currentColor = baseColor;

            {
                addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        currentColor = hoverColor;
                        repaint();
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        currentColor = baseColor;
                        repaint();
                    }
                });
            }

            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(currentColor);
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                FontMetrics fm = g2.getFontMetrics();
                String text = getText();
                int textWidth = fm.stringWidth(text);
                int textHeight = fm.getAscent();
                g2.setColor(Color.WHITE);
                g2.setFont(getFont());
                g2.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 3);

                g2.dispose();
            }
        };

        int botonY = logoY + logoHeight + spacing; 
        start.setBounds((screenWidth - btnWidth) / 2, botonY, btnWidth, btnHeight);
        start.setFont(new Font("Verdana", Font.BOLD, 16));
        start.setFocusPainted(false);
        start.setBorderPainted(false);
        start.setContentAreaFilled(false);
        start.setOpaque(false);
        start.addActionListener(e -> {
            dispose();
            new GameFrame();
        });
        panel.add(start);

        // creditos
        JLabel creditos = new JLabel("Creadores: Adriana León, Johan Barajas, Zaira Forero, Daniel Carrasco");
        creditos.setForeground(Color.LIGHT_GRAY);
        creditos.setFont(new Font("Arial", Font.PLAIN, 14));
        creditos.setBounds(20, screenHeight - 40, 1000, 20);
        panel.add(creditos);

        setVisible(true);
    }

    // img de fondo
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = ImageIO.read(new File(imagePath));
            } catch (IOException e) {
                System.err.println("No se pudo cargar el fondo: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    } 
}

















