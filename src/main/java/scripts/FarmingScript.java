package scripts;

import org.joml.Vector2f;
import org.joml.Vector2i;
import util.*;
import view.ClickAction;
import view.Window;

import java.awt.event.MouseEvent;

public class FarmingScript extends TextScript implements ClickAction {
    public static int harvestedPlants = 0;

    Vector2f mouseClickPos;
    TileMap tileMap;
    Vector2f playerPos;
    float maxRadius = 3;
    HotbarScript hotbarScript;

    //Input: none, Output: none
    //Purpose: constructor for the NPCSellScript
    //Example: none needed
    public FarmingScript() {
        super(harvestedPlants + "", new Vector2i(500,48));
    }

    @Override
    public void init() {
        mouseClickPos = new Vector2f();
        playerPos = new Vector2f();
        hotbarScript = entity.getComponent(HotbarScript.class);
        tileMap = SceneManager.getScene("world").tileMap;
    }

    @Override
    public void start() {
        setText(harvestedPlants + "");
        addClickAction();
        addToRenderer();
    }

    @Override
    public void stop() {
        removeClickAction();
        removeFromRenderer();
    }

    //Input: MouseEvent when clicked, Output: none
    //Purpose: does a farming check to see if the player is clicking on a valid tile to perform a farming action
    //Example: clickAction(e) runs checks on the mouse event to see if a farming action can be performed
    public void clickAction(MouseEvent e) {
        playerPos.set(entity.transform.position);
        mouseClickPos.set(e.getX(),e.getY());
        mouseClickPos = SceneManager.getCurrentCamera().toWorldPos(mouseClickPos).add(-24,-24).div(Window.tileSize).round();
        if (tileMap == null) {
            return;
        }
        if (0 <= mouseClickPos.x && mouseClickPos.x < tileMap.width && 0 <= mouseClickPos.y && mouseClickPos.y < tileMap.height) {
            Tile tile = tileMap.tiles2d[(int)mouseClickPos.x][(int)mouseClickPos.y];
            if (playerPos.div(Window.tileSize).round().distance(mouseClickPos) > maxRadius) {
                return;
            }
            farmingAction(tile);
        }
    }


    //Input: Tile that is being clicked on, Output: none
    //Purpose: does a specific interaction with the tile depending on the tile's data and player's hotbarScript
    //Example: farmingAction(new Tile(0,0,3))
    private void farmingAction(Tile tile) {
        if (tile.tag.equals("grass") && hotbarScript.currentItemSlot == 1) {
            tile.changeTileData(3);
        } else if (tile.tag.equals("grass_dug") && hotbarScript.currentItemSlot == 2) { //Plants the seeds
            tile.changeTileData(4);
        } else if ( 4 <= tile.id && tile.id <= 6 && hotbarScript.currentItemSlot == 3) { //Waters the plants
            tile.changeTileData(tile.id + 15);
        } else if (tile.tag.equals("grass_planted_stage3") && hotbarScript.currentItemSlot == 4) { //Harvests the plants
            System.out.println("Tile Harvested: "+tile.toString());

            tile.changeTileData(3);
            harvestedPlants++;
            this.setText(harvestedPlants + "");
        }
    }
}
