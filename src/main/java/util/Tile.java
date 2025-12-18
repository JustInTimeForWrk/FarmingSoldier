/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author riegj8298
 */
import physics.TileCollider;
import view.SpriteRenderer;
import org.joml.Vector2i;

import java.awt.image.BufferedImage;
public class Tile {
    public Vector2i position = new Vector2i();
    public TileCollider collider;
    boolean isSolid = false;
    String tag = "tile";
    SpriteRenderer spriteRenderer;
    
    Tile(int x, int y, boolean isSolid, BufferedImage image) {
        this.position.set(x,y);
        this.isSolid = isSolid;
        if (isSolid) {
            this.collider = new TileCollider();
        }
        spriteRenderer = new SpriteRenderer(image);
    }
    
    public void start() {
        spriteRenderer.addToRenderer();
    }
    
    public void stop() {
        spriteRenderer.removeFromRenderer();
    }
    
    public void init() {
        this.collider.setParentTile(this);
    }
    
}
