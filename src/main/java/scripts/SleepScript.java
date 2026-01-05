package scripts;

import org.joml.Vector2i;
import physics.BoxCollider;
import util.*;
import view.KeyHandler;
import view.Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SleepScript extends TextScript {

    FarmingScript farmingScript;
    public long timer = System.currentTimeMillis();
    public  double sleepCD = 30000; //30 seconds
    TileMap gameTileMap;
    ArrayList<Tile> tileMapArray = new ArrayList<>();
    boolean playerOnBed = false;

    public SleepScript(){
        super("Press E to Rest", new Vector2i(200,400));
    }

    @Override
    public void init() {
        Entity player = SceneManager.getScene(0).findEntityByTag("Player");
        if (player != null) {
            farmingScript = player.getComponent(FarmingScript.class);
        }
        gameTileMap = SceneManager.getScene(0).tileMap;
        if (gameTileMap != null) {
            tileMapArray = gameTileMap.tilesList;
        }
    }

    @Override
    public void onTriggerEnter(BoxCollider other) {
        if (other.entity.tag.equals("Player") && System.currentTimeMillis() - timer >= sleepCD) {
            if (KeyHandler.getKey(KeyEvent.VK_E)) {
                skipTheNight(other.entity);
            }
            playerOnBed = true;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        if (playerOnBed) {
            g2.setColor(Color.white);
            g2.setFont(font);
            g2.drawString(text, position.x, position.y);
        }
        playerOnBed = false;
    }

    public void skipTheNight(Entity player) {
        if (tileMapArray != null){
            timer = System.currentTimeMillis();
            player.transform.position.set(Window.tileSize*12.5,Window.tileSize*12);
            for (Tile tile : tileMapArray) {

                if (19 <= tile.id && tile.id <= 21) { //grows watered seeds
                    tile.changeTileData(tile.id - 14);
                } else if (4 <= tile.id && tile.id <= 6 && Math.random() * 100 + 1 <= 10) { //10 percent chance for unwatered plants to die
                    tile.changeTileData(3);
                } else if (tile.id == 3 && Math.random() * 100 + 1 <= 25) { //25 percent chance for dug grass to turn to normal grass
                    tile.changeTileData(2);
                }
            }

            GameManager.saveGame();

            if (farmingScript != null) {

            }
        }
    }
}


