package com.juegoarcade.core;

import com.juegoarcade.entidades.Jugador;
import com.juegoarcade.objetos.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class JuegoPanel extends JPanel implements ActionListener, KeyListener {
    private Timer timer;
    private Jugador jugador;
    private List<ObjetoEspacial> objetos;
    private Random random;
    private Image fondo;
    private Image explosion;
    private long tiempoInicio;
    private long tiempoFin;
    private boolean juegoTerminado;
    private int animacionY = 220;
    private final File recordFile = new File("record.txt");
    private long record = 0;

    public JuegoPanel() {
        setPreferredSize(new Dimension(400, 600));
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
        setBackground(Color.BLACK);

        fondo = new ImageIcon(getClass().getResource("/imagenes/fondo_espacio.jpg")).getImage();
        explosion = new ImageIcon(getClass().getResource("/imagenes/explosion.gif")).getImage();

        jugador = new Jugador();
        objetos = new ArrayList<>();
        random = new Random();

        cargarRecord();

        if (timer == null) {
            timer = new Timer(30, this);
            timer.start();
        }
        tiempoInicio = System.currentTimeMillis();
        juegoTerminado = false;
    }

    private void cargarRecord() {
        try {
            if (recordFile.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(recordFile));
                record = Long.parseLong(reader.readLine());
                reader.close();
            }
        } catch (Exception e) {
            record = 0;
        }
    }

    private void guardarNuevoRecord(long nuevoRecord) {
        try (PrintWriter writer = new PrintWriter(recordFile)) {
            writer.println(nuevoRecord);
        } catch (IOException e) {
            System.err.println("No se pudo guardar el record");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (juegoTerminado) {
            if (animacionY > 100) animacionY -= 2;
            repaint();
            return;
        }

        if (jugador.getVida() <= 0) {
            juegoTerminado = true;
            tiempoFin = System.currentTimeMillis();
            long tiempoActual = (tiempoFin - tiempoInicio) / 1000;
            if (tiempoActual > record) {
                guardarNuevoRecord(tiempoActual);
                record = tiempoActual;
            }
            timer.stop();
            repaint();
            return;
        }

        if (random.nextInt(15) == 0) {
            int x = random.nextInt(370);
            int tipo = random.nextInt(6);
            switch (tipo) {
                case 0 -> objetos.add(new Meteorito(x));
                case 1 -> objetos.add(new BolaFuego(x));
                case 2 -> objetos.add(new RestosEspaciales(x));
                case 3 -> objetos.add(new Oxigeno(x));
                case 4 -> objetos.add(new Jeringa(x));
                case 5 -> objetos.add(new AstronautaFalling(x));
            }
        }

        Iterator<ObjetoEspacial> it = objetos.iterator();
        while (it.hasNext()) {
            ObjetoEspacial obj = it.next();
            obj.mover();

            if (obj.getY() > 600) {
                it.remove();
                continue;
            }

            if (obj.getBounds().intersects(jugador.getBounds())) {
                double efecto = obj.getEfecto();
                if (efecto < 0) jugador.recibirDanio(Math.abs(efecto));
                else jugador.curar(efecto);
                it.remove();
            }
        }

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(fondo, 0, 0, 400, 600, null);
        jugador.dibujar(g);

        for (ObjetoEspacial obj : objetos) {
            obj.dibujar(g);
        }

        int barraX = 10, barraY = 10, barraAncho = 150, barraAlto = 15;
        double vida = jugador.getVida();
        int vidaVisible = (int)(barraAncho * vida / 100.0);

        g.setColor(Color.RED);
        g.fillRect(barraX, barraY, barraAncho, barraAlto);

        g.setColor(Color.GREEN);
        g.fillRect(barraX, barraY, vidaVisible, barraAlto);

        g.setColor(Color.BLACK);
        g.drawRect(barraX, barraY, barraAncho, barraAlto);

        g.setColor(Color.WHITE);
        g.drawString("Vida: " + (int)vida + "%", barraX + 5, barraY + 25);

        if (juegoTerminado) {
            g.setColor(new Color(0, 0, 0, 200));
            g.fillRoundRect(40, animacionY, 320, 180, 20, 20);
            g.setColor(Color.WHITE);
            g.drawRoundRect(40, animacionY, 320, 180, 20, 20);

            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.setColor(Color.RED);
            g.drawString("\u00a1GAME OVER!", 130, animacionY + 35);

            long tiempoTranscurrido = (tiempoFin - tiempoInicio) / 1000;

            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.setColor(Color.WHITE);
            g.drawString("Sobreviviste: " + tiempoTranscurrido + " segundos", 95, animacionY + 65);
            g.drawString("Record: " + record + " segundos", 125, animacionY + 85);
            g.drawString("Presiona ESC para salir o R para reiniciar", 55, animacionY + 115);

            if (explosion != null) {
                g.drawImage(explosion, 168, animacionY - 60, 64, 64, this);
            } else {
                g.drawString("[gif no cargado]", 160, animacionY - 30);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (juegoTerminado) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> System.exit(0);
                case KeyEvent.VK_R -> {
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.dispose();
                    JFrame nuevo = new JFrame("Horizontes Lejanos - El Juego");
                    nuevo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    nuevo.setResizable(false);
                    nuevo.add(new JuegoPanel());
                    nuevo.pack();
                    nuevo.setLocationRelativeTo(null);
                    nuevo.setVisible(true);
                }
            }
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> jugador.moverIzquierda();
            case KeyEvent.VK_RIGHT -> jugador.moverDerecha();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}