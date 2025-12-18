package view;

import java.awt.*;
import java.util.ArrayList;

public class Renderer {
    public static ArrayList<drawable> renderingList = new ArrayList<>();

    public static void addRenderObject(drawable object) {
        renderingList.add(object);
    }

    public static void removeRenderObject(drawable object) {
        renderingList.remove(object);
    }

    public static void drawAll(Graphics2D g2) {
        for (drawable object : renderingList) {
            object.draw(g2);
        }
    }
}
