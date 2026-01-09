package scripts;

import org.joml.Vector2i;
import physics.BoxCollider;
import view.KeyHandler;
import view.Window;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPCSellScript extends TextScript {
    long Timer = System.currentTimeMillis();
    double PressCoolDownE = 200;
    public static int cropsNeeded = 10;

    boolean draw = false;

    public NPCSellScript() {
        super("Fetch "+ cropsNeeded +" crops", new Vector2i(Window.screenWidth/4 * 2,Window.screenHeight/4));
    }

    @Override
    public void onTrigger(BoxCollider other) {
        if (!other.entity.tag.equals("Player")) {
            return;
        }
        draw = true;

        if (FarmingScript.harvestedPlants < cropsNeeded) {
            setText("Fetch "+ cropsNeeded +" crops");
            return;
        } else {
            setText("'E' to Give "+ cropsNeeded +" crops");
        }

        if (KeyHandler.getKey(KeyEvent.VK_E) && System.currentTimeMillis() - Timer >= PressCoolDownE) {
            Timer = System.currentTimeMillis();

            FarmingScript.harvestedPlants -= cropsNeeded;
            cropsNeeded = cropsNeeded * 2 + 4;
        }
    }

    @Override
    public void update() {
        draw = false;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (draw) {
            super.draw(g2);
        }
    }
}
