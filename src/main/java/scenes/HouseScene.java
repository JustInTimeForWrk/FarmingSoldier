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

public class HouseScene extends Scene {
    Entity player;
    public HouseScene() {
        super("house");
    }

    @Override
    public void init() {
        this.getCamera().setPosition(new Vector2f(0,-Window.tileSize));
        this.tileMap = new TileMap("resources/map_house.txt");
        if (!this.tileMap.loadMap(this.tileMap.filePath)) { // only runs if the tileMap fails to load
            this.tileMap.loadMap("resources/map_house_default.txt");
            this.tileMap.saveTileMap();
        }
        addEntityToScene(new Entity("npc", new Transform(new Vector2f(Window.tileSize * 5, Window.tileSize * 0.5f)), new Component[]{new SpriteRenderer("resources/assets/hostile/hostile_down01.png"), new BoxCollider()}));
        addEntityToScene(new Entity("npc sell box", new Transform(new Vector2f(Window.tileSize * 4.5f, 0)), new Component[]{new BoxCollider(new Vector2f(Window.tileSize*2,Window.tileSize*2),new Vector2f(),true), new NPCSellScript()}));
        addEntityToScene(new Entity("Bed",new Transform(new Vector2f(Window.tileSize * 14, Window.tileSize * 5)),new Component[]{new SpriteRenderer("resources/assets/entity/bed.png"), new BoxCollider(new Vector2f(Window.tileSize - 12,Window.tileSize * 2 - 20),new Vector2f(6,8), true), new SleepScript()}));
//        addEntityToScene(new Player());
        addEntityToScene(new Entity("text", new Transform(), new Component[]{new TextScript("'Esc' for \nmain menu",new Vector2i(Window.tileSize,Window.screenHeight-Window.tileSize))}));
        addEntityToScene(new Entity("Player",new Transform(),new Component[]{new MovementScript(),new Rigidbody(),new BoxCollider(new Vector2f(40f,20f),new Vector2f(4f,32f)),
                         new SpriteRenderer("resources/assets/player/monkey_down01.png"),new SwapSceneScript()}));
        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(Window.tileSize * 8f, Window.tileSize * 0.1f)),new Component[]{new BoxCollider(true)}));

        addEntityToScene(new Entity("night", new Transform(),new Component[]{new NightScreenScript()}));
        super.init();
    }

    @Override
    public void start() {
        if ((player = findEntityByTag("Player")) != null) {
            player.transform.position.set(Window.tileSize * 8f,Window.tileSize * 1.5f);
        }

        super.start();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_K)) {
            SceneManager.loadSceneByName("world");
        }
        super.update();
    }
}
