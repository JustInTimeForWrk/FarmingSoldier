package view;

import java.awt.*;

public interface Drawable {

    //Input: none, Output: none
    //Purpose: adds the object containing the Drawable interface to the renderer
    //Example: none needed
    default void addToRenderer() {
        Renderer.addRenderObject(this);
    }

    //Input: none, Output: none
    //Purpose: removes the object containing the Drawable interface to the renderer
    //Example: none needed
    default void removeFromRenderer() {
        Renderer.removeRenderObject(this);
    }

    //Input: Graphics2D that the function is drawing to, Output: none
    //Purpose: draws anything inside the function
    //Example: none needed
    void draw(Graphics2D g2);
}
