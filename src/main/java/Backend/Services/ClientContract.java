package Backend.Services;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public interface ClientContract extends Remote {

    public String notifyMe(String message) throws RemoteException;
}
