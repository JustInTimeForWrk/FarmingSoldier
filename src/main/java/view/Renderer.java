package view;

import java.awt.*;
import java.util.ArrayList;

public class Renderer {
    public static ArrayList<drawable> renderingList = new ArrayList<>();
    private static ArrayList<drawable> rendererToAdd = new ArrayList<>();
    private static ArrayList<drawable> rendererToRemove = new ArrayList<>();

    public static void addRenderObject(drawable object) {
        rendererToAdd.add(object);
    }

    public static void removeRenderObject(drawable object) {
        rendererToRemove.add(object);
    }

    public static void update(Graphics2D g2) {
        for (drawable object : renderingList) {
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
