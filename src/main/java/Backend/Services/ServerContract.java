package Backend.Services;

import Model.RemoteTabla;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by snooze on 3/17/16.
 */
public interface ServerContract extends RemoteTabla {

    public final static int TYPE_MORE_THAN = 1;
    public final static int TYPE_LESS_THAN = 2;

    void register(ClientContract callbackClientObject, int i, int type, float cuantity) throws RemoteException;
    void unregister(ClientContract callbackClientObject, int i, int type, float cuantity) throws RemoteException;
    void unregister(ClientContract callbackClientObject) throws RemoteException;
    void sendnotify() throws RemoteException;
    ArrayList<String> getElements() throws RemoteException;

}