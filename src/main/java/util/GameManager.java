package util;

import physics.PhysicsManager;
import scenes.GameScene;
import scenes.HouseScene;
import scripts.FarmingScript;
import view.KeyHandler;
import view.MouseHandler;
import view.Renderer;
import view.Window;

public class GameManager {
    public static GameData loadedSave;
    private static Window window;

    public static int FPS = 60;

    public static void startWindow() {
        loadedSave = new GameData();
        window = new Window();
    }

    public static void startGame() {
        SceneManager.addScene(new GameScene());
        SceneManager.addScene(new HouseScene());
        SceneManager.init();

        SceneManager.loadSceneByIndex(1);
        window.startGamePanel();
    }

    public static void saveGame() {
        for (Scene scene : SceneManager.scenes) {
            if (scene.tileMap != null) {
                scene.tileMap.saveTileMap();
            }
        }
    }

    public static void requestStopGame() {
        window.gamePanel.requestingQuit = true;
    }
    
    public static void stopGame() {
        window.stopGamePanel();
        SceneManager.reset();
        KeyHandler.reset();
        MouseHandler.reset();
        Renderer.clear();
        PhysicsManager.clear();
    }
}


