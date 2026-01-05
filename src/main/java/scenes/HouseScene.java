package scenes;

import org.joml.Vector2f;
import physics.BoxCollider;
import scripts.NightScreenScript;
import scripts.SleepScript;
import scripts.TextScript;
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
        this.tileMap = new TileMap("resources/map_house.txt");
        addEntityToScene(new Entity("npc", new Transform(new Vector2f(Window.tileSize * 5, Window.tileSize * 0.5f)), new Component[]{new SpriteRenderer(), new BoxCollider()}));
        addEntityToScene(new Entity("npc sell hitbox", new Transform(new Vector2f(Window.tileSize * 4.5f, Window.tileSize * 0.5f)), new Component[]{new BoxCollider()}));
        addEntityToScene(new Entity("Bed",new Transform(new Vector2f(Window.tileSize * 14, Window.tileSize * 12)),new Component[]{new SpriteRenderer("resources/assets/Entity/bed.png"), new BoxCollider(new Vector2f(Window.tileSize - 12,Window.tileSize * 2 - 20),new Vector2f(6,8), true), new SleepScript()}));
        addEntityToScene(new Player());
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
            SceneManager.loadSceneByIndex(0);
        }
        super.update();
    }
}
