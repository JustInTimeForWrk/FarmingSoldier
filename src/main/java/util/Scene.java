package util;

import view.Camera;

import java.util.ArrayList;

public abstract class Scene {
    Camera camera = new Camera();
    String name; //ONLY USED FOR DEBUGGING
    private ArrayList<Entity> entities = new ArrayList<>();
    private ArrayList<Entity> entityToAdd = new ArrayList<>();
    private ArrayList<Entity> entityToRemove = new ArrayList<>();
    public TileMap tileMap;

    boolean isRunning = false;

    //Input: String of the name of the scene, Output: none
    //Purpose: constructor for a Scene
    public Scene(String name) {
        this.name = name;
    }

    //Input: none, Output: none
    //Purpose: updates the scene and the entities in it, also adds/removes entities in the entities queue
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

    //Input: none, Output: none
    //Purpose: initializes the scene on game panel start up
    public void init() {
        if (tileMap != null) {
            tileMap.init();
        }
        for (Entity entity : entities) {
            entity.init();
        }
    }

    //Input: none, Output: none
    //Purpose: starts the scene when it gets loaded as well as its TileMap and Entity arraylist
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

    //Input: none, Output: none
    //Purpose: stops the scene when it gets unloaded as well as its TileMap and Entity arraylist
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

    //Input: Entity representing an entity to be added to the entities arraylist, Output: none
    //Purpose: adds the entity to a queue which will get added to the entities arraylist in the update function
    public void addEntityToScene(Entity entity) {
        entity.parentScene = this;
        if (!isRunning) {
            entities.add(entity);
        } else {
            entity.init();
            entityToAdd.add(entity);
        }
    }

    //Input: Entity representing an entity to be removed from the entities arraylist, Output: none
    //Purpose: removes the entity from a queue which will get removed from the entities arraylist in the update function
    public void removeEntityFromScene(Entity entity) {
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
