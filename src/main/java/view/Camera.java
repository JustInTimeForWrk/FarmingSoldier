package view;

import org.joml.Vector2f;
import util.Transform;

public class Camera {
    Vector2f position = new Vector2f();
    Vector2f offset = new Vector2f();

    public Camera() {}

    public Camera(Vector2f pos) {
        this.position.set(pos);
    }
    public Camera(Vector2f pos, Vector2f offset) {
        this.position.set(pos);
        this.offset.set(offset);
    }

    public void setPosition(Vector2f pos) {
        position.set(pos);
    }

    public Vector2f toScreenPos(Vector2f input) {
        return new Vector2f(input).sub(position).add(offset);
    }
    public Vector2f toWorldPos(Vector2f input) {
        return new Vector2f(input).add(position).sub(offset);
    }
}
