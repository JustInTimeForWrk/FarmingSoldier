package view;

import physics.PhysicsManager;
import util.GameManager;
import util.KeyHandler;
import util.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Window.screenWidth, Window.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Preps next drawing in a draw buffer and improves performance

        this.addKeyListener(KeyHandler.get());
        this.addMouseListener(MouseHandler.get());
        this.addMouseMotionListener(MouseHandler.getMotionHandler());

        this.setFocusable(true);

    }

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
        System.out.println("Game Thread Started");
    }



    @Override
    public void run() {

        double drawInterval = 1E9d / GameManager.FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();

                repaint();
                delta--;
            }

        }
    }

    public void update() {
        SceneManager.updateScene();
        PhysicsManager.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        Renderer.update(g2);

        g2.dispose(); //releases resources being used to draw, improves memory
    }


}