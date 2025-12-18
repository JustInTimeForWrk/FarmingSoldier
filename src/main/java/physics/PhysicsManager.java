package physics;

import java.util.ArrayList;

public class PhysicsManager {
    public static ArrayList<BoxCollider> entityColliders = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersRemoveQueue = new ArrayList<>();
    private static ArrayList<BoxCollider> collidersAddQueue = new ArrayList<>();
    

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
                    System.out.println(collider.checkCollision(otherCollider));
                    if (collider.checkCollision(otherCollider)) {
                        collisionResolution(collider, otherCollider);
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
    public static void collisionResolution(BoxCollider firstCollider, BoxCollider secondCollider) {
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

}
