package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import script.TestScript;
import util.Component;
import util.Entity;
import util.Scene;
import util.Transform;
import view.SpriteRenderer;
import view.Window;

public class TestScene extends Scene {
    public TestScene() {
        super("Game");
    }

    @Override
    public void init() {
        addEntityToScene(new Entity("Button Test",
                new Transform(new Vector2f(Window.halfScreenWidth,Window.halfScreenHeight).div(2),
                new Vector2f(Window.halfScreenWidth,Window.halfScreenHeight)),
                new Component[]{new SpriteRenderer(),new BoxCollider(),new TestScript()}));
    }

}
