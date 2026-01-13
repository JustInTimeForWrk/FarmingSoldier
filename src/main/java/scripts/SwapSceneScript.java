package scripts;

import physics.BoxCollider;
import util.Component;
import util.SceneManager;

public class SwapSceneScript extends Component {
    @Override
    public void onTrigger(BoxCollider other) {
        if (!other.entity.tag.equals("Warp")) {
            return;
        }
        if (SceneManager.currentSceneName.equals("world")) {
            //loads game scene
            SceneManager.loadSceneByName("house");
        } else if (SceneManager.currentSceneName.equals("house")) {
            //loads house scene
            SceneManager.loadSceneByName("world");
        }
    }
}
