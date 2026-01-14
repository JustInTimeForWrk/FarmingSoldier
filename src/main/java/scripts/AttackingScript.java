package scripts;

/*
            ||============||
            ||UNUSED CLASS||
            ||============||

public class AttackingScript extends Component implements ClickAction {
    HotbarScript hotbarScript;
    Vector2f playerPos = new Vector2f();
    Vector2f mouseClickPos = new Vector2f();
    Vector2f mouseToPlayer = new Vector2f();

    @Override
    public void init() {
        hotbarScript = entity.getComponent(HotbarScript.class);
        super.init();
    }

    @Override
    public void start() {
        addClickAction();
        super.start();
    }

    @Override
    public void stop() {
        removeClickAction();
        super.stop();
    }


    public void clickAction(MouseEvent e) {
        if (hotbarScript.currentItemSlot == 0) {
            playerPos.set(entity.transform.getCenter());
            mouseClickPos.set(SceneManager.getCurrentCamera().toWorldPos(new Vector2f(e.getX(),e.getY())));
            mouseToPlayer = mouseClickPos.sub(playerPos).normalize();

            System.out.println(mouseToPlayer);

        }
    }
}


 */