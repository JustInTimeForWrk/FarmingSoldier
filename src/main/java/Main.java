import util.GameData;
import util.GameManager;
import util.TestManager;
import util.TileMap;

public class Main {
    public static void main(String[] args) {
//        TileMap test = new TileMap("resources/map_test.txt");
//        test.tiles2d[2][2].changeTileData(15);
//        test.saveTileMap();
        TestManager.runTests();

        GameManager.startWindow();
    }
}
