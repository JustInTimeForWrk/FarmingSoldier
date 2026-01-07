package util;

import physics.PhysicsManager;
import view.Camera;
import view.KeyHandler;
import view.Renderer;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SceneManager {
    public static long timer = 0;
    public static double sceneSwapCD = 100;

    public static ArrayList<Scene> scenes = new ArrayList<>();
    public static Scene currentScene;
    public static int currentSceneIndex = -1;
    private static int loadSceneIndex = -1;

    //Input: int representing the index wanting to load, Output: none
    //Purpose: sets the load scene to an index in the scenes arraylist if the cooldown has passed, also resets the cooldown if successful
    public static void loadSceneByIndex(int index) {
        if (System.currentTimeMillis() - timer > sceneSwapCD) { //cooldown for swapping scenes
            timer = System.currentTimeMillis();
            if (-1 < index && index < scenes.size()) {
                loadSceneIndex = index;
            }
        } else {
            System.out.println("too quick!");
        }

    }
    
    //Input: Scene that you want to add to the scenes arraylist, Output: none
    //Purpose: adds a scene to the scenes arraylist
    public static void addScene(Scene scene) {
        scenes.add(scene);
    }

    //Input: none, Output: none
    //Purpose: updates the current scene and loads a new scene if LoadSceneIndex isnt -1, also toggles on and off the debugger as well as requesting an exit to the main menu when listening for keys
    public static void updateScene() {
        if (loadSceneIndex != -1) {
            System.out.println("Switching from scene " + SceneManager.currentSceneIndex + " to " + loadSceneIndex);
            if (currentScene != null) {
                currentScene.stop();
            }
            Renderer.clear();
            PhysicsManager.clear();

            currentScene = scenes.get(loadSceneIndex);

            currentScene.start();
            currentSceneIndex = loadSceneIndex;

            loadSceneIndex = -1;
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
    public static Camera getCurrentCamera() {
        return currentScene.getCamera();
    }

    //Input: none, Output: the current active scene
    //Purpose: grabs the current active scene
    public static Scene getCurrentScene() {
        return currentScene;
    }

    //Input: int representing the scene index, Output: Scene grabbed from the scenes arraylist
    //Purpose: grabs a scene from the scenes arraylist at the scene index
    public static Scene getScene(int sceneIndex) {
        if (0 <= sceneIndex && sceneIndex <= scenes.size()) {
            return  scenes.get(sceneIndex);
        }
        return null;
    }
    
    //Input: none, Output: none
    //Purpose: initializes every scene in the scenes arraylist
    public static void init() {
        for (Scene scene : scenes) {
            scene.init();
        }
    }
    
    //Input: none, Output: none
    //Purpose: stops and clears all the scenes when exiting out of the game panel to the menu panel
    public static void reset() {
        for (Scene scene : scenes) {
            scene.stop();
            scene.tileMap.clear();
        }
        scenes.clear();
    }
}
