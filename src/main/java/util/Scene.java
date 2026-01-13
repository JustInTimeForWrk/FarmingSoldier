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
    //Example: Scene("MyScene")
    public Scene(String name) {
        this.name = name;
    }

    //Input: none, Output: none
    //Purpose: updates the scene and the entities in it, also adds/removes entities in the entities queue
    //Example: none needed
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
    //Example: none needed
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
    //Example: none needed
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
    //Example: none needed
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
    //Example: none needed
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
    //Example: none needed
    public void removeEntityFromScene(Entity entity) {
        if (!isRunning) {
            entities.remove(entity);
        } else {
            entityToRemove.add(entity);
        }
    }

    //Input: none, Output: returns the Camera of the scene, which is different with each scene
    //Purpose: to get the camera of the scene
    //Example: getCamera() returns the instance scene's Camera
    public Camera getCamera() {
        return this.camera;
    }

    //Input: String representing the tag of an entity, Output: Entity with the tag in the input
    //Purpose: to get an entity in the scene
    //Example: findEntityByTag("Player") returns the first Entity with the "Player" tag attached
    public Entity findEntityByTag(String tag) {
        for (Entity entity : entities) {
            if (entity.tag.equals(tag)) {
                return entity;
            }
        }
        return null;
    }

    //Input: String representing the tag of an entity, Output: ArrayList of all entities with the input tag
    //Purpose: to get all entities with the same tag in the scene
    //Example: findEntityByTag("Hostile") returns the every Entity with the "Hostile" tag attached
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
