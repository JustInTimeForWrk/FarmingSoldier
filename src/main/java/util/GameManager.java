package util;

import scenes.GameScene;
import scenes.HouseScene;
import view.Window;

public class GameManager {
    public static int FPS = 60;
    public static void startGame() {
        Window window = new Window();
        SceneManager.addScene(new GameScene());
        SceneManager.addScene(new HouseScene());
    }
}
