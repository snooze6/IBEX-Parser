package Backend.Services;

import Model.RemoteTabla;

import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public interface ServerContract extends RemoteTabla {

    void register(ClientContract callbackClientObject, int i) throws RemoteException;
    void unregister(ClientContract callbackClientObject, int i) throws RemoteException;
    void unregister(ClientContract callbackClientObject) throws RemoteException;
    void sendnotify() throws RemoteException;

}