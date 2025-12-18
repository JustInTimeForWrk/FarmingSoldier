package util;

import view.Camera;

import java.util.ArrayList;

public abstract class Scene {
    Camera camera = new Camera();
    String name;
    public ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<Entity> entityToAdd = new ArrayList<>();
    ArrayList<Entity> entityToRemove = new ArrayList<>();

    boolean isRunning = false;

    public Scene(String name) {
        this.name = name;
    }

    public void update() {
        for (Entity entity : entities) {
            entity.update();
        }

        if (!entityToAdd.isEmpty()) {
            entities.addAll(entityToAdd);
            for (Entity entity : entityToAdd) {
                entity.start();
            }
            entityToAdd.clear();
        }
        if (!entityToRemove.isEmpty()) {
            entities.removeAll(entityToRemove);
            for (Entity entity : entityToRemove) {
                entity.destroy();
            }
            entityToRemove.clear();
        }
    }

    public void init() {
        for (Entity entity : entities) {
            entity.init();
        }
    }

    public void start() {
        if (!isRunning) {
            for (Entity entity : entities) {
                entity.start();
            }
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            for (Entity entity : entities) {
                entity.stop();
            }
            isRunning = false;
        }
    }

    public void addEntityToScene(Entity entity) {
        if (!isRunning) {
            entities.add(entity);
        } else {
            entity.init();
            entityToAdd.add(entity);
        }
    }

    public void removeEntityToScene(Entity entity) {
        if (!isRunning) {
            entities.remove(entity);
        } else {
            entityToRemove.add(entity);
        }
    }


    public Camera getCamera() {
        return this.camera;
    }

}
