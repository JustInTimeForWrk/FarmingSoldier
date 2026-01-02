package util;

import view.MouseHandler;

import java.awt.event.MouseEvent;

public interface ClickAction {

    default void addClickAction() {
        MouseHandler.get().addClickAction(this);
    }

    default void removeClickAction() {
        MouseHandler.get().removeClickAction(this);
    }

    void clickAction(MouseEvent e);
}
