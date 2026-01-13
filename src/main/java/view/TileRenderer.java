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
import java.awt.*;
import java.awt.image.BufferedImage;
import physics.TileCollider;
import util.Tile;

public class TileRenderer extends Component implements Drawable {
    public BufferedImage sprite;
    public String type;
    public Tile tile;

    //Input: BufferedImage, Output: none
    //Purpose: constructor for the TileRenderer given the buffered image
    //Example: TileRender(Renderer.loadImage("resources/assets/tiles/grass.png"))
    public TileRenderer(BufferedImage image) {
        this.sprite = image;
        this.type = "image";
        if (image == null) {
            this.type = "rectangle";
        }
    }

    //Input: Tile, Output: none
    //Purpose: sets the parent tile to the input
    //Example: setParentTile(new Tile(0,0,4))
    public void setParentTile(Tile tile) {
        this.tile = tile;
    }

    //Input: BufferedImage, Output: none
    //Purpose: changes the rendered image to the input image
    //Example: changeImage(Renderer.loadImage("resources/assets/tiles/grass.png"))
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
    //Purpose: constructor for the TileRenderer
    //Example: none needed
    public TileRenderer() {
        this.type = "rectangle";
    }

    //Input: g2 representing the graphics of the paint component, Output: none
    //Purpose: to draw the component to the game panel
    //Example: none needed
    public void draw(Graphics2D g2) {

        Vector2f screenPos = SceneManager.getCurrentCamera().toScreenPos(new Vector2f(tile.position)); //Gets the position relative to the camera

        Vector2f size;

        g2.setColor(Color.white);

        switch(this.type) {
            case "image":
                size = new Vector2f(((this.sprite.getWidth() * Window.scale)),
                                          ((this.sprite.getHeight() * Window.scale)));

                g2.drawImage(this.sprite, (int)screenPos.x, (int)screenPos.y,
                        (int)size.x,
                        (int)size.y,null);
                break;
            case "rectangle":

                size = new Vector2f((Window.tileSize),(Window.tileSize));

                g2.fillRect((int)screenPos.x, (int)screenPos.y,
                        (int)size.x,(int)size.y);
                break;
            default:
                System.out.println("Cannot draw SpriteRenderer type: "+this.type);
                break;
        }
        if (Renderer.debugging == true) {
            TileCollider collider = tile.collider;

            if (collider != null) {

                Vector2f topLeft = SceneManager.getCurrentCamera().toScreenPos(collider.getMin());
                g2.setColor(Color.red);
                g2.drawRect((int)topLeft.x,(int)topLeft.y,Window.tileSize,Window.tileSize);
            }

            g2.setColor(Color.yellow);
            Vector2f topLeft = SceneManager.getCurrentCamera().toScreenPos(new Vector2f(tile.position.x-2,tile.position.y-2));
            g2.fillRect((int)topLeft.x,(int)topLeft.y,4,4);
        }
    }
}
