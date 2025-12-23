package util;

import org.joml.Vector2i;
import physics.PhysicsManager;
import view.SpriteRenderer;
import view.Window;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class TileMap {

    public static BufferedImage grass = SpriteRenderer.loadImage("resources/assets/tiles/grass.png");
    public static BufferedImage wall = SpriteRenderer.loadImage("resources/assets/tiles/wall.png");
    public static BufferedImage water = SpriteRenderer.loadImage("resources/assets/tiles/water.png");

    public String filepath;

    public Tile[][] tiles2d;
    public ArrayList<Tile> tilesList = new ArrayList<>();

    public int width;
    public int height;

    public TileMap(String filepath) {
        this.filepath = filepath;
        loadMap(this.filepath);
    }

    public void loadMap(String filePath) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            ArrayList<String[]> data = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(" "));
            }

            width = data.get(0).length;
            height = data.size();

            tiles2d = new Tile[width][height];

            Vector2i pos = new Vector2i(0,-1);
            Tile tile = null;
            for (String[] row : data) {
                pos.set(-1,pos.y);
                pos.add(0,1);
                for (String col : row) {
                    pos.add(1,0);
                    switch(col) {
                        case "1":
                            tile = new Tile(pos.x * Window.tileSize, pos.y * Window.tileSize, grass, false);
                            tile.tag = "grass";
                            break;
                        case "2":
                            tile = new Tile(pos.x * Window.tileSize, pos.y * Window.tileSize, wall, true);
                            tile.tag = "wall";
                            break;
                        case "3":
                            tile = new Tile(pos.x * Window.tileSize, pos.y * Window.tileSize, water, true);
                            tile.tag = "water";
                            break;
                        default:
                            break;
                    }
                    if (tile != null) {
                        tilesList.add(tile);
                    }
                    tiles2d[pos.x][pos.y] = tile;
                }
            }
        } catch (Exception e) {
            tiles2d = null;
            e.printStackTrace();
        }
    }


    public void stop() {
        PhysicsManager.setTileMap(null);
        for (Tile tile : tilesList) {
            tile.stop();
        }
    }

    public void start() {
        PhysicsManager.setTileMap(this);
        for (Tile tile : tilesList) {
            tile.start();
        }
    }

    public void update() {

    }

    public void init() {
        for (Tile tile : tilesList) {
            tile.init();
        }
    }
}
