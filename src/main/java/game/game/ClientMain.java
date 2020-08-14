package game.game;

import com.jme3.app.SimpleApplication;
import com.jme3.network.Client;
import com.jme3.network.ClientStateListener;
import com.jme3.network.Message;
import com.jme3.network.Network;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;
import game.networking.listeners.ClientListener;
import game.networking.messages.HelloMessage;
import java.io.IOException;

public class ClientMain extends SimpleApplication implements ClientStateListener {
    public static void main(String[] args) {
        ClientMain app = new ClientMain();
        app.start(JmeContext.Type.Display); // standard display type
    }

    @Override
    public void simpleInitApp() {

        Client myClient = null;
        try {
            myClient = Network.connectToServer("localhost", 6143);
            initializeSerializables();
            myClient.addMessageListener(new ClientListener(), HelloMessage.class);


            /*
                Add the client state listener, this will handle clientConnected(Client c)
                and clientDisconnected(Client c, DisconnectInfo info)

                These are brought in when we implement ClientStateListener
             */
            myClient.addClientStateListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        myClient.start();

        Message message = new HelloMessage("Hello world!");
        myClient.send(message);
    }




    public void initializeSerializables() {
        Serializer.registerClass(HelloMessage.class);
    }

    @Override
    public void clientConnected(Client client) {

    }

    @Override
    public void clientDisconnected(Client client, DisconnectInfo disconnectInfo) {

    }
}