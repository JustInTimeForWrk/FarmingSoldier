package scripts;

import physics.BoxCollider;
import util.Component;
import util.SceneManager;

public class SwapSceneScript extends Component {
    @Override
    public void onTriggerEnter(BoxCollider other) {
        if (!other.entity.tag.equals("Warp")) {
            return;
        }
        if (SceneManager.currentSceneIndex == 1) {
            //loads game scene
            SceneManager.loadSceneByIndex(0);
        } else if (SceneManager.currentSceneIndex == 0) {
            //loads house scene
            SceneManager.loadSceneByIndex(1);

        }

    }
}
