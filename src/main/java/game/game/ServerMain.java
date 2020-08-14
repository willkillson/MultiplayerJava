package game.game;

import com.jme3.app.SimpleApplication;
import com.jme3.network.ConnectionListener;
import com.jme3.network.HostedConnection;
import com.jme3.network.Network;
import com.jme3.network.Server;
import com.jme3.network.serializing.Serializer;
import com.jme3.system.JmeContext;
import java.io.IOException;
import game.networking.listeners.ServerListener;
import game.networking.messages.HelloMessage;

public class ServerMain extends SimpleApplication implements ConnectionListener {
    public static void main(String[] args) {
        ServerMain app = new ServerMain();
        app.start(JmeContext.Type.Headless); // headless type for servers!
    }

    @Override
    public void simpleInitApp() {
        Server myServer = null;
        try {
            myServer = Network.createServer(6143);
            myServer.addMessageListener(new ServerListener(), HelloMessage.class);
            this.initializeSerializables();

            /*
                First implement the ConnectionListener interface in the Server class.
                Then register it to myServer in MyGameServer’s simpleInitApp() method.
             */
            myServer.addConnectionListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
        myServer.start();


        /*
            Or the server can broadcast this message to all HostedConnection (clients):
            Message message = new HelloMessage("Welcome!");
            myServer.broadcast(message);
         */

        /*
            Or the server can send the message to a specific subset of clients (e.g. to HostedConnection conn1, conn2, and conn3):
            myServer.broadcast( Filters.in( conn1, conn2, conn3 ), message );
         */

        /*
            Or the server can send the message to all but a few selected clients (e.g. to all HostedConnections but conn4):
            myServer.broadcast( Filters.notEqualTo( conn4 ), message );
         */
    }

    public void initializeSerializables() {
        Serializer.registerClass(HelloMessage.class);
    }

    @Override
    public void connectionAdded(Server server, HostedConnection hostedConnection) {

    }

    @Override
    public void connectionRemoved(Server server, HostedConnection hostedConnection) {

    }
}