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

public class WorldScene extends Scene {
    Entity player;

    //Input: none, Output: none
    //Purpose: constructor for the WorldScene
    //Example: none needed
    public WorldScene() {
        super("world");
    }

    @Override
    public void init() {
        tileMap = new TileMap("resources/map_world.txt");
        if (!this.tileMap.loadMap(this.tileMap.filePathToSaveTo)) { // only runs if the tileMap fails to load
            this.tileMap.loadMap("resources/map_world_default.txt");
            this.tileMap.saveTileMap();
        }
        addEntityToScene(new Entity("NPC Monologuer", new Transform(new Vector2f(Window.tileSize * 28, Window.tileSize * 18)), new Component[]{new BoxCollider(new Vector2f(16,32),new Vector2f(16,12)),new SpriteRenderer("resources/assets/hostile/hostile_left01.png")}));
        addEntityToScene(new Entity("NPC Monologuer hitbox", new Transform(new Vector2f(Window.tileSize * 27.5f, Window.tileSize * 17.5f), new Vector2f(2,2)), new Component[]{new BoxCollider(true),new ActivateNPCMonologueScript("My crops grow faster when I sleep",new Vector2f(Window.tileSize*22,Window.tileSize*17.5f))}));

        addEntityToScene(new Entity("NPC Monologuer2", new Transform(new Vector2f(Window.tileSize * 13, Window.tileSize * 22)), new Component[]{new BoxCollider(new Vector2f(16,32),new Vector2f(16,12)),new SpriteRenderer("resources/assets/hostile/hostile_right01.png")}));
        addEntityToScene(new Entity("NPC Monologuer hitbox2", new Transform(new Vector2f(Window.tileSize * 12.5f, Window.tileSize * 21.5f), new Vector2f(2,2)), new Component[]{new BoxCollider(true),new ActivateNPCMonologueScript("Every day feels like they last 30 seconds",new Vector2f(Window.tileSize*7,Window.tileSize*21.5f))}));

        addEntityToScene(new Entity("NPC Monologuer3", new Transform(new Vector2f(Window.tileSize * 16, Window.tileSize * 15)), new Component[]{new BoxCollider(new Vector2f(16,32),new Vector2f(16,12)),new SpriteRenderer("resources/assets/hostile/hostile_down_right01.png")}));
        addEntityToScene(new Entity("NPC Monologuer hitbox3", new Transform(new Vector2f(Window.tileSize * 15.5f, Window.tileSize * 14.5f), new Vector2f(2,2)), new Component[]{new BoxCollider(true),new ActivateNPCMonologueScript("I would till this grass if I had a hoe",new Vector2f(Window.tileSize*10,Window.tileSize*14.5f))}));

        addEntityToScene(new Entity("NPC WaterNeeder", new Transform(new Vector2f(Window.tileSize * 31, Window.tileSize * 20)), new Component[]{new BoxCollider(new Vector2f(16,32),new Vector2f(16,12)),new SpriteRenderer("resources/assets/hostile/hostile_right01.png")}));
        addEntityToScene(new Entity("NPC WaterNeeder hitbox", new Transform(new Vector2f(Window.tileSize * 30.5f, Window.tileSize * 19.5f), new Vector2f(2,2)), new Component[]{new BoxCollider(true),new ActivateNPCMonologueScript("I'm so thirsty I could use some water",new Vector2f(Window.tileSize*25,Window.tileSize*20f)), new PourWaterOnNPCQuestScript()}));

        addEntityToScene(new Entity("Player",new Transform(),new Component[]{new MovementScript(),new Rigidbody(),new BoxCollider(new Vector2f(40f,20f),new Vector2f(4f,32f)),
                         new SpriteRenderer("resources/assets/player/monkey_down01.png"),new FarmingScript(),new SwapSceneScript(),new HotbarScript(),new MoveCameraScript()}));
        
        addEntityToScene(new Entity("Warp",new Transform(new Vector2f(Window.tileSize*23+12,Window.tileSize*23f), new Vector2f(0.5f,0.5f)),new Component[]{new BoxCollider(true)}));
        addEntityToScene(new Entity("night", new Transform(),new Component[]{new NightScreenScript()}));

        addEntityToScene(new Entity("text", new Transform(), new Component[]{new TextScript("'Esc' for \nmain menu",new Vector2i(Window.tileSize,Window.screenHeight-Window.tileSize))}));
        super.init();
    }

    @Override
    public void start() {

        if ((player = findEntityByTag("Player")) != null) {
            player.transform.position.set(Window.tileSize*23, Window.tileSize*21.5); //Basically sets the player's spawnpoint
        }
        super.start();
    }

    @Override
    public void update() {
        if (KeyHandler.getKey(KeyEvent.VK_L)) {
            SceneManager.loadSceneByName("house");
        }
        super.update();
    }
}
