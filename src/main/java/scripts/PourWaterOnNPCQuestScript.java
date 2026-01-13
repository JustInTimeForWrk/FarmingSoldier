package scripts;

import org.joml.Vector2f;
import physics.BoxCollider;
import util.ClickAction;
import util.Component;
import util.Entity;
import util.SceneManager;
import view.Window;
import java.awt.event.MouseEvent;

public class PourWaterOnNPCQuestScript extends Component implements ClickAction {
    public static boolean watered = false;
    private BoxCollider collider;
    Vector2f mousePos = new Vector2f();
    ActivateNPCMonologueScript monologueScript;
    FarmingScript playerFarmingScript;
    Entity player;
    float maxPlayerDistance = Window.tileSize * 2f;

    @Override
    public void init() {
        collider = entity.getComponent(BoxCollider.class);
        monologueScript = entity.getComponent(ActivateNPCMonologueScript.class);
        player = SceneManager.getScene("world").findEntityByTag("Player");
        playerFarmingScript = player.getComponent(FarmingScript.class);
        if (watered) {
            monologueScript.setTextWhenInRange("That's much better! Have 15 crops");
        }
    }

    @Override
    public void start() {
        addClickAction();
    }

    @Override
    public void stop() {
        removeClickAction();
    }


    //Input: MouseEvent when clicked, Output: none
    //Purpose: 
    //Example: 
    public void clickAction(MouseEvent e) {
        mousePos.set(e.getX(),e.getY());
        if (collider.checkPointCollision(SceneManager.getCurrentCamera().toWorldPos(mousePos)) && HotbarScript.currentItemSlot == 3 && watered == false && this.entity.transform.position.distance(player.transform.position) <= maxPlayerDistance) {
            monologueScript.setTextWhenInRange("That's much better! Have 15 crops");
            FarmingScript.harvestedPlants += 15;
            playerFarmingScript.setText(FarmingScript.harvestedPlants + "");
            watered = true;
        }
    }
}
