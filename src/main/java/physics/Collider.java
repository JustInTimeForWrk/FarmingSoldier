/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics;

import java.awt.Rectangle;
import org.joml.Vector2f;
import util.Component;

/**
 *
 * @author riegj8298
 */

public abstract class Collider extends Component {
    Vector2f position = new Vector2f();
    Rectangle hitbox = new Rectangle();
    public boolean enabled = true;
    
    public Vector2f getMax() {
        return new Vector2f((float)hitbox.getMaxX(),(float)hitbox.getMaxY());
    }

    public Vector2f getMin() {
        return new Vector2f((float)hitbox.getMinX(),(float)hitbox.getMinY());
    }

    public Vector2f getCenter() {
        return new Vector2f((float)hitbox.getCenterX(),(float)hitbox.getCenterY());
    }
    
    public boolean checkPointCollision(float x, float y) {
        return hitbox.contains(x,y);
    }
    
    public boolean checkCollision(Collider other) {
        return hitbox.intersects(other.hitbox);
    }

    public boolean checkLineCollision(float x, float y, float x2, float y2) {
        return hitbox.intersectsLine(x,y,x2,y2);
    }
}
