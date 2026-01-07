package view;

import org.joml.Vector2i;
import util.ClickAction;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class MouseHandler implements MouseListener {

    private static MouseHandler instance;
    private Vector2i mousePos;
    private boolean[] keys;
    private MouseMotionHandler mouseMotionHandler;
    private ArrayList<ClickAction> clickActions;

    //Input: none, Output: none
    //Purpose: constructor for the MouseHandler
    private MouseHandler() {
        keys = new boolean[5];
        mousePos = new Vector2i();
        mouseMotionHandler = new MouseMotionHandler();
        clickActions = new ArrayList<>();
    }

    //Input: none, Output: MouseHandler
    //Purpose: returns the instance of the MouseHandler
    public static MouseHandler get() {
        if (instance == null) {
            instance = new MouseHandler();
        }
        return instance;
    }

    //Input: clickAction, Output: none
    //Purpose: adds a click action to the click actions list which gets called when the mouse is clicked
    public void addClickAction(ClickAction clickAction) {
        clickActions.add(clickAction);
    }

    //Input: clickAction, Output: none
    //Purpose: removes a click action from the click actions list
    public void removeClickAction(ClickAction clickAction) {
        clickActions.remove(clickAction);
    }

    //Input: none, Output: MouseMotonHandler instance
    //Purpose: returns MouseMotionHandler to be added to a panel as a listener
    public static MouseMotionHandler getMotionHandler() {
        return instance.mouseMotionHandler;
    }

    //Input: none, Output: Vector2i representing MousePosition
    //Purpose: returns a Vector2i representing the location of the cursor
    public static Vector2i getMousePos() {
        return instance.mousePos.set(instance.mouseMotionHandler.getMousePos()); //prevents overwriting mouse pos values
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (ClickAction clickAction : clickActions) {
            clickAction.clickAction(e);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int code = e.getButton();
        if (0 <= code && code < keys.length ) {
            keys[code] = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int code = e.getButton();
        if (0 <= code && code < keys.length ) {
            keys[code] = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    //Input: int representing a key code of a mouse button, Output: boolean representing if it is pressed or not
    //Purpose: returns a boolean of whether the mouse button is being pressed or not
    public static boolean getKey(int code) {
        if (0 <= code && code < instance.keys.length ) {
            return instance.keys[code];
        }
        return false;
    }

    //Input: none, Output: none
    //Purpose: reset function for when quitting out of the game
    public static void reset() {
        instance.clickActions.clear();
        instance.mouseMotionHandler.reset();
        instance.keys = new boolean[5];
    }


}

class MouseMotionHandler implements java.awt.event.MouseMotionListener {

    private Vector2i mousePos = new Vector2i();

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos.set(e.getX(),e.getY());
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.set(e.getX(),e.getY());
    }

    //Input: none, Output: Vector2i representing the mouse position
    //Purpose: reset function for when quitting out of the game
    public Vector2i getMousePos() {
        return mousePos;
    }

    //Input: none, Output: none
    //Purpose: reset function for when quitting out of the game
    public void reset() {
        mousePos.set(0,0);
    }
}
