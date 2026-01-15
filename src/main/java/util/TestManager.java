package util;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.Rigidbody;
import scenes.TestScene;
import scripts.TestComponent;
import view.Camera;

public class TestManager {

    //Input: none, Output: none
    //Purpose: runs and prints all the test functions inside the function
    public static void runTests() {
        System.out.println(BoxColliderGetMinTest(new Vector2f(0, 0), new BoxCollider(), new Vector2f(0, 0)));
        System.out.println(BoxColliderGetMinTest(new Vector2f(10, 5), new BoxCollider(new Vector2f(8, 8), new Vector2f(2, 3)), new Vector2f(12, 8)));
        System.out.println(BoxColliderGetMaxTest(new Vector2f(5, 5), new BoxCollider(new Vector2f(10, 10)), new Vector2f(15, 15)));
        System.out.println(BoxColliderGetCenterTest(new Vector2f(0, 0), new BoxCollider(new Vector2f(10, 10)), new Vector2f(5, 5)));
        System.out.println(BoxColliderCollisionTest(new Vector2f(0, 0), new Vector2f(5, 5), new BoxCollider(new Vector2f(10, 10)), new BoxCollider(new Vector2f(10, 10)), true));
        System.out.println(BoxColliderCollisionTest(new Vector2f(0, 0), new Vector2f(20, 20), new BoxCollider(new Vector2f(10, 10)), new BoxCollider(new Vector2f(10, 10)), false));
        System.out.println(BoxColliderCollisionTest(new Vector2f(100, 100),new Vector2f(132, 100),new BoxCollider(new Vector2f(32, 32)),new BoxCollider(new Vector2f(32, 32)),false));
        System.out.println(ColliderPointCollisionTest(new Vector2f(25, 25), new BoxCollider(new Vector2f(100, 100)), new Vector2f(50, 50), true));
        System.out.println(ColliderPointCollisionTest(new Vector2f(25, 25), new BoxCollider(new Vector2f(100, 100)),new Vector2f(200, 200), false));


        System.out.println(SceneFindEntityByTagTest(new TestScene(), "Player"));
        System.out.println(SceneFindEntityArrayListByTagTest(new TestScene(), "Enemy", 3));
        System.out.println(SceneInitializesEntityTest(new TestScene(), new Entity("Test", new Transform())));
        System.out.println(SceneAddEntityWhileRunningTest(new TestScene(), new Entity("TestEntityAdded", new Transform())));
        System.out.println(SceneRemoveEntityWhileRunningTest(new TestScene(), new Entity("TestEntityRemove", new Transform())));

        System.out.println(SaveLoadGameDataTest(150,2500,false));

        System.out.println(RigidbodyFrictionTest(new Vector2f(10, 0), 0.5f, new Vector2f(5, 0),0.0001f));
        System.out.println(RigidbodyFrictionTest(new Vector2f(4, 6), 0.25f, new Vector2f(3, 4.5f),0.0001f));
        System.out.println(RigidbodyAddVelocityTest(new Vector2f(2, 3), new Vector2f(5, -1), new Vector2f(7, 2)));
        System.out.println(RigidbodyAddVelocityTest(new Vector2f(0, 0), new Vector2f(1, 1), new Vector2f(1, 1)));
        System.out.println(RigidbodyUpdatePositionTest(new Entity("RBTest1", new Transform()), new Vector2f(4, 6), 0f, new Vector2f(4, 6),0.0001f)); //Without friction
        System.out.println(RigidbodyUpdatePositionTest(new Entity("RBTest2", new Transform()), new Vector2f(10, 0), 0.5f, new Vector2f(5, 0),0.0001f)); //With friction

        System.out.println(TileConstructorWithTileIDTest(2, 2));
        System.out.println(TileConstructorWithTileIDTest(999, -1));
        System.out.println(TileChangeDataTest(15,1, 1, "wall", true));
        System.out.println(TileChangeDataTest(0,-1000, 0, "water", true));

        System.out.println(TileMapClearTest());
        System.out.println(TileMapSaveLoadTest());

        System.out.println(AddComponentSetParentEntityTest());

        System.out.println(CameraWorldToScreenTest(new Vector2f(15, 15), new Vector2f(5, 5), new Vector2f(10, 10)));
        System.out.println(CameraScreenToWorldTest(new Vector2f(10, 10), new Vector2f(5, 5), new Vector2f(15, 15)));
    }

    //Input: Vector2f of the entity position, BoxCollider to add the test entity, Vector2f of expected minimum point
    //Output: boolean of if the expected min point matches the actual min point
    //Purpose: makes sure getMin() returns the correct position
    public static boolean BoxColliderGetMinTest(Vector2f entityPos, BoxCollider collider, Vector2f expectedPoint) {
        Entity testEntity = new Entity();
        testEntity.transform.position.set(entityPos);
        testEntity.addComponent(collider);
        testEntity.init();
        collider.updateCollider();
        return collider.getMin().equals(expectedPoint);
    }

    //Input: Vector2f of the entity position, BoxCollider to add the test entity, Vector2f of expected maximum point
    //Output: boolean of if the expected max point matches the actual max point
    //Purpose: makes sure getMax() returns the correct position
    public static boolean BoxColliderGetMaxTest(Vector2f entityPos, BoxCollider collider, Vector2f expectedPoint) {
        Entity testEntity = new Entity();
        testEntity.transform.position.set(entityPos);
        testEntity.addComponent(collider);
        testEntity.init();
        collider.updateCollider();
        return collider.getMax().equals(expectedPoint);
    }

    //Input: Vector2f of the entity position, BoxCollider to add the test entity, Vector2f of expected center point, Output: boolean of if the expected center point matches the actual center point
    //Purpose: makes sure getCenter() returns the correct position
    public static boolean BoxColliderGetCenterTest(Vector2f entityPos, BoxCollider collider, Vector2f expectedPoint) {
        Entity testEntity = new Entity();
        testEntity.transform.position.set(entityPos);
        testEntity.addComponent(collider);
        testEntity.init();
        collider.updateCollider();
        return collider.getCenter().equals(expectedPoint);
    }

    //Input: 2 Vector2f of the first entity and second entity of two entities, 2 BoxColliders of both the entity, boolean of if the collision happens, Output: boolean of if the collision result matches the expected result
    //Purpose: verifies BoxCollider.checkCollision() correctly detects overlap
    public static boolean BoxColliderCollisionTest(Vector2f posA, Vector2f posB, BoxCollider colliderA, BoxCollider colliderB, boolean expectedResult) {
        Entity a = new Entity();
        Entity b = new Entity();
        a.transform.position.set(posA);
        b.transform.position.set(posB);
        a.addComponent(colliderA);
        b.addComponent(colliderB);
        a.init();
        b.init();
        colliderA.updateCollider();
        colliderB.updateCollider();
        return colliderA.checkCollision(colliderB) == expectedResult;
    }

    //Input: Vector2f of entity position, BoxCollider to test, Vector2f of point to check, boolean of the expectedResult, Output: boolean of if the point collision result matches the expected result boolean
    //Purpose: makes sure checkPointCollision correctly detects point intersection with hitbox
    public static boolean ColliderPointCollisionTest(Vector2f entityPos, BoxCollider collider, Vector2f point, boolean expectedResult) {
        Entity testEntity = new Entity();
        testEntity.transform.position.set(entityPos);
        testEntity.addComponent(collider);
        testEntity.init();
        collider.updateCollider();
        return collider.checkPointCollision(point) == expectedResult;
    }

    //Input: test scene to add entity to, tag to search for any entity in that scene, Output: boolean if the entity with the input tag is found
    //Purpose: checks if Scene.findEntityByTag() returns the correct entity
    public static boolean SceneFindEntityByTagTest(Scene scene, String tag) {
        Entity e = new Entity(tag, new Transform());
        scene.addEntityToScene(e);
        scene.init();
        return scene.findEntityByTag(tag) == e;
    }

    //Input: test scene to add the entities to, tag of the entities in the scene, int for how many entities to make and get, Output: boolean indicating if correct number of entities were found
    //Purpose: makes sure that Scene.findEntityArrayListByTag() gives all matching entities
    public static boolean SceneFindEntityArrayListByTagTest(Scene scene, String tag, int expectedCount) {
        for (int i = 0; i < expectedCount; i++) {
            scene.addEntityToScene(new Entity(tag, new Transform()));
        }
        scene.init();
        boolean successful = true;
        for (Entity e : scene.findEntityArrayListByTag(tag)) {
            if (!e.tag.equals(tag)) {
                successful = false;
            }
        }
        return successful;
    }

    //Input: test scene to initialize entity, Entity to add to the scene, Output: boolean if successful
    //Purpose: makes sure entities are initialized when the scene is initialized
    public static boolean SceneInitializesEntityTest(Scene scene, Entity entity) {
        scene.addEntityToScene(entity);
        scene.init();
        return entity.initialized;
    }

    //Input: test scene to add entity, Entity to add after start, Output: boolean if the entity was added
    //Purpose: makes sure that addEntityToScene works when the scene is running
    public static boolean SceneAddEntityWhileRunningTest(Scene scene, Entity entity) {
        scene.init();
        scene.start();
        scene.addEntityToScene(entity);
        scene.update();
        return scene.findEntityByTag(entity.tag) == entity;
    }

    //Input: test scene to add entity, Entity to add after start, Output: boolean if the entity was added
    //Purpose: makes sure that removeEntityFromScene works when the scene is running
    public static boolean SceneRemoveEntityWhileRunningTest(Scene scene, Entity entity) {
        scene.addEntityToScene(entity);
        scene.init();
        scene.start();
        scene.removeEntityFromScene(entity);
        scene.update();
        return scene.findEntityByTag(entity.tag) != entity;
    }

    //Input: int 1 representing the playerCropCount to save, input2 representing the playerCropsNeeded to save boolean representing the wateredTheNPC flag, Output: boolean if the save function and the load function works
    //Purpose: to make sure that saving and loading functions are working properly.
    public static boolean SaveLoadGameDataTest(int input1, int input2, boolean input3) {
        GameData test = new GameData();
        test.playerCropCount = input1;
        test.playerCropsNeeded = input2;
        test.wateredTheNPC = input3;
        if (!GameData.saveGameData("resources/saves/filetest.db",test)) { //checks if fails to save
            return false;
        }
        GameData loaded = GameData.loadGameData("resources/saves/filetest.db");
        return loaded.playerCropsNeeded == test.playerCropsNeeded && loaded.playerCropCount == test.playerCropCount && loaded.wateredTheNPC == test.wateredTheNPC;
    }

    //Input: Vector2f representing initialVelocity, float representing friction amount, Vector2f of the expected velocity after friction applied, Output: boolean of if friction was applied correctly
    //Purpose: makes sure Rigidbody.update() applies friction to it's velocity
    public static boolean RigidbodyFrictionTest(Vector2f initialVelocity, float friction, Vector2f expectedVelocity, float threshold) {
        Entity e = new Entity();
        Rigidbody rb = new Rigidbody();
        e.addComponent(rb);
        e.init();
        rb.velocity.set(initialVelocity);
        rb.friction = friction;
        rb.update();
        return rb.velocity.sub(expectedVelocity).length() <= threshold;
    }

    //Input: Vector2f representing initialVelocity, Vector2f representing velocity to add, Vector2f of the expected velocity after adding to velocity, Output: boolean indicating if velocity was added correctly
    //Purpose: makes sure Rigidbody.addToVelocity() modifies velocity
    public static boolean RigidbodyAddVelocityTest(Vector2f initialVelocity, Vector2f velocityToAdd, Vector2f expectedVelocity) {
        Entity e = new Entity();
        Rigidbody rb = new Rigidbody();
        e.addComponent(rb);
        e.init();
        rb.velocity.set(initialVelocity);
        rb.addToVelocity(velocityToAdd);
        return rb.velocity.equals(expectedVelocity);
    }

    //Input: Entity to test, Vector2f of velocity to set, float of friction to set, Vector2f of expected position, float representing how much of an approximation it should calculate for floating point imprecision, Output: boolean of if the entity position was correctly moved
    //Purpose: makes sure Rigidbody.update() moves the entity correctly
    public static boolean RigidbodyUpdatePositionTest(Entity entity, Vector2f velocity, float friction, Vector2f exepectedPosition, float threshold) {
        Rigidbody rb = new Rigidbody();
        entity.addComponent(rb);
        entity.init();
        rb.velocity.set(velocity);
        rb.friction = friction; //Makes it elastic
        entity.update();
        return entity.transform.position.sub(exepectedPosition).length() <= threshold;
    }

    //Input: int representing an index in defaultTiles array, int representing the expected tileID, Output: boolean of if the id of the new tile matches the expected tile id
    //Purpose: makes sure the constructor for a tile works with certain test ids
    public static boolean TileConstructorWithTileIDTest(int tileID, int expectedTileID) {
        Tile t = new Tile(0, 0, tileID);
        return t.id == expectedTileID;
    }

    //Input: int tileID of the tile to start with, int tileID to changeTileData() to, int of expected tileID, String of expected tag, boolean of expectedIsSolidBool, Output: boolean of if the changed tile has all the expected variables
    //Purpose: makes sure changeTileData correctly updates tile properties
    public static boolean TileChangeDataTest(int initialTileID, int tileID, int expectedID, String expectedTag, boolean expectedIsSolidBool) {
        Tile t = new Tile(0, 0, initialTileID);
        t.changeTileData(tileID);
        return t.id == expectedID && t.isSolid == expectedIsSolidBool && t.tag.equals(expectedTag);
    }

    //Input: none, Output: boolean of if the clear works
    //Purpose: makes sure TileMap.clear() removes all tiles from the tilesList
    public static boolean TileMapClearTest() {
        TileMap map = new TileMap("test");
        map.tiles2d = new Tile[10][5];
        map.tilesList.add(new Tile(0, 0, 2));
        map.clear();
        return map.tilesList.isEmpty(); //returns true if nothing is in tilesList
    }

    //Input: none, Output: boolean of if the tile map can save and load successfully
    //Purpose: makes sure that saving and loading tileMaps construct the same thing
    public static boolean TileMapSaveLoadTest() {
        TileMap saveMapTest = new TileMap("resources/map_test.txt");
        saveMapTest.tiles2d = new Tile[][]{
                {new Tile(0,0,4),new Tile(0,0,8), new Tile(0,0,12)},
                {new Tile(0,0,2),new Tile(0,0,2), new Tile(0,0,2)},
                {new Tile(0,0,7),new Tile(0,0,6), new Tile(0,0,2)},
                {new Tile(0,0,8),new Tile(0,0,5), new Tile(0,0,2)}
        };
        /*
        Save should look like:
                                    4 8 12
                                    2 2 2
                                    7 6 2
                                    8 5 2
         */
        saveMapTest.width = 4;
        saveMapTest.height = 3;
        boolean mapSaved = saveMapTest.saveTileMap();
        TileMap loadMapTest = new TileMap("resources/map_test.txt");
        boolean mapLoaded = loadMapTest.loadMap("resources/map_test.txt");
        boolean tiles2dAreTheSame = true;
        for (int i = 0; i < loadMapTest.tiles2d.length; i++) {
            for (int j = 0; j < loadMapTest.tiles2d[i].length; j++) {
                if (loadMapTest.tiles2d[i][j].id != saveMapTest.tiles2d[i][j].id) {
                    tiles2dAreTheSame = false;
                }
            }
        }
        return tiles2dAreTheSame && mapLoaded && mapSaved;
    }

    //Input: none, Output: boolean of if the component's parent entity is the entity created in the function
    //Purpose: makes sure Component.setParentEntity() assigns entity correctly and Entity.addComponent() calls Component.setParentEntity
    public static boolean AddComponentSetParentEntityTest() {
        TestComponent c = new TestComponent();
        Entity e = new Entity();
        e.addComponent(c);
        return c.entity == e;
    }

    //Input: Vector2f representing world position, Vector2f representing camera position, Vector2f representing expected screen position, Output: boolean if the expected Vector2f matches the calculated Vector2f
    //Purpose: makes sure world position to screen position conversion works properly
    public static boolean CameraWorldToScreenTest(Vector2f worldPos, Vector2f camPos, Vector2f expected) {
        Camera cam = new Camera(camPos);
        return cam.toScreenPos(worldPos).equals(expected);
    }

    //Input: Vector2f representing screen position, Vector2f representing camera position, Vector2f representing expected world position, Output: boolean if the expected Vector2f matches the calculated Vector2f
    //Purpose: makes sure screen position to world position conversion works properly
    public static boolean CameraScreenToWorldTest(Vector2f screenPos, Vector2f camPos, Vector2f expected) {
        Camera cam = new Camera(camPos);
        return cam.toWorldPos(screenPos).equals(expected);
    }
}