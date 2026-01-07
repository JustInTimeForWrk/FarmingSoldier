package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private static KeyHandler instance;
    private boolean[] keys;

    //Input: none, Output: none
    //Purpose: constructor for the KeyHandler
    private KeyHandler() {
        keys = new boolean[256];
    }

    //Input: none, Output: KeyHandler representing the instance
    //Purpose: grabs the instance of the key handler
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

    //Input: int representing a java.awt.KeyEvent key code, Output: boolean representing if the key is pressed
    //Purpose: constructor for the MenuPanel
    public static boolean getKey(int code) {
        if (code < instance.keys.length) {
            return instance.keys[code];
        }
        return false;
    }

    //Input: none, Output: none
    //Purpose: reset function for when quitting out of the game
    public static void reset() {
        instance.keys = new boolean[256];
    }

}
