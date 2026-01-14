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

    boolean ifNightTimeThenDraw = false;

    //Input: none, Output: none
    //Purpose: constructor for the NPCSellScript
    //Example: none needed
    public NPCSellScript() {
        super("Fetch "+ cropsNeeded +" crops", new Vector2i(Window.screenWidth/4 * 2,Window.screenHeight/4));
    }

    @Override
    public void onTrigger(BoxCollider other) {
        if (!other.entity.tag.equals("Player")) {
            return;
        }
        ifNightTimeThenDraw = true;

        if (FarmingScript.harvestedPlants < cropsNeeded) {
            setText("Fetch "+ cropsNeeded +" crops");
            return;
        } else {
            setText("'E' to Give "+ cropsNeeded +" crops");
        }

        if (KeyHandler.getKey(KeyEvent.VK_E) && System.currentTimeMillis() - Timer >= PressCoolDownE) {
            giveCrops();
        }
    }

    //Input: none, Output: none
    //Purpose: gives the player's crops to the npc and increases the npc price. Also resets the timer
    //Example: none needed
    private void giveCrops() {
        Timer = System.currentTimeMillis();

        FarmingScript.harvestedPlants -= cropsNeeded;
        cropsNeeded = cropsNeeded * 2 + 4;
    }

    @Override
    public void update() {
        ifNightTimeThenDraw = false;
    }

    //Input: graphics object to draw to, Output: none
    //Purpose: draws a translucent blue image when night hits
    //Example: draw(g2) draws a translucent blue screen when night is hit
    public void draw(Graphics2D g2) {
        if (ifNightTimeThenDraw) {
            super.draw(g2);
        }
    }
}
