package view;

import java.awt.*;
import java.util.ArrayList;

public class Renderer {
    public static ArrayList<Drawable> renderingList = new ArrayList<>();
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
}
