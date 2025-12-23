package physics;

import org.joml.Vector2f;
import util.TileMap;
import view.Window;

import java.util.ArrayList;

public class PhysicsManager {
    public static ArrayList<BoxCollider> entityColliders = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersRemoveQueue = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersAddQueue = new ArrayList<>();

    private static TileCollider[][] tileColliders;

    public static void addCollider(BoxCollider newCollider) {
        collidersAddQueue.add(newCollider);
    }

    public static void removeCollider(BoxCollider collider) {
        collidersRemoveQueue.add(collider);
    }

    public static void update() {
            for (int i = 0; i < entityColliders.size(); i++) {
            BoxCollider collider = entityColliders.get(i);
            collider.updateCollider();

            if (collider.enabled) {
                for (int j = i + 1; j < entityColliders.size(); j++) { //If the first object has checked every other object, then the second object doesn't need to check the first
                    BoxCollider otherCollider = entityColliders.get(j);
                    if (collider.checkCollision(otherCollider)) {
                        entityCollisionResolution(collider, otherCollider);
                    }
                }
            }

            if (tileColliders != null) {
                for (int x = -1; x < 2; x++) {
                    for (int y = -1; y < 2; y++) {
                        int xGrid = (int)collider.getCenter().div(Window.tileSize).x+x;
                        int yGrid = (int)collider.getCenter().div(Window.tileSize).y+y;
//                        System.out.println(xGrid+", "+yGrid);
                        TileCollider tileCollider = tileColliders[xGrid][yGrid];
                        if (tileCollider != null) {
                            if (collider.checkCollision(tileCollider)) {
//                                System.out.println("Colliding");
                                tileCollisionResolution(collider, tileCollider);
                            }
                        }
                    }
                }
            }
        }

        if (!collidersAddQueue.isEmpty()) {
            entityColliders.addAll(collidersAddQueue);
            collidersAddQueue.clear();
        }
        if (!collidersRemoveQueue.isEmpty()) {
            entityColliders.removeAll(collidersRemoveQueue);
            collidersRemoveQueue.clear();
        }
    }

    public static void clear() {
        entityColliders.clear();
        collidersRemoveQueue.clear();
        collidersAddQueue.clear();
    }
    public static void tileCollisionResolution(BoxCollider entityCollider, TileCollider tileCollider) {
        Rigidbody entityRB = entityCollider.entity.getComponent(Rigidbody.class);
        if (entityRB != null) {
            entityRB.solveCollidingTile(tileCollider);
        }
    }

    public static void entityCollisionResolution(BoxCollider firstCollider, BoxCollider secondCollider) {
        if (firstCollider.isTrigger || secondCollider.isTrigger) { //If either collider is a trigger, then call the onTriggerEnter function
            firstCollider.entity.onTriggerEnter(secondCollider);
            secondCollider.entity.onTriggerEnter(firstCollider);
        } else {
            firstCollider.entity.onCollisionEnter(secondCollider);
            secondCollider.entity.onCollisionEnter(firstCollider);

            Rigidbody firstRB = firstCollider.entity.getComponent(Rigidbody.class);
            Rigidbody secondRB = secondCollider.entity.getComponent(Rigidbody.class);

            if (firstRB != null && secondRB == null) {
                firstRB.solveCollidingEntity(secondCollider);

            } else if (secondRB != null && firstRB == null) {
                secondRB.solveCollidingEntity(firstCollider);

            } else if (secondRB != null && firstRB != null) {
                firstRB.solveCollidingEntity(secondCollider);
                secondRB.solveCollidingEntity(firstCollider);
            }
        }
    }

    public static void setTileMap(TileMap tileMap) {
        if (tileMap == null) {
            tileColliders = null;
        } else {
            tileColliders = new TileCollider[tileMap.width][tileMap.height];

            for (int x = 0; x < tileMap.width; x++) {
                for (int y = 0; y < tileMap.height; y++) {
                    TileCollider collider = tileMap.tiles2d[x][y].collider;
                    if (collider != null) {
                        tileColliders[x][y] = collider;
                    }
                }
            }
        }
    }

    public static TileCollider[][] getTileColliders() {
        return tileColliders;
    }

    public static boolean lineIntersectsTile(Vector2f pos1, Vector2f pos2) {
        for (TileCollider[] tiles : tileColliders) {
            for (TileCollider tile : tiles) {
                if (tile != null) {
                    if (tile.checkLineCollision(pos1.x,pos1.y,pos2.x,pos2.y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
