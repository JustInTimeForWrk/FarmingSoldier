package scenes;

import org.joml.Vector2f;
import org.joml.Vector2i;
import physics.BoxCollider;
import scripts.NightScreenScript;
import scripts.TextScript;
import util.*;
import view.KeyHandler;
import view.Window;

import java.awt.event.KeyEvent;

public class GameScene extends Scene {
    Entity player;
    public GameScene() {
        super("Game");
    }

    @Override
    public void init() {
        tileMap = new TileMap("resources/map_world.txt");

        addEntityToScene(new Player());
//        addEntityToScene(new HostileTest(new Transform(new Vector2f(1000,1000))));

        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(Window.tileSize*23+12,Window.tileSize*23f), new Vector2f(0.5f,0.5f)),new Component[]{new BoxCollider(true)}));
        addEntityToScene(new Entity("night", new Transform(),new Component[]{new NightScreenScript()}));

        addEntityToScene(new Entity("text", new Transform(), new Component[]{new TextScript(null,new Vector2i(100,100))}));
        super.init();
    }

    @Override
    public void start() {

        if ((player = findEntityByTag("Player")) != null) {
            player.transform.position.set(Window.tileSize*23, Window.tileSize*21.5);
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
