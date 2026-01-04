package view;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Renderer {
    public static ArrayList<Drawable> renderingList = new ArrayList<>();
    public static boolean debugging = false;
    private static ArrayList<Drawable> rendererToAdd = new ArrayList<>();
    private static ArrayList<Drawable> rendererToRemove = new ArrayList<>();



    public static void addRenderObject(Drawable object) {
        rendererToAdd.add(object);
    }

    public static void removeRenderObject(Drawable object) {
        rendererToRemove.add(object);
    }

    public static void update(Graphics2D g2) {
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

    public static void clear() {
        renderingList.clear();
        rendererToRemove.clear();
        rendererToAdd.clear();
    }

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
