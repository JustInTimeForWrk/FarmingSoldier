package physics;

import java.util.ArrayList;

public class PhysicsHandler {

    private static ArrayList<BoxCollider> boxColliders = new ArrayList<>();
    
    public static void addCollider(BoxCollider collider) {
        boxColliders.add(collider);
    }

    public static void removeCollider(BoxCollider collider) {
        boxColliders.remove(collider);
    }

    public static void update() {
        for (int i = 0; i < boxColliders.size(); i++) {
            BoxCollider collider = boxColliders.get(i);
            collider.updateCollider();

            if (collider.enabled) {
                for (int j = i + 1; j < boxColliders.size(); j++) { //If the first object has checked every other object, then the second object doesn't need to check the first
                    BoxCollider otherCollider = boxColliders.get(j);
                    if (collider.checkCollision(otherCollider)) {
                        collisionResolution(collider, otherCollider);
                    }
                }
            }
        }
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
                firstRB.collided(secondCollider);

            } else if (secondRB != null && firstRB == null) {
                secondRB.collided(firstCollider);

            } else if (secondRB != null && firstRB != null) {
                firstRB.collided(secondCollider);
                secondRB.collided(firstCollider);
            }
        }
    }
}
