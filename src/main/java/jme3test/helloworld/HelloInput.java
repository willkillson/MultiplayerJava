package jme3test.helloworld;

import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapText;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 * Sample 5 - how to map keys and mousebuttons to actions
 */
public class HelloInput extends SimpleApplication {

    public static void main(String[] args) {
        HelloInput app = new HelloInput();
        app.start();
    }

    private boolean isRunning = true;
    private float vx = 0.0f;


    private Node pivot;

    @Override
    public void simpleInitApp() {
        Box c = new Box(1,1,1);
        Geometry mPlayer = new Geometry("mPlayer", c);
        Material mMat = new Material(assetManager,"Common/MatDefs/Misc/Unshaded.j3md");
        mMat.setColor("Color",ColorRGBA.Orange);
        mPlayer.setMaterial(mMat);



        ///////font
        guiFont = assetManager.loadFont("Interface/Fonts/Default.fnt");
        BitmapText helloText = new BitmapText(guiFont, false);
        helloText.setSize(guiFont.getCharSet().getRenderedSize());
        helloText.setText("Hello World");
        helloText.setSize(0.5f);

        //helloText.setLocalTranslation(300, helloText.getLineHeight(), 0);



        rootNode.attachChild(mPlayer);

        this.pivot = new Node("pivot");
        pivot.attachChild(mPlayer);
        pivot.attachChild(helloText);

        rootNode.attachChild(pivot);


        initKeys(); // load my custom keybinding
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause",  new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Left",   new KeyTrigger(KeyInput.KEY_J));
        inputManager.addMapping("Right",  new KeyTrigger(KeyInput.KEY_K));
        inputManager.addMapping("Rotate", new KeyTrigger(KeyInput.KEY_SPACE),
            new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause");
        inputManager.addListener(analogListener, "Left", "Right", "Rotate");

    }

    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                isRunning = !isRunning;
            }
        }
    };

    private final AnalogListener analogListener = new AnalogListener() {
        @Override
        public void onAnalog(String name, float value, float tpf) {
            if (isRunning) {
                if (name.equals("Rotate")) {
                    vx+=0.01*tpf;

                }

            } else {
                System.out.println("Press P to unpause.");
            }
        }
    };

    /* Use the main event loop to trigger repeating actions. */
    @Override
    public void simpleUpdate(float tpf) {
        // make the player rotate:
        if(vx>0){
            vx-=0.001*tpf;
        }
        if(isRunning)
            pivot.rotate(-vx, vx, 0);

    }
}