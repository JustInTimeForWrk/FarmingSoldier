package util;

import scenes.*;
import view.Window;

public class GameManager {
    public static final int FPS = 60;

    private static GameManager instance;
    private static Window window = new Window();

    public static void startGame() {
        SceneManager.addScene(new TestScene());
        SceneManager.addScene(new GameScene());

        SceneManager.loadCurrentSceneByIndex(0);


        window.startGame();
    }
    public static Window getWindow() {
        return window;
    }

}
