package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private static KeyHandler instance;
    private boolean[] keys;

    private KeyHandler() {
        keys = new boolean[256];
    }

    public static KeyHandler get() {
        if (instance == null) {
            instance = new KeyHandler();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //Unnecessary
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code < keys.length) {
            keys[code] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code < keys.length) {
            keys[code] = false;
        }
    }

    public static boolean getKey(int code) {
        if (code < instance.keys.length) {
            return instance.keys[code];
        }
        return false;
    }

}
