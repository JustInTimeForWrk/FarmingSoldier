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
    public ArrayList<String> tagsThatCantMoveThis = new ArrayList<>();;

    public Vector2f velocity;
    public float friction = 0.4f;
    public BoxCollider collider;

    public Rigidbody(BoxCollider collider) {
        velocity = new Vector2f();
        this.collider = collider;
    }
    
    public void addToVelocity(Vector2f vector) {
        velocity.add(vector);
    }
    
    @Override
    public void update() {
        velocity.mul(1 - friction);
        entity.transform.position.add(velocity);
    }


    public void addTagImmovable(String tag) {
        tagsThatCantMoveThis.add(tag);
    }
    public void removeTagImmovable(String tag) {
        tagsThatCantMoveThis.remove(tag);
    }

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
    
    public void solveCollidingTile(TileCollider other) {
        solveCollision(other);
    }
    
    public void solveCollidingEntity(BoxCollider other) {
    
        
        if (tagsThatCantMoveThis.contains(other.entity.tag)) {
            return; //Doesnt allow certain
        }
        
        solveCollision(other);
    }

}
