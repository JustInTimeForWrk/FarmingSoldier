package view;

import java.awt.*;

public interface Drawable {

    //Input: none, Output: none
    //Purpose: adds the object containing the Drawable interface to the renderer
    default void addToRenderer() {
        Renderer.addRenderObject(this);
    }

    //Input: none, Output: none
    //Purpose: removes the object containing the Drawable interface to the renderer
    default void removeFromRenderer() {
        Renderer.removeRenderObject(this);
    }

    //Input: Graphics2D that the function is drawing to, Output: none
    //Purpose: draws anything inside the function
    void draw(Graphics2D g2);
}
