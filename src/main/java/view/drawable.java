package view;

import java.awt.*;

public interface drawable {
    default void addToRenderer() {
        Renderer.addRenderObject(this);
    }

    default void removeFromRenderer() {
        Renderer.removeRenderObject(this);
    }

    void draw(Graphics2D g2);
}
