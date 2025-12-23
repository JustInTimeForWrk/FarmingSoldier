package util;

import physics.BoxCollider;
import physics.TileCollider;

import java.util.ArrayList;

public class Entity {
    public ArrayList<Component> components;
    public Transform transform;
    public String tag;
    public Scene parentScene;

    public Entity() {
        this.tag = "Entity";
        this.transform = new Transform();
        components = new ArrayList<>();
    }

    public Entity(String objectTag, Transform transform) {
        this.tag = objectTag;
        this.transform = transform;
        components = new ArrayList<>();
    }

    public Entity(String objectTag, Transform transform, Component[] componentArray) {
        this.tag = objectTag;
        this.transform = transform;
        components = new ArrayList<>();
        for (Component c : componentArray) {
            addComponent(c);
        }
    }

    public void start() {
        for (Component c : components) {
            c.start();
        }
    }

    public void destroy() {
        for (Component c : components) {
            c.destroy();
        }
    }

    public void update() {
        for (Component c : components) {
            c.update();
        }
    }

    public void init() {
        for (Component c : components) {
            c.init();
        }
    }

    public void stop() {

    }

    public void addComponent(Component c) {
        c.setParentEntity(this);
        components.add(c);
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
        for (Component comp : components) {
            if (componentType.isInstance(comp)) { //Returns true or false if comp is an instance of the component type
                return componentType.cast(comp); //converts the Component comp to componentType which is returned
            }
        }
        return null;
    }

    public void onTriggerEnter(BoxCollider other) {
        for (Component c : components) {
            c.onTriggerEnter(other);
        }
    }

    public void onEntityCollisionEnter(BoxCollider other) {
        for (Component c : components) {
            c.onEntityCollisionEnter(other);
        }
    }

    public void onTileCollisionEnter(TileCollider tile) {
        for (Component c : components) {
            c.onTileCollisionEnter(tile);
        }
    }
}
