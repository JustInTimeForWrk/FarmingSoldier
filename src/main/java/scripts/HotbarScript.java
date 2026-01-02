package scripts;

import util.Component;
import util.KeyHandler;

import java.awt.event.KeyEvent;

public class HotbarScript extends Component {
    public int currentItemSlot = -1;


    @Override
    public void update() {
        for (int i = 0; i < 9; i++) { // i = 0-8
            if (KeyHandler.getKey(KeyEvent.VK_1 + i)) { // VK_1 = 49, VK_2 = 50, VK_3 = 51...
                currentItemSlot = i;
                System.out.println(currentItemSlot);
            }
        }
    }
}
