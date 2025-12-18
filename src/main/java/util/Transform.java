package util;

import org.joml.Vector2f;

public class Transform {
    public Vector2f scale;
    public Vector2f position;

    public Transform() {
        scale = new Vector2f(1.0f,1.0f);
        position = new Vector2f(0.0f,0.0f);
    }

    public Transform(Vector2f pos) {
        scale = new Vector2f(1.0f,1.0f);
        position = pos;
    }

    public Transform(Vector2f pos, Vector2f scale) {
        this.scale = scale;
        this.position = pos;
    }

    public Transform(Transform transform) {
        this.scale = new Vector2f(transform.scale);
        this.position = new Vector2f(transform.position);
    }

    public Transform copy() {
        return new Transform(new Vector2f(this.scale), new Vector2f(this.position));
    }
}
