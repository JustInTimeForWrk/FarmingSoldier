package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import util.*;
import view.SpriteRenderer;

import java.awt.event.KeyEvent;

public class HouseScene extends Scene {
    Entity player;
    public HouseScene() {
        super("house");
    }

    @Override
    public void init() {
        this.tileMap = new TileMap("resources/map_house.txt");
        addEntityToScene(new Player());
        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(900,300), new Vector2f(1,1)),new Component[]{new SpriteRenderer(), new BoxCollider(true)}));

        super.init();
    }

    @Override
    public void start() {

        if ((player = findEntityByTag("Player")) != null) {
            player.transform.position.set(100,100);
        }
        super.start();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_K)) {
            SceneManager.loadSceneByIndex(0);
        }
        super.update();
    }
}
