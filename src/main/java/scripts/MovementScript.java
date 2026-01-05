package scripts;

import org.joml.Vector2f;
import physics.Rigidbody;
import util.Component;
import view.KeyHandler;
import view.Renderer;
import view.SpriteRenderer;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;

public class MovementScript extends Component {
    private float speed = 2.4f;
    private float speedCap = 15f;

    private Rigidbody rb;
    private SpriteRenderer sp;

    private String movingDirY = "down";
    private String movingDirX = "";
    private String lookingImagePath = "resources/assets/player/monkey_down01.png";
    private String[] imageNames = {"monkey_down","monkey_down_left","monkey_left","monkey_up_left",
                                   "monkey_up","monkey_up_right","monkey_right","monkey_down_right"};
    private int frameCounter = 0;
    private int playerFrame; //Which frame of the animation the player is on
    private HashMap<String,BufferedImage> playerImages;


    @Override
    public void init() {
        rb = entity.getComponent(Rigidbody.class);
        sp = entity.getComponent(SpriteRenderer.class);
        playerImages = new HashMap<>();

        movingDirX = "";
        movingDirY = "_down";

        playerFrame = 1;

        for (String str: imageNames) {
            for (int i = 1; i < 5; i++) { // i = 1-4
                String imagePath = "resources/assets/player/" + str + "0" + i + ".png"; //Creates filepath string
                playerImages.put(imagePath, Renderer.loadImage(imagePath));
            }
        }
        super.init();

    }

    @Override
    public void start() {

    }

    @Override
    public void update() {
        Vector2f dir = new Vector2f();
        if (KeyHandler.getKey(KeyEvent.VK_W)) {
            movingDirY = "_up";
            dir.y -= 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_S)) {
            movingDirY = "_down";
            dir.y += 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_A)) {
            movingDirX = "_left";
            dir.x -= 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_D)) {
            movingDirX = "_right";
            dir.x += 1;
        }


        //Below contains the walking animation
        if (!dir.equals(0,0)) { //if a vector of (0,0) is normalized, it returns NaN, it can be used to see if player is moving
            dir.normalize();

            frameCounter++;
            if (frameCounter >= 10) {
                frameCounter -= 10; //changes player animation every 10 frames

                playerFrame++;
                if (playerFrame >= 5) {
                    playerFrame -= 4;
                }
            }

        } else {
            if (playerFrame != 1) {
                lookingImagePath = "resources/assets/player/monkey" + movingDirY + movingDirX + "0" + playerFrame + ".png";
                playerFrame = 1;
            }

        }

        if (!movingDirX.equals("") || !movingDirY.equals("")) {
            lookingImagePath = "resources/assets/player/monkey" + movingDirY + movingDirX + "0" + playerFrame + ".png";
        }

        sp.changeImage(playerImages.get(lookingImagePath));

        if (dir.x == 0) {
            movingDirX = "";
        }
        if (dir.y == 0) {
            movingDirY = "";
        }

        rb.addToVelocity(dir.mul(speed));
        if (rb.velocity.length() > speedCap) {
            rb.velocity.normalize(speedCap);
        }
    }
}
