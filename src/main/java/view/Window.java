package view;

import javax.swing.*;

public class Window extends JFrame {

    static final int originalTileSize = 16;
    static final int scale = 3;
    public static final int tileSize = originalTileSize * scale; //48x48

    final static int maxScreenCol = 16;
    final static int maxScreenRow = 12;
    final static int screenWidth = maxScreenCol * tileSize;
    final static int screenHeight = maxScreenRow * tileSize;

    public final static int halfScreenWidth = screenWidth / 2;
    public final static int halfScreenHeight = screenHeight / 2;

    GamePanel gamePanel;
    public Window() {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Island Survival");

        gamePanel = new GamePanel();
        this.add(gamePanel);

        this.pack();
    }

    public void startGame() {
        gamePanel.startGameThread();
        this.setVisible(true);
    }
}
