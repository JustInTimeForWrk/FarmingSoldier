package util;

import physics.BoxCollider;
import physics.TileCollider;

public abstract class Component {
    public Entity entity;


    //Input: BoxCollider, Output: nothing/void
    //Purpose: gets called when a BoxCollider is intersecting with the parent entity's box collider
    public void onEntityCollisionEnter(BoxCollider other) {

    }

    //Input: TileCollider, Output: nothing/void
    //Purpose: gets called when a TileCollider is intersecting with the parent entity's box collider
    public void onTileCollisionEnter(TileCollider tile) {

    }

    //Input: BoxCollider, Output: nothing/void
    //Purpose: gets called when an a box collider that has it's trigger set to true is intersecting with the player
    public void onTriggerEnter(BoxCollider other) {

    }

    //Input: Entity, Output: nothing/void
    //Purpose: sets the entity of the component class to the entity the component is attached to
    public void setParentEntity(Entity parentEntity) {
        this.entity = parentEntity;
    }

    //Input: nothing/void, Output: nothing/void
    //Purpose: gets called when scene is loading/starting up
    public void start() {

    }

    
    //Input: nothing/void, Output: nothing/void
    //Purpose: gets called once every frame
    public void update() {

    }

    
    public void destroy() {

    }

    //Input: Entity, Output: nothing/void
    //Purpose: gets called when the scene is stopping, used to detach
    public void stop() {

    }

    //Input: nonthing/void, Output: nothing/void
    //Purpose: gets called after the Scene gets initialized which when the gamethread starts and after every Entity and script has been built
    public void init() {

    }
}
