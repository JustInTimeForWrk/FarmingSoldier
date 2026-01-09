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
import physics.BoxCollider;
import util.Component;
import util.SceneManager;
import util.Transform;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SpriteRenderer extends Component implements Drawable {
    public BufferedImage sprite;
    public String type;

    public SpriteRenderer(BufferedImage image) {
        this.sprite = image;
        this.type = "image";
        if (image == null) {
            this.type = "rectangle";
        }
    }

    //Input: none, Output: none
    //Purpose: constructor for the SpriteRenderer given the filePath
    public SpriteRenderer(String filePath) {
        this.type = "image";
        if ((this.sprite = Renderer.loadImage(filePath)) == null) {
            this.type = "rectangle";
        }
    }

    //Input: BufferedImage, Output: none
    //Purpose: changes the rendered image to the input image
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
    public SpriteRenderer() {
        this.type = "rectangle";
    }

    public void draw(Graphics2D g2) {

        Transform transform = entity.transform;

        Vector2f screenPos = SceneManager.getCurrentCamera().toScreenPos(transform.position); //coordinates of the center of the person relative to the screen

//        Vector2f screenPos = new Vector2f(transform.position);

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
//        if (Renderer.debugging) {
//            BoxCollider collider = entity.getComponent(BoxCollider.class);
//
//            if (collider != null) {
//
//                Vector2f topLeft = SceneManager.getCurrentCamera().toScreenPos(collider.getMin());
//                g2.setColor(Color.red);
//                g2.drawRect((int)topLeft.x,(int)topLeft.y,(int)collider.size.x,(int)collider.size.y);
//            }
//
//            g2.setColor(Color.yellow);
//            Vector2f topLeft = SceneManager.getCurrentCamera().toScreenPos(new Vector2f(transform.position.x-2,transform.position.y-2));
//            g2.fillRect((int)topLeft.x,(int)topLeft.y,4,4);
//        }
    }
}
