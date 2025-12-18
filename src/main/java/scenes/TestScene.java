package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import util.*;
import view.SpriteRenderer;

import java.awt.event.KeyEvent;

public class TestScene extends Scene {

    public TestScene() {
        super("test");
    }

    @Override
    public void init() {
        addEntityToScene(new Entity("messy test", new Transform(new Vector2f(100,100), new Vector2f(1,1)), new Component[]{new SpriteRenderer("uh oh"), new BoxCollider()}));
        super.init();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_K)) {
            SceneManager.loadSceneByIndex(0);
        }
        super.update();
    }
}
