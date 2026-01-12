package util;

import physics.BoxCollider;
import physics.Rigidbody;
import scripts.HostileMovement;
import view.SpriteRenderer;

/*
            ||============||
            ||UNUSED CLASS||
            ||============||
 */
public class HostileTest extends Entity{
    public int damage = 100;

    public HostileTest(Transform transform) {
        super("Hostile", transform);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() {
        this.addComponent(new BoxCollider());
        this.addComponent(new SpriteRenderer("resources/assets/hostile/hostile_down01.png"));
        this.addComponent(new HostileMovement());
        this.addComponent(new Rigidbody());

        super.init();
    }

    @Override
    public void addComponent(Component c) {
        super.addComponent(c);
    }

    @Override
    public <T extends Component> T getComponent(Class<T> componentType) {
        return super.getComponent(componentType);
    }

    @Override
    public void onTriggerEnter(BoxCollider other) {
        super.onTriggerEnter(other);
    }

    @Override
    public void onEntityCollisionEnter(BoxCollider other) {
        super.onEntityCollisionEnter(other);
    }
}
