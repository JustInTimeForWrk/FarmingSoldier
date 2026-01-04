package util;

import view.Camera;

import java.util.ArrayList;

public abstract class Scene {
    Camera camera = new Camera();
    String name;
    public ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<Entity> entityToAdd = new ArrayList<>();
    ArrayList<Entity> entityToRemove = new ArrayList<>();
    public TileMap tileMap;


    boolean isRunning = false;

    public Scene(String name) {
        this.name = name;
    }

    public void update() {
        if (tileMap != null) {
            tileMap.update();
        }


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
        if (tileMap != null) {
            tileMap.init();
        }
        for (Entity entity : entities) {
            entity.init();
        }
    }

    public void start() {
        if (!isRunning) {
            if (tileMap != null) {
                tileMap.start();
            }

            for (Entity entity : entities) {
                entity.start();
            }
            isRunning = true;
        }
    }

    public void stop() {
        if (isRunning) {
            if (tileMap != null) {
                tileMap.stop();
            }

            for (Entity entity : entities) {
                entity.stop();
            }
            isRunning = false;
        }
    }

    public void addEntityToScene(Entity entity) {
        entity.parentScene = this;
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

    public Entity findEntityByTag(String tag) {
        for (Entity entity : entities) {
            if (entity.tag.equals(tag)) {
                return entity;
            }
        }
        return null;
    }

    public ArrayList<Entity> findEntityArrayListByTag(String tag) {
        ArrayList<Entity> returnList = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.tag.equals(tag)) {
                returnList.add(entity);
            }
        }
        if (returnList.isEmpty()) {
            return null;
        }
        return returnList;
    }
}
