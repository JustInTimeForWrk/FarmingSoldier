package util;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.Rigidbody;
import scripts.*;
import view.SpriteRenderer;
import view.Window;

public class Player extends Entity {

    public Player() {
        super("Player", new Transform(new Vector2f(480,384)));

    }

    @Override
    public void init() {
        addComponent(new MovementScript());

        BoxCollider boxCollider = new BoxCollider(new Vector2f(40f,20f),new Vector2f(4f,32f));

        addComponent(new Rigidbody());

        addComponent(boxCollider);

        addComponent(new SpriteRenderer("resources/assets/player/monkey_down01.png"));

        addComponent(new FarmingScript());

        addComponent(new SwapSceneScript());

//        addComponent(new playerTakeDamage());

        addComponent(new HotbarScript());

        addComponent(new MoveCameraScript());
//        addComponent(new AttackingScript());
        super.init();
    }

}
