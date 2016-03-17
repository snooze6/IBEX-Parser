package Backend.Services;

import Model.Tabla;
import Model.Valor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by snooze on 3/17/16.
 */
public class ServerServices extends UnicastRemoteObject implements ServerContract {

    Tabla t;

    protected ServerServices() throws RemoteException {
        super();
        t = new Tabla();
    }

    public ServerServices(Tabla t) throws RemoteException {
        super();
        this.t = t;
    }

    public String toString(String message) throws RemoteException {
        return t.toString();
    }

    public Valor get(int i) throws RemoteException {
        if (i<t.size())
            return t.get(i);
        return new Valor("Mal", -1, -1);
    }

    public int size() throws RemoteException {
        return t.size();
    }

    public void print() throws RemoteException {
        t.print();
    }
}
