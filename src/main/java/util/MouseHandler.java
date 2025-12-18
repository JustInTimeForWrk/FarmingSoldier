package util;

import org.joml.Vector2i;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseHandler implements MouseListener {

    private static MouseHandler instance;
    private Vector2i mousePos;
    private boolean[] keys;
    private MouseMotionHandler mouseMotionHandler;

    private MouseHandler() {
        keys = new boolean[4];
        mousePos = new Vector2i();
        mouseMotionHandler = new MouseMotionHandler();
    }

    public static MouseHandler get() {
        if (instance == null) {
            instance = new MouseHandler();
        }
        return instance;
    }

    public static MouseMotionHandler getMotionHandler() {
        return instance.mouseMotionHandler;
    }

    public static Vector2i getMousePos() {
        return instance.mousePos.set(instance.mouseMotionHandler.getMousePos()); //prevents overwriting mouse pos values
    }

    @Override
    public void mouseClicked(MouseEvent e) {

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
}
