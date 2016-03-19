package Backend.Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import GUI.GUI;
import GUI.Dialogs;

/**
 * Created by snooze on 3/17/16.
 */
public class ClientServices extends UnicastRemoteObject implements ClientContract {

    GUI g = null;

    public ClientServices() throws RemoteException {
        super();
    }

    public ClientServices setGUI(GUI g){
        this.g = g;
        return this;
    }

    public String notifyMe(String message) throws RemoteException {
        String returnMessage = "-- Notification received: " + message;
        System.out.println(returnMessage);

        if (g!=null){
            g.showMessage(message);
        }

        return returnMessage;
    }
}
