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

    //Input: none, Output: none
    //Purpose: creates the Window of the application as well as tries to fetch game data
    public static void startWindow() {
        loadedSave = GameData.loadGameData("resources/save.db");
        window = new Window();
    }

    //Input: none, Output: none
    //Purpose: to build all the scenes, initialize and load the scene manager, and start the game panel
    public static void startGame() {
        SceneManager.addScene(new GameScene());
        SceneManager.addScene(new HouseScene());
        SceneManager.init();

        SceneManager.loadSceneByIndex(1);
        window.startGamePanel();
    }

    //Input: none, Output: none
    //Purpose: saves all game data and map data
    public static void saveGame() {
        for (Scene scene : SceneManager.scenes) {
            if (scene.tileMap != null) {
                scene.tileMap.saveTileMap();
            }
        }
    }

    //Input: none, Output: none
    //Purpose: flips a boolean in the game panel update function to safely stop the game thread after updates and for loops are finished
    public static void requestStopGame() {
        window.gamePanel.requestingQuit = true;
    }
    
    //Input: none, Output: none
    //Purpose: completely clears the data in all handlers and managers of the game as well as swaps from the game panel to the menu panel
    public static void stopGame() {
        window.stopGamePanel();
        SceneManager.reset();
        KeyHandler.reset();
        MouseHandler.reset();
        Renderer.clear();
        PhysicsManager.clear();
    }
}


