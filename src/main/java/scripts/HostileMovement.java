package scripts;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.PhysicsManager;
import physics.Rigidbody;
import physics.TileCollider;
import util.Component;
import util.Entity;
import util.Player;
import util.SceneManager;
import view.Window;

import java.awt.*;

public class HostileMovement extends Component {

    public int aggroDistance = Window.tileSize * 8;
    public boolean aggroed;
    public BoxCollider hostileCollider;
    public BoxCollider playerCollider;
    float speed = 0.1f;
    float speedCap = 2f;
    public Rigidbody rb;


    @Override
    public void update() {
        if (playerCollider != null) {
            float distance = hostileCollider.getCenter().distance(playerCollider.getCenter());
//            System.out.println(distance+","+playerCollider.getCenter());
            if (distance < aggroDistance) {
                aggroed = true;
            } else {
                aggroed = false;
            }
            if (aggroed) {
                for (TileCollider[] tiles : PhysicsManager.getTileColliders()) {
                    for (TileCollider tile : tiles) {
                        if (tile != null) {
                            if (tile.checkLineCollision(hostileCollider.getCenter().x,hostileCollider.getCenter().y,playerCollider.getCenter().x,playerCollider.getCenter().y)) {
                                aggroed = false;
                                return;
                            }
                        }
                    }
                }

                Vector2f dir = (playerCollider.getCenter()).sub(hostileCollider.getCenter());
                rb.addToVelocity(dir.mul(speed));
                if (rb.velocity.length() > (speedCap)) {
                    rb.velocity.normalize().mul(speedCap);
                }
            }
        }
    }

    @Override
    public void init() {
        hostileCollider = entity.getComponent(BoxCollider.class);
        rb = entity.getComponent(Rigidbody.class);
        Entity player = entity.parentScene.findEntityByTag("Player");
        playerCollider = player.getComponent(BoxCollider.class);

        super.init();
    }
}
