package physics;

import view.Window;
import util.Tile;

public class TileCollider extends Collider {
    public Tile tile;

    //Input: none, Output: none
    //Purpose: Constructor for TileCollider
    public TileCollider() {
        
    }

    //Input: Tile parent of this TileCollider, Output: none
    //Purpose: sets the Tile of this TileCollider to the Tile this TileCollider is attached to
    public void setParentTile(Tile tile) {
        this.tile = tile;
    }

    //Input: boolean representing if the hitbox should be toggled on (true) or off (false), Output: none
    //Purpose: Enables or disables the tile Collider
    public void setEnabled(boolean TorF) {
        this.enabled = TorF;
    }

    @Override
    public void init() {
        position.set(tile.position); //Position relative to the player
        //sets the size relative to the entity's scale
        hitbox.setRect(position.x,position.y,Window.tileSize, Window.tileSize);
    }

}
