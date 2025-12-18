package physics;

import java.util.ArrayList;

public class PhysicsManager {
    public static ArrayList<BoxCollider> colliders = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersRemoveQueue = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersAddQueue = new ArrayList<>();


    public static void addCollider(BoxCollider newCollider) {
        collidersAddQueue.add(newCollider);
    }

    public static void removeCollider(BoxCollider collider) {
        collidersRemoveQueue.add(collider);
    }

    public static void update() {






        if (!collidersAddQueue.isEmpty()) {
            colliders.addAll(collidersAddQueue);
            collidersAddQueue.clear();
        }
        if (!collidersRemoveQueue.isEmpty()) {
            colliders.removeAll(collidersRemoveQueue);
            collidersRemoveQueue.clear();
        }
    }

    public static void clear() {
        colliders.clear();
        collidersRemoveQueue.clear();
        collidersAddQueue.clear();
    }



}
