package scripts;

import util.Component;
import view.Drawable;
import view.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class HotbarScript extends Component implements Drawable {
    public static int currentItemSlot = -1;
    Font font = new Font("Arial", Font.BOLD, 12);

    String[] itemNames = {"","Hoe","Seeds","Water","Harvest","","","",""};

    @Override
    public void start() {
        addToRenderer();
    }

    @Override
    public void stop() {
        removeFromRenderer();
    }

    @Override
    public void update() {
        for (int i = 0; i < 9; i++) { // i = 0-8
            if (KeyHandler.getKey(KeyEvent.VK_1 + i)) { // VK_1 = 49, VK_2 = 50, VK_3 = 51...
                currentItemSlot = i;
            }
        }
    }

    //Input: graphics object to draw to, Output: none
    //Purpose: draws the hotbar
    //Example: draw(g2) draws white text and hollow box extending out from the top left corner of the screen, if a slot is selected, the corresponding box and text are both green
    public void draw(Graphics2D g2) {
        for (int i = 0; i < 9; i++) { // i = 0 - 8
            g2.setColor(Color.white);
            if (i == currentItemSlot) {
                g2.setColor(Color.green);
            }
            g2.drawRect(i*48+i,0,48,48);
            g2.setFont(font);
            g2.drawString(itemNames[i],i*48 + 3 + i,32);
        }
    }
}
