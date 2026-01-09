package scenes;

import org.joml.Vector2f;
import org.joml.Vector2i;
import physics.BoxCollider;
import physics.Rigidbody;
import scripts.*;
import util.*;
import view.KeyHandler;
import view.SpriteRenderer;
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
        if (!this.tileMap.loadMap(this.tileMap.filePath)) { // only runs if the tileMap fails to load
            this.tileMap.loadMap("resources/map_house_default.txt");
            this.tileMap.saveTileMap();
        }
        addEntityToScene(new Entity("Player",new Transform(),new Component[]{new MovementScript(),new Rigidbody(),new BoxCollider(new Vector2f(40f,20f),new Vector2f(4f,32f)),
                         new SpriteRenderer("resources/assets/player/monkey_down01.png"),new FarmingScript(),new SwapSceneScript(),new HotbarScript(),new MoveCameraScript()}));
//        addEntityToScene(new Player());
//        addEntityToScene(new HostileTest(new Transform(new Vector2f(1000,1000))));

        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(Window.tileSize*23+12,Window.tileSize*23f), new Vector2f(0.5f,0.5f)),new Component[]{new BoxCollider(true)}));
        addEntityToScene(new Entity("night", new Transform(),new Component[]{new NightScreenScript()}));

        addEntityToScene(new Entity("text", new Transform(), new Component[]{new TextScript("'Esc' for \nmain menu",new Vector2i(Window.tileSize,Window.screenHeight-Window.tileSize))}));
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
