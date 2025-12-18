package physics;

import view.Window;
import util.Tile;

public class TileCollider extends Collider {
    public Tile tile;
    
    public TileCollider() {
        
    }
    
    public void setParentTile(Tile tile) {
        this.tile = tile;
    }

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
