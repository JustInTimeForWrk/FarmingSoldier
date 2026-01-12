/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author riegj8298
 */

import org.joml.Vector2f;
import util.Component;
import util.SceneManager;
import util.Transform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component implements Drawable {
    public BufferedImage sprite;
    public String type;

    //Input: BufferedImage to display, Output: none
    //Purpose: constructor for the SpriteRenderer given the filePath
    //Example: SpriteRenderer(Renderer.loadImage("resources/assets/night_screen.png"))
    public SpriteRenderer(BufferedImage image) {
        this.sprite = image;
        this.type = "image";
        if (image == null) {
            this.type = "rectangle";
        }
    }

    //Input: String filepath of the image to display, Output: none
    //Purpose: constructor for the SpriteRenderer given the filePath
    //Example: SpriteRenderer("resources/assets/night_screen.png")
    public SpriteRenderer(String filePath) {
        this.type = "image";
        if ((this.sprite = Renderer.loadImage(filePath)) == null) {
            this.type = "rectangle";
        }
    }

    //Input: BufferedImage, Output: none
    //Purpose: changes the rendered image to the input image
    //Example: changeImage(Renderer.loadImage("resources/assets/night_screen.png"))
    public void changeImage(BufferedImage image) {
        this.type = "image";
        if (image != null) {
            this.sprite = image;
        } else {
            this.type = "rectangle";
        }
    }

    @Override
    public void start() {
        addToRenderer();
    }

    @Override
    public void stop() {
        removeFromRenderer();
    }

    //Input: none, Output: none
    //Purpose: default constructor for SpriteRenderer
    //Example: SpriteRenderer()
    public SpriteRenderer() {
        this.type = "rectangle";
    }

    public void draw(Graphics2D g2) {

        Transform transform = entity.transform;

        Vector2f screenPos = SceneManager.getCurrentCamera().toScreenPos(transform.position); //coordinates of the center of the person relative to the screen

        Vector2f size;

        g2.setColor(Color.white);

        switch(this.type) {
            case "image":
                size = new Vector2f(((this.sprite.getWidth() * transform.scale.x * Window.scale)),
                                          ((this.sprite.getHeight() * transform.scale.y * Window.scale)));

                g2.drawImage(this.sprite, (int)screenPos.x, (int)screenPos.y,
                        (int)size.x,
                        (int)size.y,null);
                break;
            case "rectangle":

                size = new Vector2f((Window.tileSize * transform.scale.x),
                                          (Window.tileSize * transform.scale.y));

                g2.fillRect((int)screenPos.x, (int)screenPos.y,
                        (int)size.x,(int)size.y);
                break;
            default:
                System.out.println("Cannot draw SpriteRenderer type: "+this.type);
                break;
        }
    }
}
