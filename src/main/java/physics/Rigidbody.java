/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package physics;

/**
 *
 * @author riegj8298
 */

import org.joml.Vector2f;
import util.Component;

import java.util.ArrayList;

public class Rigidbody extends Component {

    public Vector2f velocity;
    public float friction = 0.4f;
    public BoxCollider collider;

    
    //Input: none, Output: none
    //Purpose: constructs velocity
    //Example: none needed
    public Rigidbody() {
        velocity = new Vector2f();
    }
    
    //Input: Vector2f representing a velocity to add to the rigidbody, Output: none
    //Purpose: adds the input vector to the velocity of the rigidbody
    //Example: addToVelocity(new Vector2f(0,0))
    public void addToVelocity(Vector2f vector) {
        velocity.add(vector);
    }

    @Override
    public void init() {
        this.collider = entity.getComponent(BoxCollider.class);
    }

    @Override
    public void update() {
        velocity.mul(1 - friction);
        entity.transform.position.add(velocity);
    }

    //Input: a collider that is intersecting with the set box collider, Output: none
    //Purpose: solves a collision with another collider by checking how much the rigidbody is overlapping with the collider intersecting it and moving the rigidbody the smallest axis of overlap
    //Example: solveCollision(new BoxCollider())
    public void solveCollision(Collider other) {
        Vector2f overlap = collider.getMax().min(other.getMax()).sub(collider.getMin().max(other.getMin()));

        if (overlap.x < overlap.y) {//pushes entity on the smallest distance needed
            if (collider.getCenter().x < other.getCenter().x) { //If the entity is left of the other
                entity.transform.position.x -= overlap.x;
            } else { //If the entity is right of the other
                entity.transform.position.x += overlap.x;
            }
            velocity.x = 0f;
        } else {
            if (collider.getCenter().y < other.getCenter().y) { //If the entity is above the other
                entity.transform.position.y -= overlap.y;
            } else { //If the entity is below the other
                entity.transform.position.y += overlap.y;
            }
            velocity.y = 0f;
        }

        collider.updateCollider();
    }
}
