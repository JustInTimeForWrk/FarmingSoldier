package util;

import physics.PhysicsManager;
import view.Camera;
import view.KeyHandler;
import view.Renderer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class SceneManager {
    public static long timer = 0;
    public static double sceneSwapCD = 100;

    public static HashMap<String,Scene> scenes = new HashMap<>();
    public static Scene currentScene;
    public static String currentSceneName = null;
    private static String loadSceneName = null;

    //Input: int representing the index wanting to load, Output: none
    //Purpose: sets the load scene to an index in the scenes arraylist if the cooldown has passed, also resets the cooldown if successful
    //Example: loadSceneByName("house")
    public static void loadSceneByName(String name) {
        if (System.currentTimeMillis() - timer > sceneSwapCD) { //cooldown for swapping scenes
            timer = System.currentTimeMillis();
            if (scenes.get(name) != null) {
                loadSceneName = name;
            }
        }
    }
    
    //Input: Scene that you want to add to the scenes arraylist, Output: none
    //Purpose: adds a scene to the scenes arraylist
    //Example: addScene(new TestScene())
    public static void addScene(Scene scene) {
        scenes.put(scene.name,scene);
    }

    //Input: none, Output: none
    //Purpose: updates the current scene and loads a new scene if LoadSceneIndex isnt -1, also toggles on and off the debugger as well as requesting an exit to the main menu when listening for keys
    //Example: none needed
    public static void updateScene() {
        if (loadSceneName != null) {
            System.out.println("Switching from scene " + SceneManager.currentSceneName + " to " + loadSceneName);
            if (currentScene != null) {
                currentScene.stop();
            }
            Renderer.clear();
            PhysicsManager.clear();

            currentScene = scenes.get(loadSceneName);

            currentScene.start();
            currentSceneName = loadSceneName;

            loadSceneName = null;
        }

        currentScene.update();

        if (KeyHandler.getKey(KeyEvent.VK_O)) {
            Renderer.debugging = true;
        }
        if (KeyHandler.getKey(KeyEvent.VK_P)) {
            Renderer.debugging = false;
        }
        if (KeyHandler.getKey(KeyEvent.VK_ESCAPE)) {
            GameManager.requestStopGame();
        }

    }

    //Input: none, Output: camera from the current active scene
    //Purpose: grabs the camera from the current active scene
    //Example: getCurrentCamera() returns the GameScene's camera if the current scene is a GameScene()
    public static Camera getCurrentCamera() {
        return currentScene.getCamera();
    }

    //Input: none, Output: the current active scene
    //Purpose: grabs the current active scene
    //Example: getCurrentScene() returns WorldScene if WorldScene was the only scene running
    public static Scene getCurrentScene() {
        return currentScene;
    }

    //Input: int representing the scene index, Output: Scene grabbed from the scenes arraylist
    //Purpose: grabs a scene from the scenes arraylist at the scene index
    //Example: getScene("house") returns the house Scene if a house scene was put or returns null
    public static Scene getScene(String name) {
        return scenes.get(name);
    }
    
    //Input: none, Output: none
    //Purpose: initializes every scene in the scenes arraylist
    //Example: none needed
    public static void init() {
        for (Scene scene : scenes.values()) {
            scene.init();
        }
    }
    
    //Input: none, Output: none
    //Purpose: stops and clears all the scenes when exiting out of the game panel to the menu panel
    //Example: none needed
    public static void reset() {
        for (Scene scene : scenes.values()) {
            scene.stop();
            scene.tileMap.clear();
        }
        scenes.clear();
    }
}
