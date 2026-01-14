package physics;

import util.SceneManager;
import util.Transform;
import view.Drawable;
import view.Renderer;
import view.Window;
import org.joml.Vector2f;

import java.awt.*;

public class BoxCollider extends Collider implements Drawable {

    public Vector2f offset;
    public Vector2f size;
    public boolean isTrigger = false;

    //Input: none, Output: none
    //Purpose: Default constructor for the BoxCollider
    //Example: none needed
    public BoxCollider() {
        this.size = new Vector2f(Window.tileSize,Window.tileSize); //default = 48x48
        this.offset = new Vector2f();
    }

    //Input: Vector2f representing the size of the hitbox, Output: none
    //Purpose: Constructor for the BoxCollider
    //Example: BoxCollider(new Vector2f(16,16))
    public BoxCollider(Vector2f size) {
        this.size = size;
        this.offset = new Vector2f();
    }

    //Input: Vector2f representing the size of the hitbox, Vector2f representing the offset of the hitbox from the entity, Output: none
    //Purpose: Constructor for the BoxCollider
    //Example: BoxCollider(new Vector2f(40,16),new vector2f(20,8))
    public BoxCollider(Vector2f size, Vector2f offset) {
        this.size = size;
        this.offset = offset;
    }

    //Input: Vector2f representing the size of the hitbox, Vector2f representing the offset of the hitbox from the entity, and boolean whether the box collider is a trigger, Output: none
    //Purpose: Constructor for the BoxCollider
    //Example: BoxCollider(new Vector2f(48,48),new vector2f(24,24),false)
    public BoxCollider(Vector2f size, Vector2f offset, boolean isTrigger) {
        this.size = size;
        this.offset = offset;
        this.isTrigger = isTrigger;
    }

    //Input: boolean whether the box collider is a trigger, Output: none
    //Purpose: Constructor for the BoxCollider
    //Example: BoxCollider(true)
    public BoxCollider(boolean isTrigger) {
        this.size = new Vector2f(Window.tileSize,Window.tileSize);
        this.offset = new Vector2f();

        this.isTrigger = isTrigger;
    }

    @Override
    public void init() {
        position = new Vector2f(entity.transform.position).add(offset); //Position relative to the player
        size.mul(entity.transform.scale); //sets the size relative to the entity's scale

        hitbox.setRect(position.x,position.y,size.x,size.y);
    }

    @Override
    public void start() {
        addToRenderer();
        PhysicsManager.addCollider(this);
    }

    @Override
    public void stop() {
        removeFromRenderer();
        PhysicsManager.removeCollider(this);
    }

    //Input: none, Output: none
    //Purpose: Special update which only gets updated in the physics handler
    //Example: none needed
    public void updateCollider() {
        position.set(entity.transform.position).add(offset); //Position relative to the player
        hitbox.setRect(position.x,position.y,size.x,size.y);
    }


    @Override
    public void draw(Graphics2D g2) {
        if (Renderer.debugging) {
            Transform transform = entity.transform;
            Vector2f topLeft = SceneManager.getCurrentCamera().toScreenPos(getMin());
            g2.setColor(Color.red);
            g2.drawRect((int)topLeft.x,(int)topLeft.y,(int)size.x-1,(int)size.y-1);

            g2.setColor(Color.yellow);
            Vector2f topLeftCorner = SceneManager.getCurrentCamera().toScreenPos(new Vector2f(transform.position.x-2,transform.position.y-2));
            g2.fillRect((int)topLeftCorner.x,(int)topLeftCorner.y,4,4);
        }
    }
}
