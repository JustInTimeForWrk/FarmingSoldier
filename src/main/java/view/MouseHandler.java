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

    private MouseHandler() {
        keys = new boolean[5];
        mousePos = new Vector2i();
        mouseMotionHandler = new MouseMotionHandler();
        clickActions = new ArrayList<>();
    }

    public static MouseHandler get() {
        if (instance == null) {
            instance = new MouseHandler();
        }
        return instance;
    }

    public void addClickAction(ClickAction clickAction) {
        clickActions.add(clickAction);
    }

    public void removeClickAction(ClickAction clickAction) {
        clickActions.remove(clickAction);
    }

    public static MouseMotionHandler getMotionHandler() {
        return instance.mouseMotionHandler;
    }

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

    public static boolean getKey(int code) {
        if (0 <= code && code < instance.keys.length ) {
            return instance.keys[code];
        }
        return false;
    }
    
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos.set(e.getX(),e.getY());
    }

    public Vector2i getMousePos() {
        return mousePos;
    }


    public void reset() {
        mousePos.set(0,0);
    }
}
