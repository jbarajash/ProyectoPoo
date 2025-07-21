/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Menu;

import com.juegoarcade.core.JuegoPanel;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class Ventana extends JFrame {
    private JButton btnInstrucciones;
    private JButton btnJugar;
    private JButton btnSalir;
    private JPanel fondo;
    private Font fuenteTitulo;
    private Font fuenteBotones;
    private Image fondoImg;

    public Ventana() {
        setTitle("Horizontes Lejanos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, 800, 600, 30, 30));

        fuenteTitulo = new Font("Segoe UI", Font.BOLD, 42);
        fuenteBotones = new Font("Segoe UI", Font.BOLD, 22);

        fondoImg = new ImageIcon(getClass().getResource("/imagenes/fondo_menu.png")).getImage();
        fondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondoImg, 0, 0, getWidth(), getHeight(), this);
            }
        };
        fondo.setLayout(null);
        setContentPane(fondo);

        //logo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/imagenes/logo_horizontes.png"));
        Image logoImage = logoIcon.getImage();
        LogoPanel logo = new LogoPanel(logoImage);
        logo.setBounds((getWidth() - 400) / 2, 50, 400, 100);
        fondo.add(logo);

        //boton Jugar
        btnJugar = crearBoton("Jugar", new Color(34, 139, 34));
        btnJugar.setBounds(300, 180, 200, 60);
        btnJugar.addActionListener(e -> iniciarJuego());
        fondo.add(btnJugar);

        //boton Instrucciones
        btnInstrucciones = crearBoton("Instrucciones", new Color(70, 130, 180));
        btnInstrucciones.setBounds(300, 260, 200, 60);
        btnInstrucciones.addActionListener(e -> mostrarInstrucciones());
        fondo.add(btnInstrucciones);

        //boton Salir
        btnSalir = crearBoton("Salir", new Color(178, 34, 34));
        btnSalir.setBounds(300, 340, 200, 60);
        btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro que quieres salir?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        fondo.add(btnSalir);

        setVisible(true);
    }

    private JButton crearBoton(String texto, Color colorBase) {
        JButton boton = new JButton(texto);
        boton.setFont(fuenteBotones);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setBorderPainted(false);
        boton.setForeground(Color.WHITE);
        boton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        boton.setBackground(colorBase);

        Color hoverColor = colorBase.brighter();
        Color clickColor = colorBase.darker();

        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(hoverColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorBase);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                boton.setBackground(clickColor);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boton.setBackground(boton.getBounds().contains(e.getPoint()) ? hoverColor : colorBase);
            }
        });

        boton.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(boton.getBackground());
                g2.fillRoundRect(0, 0, boton.getWidth(), boton.getHeight(), 30, 30);
                g2.dispose();
                super.paint(g, c);
            }
        });

        return boton;
    }

    private void mostrarInstrucciones() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 60));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JTextArea area = new JTextArea("""
        • Recoge oxígeno y jeringas para recuperar vida.
        • Esquiva basura espacial, astronautas cayendo, meteoritos y bolas de fuego.
        • Mantente alerta y esquiva los obstáculos dañinos para sobrevivir.

        CONTROLES:
        • Flechas izquierda y derecha: Moverse
        """);
        area.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        area.setForeground(Color.WHITE);
        area.setBackground(new Color(30, 30, 60));
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setEditable(false);
        area.setCaretPosition(0);
        area.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(100, 149, 237), 2),
            new EmptyBorder(10, 10, 10, 10)));

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setPreferredSize(new Dimension(450, 250));
        scrollPane.setBorder(null);

        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, panel, "Instrucciones", JOptionPane.PLAIN_MESSAGE);
    }

    private void iniciarJuego() {
        dispose();
        JFrame ventana = new JFrame("Horizontes Lejanos - El Juego");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        JuegoPanel juego = new JuegoPanel();
        ventana.add(juego);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        SwingUtilities.invokeLater(juego::requestFocusInWindow);
    }

    //esto es para el efecto desvanecido del logo
    private class LogoPanel extends JPanel {
        private final Image image;
        private float alpha = 0f;

        public LogoPanel(Image image) {
            this.image = image;
            setOpaque(false);
            startFadeIn();
        }

        private void startFadeIn() {
            int frameRateMs = 30;
            float step = 0.02f;

            Timer timer = new Timer(frameRateMs, null);
            timer.addActionListener(e -> {
                alpha += step;
                if (alpha >= 1f) {
                    alpha = 1f;
                    timer.stop();
                }
                repaint();
            });
            timer.start();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image == null) return;
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            g2d.dispose();
        }
    }
}


