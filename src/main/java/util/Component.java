package util;

import physics.BoxCollider;
import physics.TileCollider;

public abstract class Component {
    public Entity entity;


    public void onEntityCollisionEnter(BoxCollider other) {

    }

    public void onTileCollisionEnter(TileCollider tile) {

    }

    public void onTriggerEnter(BoxCollider other) {

    }

    public void setParentEntity(Entity parentEntity) {
        this.entity = parentEntity;
    }

    public void start() {

    }

    public void update() {

    }

    public void destroy() {

    }

    public void stop() {

    }

    public void init() {

    }
}
