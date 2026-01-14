package view;

import org.joml.Vector2f;
import util.Transform;

public class Camera {
    Vector2f position = new Vector2f();

    //Input: none, Output: none
    //Purpose: constructor for the Camera with default values
    //Example: none needed
    public Camera() {}
    
    //Input: Vector2f representing the position of the Camera, Output: none
    //Purpose: Constructor for the camera
    //Example: Camera(new Vector2f())
    public Camera(Vector2f pos) {
        this.position.set(pos);
    }

    //Input: Vector2f representing the new position of the Camera, Output: none
    //Purpose: sets the camera position to the input position
    //Example: setPosition(new Vector2f(100,100))
    public void setPosition(Vector2f pos) {
        position.set(pos);
    }

    //Input: Vector2f representing the position of a point in World Space, Output: Vector2f representing the position of a point in Screen Space
    //Purpose: to convert a World Space coordinate to Screen Space coordinates
    //Example: toScreenPos(new Vector2f(200,200)) return Vector2f(200,150) if the camera's position is Vector2f(0,50)
    public Vector2f toScreenPos(Vector2f input) {
        return new Vector2f(input).sub(position);
    }
    //Input: Vector2f representing the position of a point in Screen Space, Output: Vector2f representing the position of a point in World Space
    //Purpose: to convert a Screen Space coordinate to World Space coordinates
    // Example: toWorldPos(new Vector2f(100,50)) returns Vector2f(200,100) if the camera's position is Vector2f(100,50)
    public Vector2f toWorldPos(Vector2f input) {
        return new Vector2f(input).add(position);
    }
}
