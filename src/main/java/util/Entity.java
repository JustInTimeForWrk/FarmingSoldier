package util;

import physics.BoxCollider;
import physics.TileCollider;

import java.util.ArrayList;

public class Entity {
    public ArrayList<Component> components;
    public Transform transform;
    public String tag;
    public Scene parentScene;
    public boolean initialized = false;

    //Input: none, Output: none
    //Purpose: default constructor for the entity
    //Example: none needed
    public Entity() {
        this.tag = "Entity";
        this.transform = new Transform();
        components = new ArrayList<>();
    }

    //Input: String representing the entity's tag, Transform of the entity, Output: none
    //Purpose: Constructor for the entity
    //Example: Entity("MyEntity", new Transform())
    public Entity(String objectTag, Transform transform) {
        this.tag = objectTag;
        this.transform = transform;
        components = new ArrayList<>();
    }

    //Input: String representing the entity's tag, Transform of the entity, a list of components that the entity will build with, Output: none
    //Purpose: Constructor for the entity
    //Example: Entity("MyEntity", new Transform(), new Component[]{ new SleepScript(), new SpriteRenderer(), new BoxCollider()})
    public Entity(String entityTag, Transform transform, Component[] componentArray) {
        this.tag = entityTag;
        this.transform = transform;
        components = new ArrayList<>();
        for (Component c : componentArray) {
            addComponent(c);
        }
    }

    //Input: none, Output: none
    //Purpose: stops all components when entering the parent scene
    //Example: none needed
    public void start() {
        for (Component c : components) {
            c.start();
        }
    }

    //Input: none, Output: none
    //Purpose: updates the components every update frame.
    //Example: none needed
    public void update() {
        for (Component c : components) {
            c.update();
        }
    }

    //Input: none, Output: none
    //Purpose: initializes the components on GameManager.startGame() where the scenes are initialized.
    //Example: none needed
    public void init() {
        for (Component c : components) {
            c.init();
        }
        initialized = true;
    }

    //Input: none, Output: none
    //Purpose: stops all components when leaving the parent scene
    //Example: none needed
    public void stop() {
        for (Component c : components) {
            c.stop();
        }
    }

    //Input: component to add, Output: none
    //Purpose: adds a component and sets its parent entity to this
    //Example: addComponent(new MovementScript())
    public void addComponent(Component c) {
        c.setParentEntity(this);
        components.add(c);
    }

    //Input: class representing the desired component type to return, Output: an object extending the Component class
    //Purpose: to find a specific component in an entity
    //Example: getComponent(SleepScript.class) returns a SleepScript if there is one added to a player or returns null if none are found
    public <T extends Component> T getComponent(Class<T> componentType) {
        for (Component comp : components) {
            if (componentType.isInstance(comp)) { //Returns true or false if comp is an instance of the component type
                return componentType.cast(comp); //converts the Component comp to componentType which is returned
            }
        }
        return null;
    }

    //Input: BoxCollider with isTrigger = true of the other entity that is colliding with this one, Output: none
    //Purpose: gets called when colliding with an entity with a BoxCollider that has isTrigger = true
    //Example: onTriggerEnter(new Entity("MyEntity", new Transform, new Component[]{new BoxCollider(true)} ))
    public void onTriggerEnter(BoxCollider other) {
        for (Component c : components) {
            c.onTrigger(other);
        }
    }

    //Input: BoxCollider with isTrigger = false of the other entity that is colliding with this one, Output: none
    //Purpose: gets called when colliding with an entity with a BoxCollider that has isTrigger = false
    //Example: onEntityCollisionEnter(new Entity("MyEntity", new Transform, new Component[]{new BoxCollider()} ))
    public void onEntityCollisionEnter(BoxCollider other) {
        for (Component c : components) {
            c.onEntityCollisionEnter(other);
        }
    }

    //Input: TileCollider of the tile the entity is colliding with, Output: none
    //Purpose: gets called when colliding with a tile with a TileCollider
    //Example: onTileCollisionEnter(new Tile())
    public void onTileCollisionEnter(TileCollider tile) {
        for (Component c : components) {
            c.onTileCollisionEnter(tile);
        }
    }
}
