package view;

import physics.PhysicsManager;
import util.GameManager;
import util.SceneManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    public boolean requestingQuit;
    Thread gameThread;

    //Input: none, Output: none
    //Purpose: constructor for the GamePanel
    //Example: none needed
    public GamePanel() {
        this.setPreferredSize(new Dimension(Window.screenWidth, Window.screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //Preps next drawing in a draw buffer and improves performance

        this.addKeyListener(KeyHandler.get());
        this.addMouseListener(MouseHandler.get());
        this.addMouseMotionListener(MouseHandler.getMotionHandler());

        this.setFocusable(true);
    }

    //Input: none, Output: none
    //Purpose: starts running the game thread
    //Example: none needed
    public void startGameThread()
    {
        requestingQuit = false;
        gameThread = new Thread(this);
        gameThread.start();
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

    //Input: none, Output: none
    //Purpose: gets called every frame and updates all the game components
    //Example: none needed
    public void update() {
        SceneManager.updateScene();
        PhysicsManager.update();
        if (requestingQuit) { // Makes sure no processes are running when stopping the game
            GameManager.stopGame();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);
        Renderer.update(g2);

        g2.dispose(); //releases resources being used to draw, improves memory
    }


}