package util;

import org.joml.Vector2f;
import view.Window;

public class Transform {
    public Vector2f position = new Vector2f();
    public Vector2f scale = new Vector2f();

    //Input: none, Output: none
    //Purpose: constructor for the Transform
    //Example: none needed
    public Transform() {
        this.position.set(0,0);
        this.scale.set(1,1);
    }

    //Input: Vector2f representing the position, Output: none
    //Purpose: constructor for the Transform
    //Example: Transform(new Vector2f(100,100))
    public Transform(Vector2f position) {
        this.position.set(position);
        this.scale.set(1,1);
    }

    //Input: Vector2f representing the position and another one representing the scale, Output: none
    //Purpose: constructor for the Transform
    //Example: new Transform(new Vector2f(100,100), new Vector2f(1,1.5))
    public Transform(Vector2f position, Vector2f scale) {
        this.position.set(position);
        this.scale.set(scale);
    }

    //Input: none, Output: Vector2f representing the center of the pos
    //Purpose: constructor for the Transform
    //Example: Vector2f getCenter() returns new Vector2f(48,48) if the position = Vector2f(24,24) and the scale = Vector2f(1,1)
    public Vector2f getCenter() {
        return new Vector2f(this.position).add(new Vector2f(this.scale).mul(Window.tileSize / 2f));
    }
}
