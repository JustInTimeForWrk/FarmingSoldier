package physics;

import view.Window;
import org.joml.Vector2f;
import util.Component;

import java.awt.*;

public class BoxCollider extends Collider {

    public Vector2f offset;
    public Vector2f size;
    public boolean isTrigger = false;

    public BoxCollider() {
        this.size = new Vector2f(Window.tileSize,Window.tileSize); //default = 48x48
        this.offset = new Vector2f();
    }

    public BoxCollider(Vector2f size) {
        this.size = size;
        this.offset = new Vector2f();
    }

    public BoxCollider(Vector2f size, Vector2f offset) {
        this.size = size;
        this.offset = offset;
    }

    public BoxCollider(Vector2f size, Vector2f offset, boolean isTrigger) {
        this.size = size;
        this.offset = offset;
        this.isTrigger = isTrigger;
    }

    public BoxCollider(boolean isTrigger) {
        this.size = new Vector2f(Window.tileSize,Window.tileSize);
        this.offset = new Vector2f();

        this.isTrigger = isTrigger;
    }

    public void setEnabled(boolean TorF) {
        this.enabled = TorF;
    }

    @Override
    public void init() {
        position = new Vector2f(entity.transform.position).add(offset); //Position relative to the player
        size.mul(entity.transform.scale); //sets the size relative to the entity's scale

        hitbox.setRect(position.x,position.y,size.x,size.y);
    }

    @Override
    public void start() {

        PhysicsManager.addCollider(this);
    }

    @Override
    public void destroy() {
        PhysicsManager.removeCollider(this);
    }

    @Override
    public void stop() {
        PhysicsManager.removeCollider(this);
    }

    public void updateCollider() { //Special update which only gets updated in the physics handler
        position = new Vector2f(entity.transform.position).add(offset); //Position relative to the player

        hitbox.setRect(position.x,position.y,size.x,size.y);
    }

}
