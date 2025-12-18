package util;

import physics.BoxCollider;
import org.joml.Vector2f;

import java.util.ArrayList;

public class Entity {
    public String tag;
    public Transform transform;
    public ArrayList<Component> components;

    public Entity(String tag) {
        this.tag = tag;
        transform = new Transform();
        components = new ArrayList<>();
    }

    public Entity(String objectTag, Vector2f pos) {
        this.tag = objectTag;
        transform = new Transform(pos);
        components = new ArrayList<>();
    }

    public Entity(String objectTag, Transform transform) {
        this.tag = objectTag;
        this.transform = new Transform(transform);
        components = new ArrayList<>();
    }

    public Entity(String objectTag, Transform transform, Component[] componentArray) {
        this.tag = objectTag;
        this.transform = new Transform(transform);
        components = new ArrayList<>();
        for (Component c : componentArray) {
            addComponent(c);
        }
    }

    public void onTriggerEnter(BoxCollider other) {

    }

    public void onCollisionEnter(BoxCollider other) {

    }

    public void onDeletion() {
        for (Component c : components) {
            c.onDeletion();
        }
    }

    public void start() { //Waits for everything to be initialized before starting components
        for (Component c : components) {
            c.start();
        }
    }

    public void addComponent(Component c) {
        System.out.println(c.getClass());
        c.setEntity(this);
        components.add(c);
    }

    public void update() {
        for (Component c : components) {
            c.update();
        }
    }

    //Input: Component.class, Output: an Inherited class of Component defined by the input, or null
    public <T extends Component> T getComponent(Class<T> componentType) {
        for (Component comp : components) {
            if (componentType.isInstance(comp)) { //Returns true or false if comp is an instance of the component type
                return componentType.cast(comp); //converts the Component comp to componentType which is returned
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        return tag;
    }

}
