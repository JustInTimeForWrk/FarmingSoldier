/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author riegj8298
 */
import physics.PhysicsManager;
import physics.TileCollider;
import org.joml.Vector2i;
import view.TileRenderer;

import java.awt.image.BufferedImage;
public class Tile {
    public Vector2i position = new Vector2i();
    public TileCollider collider;
    boolean isSolid = false;
    String tag = "tile";
    TileRenderer tileRenderer;
    
    Tile(int x, int y, BufferedImage image, boolean isSolid) {
        this.position.set(x,y);
        this.isSolid = isSolid;
        if (isSolid) {
            this.collider = new TileCollider();
        }
        tileRenderer = new TileRenderer(image);

    }
    
    public void start() {
        tileRenderer.addToRenderer();
    }
    
    public void stop() {
        tileRenderer.removeFromRenderer();
    }
    
    public void init() {
        if (this.collider != null) {
            this.collider.setParentTile(this);
            this.collider.init();
            System.out.println(this.collider.getMin()+"   |   "+this.collider.getMax());
        }

        this.tileRenderer.setParentTile(this);
        this.tileRenderer.init();
    }
    
}
