package script;

import org.joml.Vector3i;
import physics.BoxCollider;
import util.Component;
import view.MouseHandler;

import java.awt.event.MouseEvent;

public class TestScript extends Component {

    public Vector3i lastClickInfo;

    public BoxCollider boxCollider;

    @Override
    public void start() {
        lastClickInfo = MouseHandler.getLastClicked();
        boxCollider = entity.getComponent(BoxCollider.class);
    }

    @Override
    public void update() {

        if (boxCollider != null && boxCollider.checkPoint(lastClickInfo.x,lastClickInfo.y) && lastClickInfo.z == 1) {
            System.out.println("hi");
        }
    }
}
