package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Renderer {
    public static ArrayList<Drawable> renderingList = new ArrayList<>();

    public static boolean debugging = false;
    private static ArrayList<Drawable> rendererToAdd = new ArrayList<>();
    private static ArrayList<Drawable> rendererToRemove = new ArrayList<>();


    //Input: an object implementing Drawable, Output: none
    //Purpose: adds the object to a queue to be added to the renderingList
    //Example: addRenderObject(new SpriteRenderer())
    public static void addRenderObject(Drawable object) {
        rendererToAdd.add(object);
    }

    //Input: an object implementing Drawable, Output: none
    //Purpose: adds the object to a queue to be removed from the renderingList
    //Example: addRenderObject(new TileRenderer())
    public static void removeRenderObject(Drawable object) {
        rendererToRemove.add(object);
    }

    //Input: Graphics2D of the panel to draw the images and shapes, Output: none
    //Purpose: draws everything in the renderingList as well as adds/remove items from the renderingList if any are in queue
    //Example: none needed
    public static void draw(Graphics2D g2) {
        for (Drawable object : renderingList) {
            object.draw(g2);
        }

        if (!rendererToAdd.isEmpty()) {
            renderingList.addAll(rendererToAdd);
            rendererToAdd.clear();
        }
        if (!rendererToRemove.isEmpty()) {
            renderingList.removeAll(rendererToRemove);
            rendererToRemove.clear();
        }
    }
    //Input: none, Output: none
    //Purpose: to completely remove all rendering objects when stopping the game
    //Example: none needed
    public static void clear() {
        rendererToRemove.addAll(renderingList);
        rendererToAdd.clear();
    }


    //Input: String representing the filepath of the image file, Output: BufferedImage loaded at the file location
    //Purpose: to load images which will be displayed by the Renderer
    //Example: loadImage("resources/assets/tiles/wall.png") returns a BufferedImage resembling wall.png
    public static BufferedImage loadImage(String filePath) {
        try {
            File f = new File(filePath);
            return ImageIO.read(f);
        } catch(Exception e) {
            try {

                File f = new File("resources/assets/missing_file.png");
                return ImageIO.read(f);
            } catch (Exception ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
