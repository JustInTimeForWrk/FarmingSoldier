package util;

import org.joml.Vector2f;
import physics.BoxCollider;
import physics.Collider;

public class TestManager {

    //Input: none, Output: none
    //Purpose: runs and prints all the test functions inside the function
    public static void runTests() {
        System.out.println(BoxColliderGetMinTest(new BoxCollider(),new Vector2f(0,0),true));
        System.out.println(BoxColliderGetMinTest(new BoxCollider(new Vector2f(1,2),new Vector2f(10,5)),new Vector2f(10,5),true));
//        System.out.println(testSaveMap());
    }

    public static boolean BoxColliderGetMinTest(BoxCollider collider, Vector2f point, boolean expectedResult) {
        Entity testEntity = new Entity();
        testEntity.addComponent(collider);
        collider.updateCollider();
        System.out.println(collider.getMin().sub(point.x, point.y));
        return expectedResult == collider.getMin().equals(point.x, point.y);
    }

    public static boolean testSaveMap() {
        return false;
    }
}
