package util;

import org.joml.Vector2f;

public class Transform {
    public Vector2f position = new Vector2f();
    public Vector2f scale = new Vector2f();

    public Transform() {
        this.position.set(0,0);
        this.scale.set(1,1);
    }

    public Transform(Vector2f position) {
        this.position.set(position);
        this.scale.set(1,1);
    }

    public Transform(Vector2f position, Vector2f scale) {
        this.position.set(position);
        this.scale.set(scale);
    }
}
