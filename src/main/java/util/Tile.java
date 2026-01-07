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
    public static final Tile[] defaultTiles = new Tile[]{new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/water.png"), true, "water", 0),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/wall.png"), true, "wall", 1),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass.png"), false, "grass", 2),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_dug.png"), false, "grass_dug", 3),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage0_unwatered.png"), false, "grass_planted_stage0_unwatered", 4),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage1_unwatered.png"), false, "grass_planted_stage1_unwatered", 5),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage2_unwatered.png"), false, "grass_planted_stage2_unwatered", 6),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage3.png"), false, "grass_planted_stage3", 7),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_floor.png"), false, "house_floor", 8),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_top_and_bottom.png"), true, "house_wall_top_and_bottom", 9),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_left_top.png"), true, "house_wall_left_top", 10),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_right_top.png"), true, "house_wall_right_top", 11),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_left_middle.png"), true, "house_wall_left_middle", 12),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_right_middle.png"), true, "house_wall_right_middle", 13),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_left_bottom.png"), true, "house_wall_left_bottom", 14),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_right_bottom.png"), true, "house_wall_right_bottom", 15),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_window.png"), true, "house_window", 16),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_door.png"), true, "house_door", 17),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/house_outside.png"), true, "house_outside", 18),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage0_watered.png"), false, "grass_planted_stage0_watered", 19),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage1_watered.png"), false, "grass_planted_stage1_watered", 20),
            new Tile(0, 0, Renderer.loadImage("resources/assets/tiles/grass_planted_stage2_watered.png"), false, "grass_planted_stage2_watered", 21)
    };
    public int id;
    public Vector2i position = new Vector2i();
    public TileCollider collider;
    public boolean isSolid = false;
    public String tag = "tile";
    public TileRenderer tileRenderer;
    
    //Input: 2 ints represents x and y position, a buffered image to draw, a boolean for if it's solid, an int representing the id of the Tile, Output: none
    //Purpose: constructor for a Tile
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

    //Input: 2 ints representing the Tile x and y position and another int representing the tileID from the default tiles array, Output: none
    //Purpose: constructor for a Tile
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
    
    //Input: none, Output: none
    //Purpose: adds the tileRenderer to the Renderer when the scenes change
    public void start() {
        tileRenderer.addToRenderer();
    }
    
    //Input: none, Output: none
    //Purpose: removes the tileRenderer from the Renderer when the scenes change
    public void stop() {
        tileRenderer.removeFromRenderer();
    }

    //Input: none, Output: none
    //Purpose: TileCollider and TileRenderer script are initialized when loading the game
    public void init() {
        if (this.collider != null) {
            this.collider.setParentTile(this);
            this.collider.init();
        }

        this.tileRenderer.setParentTile(this);
        this.tileRenderer.init();
    }

    //Input: int representing an index from the defaultTiles array, Output: none
    //Purpose: changes the tileData to the tileData of a default Tile grabbed by the tileID
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
