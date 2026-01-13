package util;

import physics.PhysicsManager;
import scenes.WorldScene;
import scenes.HouseScene;
import scripts.FarmingScript;
import scripts.NPCSellScript;
import scripts.PourWaterOnNPCQuestScript;
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
    //Example: none needed
    public static void startWindow() {
        loadedSave = GameData.loadGameData("resources/saves/gameSave.db");
        window = new Window();
    }

    //Input: none, Output: none
    //Purpose: to build all the scenes, initialize and load the scene manager, and start the game panel
    //Example: none needed
    public static void startGame() {
        FarmingScript.harvestedPlants = loadedSave.playerCropCount;
        NPCSellScript.cropsNeeded = loadedSave.playerCropsNeeded;
        PourWaterOnNPCQuestScript.watered = loadedSave.wateredTheNPC;

        SceneManager.addScene(new WorldScene());
        SceneManager.addScene(new HouseScene());
        SceneManager.init();

        SceneManager.loadSceneByName("house");
        window.startGamePanel();
    }

    //Input: none, Output: none
    //Purpose: saves all game data and map data
    //Example: none needed
    public static void saveGame() {
        for (Scene scene : SceneManager.scenes.values()) {
            if (scene.tileMap != null) {
                scene.tileMap.saveTileMap();
            }
        }
        loadedSave.playerCropCount = FarmingScript.harvestedPlants;
        loadedSave.playerCropsNeeded = NPCSellScript.cropsNeeded;
        loadedSave.wateredTheNPC = PourWaterOnNPCQuestScript.watered;
        GameData.saveGameData("resources/saves/gameSave.db",loadedSave);
    }

    //Input: none, Output: none
    //Purpose: flips a boolean in the game panel update function to safely stop the game thread after updates and for loops are finished
    //Example: none needed
    public static void requestStopGame() {
        window.gamePanel.requestingQuit = true;
    }
    
    //Input: none, Output: none
    //Purpose: completely clears the data in all handlers and managers of the game as well as swaps from the game panel to the menu panel
    //Example: none needed
    public static void stopGame() {
        window.stopGamePanel();
        SceneManager.reset();
        KeyHandler.reset();
        MouseHandler.reset();
        Renderer.clear();
        PhysicsManager.clear();
    }
}


