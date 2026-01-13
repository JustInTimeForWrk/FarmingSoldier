package scripts;

import org.joml.Vector2f;
import org.joml.Vector2i;
import physics.BoxCollider;
import util.SceneManager;

public class ActivateNPCMonologueScript extends TextScript {

    private String textWhenInRange;
    Vector2f textPos;

    //Input: String of the text to appear when in range Vector2f of the text's position, Output: none
    //Purpose: constructor for the ActivateNPCMonologueScript
    //Example: none needed
    public ActivateNPCMonologueScript(String textWhenInRange, Vector2f textPos) {
        super(textWhenInRange, new Vector2i());
        this.textPos = textPos;
        this.textWhenInRange = textWhenInRange;
    }

    //Input: String of the text to appear when in range Vector2f of the text's position, Output: none
    //Purpose: constructor for the ActivateNPCMonologueScript
    //Example: none needed
    public void setTextWhenInRange(String newTextWhenInRange) {
        this.textWhenInRange = newTextWhenInRange;
    }

    @Override
    public void update() {
        this.setText("");
    }

    @Override
    public void onTrigger(BoxCollider other) {
        if (other.entity.tag.equals("Player")) {
            Vector2f positionFloatVector = SceneManager.getCurrentCamera().toScreenPos(this.textPos);
            this.position.set((int) positionFloatVector.x, (int) positionFloatVector.y);
            this.setText(textWhenInRange);
        }
    }
}
