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
        sleepScript = SceneManager.getScene(1).findEntityByTag("Bed").getComponent(SleepScript.class);
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
        if (System.currentTimeMillis() - sleepScript.timer > sleepScript.sleepCD) {
            night = true;
        } else {
            night = false;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (night && image != null) {
            g2.drawImage(image, 0,0, null);
        }
    }
}
