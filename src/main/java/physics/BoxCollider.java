package physics;

import org.joml.Vector2f;
import util.Component;
import view.Window;

import java.awt.*;

public class BoxCollider extends Component {

    public Vector2f offset;
    public Vector2f size;
    public Vector2f position = new Vector2f();

    public boolean enabled = true;
    public boolean isTrigger = false;

    public Rectangle hitbox = new Rectangle();

    public BoxCollider() {
        this.size = new Vector2f(1,1); //default = 48x48
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
    public void start() {
        position = new Vector2f(entity.transform.position).add(offset); //Position relative to the player
        size.mul(entity.transform.scale); //sets the size relative to the entity's scale

        hitbox.setRect(position.x,position.y,size.x,size.y);

        PhysicsHandler.addCollider(this);
    }

    public Vector2f getMax() {
        return new Vector2f((float)hitbox.getMaxX(),(float)hitbox.getMaxY());
    }

    public Vector2f getMin() {
        return new Vector2f((float)hitbox.getMinX(),(float)hitbox.getMinY());
    }

    public Vector2f getCenter() {
        return new Vector2f((float)hitbox.getCenterX(),(float)hitbox.getCenterY());
    }

    @Override
    public void onDeletion() {
        PhysicsHandler.removeCollider(this);
    }

    public void updateCollider() { //Special update which only gets updated in the physics handler
        position = new Vector2f(entity.transform.position).add(offset); //Position relative to the player

        hitbox.setRect(position.x,position.y,size.x,size.y);
    }

    public boolean checkCollision(BoxCollider other) {
        return hitbox.intersects(other.hitbox);
    }

    public boolean checkPoint(Vector2f coords) {
        return hitbox.contains(new Point((int)coords.x,(int)(coords.y)));
    }
    public boolean checkPoint(float x, float y) {
        return hitbox.contains(new Point((int)x,(int)y));
    }
}
