package util;

import view.MouseHandler;

import java.awt.event.MouseEvent;

public interface ClickAction {

    //Input: none, Output: none
    //Purpose: adds the object containing the Drawable interface to the MouseHandler
    default void addClickAction() {
        MouseHandler.get().addClickAction(this);
    }

    //Input: none, Output: none
    //Purpose: removes the object containing the Drawable interface to the MouseHandler
    default void removeClickAction() {
        MouseHandler.get().removeClickAction(this);
    }

    //Input: MouseEvent representing the data of the mouse when it was clicked, Output: none
    //Purpose: gets called when a mouse click happens
    void clickAction(MouseEvent e);
}
