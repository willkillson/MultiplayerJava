package game.networking.listeners;

import com.jme3.network.HostedConnection;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;
import game.networking.messages.HelloMessage;

public class ServerListener implements MessageListener<HostedConnection> {

    @Override
    public void messageReceived(HostedConnection hostedConnection, Message message) {
        if (message instanceof HelloMessage) {
            // do something with the message
            HelloMessage helloMessage = (HelloMessage) message;
            System.out.println("Server received '"
                +helloMessage.getSomething() +"' from client #"+hostedConnection.getId());
        }
    }
}