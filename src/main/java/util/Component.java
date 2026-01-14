package util;

import physics.BoxCollider;
import physics.TileCollider;

public abstract class Component {
    public Entity entity;


    //Input: BoxCollider, Output: none
    //Purpose: gets called when a BoxCollider is intersecting with the parent entity's box collider
    //Example: onEntityCollisionEnter(new BoxCollider())
    public void onEntityCollisionEnter(BoxCollider other) {

    }

    //Input: TileCollider, Output: none
    //Purpose: gets called when a TileCollider is intersecting with the parent entity's box collider
    //Example: onTileCollisionEnter(new TileCollider())
    public void onTileCollisionEnter(TileCollider tile) {

    }

    //Input: BoxCollider, Output: none
    //Purpose: gets called when a box collider that has it's trigger set to true is intersecting with the player
    //Example: onTrigger(new BoxCollider(true))
    public void onTrigger(BoxCollider other) {

    }

    //Input: Entity, Output: none
    //Purpose: sets the entity of the component class to the entity the component is attached to
    //Example: setParentEntity(new Entity())
    public void setParentEntity(Entity parentEntity) {
        this.entity = parentEntity;
    }

    //Input: none, Output: none
    //Purpose: gets called when scene is loading/starting up
    //Example: none needed
    public void start() {

    }
    
    //Input: none, Output: none
    //Purpose: gets called once every frame
    //Example: none needed
    public void update() {

    }

    //Input: none, Output: none
    //Purpose: gets called when the scene is stopping, used to detach
    //Example: none needed
    public void stop() {

    }

    //Input: none, Output: none
    //Purpose: gets called after the Scene gets initialized which when the gamethread starts and after every Entity and script has been built
    //Example: none needed
    public void init() {

    }
}
