package physics;

import view.Window;
import util.Tile;

public class TileCollider extends Collider {
    public Tile tile;

    //Input: none, Output: none
    //Purpose: Constructor for TileCollider
    //Example: none needed
    public TileCollider() {
        
    }

    //Input: Tile parent of this TileCollider, Output: none
    //Purpose: sets the Tile of this TileCollider to the Tile this TileCollider is attached to
    //Example: setParentTile(new Tile(96,128,3))
    public void setParentTile(Tile tile) {
        this.tile = tile;
    }

    @Override
    public void init() {
        position.set(tile.position); //Position relative to the player
        //sets the size relative to the tile's scale
        hitbox.setRect(position.x,position.y,Window.tileSize, Window.tileSize);
    }

}
