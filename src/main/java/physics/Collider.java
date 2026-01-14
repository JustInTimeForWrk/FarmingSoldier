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
    
    //Input: none, Output: Vector2f containing the biggest x and y values which is the bottom right corner of the rectangular object
    //Purpose: to be used in calculations like collision resolution where you need to know the shape of the object\
    //Example: getMax() returns new Vector2f(6,6) if the hitbox is a new Rectangle(2,2,6,6)
    public Vector2f getMax() {
        return new Vector2f((float)hitbox.getMaxX(),(float)hitbox.getMaxY());
    }

    //Input: none, Output: Vector2f containing the smallest x and y values which is the top left corner of the rectangular object
    //Purpose: to be used in calculations like collision resolution where you need to know the shape of the object
    //Example: getMin() returns new Vector2f(2,2) if the rectangle is a new Rectangle(2,2,6,6)
    public Vector2f getMin() {
        return new Vector2f((float)hitbox.getMinX(),(float)hitbox.getMinY());
    }

    //Input: none, Output: Vector2f containing the x and y values of the center of the rectangle
    //Purpose: to be used in calculations like collision resolution where you need to know the shape of the object as well as distance calculations
    //Example: getCenter() returns new Vector2f(4,4) if the rectangle is a new Rectangle(2,2,6,6)
    public Vector2f getCenter() {
        return new Vector2f((float)hitbox.getCenterX(),(float)hitbox.getCenterY());
    }

    //Input: the other collider that is checking collision, Output: boolean of whether the colliders are intersecting
    //Purpose: checks if two colliders are intersecting
    //Example: checkCollision(new Collider()) returns true if the collider hitbox intersects with the other collider
    public boolean checkCollision(Collider other) {
        return hitbox.intersects(other.hitbox);
    }

    //Input: floats representing the x and y of a point, Output: boolean of whether the collider intersects with the line
    //Purpose: checks if a point intersects this collider
    //Example: checkPointCollision(50,50) returns true if the collider hitbox intersects with the point, e.g. hitbox = new Rectangle(25,25,100,100)
    public boolean checkPointCollision(Vector2f point) {
        return hitbox.contains(point.x, point.y);
    }

    /*
                    ===============
                    Unused Function
                    ===============
    //Input: floats representing the x and y of one point and the x and y of the other point, Output: boolean of whether the collider intersects with the line
    //Purpose: checks if a line between two points intersects this collider
    //Example: checkLineCollision(10,20,100,200) returns true if the collider hitbox intersects with the line, e.g. hitbox = new Rectangle(25,25,100,100)
    public boolean checkLineCollision(float x, float y, float x2, float y2) {
        return hitbox.intersectsLine(x,y,x2,y2);
    }
     */
}
