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
import util.GameManager;

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
    private float framesPerImageChange;
    private HashMap<String,BufferedImage> playerImages;
    private Vector2f dir = new Vector2f();

    @Override
    public void init() {
        framesPerImageChange = 10f/GameManager.GameSpeed;
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
    }

    @Override
    public void update() {
        dir.set(0,0);
        if (KeyHandler.getKey(KeyEvent.VK_W)) {
            dir.y -= 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_S)) {
            dir.y += 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_A)) {
            dir.x -= 1;
        }
        if (KeyHandler.getKey(KeyEvent.VK_D)) {
            dir.x += 1;
        }

        if (dir.x > 0) { //Moving right
            movingDirX = "_right";
        } else if (dir.x < 0) { //Moving left
            movingDirX = "_left";
        }

        if (dir.y > 0) { //Moving up
            movingDirY = "_down";
        } else if (dir.y < 0) { //Moving down
            movingDirY = "_up";
        }

        walkingSpriteUpdate();

        rb.addToVelocity(dir.mul(speed * GameManager.GameSpeed));
        if (rb.velocity.length() > speedCap) {
            rb.velocity.normalize(speedCap);
        }
    }

    //Input: none, Output: none
    //Purpose: changes the sprite depending on if and where the player is moving
    //Example: none needed
    private void walkingSpriteUpdate() {
        if (!dir.equals(0,0)) { //if a vector of (0,0) is normalized, it returns NaN, it can be used to see if player is moving
            dir.normalize();

            frameCounter++;
            if (frameCounter >= framesPerImageChange) {
                frameCounter -= Math.round(framesPerImageChange);

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
    }
}
