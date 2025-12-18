package util;

import org.joml.Vector2f;
import physics.PhysicsHandler;
import view.Camera;
import view.SpriteRenderer;

import java.util.ArrayList;

public abstract class Scene {

    private boolean isRunning = false;
    protected String name;
    Camera camera;
    ArrayList<Entity> entities;

    public Scene(String name) {
        this.name = name;
        camera = new Camera();
        entities = new ArrayList<>();
    }
    
    public void init()
    {

    }

    public void stop() {
        for (int i = entities.size() - 1; i >= 0; i--) {
            removeEntityFromScene(i);
        }
        isRunning = false;
    }

    public void start() {
        if (!isRunning) {
            for (Entity e : entities)
            {
                e.start();
            }
            isRunning = true;
        }
    }

    public void update() {
        for (Entity e : entities) {
            e.update();
        }
        PhysicsHandler.update();
    }

    public void addEntityToScene(Entity e)
    {
        if (!isRunning)
        {
            entities.add(e);
        }
        else
        {
            e.start();
            entities.add(e);
        }
    }

    public void removeEntityFromScene(Entity entity)
    {
        entity.onDeletion();
        entities.remove(entity);
    }

    public void removeEntityFromScene(int index)
    {
        if (-1 < index && index < entities.size()) {
            entities.get(index).onDeletion();
            entities.remove(index);
        }
    }


}
