package scripts;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.PhysicsManager;
import physics.Rigidbody;
import physics.TileCollider;
import util.Component;
import util.Entity;
import util.SceneManager;
import view.Window;

import java.awt.*;
import java.util.HashMap;

import java.awt.image.BufferedImage;
import view.SpriteRenderer;
import view.drawable;

public class HostileMovement extends Component implements drawable {

    public int aggroDistance = Window.tileSize * 8;
    public boolean aggroed;
    public BoxCollider hostileCollider;
    public BoxCollider playerCollider;
    float speed = 0.1f;
    float speedCap = 2f;
    public Rigidbody rb;
    public HashMap<String, BufferedImage> hostileImages = new HashMap();
    private String[] imageNames = {"hostile_down","hostile_down_left","hostile_left","hostile_up_left",
                                   "hostile_up","hostile_up_right","hostile_right","hostile_down_right"};
    private int frameCounter = 0;
    private int hostileFrame = 1;
    private SpriteRenderer sr;
    String imagePath = "resources/assets/hostile/hostile_down01.png";

    private Vector2f lastPlayerPos;

    Vector2f dir = new Vector2f();
    String dirStringX = "";
    String dirStringY = "_down";
    
    @Override
    public void update() {
        if (playerCollider != null) {
            Vector2f hostileCenter = hostileCollider.getCenter();

            float distance = hostileCollider.getCenter().distance(playerCollider.getCenter());
            if (distance < aggroDistance) {
                aggroed = true;
                if (!PhysicsManager.lineIntersectsTile(playerCollider.getCenter(),hostileCenter)) {
                    lastPlayerPos.set(playerCollider.getCenter().sub(24,-24).div(Window.tileSize).round().mul(Window.tileSize).add(Window.tileSize/2f,-Window.tileSize/2f));
                } //                                             ^
            }
            if (lastPlayerPos.distance(hostileCenter) < 4) {
                lastPlayerPos.set(hostileCenter);
                aggroed = false;
                rb.velocity.set(0,0);
            }
            if (aggroed) {
                System.out.println(lastPlayerPos+"    |    "+hostileCenter);
                dir.set(lastPlayerPos).sub(hostileCenter);
                rb.addToVelocity(dir.mul(speed));
                if (rb.velocity.length() > (speedCap)) {
                    rb.velocity.normalize().mul(speedCap);
                }
                dir.normalize();
                if (dir.x > 0.5) {
                    dirStringX = "_right";
                } else if (dir.x < -0.5) {
                    dirStringX = "_left";
                } else {
                    dirStringX = "";
                }
                if (dir.y > 0.5) {
                    dirStringY = "_down";
                } else if (dir.y < -0.5){
                    dirStringY = "_up";
                } else {
                    dirStringY = "";
                }
                
                frameCounter++;
                if (frameCounter >= 16) {
                    frameCounter -= 16; //changes hostile animation every 16 frames

                    hostileFrame++;
                    if (hostileFrame >= 5) {
                            hostileFrame -= 4;
                    }
                }
                
                imagePath = "resources/assets/hostile/hostile"+dirStringY+dirStringX+"0"+hostileFrame+".png";
            } else {
                if (hostileFrame != 1) {
                    hostileFrame = 1;
                }
                imagePath = "resources/assets/hostile/hostile"+dirStringY+dirStringX+"0"+hostileFrame+".png";
            }
            
        } sr.changeImage(hostileImages.get(imagePath));
    }

    @Override
    public void init() {
        hostileCollider = entity.getComponent(BoxCollider.class);
        rb = entity.getComponent(Rigidbody.class);
        Entity player = entity.parentScene.findEntityByTag("Player");
        playerCollider = player.getComponent(BoxCollider.class);

        for (String str : imageNames) {
            for (int i = 1; i < 5; i++) {
                String imagePath = "resources/assets/hostile/"+str+"0"+i+".png";
                hostileImages.put(imagePath, SpriteRenderer.loadImage(imagePath));
            }
        }
        this.sr = entity.getComponent(SpriteRenderer.class);

        lastPlayerPos = new Vector2f().set(this.entity.transform.position);

        rb.friction = 0;

        super.init();
    }

    @Override
    public void start() {
        addToRenderer();
    }

    @Override
    public void stop() {
        removeFromRenderer();
    }

    @Override
    public void draw(Graphics2D g2) {
        if (SpriteRenderer.debugging) {
            g2.setColor(Color.PINK);
            Vector2f newPos = new Vector2f(SceneManager.getCurrentCamera().toScreenPos(lastPlayerPos));
            g2.fillRect((int)newPos.x-2,(int)newPos.y-2,4,4);
        }
    }
}
