package jme3test.helloworld;

import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.scene.Spatial;

public class MyLoadExercise extends SimpleApplication {

    public static void main(String[] args){
        MyLoadExercise app = new MyLoadExercise();
        app.start();
    }
    @Override
    public void simpleInitApp() {
        assetManager.registerLocator("town.zip", ZipLocator.class);
        Spatial gameLevel = assetManager.loadModel("main.scene");
        gameLevel.setLocalTranslation(0, -5.2f, 0);
        gameLevel.setLocalScale(2);
        rootNode.attachChild(gameLevel);

    }
}
