package game.networking.listeners;

import com.jme3.network.Client;
import com.jme3.network.Message;
import com.jme3.network.MessageListener;

import game.networking.messages.HelloMessage;

public class ClientListener implements MessageListener<Client> {

    @Override
    public void messageReceived(Client client, Message message) {

        if(message instanceof HelloMessage) {
            HelloMessage helloMessage = (HelloMessage) message;
            System.out.println("Client #"+client.getId()+" received: '"+helloMessage.getSomething()+"'");
        }
    }
}
