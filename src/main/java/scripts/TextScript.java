package scripts;

import org.joml.Vector2i;
import util.Component;
import view.Drawable;

import java.awt.*;

public class TextScript extends Component implements Drawable {

    Font font = new Font("Arial",Font.PLAIN,40);
    String text = "null";

    Vector2i position = new Vector2i();

    //Input: String of the text to display and Vector2i representing the position of the text on the screen, Output: none
    //Purpose: Constructor for the text script
    //Example: TextScript("MyTextToDisplay", new Vector2i(100,100))
    public TextScript(String text, Vector2i position) {
        if (text != null) {
            this.text = text;
        }
        if (position != null) {
            this.position = position;
        }
    }

    //Input: String of the new displayable text, Output: none
    //Purpose: changes the text that will be displayed
    //Example: none needed
    public void setText(String text) {
        this.text = text;
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
        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(text, position.x, position.y);

    }
}
