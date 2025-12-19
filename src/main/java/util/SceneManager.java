package util;

import physics.PhysicsManager;
import view.Camera;
import view.Renderer;

import java.util.ArrayList;

public class SceneManager {
    public static ArrayList<Scene> scenes = new ArrayList<>();
    public static Scene currentScene;
    public static int currentSceneIndex = -1;
    private static int loadSceneIndex = -1;

    public static void loadSceneByIndex(int index) {
        if (index > -1 && index < scenes.size()) {
            loadSceneIndex = index;
        }
    }

    public static void addScene(Scene scene) {
        scenes.add(scene);
        scene.init();
    }

    public static void updateScene() {
        if (loadSceneIndex != -1) {
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
    }

    public static Camera getCurrentCamera() {
        return currentScene.getCamera();
    }

    public static Scene getCurrentScene() {
        return currentScene;
    }
}
