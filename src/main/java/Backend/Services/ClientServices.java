package Backend.Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by snooze on 3/17/16.
 */
public class ClientServices extends UnicastRemoteObject implements ClientContract {

    public ClientServices() throws RemoteException {
        super();
    }

    public String notifyMe(String message) throws RemoteException {
        String returnMessage = "-- Notification received: " + message;
        System.out.println(returnMessage);
        return returnMessage;
    }
}
