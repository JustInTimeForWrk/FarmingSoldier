package scripts;

import physics.BoxCollider;
import util.Component;
import util.SceneManager;
import util.Tile;

import java.util.ArrayList;

public class SleepScript extends Component {
    ArrayList<Tile> tileMapArray = new ArrayList<>();

    @Override
    public void start() {
        tileMapArray = SceneManager.getCurrentScene().tileMap.tilesList;
    }

    @Override
    public void stop() {
        tileMapArray = null;
    }

    @Override
    public void onTriggerEnter(BoxCollider other) {
        if (other.entity.tag.equals("Bed") && tileMapArray != null) {
            this.entity.transform.position.set(800,350);
            for (Tile tile : tileMapArray) {

                //seeds growing
                if (4 <= tile.id && tile.id <= 6) {
                    tile.changeTileData(tile.id + 1);
                }
                //25 percent chance for dug grass to turn to normal grass
                if (tile.id == 3 && Math.random() * 100 + 1 < 25) {
                    tile.changeTileData(2);
                }
            }
        }
    }
}
