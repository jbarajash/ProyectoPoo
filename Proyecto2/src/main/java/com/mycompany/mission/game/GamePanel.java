/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mission.game;

/**
 *
 * @author Camila León
 */

import com.mycompany.mission.*;
import com.mycompany.mission.control.MissionController;
import com.mycompany.mission.control.MissionController.VentanaCallback;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Panel principal del juego.
 * Cada astronauta recibe 4 tareas distintas al inicio,
 * las ejecuta en orden y se le muestra la siguiente automáticamente.
 */
public class GamePanel extends JPanel implements Runnable, VentanaCallback {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static final int FPS = 60;
    /** Intervalo entre eventos aleatorios en milisegundos. */
    private static final int EVENT_INTERVAL = 12000;

    // tamaño del cohete dibujado
    private static final int ROCKET_W = 200;
    private static final int ROCKET_H = 250;

    private BufferedImage bg, rocketImg, pilotImg, scientistImg;
    private BufferedImage fireImg, leakImg, meteorImg, targetImg;

    private MissionController controller;

    /**
     * Representación visual de un astronauta.
     * Solo conserva su sprite y referencia al modelo,
     * pues las coordenadas se almacenan ahora en el propio
     * {@link Astronauta}.
     */
    private static class Avatar {
        Astronauta   model;
        BufferedImage sprite;
        int           tasksDone = 0;
    }
    private final List<Avatar> avatars = new ArrayList<>();
    private int selected = 0;

    private static class Hazard {
        Inconveniente model;
        int x, y;
        BufferedImage icon;
        Avatar encargado;
    }
    private static class Target {
        String tipo;
        int x, y;
        BufferedImage icon;
        boolean completado = false;
    }
    private final List<Hazard> hazards = new ArrayList<>();
    private final List<Target> targets = new ArrayList<>();

    private final Map<Avatar, Deque<Tarea>> taskQueues = new HashMap<>();

    private Thread gameThread;
    private volatile boolean running = false;
    private long lastEvent = 0;

    public GamePanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        cargarImagenes();
        controller = new MissionController(this);

        crearAvatares();
        asignarTareasIniciales();
        crearTargetsIniciales();

        configurarTeclado();
        SwingUtilities.invokeLater(this::requestFocusInWindow);
    }

    @Override
    public void log(String m) {
        System.out.println("[LOG] " + m);
    }

    @Override
    public void refrescar() {
        repaint();
    }

    public void startGame() {
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        long frameTime = 1000 / FPS;
        while (running) {
            long start = System.currentTimeMillis();
            updateGame();
            repaint();
            long delta = System.currentTimeMillis() - start;
            if (delta < frameTime) {
                try {
                    Thread.sleep(frameTime - delta);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    private void updateGame() {
        long now = System.currentTimeMillis();
        if (now - lastEvent > EVENT_INTERVAL) {
            Inconveniente inc = controller.eventoAleatorio();
            Hazard h = new Hazard();
            h.model = inc;
            int rx = (WIDTH - ROCKET_W) / 2;
            int ry = 50; // pos y fija del cohete
            if (inc instanceof Fuego) {
                h.x = rx + (ROCKET_W - 50) / 2;
                h.y = ry + 20;
                h.icon = fireImg;
            } else {
                h.x = rx + new Random().nextInt(ROCKET_W - 50);
                h.y = ry + new Random().nextInt(ROCKET_H - 50);
                if (inc instanceof FugaOxigeno) h.icon = leakImg;
                else h.icon = meteorImg;
            }

            // asignar a un astronauta al azar para resolverlo
            Avatar elegido = avatars.get(new Random().nextInt(avatars.size()));
            h.encargado = elegido;
            taskQueues.get(elegido)
                     .addFirst(new RepararModulo("Atender " + inc.getNombre()));
            log(elegido.model.getNombre() + " asignado a " + inc.getNombre());

            hazards.add(h);
            lastEvent = now;
        }
        verificarEscudo();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
        int rx = (WIDTH - ROCKET_W) / 2;
        g.drawImage(rocketImg, rx, 50, ROCKET_W, ROCKET_H, null);

        for (Avatar av : avatars) {
            g.drawImage(av.sprite,
                        av.model.getPosX(), av.model.getPosY(),
                        80, 80, null);
        }
        for (Hazard hz : hazards) {
            g.drawImage(hz.icon, hz.x, hz.y, null);
        }
        for (Target t : targets) {
            if (!t.completado) {
                g.drawImage(t.icon, t.x, t.y, null);
            }
        }

        Avatar sel = avatars.get(selected);
        g.setColor(Color.YELLOW);
        g.drawRect(sel.model.getPosX(), sel.model.getPosY(), 80, 80);
        g.setColor(Color.WHITE);
        g.drawString(sel.model.getNombre(),
                     sel.model.getPosX(), sel.model.getPosY() - 5);

        g.setFont(new Font("Consolas", Font.PLAIN, 12));
        int y0 = 15;
        for (Avatar av : avatars) {
            Deque<Tarea> q = taskQueues.get(av);
            Tarea next = q.peek();
            String nextDesc = next != null ? next.getDescripcion() : "Ninguna";
            g.drawString(av.model.getNombre() + " [Hechas: " + av.tasksDone + "/4]" +
                         " – Salud: " + av.model.getSalud() +
                         " – Próxima: " + nextDesc,
                         10, y0 += 15);
        }
        Recurso ox = controller.getMision().getRecursos().get(0);
        g.drawString("Oxígeno: " + ox.getCantidad(), 10, y0 + 20);

        // Dibujar barras de salud junto a cada avatar
        for (Avatar av : avatars) {
            int bx = av.model.getPosX();
            int by = av.model.getPosY() - 10;
            drawBar(g, bx, by, 80, 5,
                    av.model.getSalud() / 100.0,
                    Color.GREEN, Color.RED);
        }

        // Barras de recursos en la parte inferior
        int rxPos = 10;
        int ry = HEIGHT - 20;
        for (Recurso r : controller.getMision().getRecursos()) {
            int max = (int) r.getCapacidadInicial();
            drawBar(g, rxPos, ry - 8, 150, 8,
                    r.getCantidad() / (double) max,
                    Color.CYAN, Color.DARK_GRAY);
            g.setColor(Color.WHITE);
            g.drawString(r.getTipo(), rxPos, ry - 10);
            rxPos += 160;
        }
    }

    private void drawBar(Graphics g, int x, int y, int w, int h,
                         double pct, Color fg, Color bg) {
        pct = Math.max(0.0, Math.min(1.0, pct));
        g.setColor(bg);
        g.fillRect(x, y, w, h);
        g.setColor(fg);
        int pw = (int) (w * pct);
        g.fillRect(x, y, pw, h);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, w, h);
    }

    private void configurarTeclado() {
        int cond = WHEN_IN_FOCUSED_WINDOW;
        InputMap im = getInputMap(cond);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke("LEFT"), "a");
        im.put(KeyStroke.getKeyStroke("RIGHT"), "d");
        im.put(KeyStroke.getKeyStroke("UP"), "w");
        im.put(KeyStroke.getKeyStroke("DOWN"), "s");
        im.put(KeyStroke.getKeyStroke("TAB"), "p");
        im.put(KeyStroke.getKeyStroke('e'), "EX");

        am.put("a", acc(() -> mover(-5, 0)));
        am.put("d", acc(() -> mover(5, 0)));
        am.put("w", acc(() -> mover(0, -5)));
        am.put("s", acc(() -> mover(0, 5)));
        am.put("p", acc(() -> { selected = (selected + 1) % avatars.size(); repaint(); }));
        am.put("EX", acc(this::ejecutarTareaActual));
    }

    private AbstractAction acc(Runnable r) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r.run();
            }
        };
    }

    /**
     * Mueve al astronauta seleccionado actualizando las
     * coordenadas almacenadas en el modelo.
     */
    private void mover(int dx, int dy) {
        Avatar av = avatars.get(selected);
        int nx = Math.max(0, Math.min(av.model.getPosX() + dx, WIDTH - 80));
        int ny = Math.max(0, Math.min(av.model.getPosY() + dy, HEIGHT - 80));
        av.model.mover(nx - av.model.getPosX(), ny - av.model.getPosY());
    }

    private void asignarTareasIniciales() {
        // Preparar las colas de cada avatar
        for (Avatar av : avatars) {
            taskQueues.put(av, new ArrayDeque<>());
        }

        // Lista de tareas iniciales
        List<Tarea> todas = List.of(
            new TomarFotos("Tomar fotos en cráter"),
            new RepararModulo("Reparar módulo"),
            new TomarMuestras("Tomar muestras de rocas"),
            new TomarFotos("Fotografía panorámica"),

            new TomarMuestras("Tomar muestras de suelo"),
            new TomarFotos("Fotografiar cielos"),
            new RepararModulo("Reparar antena"),
            new TomarMuestras("Muestras de atmósfera"),

            new RepararModulo("Reparar sistema de aires"),
            new TomarFotos("Fotografiar relieve"),
            new TomarMuestras("Tomar muestras de hielo")
        );

        Random rnd = new Random();
        for (Tarea t : todas) {
            Avatar elegido = avatars.get(rnd.nextInt(avatars.size()));
            taskQueues.get(elegido).add(t);
            log(elegido.model.getNombre() + " - Tarea asignada: " + t.getDescripcion());
        }
    }

    private void ejecutarTareaActual() {
        Avatar av = avatars.get(selected);
        Deque<Tarea> queue = taskQueues.get(av);
        Tarea current = queue.peek();
        if (current == null) return;

        Hazard hit = hazards.stream()
                .filter(h -> distancia(av.model.getPosX(), av.model.getPosY(), h.x, h.y) < 40)
                .findFirst().orElse(null);
        if (hit != null) {
            if (hit.encargado != av) {
                log("Este inconveniente lo debe resolver " + hit.encargado.model.getNombre());
            } else if (current instanceof RepararModulo) {
                controller.resolverInconveniente(hit.model);
                hazards.remove(hit);
                av.tasksDone++;
                queue.poll();
                log(av.model.getNombre() + " completó: " + current.getDescripcion());
                mostrarSiguiente(av, queue);
            } else {
                log("Tarea no compatible con el inconveniente actual");
            }
            return;
        }

        Target tgt = targets.stream()
                .filter(t -> !t.completado &&
                        distancia(av.model.getPosX(), av.model.getPosY(), t.x, t.y) < 45)
                .findFirst().orElse(null);
        if (tgt != null) {
            boolean permiso = (current instanceof TomarFotos && tgt.tipo.equals("Fotos")) ||
                               (current instanceof TomarMuestras && tgt.tipo.equals("Muestras"));
            if (permiso) {
                controller.realizarTarea(av.model, current);
                tgt.completado = true;
                crearTarget(tgt.tipo);
                av.tasksDone++;
                queue.poll();
                controller.getMision().getRecursos().get(0).reabastecer(10);
                mostrarSiguiente(av, queue);
            } else {
                log("Debe ejecutar: " + current.getDescripcion());
            }
        }
    }

    private void mostrarSiguiente(Avatar av, Deque<Tarea> q) {
        Tarea next = q.peek();
        if (next != null) {
            log(av.model.getNombre() + " - Siguiente tarea: " + next.getDescripcion());
        } else {
            log(av.model.getNombre() + " - ¡Todas las tareas completadas!");
            if (av.tasksDone >= 4) {
                running = false;
                SwingUtilities.invokeLater(() -> {
                    int opt = JOptionPane.showOptionDialog(
                            this,
                            av.model.getNombre() + " completó 4 tareas. ¡Has ganado!",
                            "¡Victoria!",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Reiniciar", "Salir"},
                            "Reiniciar");
                    if (opt == JOptionPane.YES_OPTION) reiniciarJuego();
                    else System.exit(0);
                });
            }
        }
    }

    private void reiniciarJuego() {
        hazards.clear();
        targets.clear();
        avatars.clear();
        taskQueues.clear();
        controller = new MissionController(this);
        crearAvatares();
        asignarTareasIniciales();
        crearTargetsIniciales();
        lastEvent = 0;
        startGame();
    }

    /** Verifica si el recurso "Escudo" se ha agotado y, de ser as\u00ed,
     *  termina la partida mostrando un mensaje. */
    private void verificarEscudo() {
        for (Recurso r : controller.getMision().getRecursos()) {
            if (r.getTipo().equals("Escudo") && r.getCantidad() <= 0) {
                running = false;
                SwingUtilities.invokeLater(() -> {
                    int opt = JOptionPane.showOptionDialog(
                            this,
                            "El escudo se ha agotado. Fin del juego.",
                            "Derrota",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new String[]{"Reiniciar", "Salir"},
                            "Reiniciar");
                    if (opt == JOptionPane.YES_OPTION) reiniciarJuego();
                    else System.exit(0);
                });
                break;
            }
        }
    }

    private void crearAvatares() {
        int x0 = 350;
        for (Astronauta a : controller.getMision().getAstronautas()) {
            // posicionar al modelo
            a.mover(x0 - a.getPosX(), 400 - a.getPosY());

            Avatar av = new Avatar();
            av.model  = a;
            av.sprite = a.getNombre().equals("Juan") ? pilotImg : scientistImg;
            avatars.add(av);

            x0 += 100;
        }
    }

    /** Crea un nuevo objetivo del tipo dado en una posición aleatoria. */
    private void crearTarget(String tipo) {
        Random rnd = new Random();
        Target t = new Target();
        t.tipo = tipo;
        t.x = 80 + rnd.nextInt(WIDTH - 160);
        t.y = 280 + rnd.nextInt(70);
        t.icon = targetImg;
        targets.add(t);
    }

    private void crearTargetsIniciales() {
        crearTarget("Fotos");
        crearTarget("Muestras");
        crearTarget("Fotos");
        crearTarget("Muestras");
    }

    private BufferedImage escalar(BufferedImage src, int w, int h) {
        Image tmp = src.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bi.createGraphics();
        g2.drawImage(tmp, 0, 0, null);
        g2.dispose();
        return bi;
    }

    private void cargarImagenes() {
        try {
            String base = System.getProperty("user.dir") + File.separator + "src" + File.separator;
            bg = ImageIO.read(new File(base + "bg_space.png"));
            rocketImg = escalar(ImageIO.read(new File(base + "rocket.png")), ROCKET_W, ROCKET_H);
            pilotImg = escalar(ImageIO.read(new File(base + "astro_pilot.png")), 80, 80);
            scientistImg = escalar(ImageIO.read(new File(base + "astro_scientist.png")), 80, 80);
            fireImg = escalar(ImageIO.read(new File(base + "fire.png")), 50, 50);
            leakImg = escalar(ImageIO.read(new File(base + "leak.png")), 50, 50);
            meteorImg = escalar(ImageIO.read(new File(base + "meteor.png")), 50, 50);
            targetImg = escalar(ImageIO.read(new File(base + "target.png")), 60, 60);
        } catch (IOException ex) {
            throw new RuntimeException("Error cargando imágenes", ex);
        }
    }

    private double distancia(int x1, int y1, int x2, int y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }
}