package util;

public abstract class Component {
    public Entity entity;
    public boolean enabled = true;

    public void setEntity(Entity go) {
        this.entity = go;
    }

    public void start() {

    }

    public void update() {

    }

    public void onDeletion() {

    }
    public void setEnabled(boolean bool) {
        enabled = bool;
    }
}
