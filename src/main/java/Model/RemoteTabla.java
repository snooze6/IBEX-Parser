package Model;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public interface RemoteTabla extends Remote {

    String toString(String message) throws RemoteException;

    Valor get(int i) throws RemoteException;

    int size() throws RemoteException;

    void print() throws RemoteException;

}
