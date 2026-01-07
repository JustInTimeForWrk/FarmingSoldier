package view;

import org.joml.Vector2f;
import util.Transform;

public class Camera {
    Vector2f position = new Vector2f();
    Vector2f offset = new Vector2f();

    //Input: none, Output: none
    //Purpose: constructor for the Camera with default values
    public Camera() {}
    
    //Input: Vector2f representing the position of the Camera, Output: none
    //Purpose: Constructor for the camera
    public Camera(Vector2f pos) {
        this.position.set(pos);
    }
    //Input: two different Vector2f that represents the offset and position of the Camera, Output: none
    //Purpose: Constructor for the camera
    public Camera(Vector2f pos, Vector2f offset) {
        this.position.set(pos);
        this.offset.set(offset);
    }

    //Input: Vector2f representing the new position of the Camera, Output: none
    //Purpose: sets the camera position to the input position
    public void setPosition(Vector2f pos) {
        position.set(pos);
    }

    //Input: Vector2f representing the position of a point in World Space, Output: Vector2f representing the position of a point in Screen Space
    //Purpose: to convert a World Space coordinate to Screen Space coordinates
    public Vector2f toScreenPos(Vector2f input) {
        return new Vector2f(input).sub(position).add(offset);
    }
    //Input: Vector2f representing the position of a point in Screen Space, Output: Vector2f representing the position of a point in World Space
    //Purpose: to convert a Screen Space coordinate to World Space coordinates
    public Vector2f toWorldPos(Vector2f input) {
        return new Vector2f(input).add(position).sub(offset);
    }
}
