package juegoarcade.core;

import juegoarcade.entidades.Jugador;
import juegoarcade.objetos.*;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class JuegoPanel extends JPanel implements ActionListener, KeyListener {

    private Timer timer;
    private Jugador jugador;
    private List<ObjetoEspacial> objetos;
    private Random random;
    private Image fondo;
    private long tiempoInicio;
    private long tiempoFin;
    private boolean juegoTerminado;

 public JuegoPanel() {
    setPreferredSize(new Dimension(400, 600));
    setFocusable(true);
    requestFocusInWindow(); // <- asegúrate de esto
    addKeyListener(this);
    setBackground(Color.BLACK);

    fondo = new ImageIcon(getClass().getResource("/imagenes/fondo_espacio.jpg")).getImage();

    jugador = new Jugador();
    objetos = new ArrayList<>();
    random = new Random();

    if (timer == null) {  // evita múltiples timers si se recrea el panel
        timer = new Timer(30, this);
        timer.start();
    }
    tiempoInicio = System.currentTimeMillis();
    juegoTerminado = false;
}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (juegoTerminado) return;
        // si la vida del jugador llega a 0 el juego se detiene y muestra el resultado
        if (jugador.getVida() <= 0) {
            juegoTerminado = true;
            tiempoFin = System.currentTimeMillis();
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
                if (efecto < 0) jugador.recibirDanio(-efecto);
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

        int barraX = 10;
        int barraY = 10;
        int barraAncho = 150;
        int barraAlto = 15;
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
        g.setColor(Color.WHITE);
        g.fillRect(50, 250, 300, 100);
        g.setColor(Color.BLACK);
        g.drawRect(50, 250, 300, 100);

        g.setColor(Color.RED);
        g.drawString("GAME OVER", 170, 280);

        long tiempoTranscurrido = (tiempoFin - tiempoInicio) / 1000;
        g.drawString("Puntaje (segundos sobrevividos): " + tiempoTranscurrido, 90, 320);
        }
    }

    @Override public void keyPressed(KeyEvent e) {
        if (juegoTerminado) return;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> jugador.moverIzquierda();
            case KeyEvent.VK_RIGHT -> jugador.moverDerecha();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
