package scripts;

import org.joml.Vector2f;
import util.*;
import util.Component;
import view.SpriteRenderer;
import view.Window;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FarmingScript extends Component implements ClickAction {
    Vector2f mouseClickPos;
    TileMap tileMap;
    Vector2f playerPos;
    float maxRadius = 3;
    HotbarScript hotbarScript;

    @Override
    public void init() {
        mouseClickPos = new Vector2f();
        playerPos = new Vector2f();
        hotbarScript = entity.getComponent(HotbarScript.class);
    }

    @Override
    public void start() {
        addClickAction();
        tileMap = SceneManager.getCurrentScene().tileMap;
    }

    @Override
    public void stop() {
        removeClickAction();
    }

    @Override
    public void clickAction(MouseEvent e) {
        if (hotbarScript.currentItemSlot == 2) {
            playerPos.set(entity.transform.position);
            Tile tile;
            mouseClickPos.set(e.getX(),e.getY());
            mouseClickPos = SceneManager.getCurrentCamera().toWorldPos(mouseClickPos).add(-24,-24).div(Window.tileSize).round();
            if (tileMap != null) {
                if (0 < mouseClickPos.x && mouseClickPos.x < tileMap.width && 0 < mouseClickPos.y && mouseClickPos.y < tileMap.height) {
                    tile = tileMap.tiles2d[(int)mouseClickPos.x][(int)mouseClickPos.y];
                    if (tile.tag.equals("grass") && playerPos.div(Window.tileSize).round().distance(mouseClickPos) < maxRadius) {
                        tile.tag = "dug grass";
                        tile.tileRenderer.changeImage(SpriteRenderer.loadImage("resources/assets/tiles/grass_dug.png"));
                    }
                }
            }
        }

    }

}
