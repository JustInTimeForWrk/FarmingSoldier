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

    public static void loadSceneByIndex(int index) {
        if (System.currentTimeMillis() - timer > sceneSwapCD) {
            timer = System.currentTimeMillis();
            if (index > -1 && index < scenes.size()) {
                loadSceneIndex = index;
            }
        } else {
            System.out.println("too quick!");
        }

    }

    public static void addScene(Scene scene) {
        scenes.add(scene);
    }

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
            GameManager.stopGame();
        }

    }

    public static Camera getCurrentCamera() {
        return currentScene.getCamera();
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static Scene getScene(int index) {
        if (0 <= index && index <= scenes.size()) {
            return  scenes.get(index);
        }
        return null;
    }
    public static void init() {
        for (Scene scene : scenes) {
            scene.init();
        }
    }

    public static void reset() {
        for (Scene scene : scenes) {
            scene.stop();
        }
        scenes.clear();
    }
}
