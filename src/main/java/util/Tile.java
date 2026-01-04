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
import view.Renderer;
import view.TileRenderer;
import java.awt.image.BufferedImage;

public class Tile {
    public static final Tile[] defaultTiles = { new Tile(0,0,Renderer.loadImage("resources/assets/tiles/water.png"),true,"water", 0),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/wall.png"),true,"wall", 1),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass.png"),false,"grass", 2),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass_dug.png"),false,"grass_dug", 3),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass_planted_stage0.png"),false,"grass_planted_stage0", 4),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass_planted_stage1.png"),false,"grass_planted_stage1", 5),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass_planted_stage2.png"),false,"grass_planted_stage2", 6),
                                                new Tile(0,0,Renderer.loadImage("resources/assets/tiles/grass_planted_stage3.png"),false,"grass_planted_stage3", 7)
                                                };
    public int id;
    public Vector2i position = new Vector2i();
    public TileCollider collider;
    public boolean isSolid = false;
    public String tag = "tile";
    public TileRenderer tileRenderer;
    
    public Tile(int x, int y, BufferedImage image, boolean isSolid, String tag, int id) {
        this.id = id;
        this.tag = tag;
        this.position.set(x,y);
        this.isSolid = isSolid;
        if (isSolid) {
            this.collider = new TileCollider();
        }
        tileRenderer = new TileRenderer(image);
    }

    public Tile(int x, int y, int tileID) {
        this.position.set(x,y);
        if (0 <= tileID && tileID < defaultTiles.length) {
            Tile tile = defaultTiles[tileID];
            this.collider = tile.collider;
            this.tag = tile.tag;
            this.id = tile.id;
            this.isSolid = tile.isSolid;
            tileRenderer = new TileRenderer(tile.tileRenderer.sprite);
        } else {

            id = -1;
            tileRenderer = new TileRenderer();
        }

        if (isSolid) {
            this.collider = new TileCollider();
        }
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

    public void changeTileData(int tileID) {
        if (0 <= tileID && tileID < defaultTiles.length) {
            Tile tile = defaultTiles[tileID];
            this.collider = tile.collider;
            this.tag = tile.tag;
            this.id = tile.id;
            this.isSolid = tile.isSolid;

            if (isSolid) {
                this.collider = new TileCollider();
            } else {
                this.collider = null;
            }

            this.tileRenderer.changeImage(tile.tileRenderer.sprite);
        }

    }
    
}
