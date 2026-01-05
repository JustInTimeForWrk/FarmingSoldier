package scripts;

import org.joml.Vector2f;
import org.joml.Vector2i;
import util.Component;
import util.SceneManager;
import view.Drawable;

import java.awt.*;

public class TextScript extends Component implements Drawable {

    Font font = new Font("Arial",Font.PLAIN,40);
    String text = "null";

    Vector2i position = new Vector2i();

    public void setText(String text) {
        this.text = text;
    }

    public TextScript(String text, Vector2i position) {
        if (text != null) {
            this.text = text;
        }
        if (position != null) {
            this.position = position;
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

    @Override
    public void draw(Graphics2D g2) {
        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(text, position.x, position.y);

    }
}
