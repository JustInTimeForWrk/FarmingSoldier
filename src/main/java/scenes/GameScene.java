package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import util.*;
import view.SpriteRenderer;

import java.awt.event.KeyEvent;

public class GameScene extends Scene {
    Entity player;
    public GameScene() {
        super("Game");
    }

    @Override
    public void init() {
        tileMap = new TileMap("resources/map01.txt");
        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(900,300), new Vector2f(1,1)),new Component[]{new SpriteRenderer(), new BoxCollider(true)}));
        addEntityToScene(new Player());
//        addEntityToScene(new HostileTest(new Transform(new Vector2f(1000,1000))));

        super.init();
    }

    @Override
    public void start() {

        if ((player = findEntityByTag("Player")) != null) {
            player.transform.position.set(500,800);
        }
        super.start();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_L)) {
            SceneManager.loadSceneByIndex(1);
        }
        super.update();
    }
}
