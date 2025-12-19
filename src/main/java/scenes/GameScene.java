package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import util.*;
import view.SpriteRenderer;

import java.awt.event.KeyEvent;

public class GameScene extends Scene {

    public GameScene() {
        super("Game");
    }

    @Override
    public void init() {
        tileMap = new TileMap("resources/map01.txt");
        addEntityToScene(new Entity("Test",new Transform(new Vector2f(300,100), new Vector2f(1,1)),new Component[]{new SpriteRenderer(), new BoxCollider()}));
        addEntityToScene(new Player());
        addEntityToScene(new HostileTest(new Transform(new Vector2f(1000,1000))));

        super.init();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_L)) {
            SceneManager.loadSceneByIndex(1);
        }
        super.update();
    }
}
