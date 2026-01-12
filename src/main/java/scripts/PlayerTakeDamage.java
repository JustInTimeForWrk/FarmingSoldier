package scripts;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.Rigidbody;
import util.Component;
import util.SceneManager;


//Unused Class
public class PlayerTakeDamage extends Component {

    public double health = 100f;
    public long invulnerabilityTimer = 0;
    public double damageCD = 2000f;

    public float knockback = 50f;
    public double kbCD = 100f;
    public long kbTimer = 0;
    public boolean hit = false;
    public Vector2f kbVector;

    public Rigidbody playerRB;

    @Override
    public void init() {
        //grabs the rigidbody class from the player
        playerRB = entity.getComponent(Rigidbody.class);
    }

    @Override
    public void update() {
        if (hit) {

            System.out.println(playerRB.velocity+"  |  "+kbVector);
            playerRB.velocity.set(kbVector);

            if (System.currentTimeMillis() - kbTimer > kbCD) {
                hit = false;
                kbVector.set(0,0);
            }
        }
    }

    @Override
    public void onEntityCollisionEnter(BoxCollider other) {
        HostileMovement hostileScript;

        if (other.entity.tag.equals("Hostile")) {
            if ((hostileScript = other.entity.getComponent(HostileMovement.class)) != null && System.currentTimeMillis() - invulnerabilityTimer > damageCD) {
                invulnerabilityTimer = System.currentTimeMillis();
                health -= hostileScript.damage;
                checkForDeath();

                hit = true;
                kbTimer = System.currentTimeMillis();
                kbVector = playerRB.collider.getCenter().sub(other.getCenter()).normalize().mul(knockback);
            }
        }

    }

    private void checkForDeath() {
        if (health <= 0) {
            health = 100f;
            SceneManager.loadSceneByName("house");
        }
    }
}
