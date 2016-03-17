package Backend.Services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by snooze on 3/17/16.
 */
public class ClientServices extends UnicastRemoteObject implements ClientContract {

    protected ClientServices() throws RemoteException {
        super();
    }
}
