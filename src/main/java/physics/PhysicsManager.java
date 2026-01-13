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

    //Input: BoxCollider to add, Output: none
    //Purpose: adds a collider to the colliders to add queue, which is added after all existing colliders have been updated to prevent index out of bound error
    //Example: none needed
    public static void addCollider(BoxCollider newCollider) {
        collidersAddQueue.add(newCollider);
    }

    //Input: BoxCollider to remove, Output: none
    //Purpose: removes a collider to the colliders to remove queue, which is removed after all existing colliders have been updated to prevent index out of bound error
    //Example: none needed
    public static void removeCollider(BoxCollider collider) {
        collidersRemoveQueue.add(collider);
    }

    //Input: none, Output: none
    //Purpose: updates collider positions as well as looks for collisions between entity to entity as well as entity to tile
    //Example: none needed
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
                        if (0 <= xGrid && xGrid < tileColliders.length) {
                            if (0 <= yGrid && yGrid < tileColliders[xGrid].length) {
                                TileCollider tileCollider = tileColliders[xGrid][yGrid];
                                if (tileCollider != null) {
                                    if (collider.checkCollision(tileCollider)) {
                                        tileCollisionResolution(collider, tileCollider);
                                    }
                                }
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

    //Input: nothing/void, Output: nothing/void
    //Purpose: completely wipes out any BoxColliders within entityColliders, collidersRemoveQueue, and collidersAddQueue
    //Example: none needed
    public static void clear() {
        entityColliders.clear();
        collidersRemoveQueue.clear();
        collidersAddQueue.clear();
    }
    
    //Input: BoxCollider of the entity colliding & tileCollider of the tile colliding, Ouput: nothing/void
    //Purpose: calls solveCollision in the entityRB if they have one and calls onTileCollisionEnter for the entity
    //Example: tileCollisionResolution(new BoxCollider(), new TileCollider())
    public static void tileCollisionResolution(BoxCollider entityCollider, TileCollider tileCollider) {
        Rigidbody entityRB = entityCollider.entity.getComponent(Rigidbody.class);
        if (entityRB != null) {
            entityRB.solveCollision(tileCollider);
        }
        entityCollider.entity.onTileCollisionEnter(tileCollider);
    }
    
    //Input: BoxCollider of the first object & BoxCollider of the second object (order doesn't matter just as long as they are both colliders), Ouput: nothing/void
    //Purpose: resolves collisions and runs collision/trigger callbacks
    //Example: entityCollisionResolution(new BoxCollider(), new BoxCollider())
        public static void entityCollisionResolution(BoxCollider firstCollider, BoxCollider secondCollider) {
            if (firstCollider.isTrigger || secondCollider.isTrigger) { //If either collider is a trigger, then call the onTriggerEnter function
                firstCollider.entity.onTriggerEnter(secondCollider);
                secondCollider.entity.onTriggerEnter(firstCollider);
            } else {
                firstCollider.entity.onEntityCollisionEnter(secondCollider);
                secondCollider.entity.onEntityCollisionEnter(firstCollider);

                Rigidbody firstRB = firstCollider.entity.getComponent(Rigidbody.class);
                Rigidbody secondRB = secondCollider.entity.getComponent(Rigidbody.class);

                if (firstRB != null && secondRB == null) {
                    firstRB.solveCollision(secondCollider);
                } else if (secondRB != null && firstRB == null) {
                    secondRB.solveCollision(firstCollider);
                } else if (secondRB != null && firstRB != null) {
                    firstRB.solveCollision(secondCollider);
                    secondRB.solveCollision(firstCollider);
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

    /*
                    ===============
                    Unused Function
                    ===============
    //Input: Vector2f of the first point and Vector2f of the second point, Output: boolean to see if the line between the two points intersects a tile
    //Purpose: to check if a line between two points is obstructed by the current tileColliders list
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
    */
}
