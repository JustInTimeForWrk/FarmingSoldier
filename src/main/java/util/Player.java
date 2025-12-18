package util;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.Rigidbody;
import scripts.MovementScript;
import view.SpriteRenderer;

public class Player extends Entity {


    public Player() {
        super("player", new Transform(new Vector2f(384,384)));

    }

    @Override
    public void init() {
        addComponent(new MovementScript());

        BoxCollider boxCollider = new BoxCollider(new Vector2f(40f,20f),new Vector2f(4f,32f));

        addComponent(new Rigidbody(boxCollider));

        addComponent(boxCollider);

        addComponent(new SpriteRenderer("resources/assets/player/monkey_down01.png"));
        super.init();
    }

    @Override
    public void start() {
        super.start();
    }


    @Override
    public void update() {
        super.update();

//        SceneManager.getCurrentCamera().setPosition(transform.position);
    }

}
