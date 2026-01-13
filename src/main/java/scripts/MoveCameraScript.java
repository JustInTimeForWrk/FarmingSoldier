package scripts;

import org.joml.Vector2f;
import util.Component;
import util.SceneManager;
import view.Window;

public class MoveCameraScript extends Component {

    @Override
    public void update() {
        SceneManager.getCurrentCamera().setPosition(new Vector2f(entity.transform.position).sub(Window.halfScreenWidth,Window.halfScreenHeight));
    }
}
