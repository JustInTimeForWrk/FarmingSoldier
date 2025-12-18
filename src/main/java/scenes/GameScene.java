package scenes;

import org.joml.Vector2f;
import util.Component;
import util.Entity;
import util.Scene;
import util.Transform;
import view.SpriteRenderer;

public class GameScene extends Scene {

    public GameScene() {
        super("Game");
    }

    @Override
    public void init() {
        addEntityToScene(new Entity("test",new Transform(new Vector2f(200,200)),new Component[]{new SpriteRenderer()}));
    }
}
