package util;

import org.joml.Vector2i;
import physics.PhysicsManager;
import view.Renderer;
import view.Window;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class TileMap {

    public static BufferedImage grass = Renderer.loadImage("resources/assets/tiles/grass.png");
    public static BufferedImage wall = Renderer.loadImage("resources/assets/tiles/wall.png");
    public static BufferedImage water = Renderer.loadImage("resources/assets/tiles/water.png");

    public String filePath;
    
    public Tile[][] tiles2d;
    public ArrayList<Tile> tilesList = new ArrayList<>();

    public int width;
    public int height;

    //Input: String representing the filepath to a .txt file, Output: none
    //Purpose: constructor for the TileMap
    //Example: TileMap("resources/assets/map_world_default.txt")
    public TileMap(String filepath) {
        this.filePath = filepath;
    }
    
    //Input: String of a filepath to a .txt file, Output: boolean representing if loading the tilemap was successful or not
    //Purpose: tries to load a TileMap from the file located at the filepath string
    //Example: loadMap("resources/assets/map_world_default.txt")
    public boolean loadMap(String filePath) {
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
            Tile tile;
            for (String[] row : data) {
                pos.set(-1,pos.y);
                pos.add(0,1);
                for (String col : row) {
                    pos.add(1,0);
                    int tileID = Integer.parseInt(col);
                    if (0 <= tileID && tileID < Tile.defaultTiles.length) {
                        tile = new Tile(pos.x * Window.tileSize, pos.y * Window.tileSize, tileID);
                    } else {
                        tile = null;
                    }
                    if (tile != null) {
                        tilesList.add(tile);
                    }
                    tiles2d[pos.x][pos.y] = tile;
                }
            }
            br.close();
        } catch (FileNotFoundException e){
            System.out.println("No File At: "+ filePath);
            tiles2d = null;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            tiles2d = null;
            return false;
        }
        return true;
    }

    //Input: none, Output: none
    //Purpose: detatches the TileMap from the PhysicsManager and Renderer when switching scenes
    //Example: none needed
    public void stop() {
        PhysicsManager.setTileMap(null);
        for (Tile tile : tilesList) {
            tile.stop();
        }
    }

    //Input: none, Output: none
    //Purpose: attaches the TileMap to the PhysicsManager and Renderer when switching scenes
    //Example: none needed
    public void start() {
        PhysicsManager.setTileMap(this);
        for (Tile tile : tilesList) {
            tile.start();
        }
    }

    //Input: none, Output: none
    //Purpose: initializes tiles
    //Example: none needed
    public void init() {
        for (Tile tile : tilesList) {
            tile.init();
        }
    }
    
    //Input: none, Output: none
    //Purpose: removes every tile from the tilesList when quitting the game panel to the menu panel
    //Example: none needed
    public void clear() {
        tilesList.clear();
    }

    //Input: none, Output: none
    //Purpose: updates the TileMap on it's filepath
    //Example: none needed
    public boolean saveTileMap() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Tile tile = tiles2d[x][y];
                    if (tile != null) {
                        bw.write(tile.id + ""); //the + "" turns it into a string instead of a character
                    } else {
                        bw.write("-1");
                    }
                    if (x != width -1 ) { //makes sure there isn't an extra space at the end of a line
                        bw.write(" "); //Space for the split(" ") function in the loadTileMap() function
                    }
                }
                bw.write("\n");
            }
            bw.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
