package view;

import org.joml.Vector2f;

public class Camera {
    public Vector2f position;
    public Vector2f offset;

    public Camera() {
        offset = new Vector2f();
        position = new Vector2f();
    }

    public Camera(Vector2f startPos) {
        position = startPos;
    }
    public Camera(Vector2f startPos, Vector2f startOffset) {
        position = startPos;
        offset = startOffset;
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
