package util;

import view.Camera;

import java.util.ArrayList;

public class SceneManager {
    private static ArrayList<Scene> scenes = new ArrayList<>();
    private static int currentSceneIndex = -1;
    private static Scene currentScene;

    public static void loadCurrentSceneByIndex(int index) {
        if (-1 < index && index < scenes.size()) {
            currentSceneIndex = index;
            if (currentScene != null) {
                currentScene.stop();
            }
            currentScene = scenes.get(index);
            currentScene.start();
        }
    }

    public static void addScene(Scene scene) {
        scenes.add(scene);
        scene.init();
    }


    public static Scene getCurrentScene() {
        return currentScene;
    }

    public static Camera getSceneCamera() {
        return currentScene.camera;
    }
}
