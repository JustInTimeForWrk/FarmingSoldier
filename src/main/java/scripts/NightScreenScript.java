package scripts;

import util.Component;
import util.SceneManager;
import view.Drawable;
import view.Renderer;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NightScreenScript extends Component implements Drawable {
    BufferedImage image;
    boolean night = false;
    public SleepScript sleepScript;

    @Override
    public void init() {
        image = Renderer.loadImage("resources/assets/night_screen.png");
        super.init();
    }

    @Override
    public void start() {
        addToRenderer();
        sleepScript = SceneManager.getScene("house").findEntityByTag("Bed").getComponent(SleepScript.class);
    }

    @Override
    public void stop() {
        removeFromRenderer();
    }

    @Override
    public void update() {
        if (sleepScript == null) {
            return;
        }
        night = false;
        if (System.currentTimeMillis() - sleepScript.timer > sleepScript.sleepCD) {
            night = true;
        }
    }
    //Input: graphics object to draw to, Output: none
    //Purpose: draws a translucent blue image when night hits
    //Example: draw(g2) draws a translucent blue screen when night is hit
    public void draw(Graphics2D g2) {
        if (night) {
            g2.drawImage(image, 0,0, null);
        }
    }
}
