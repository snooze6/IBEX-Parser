package Backend.Services;

import Model.Tabla;
import Model.Valor;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

/**
 * Created by snooze on 3/17/16.
 */
public class ServerServices extends UnicastRemoteObject implements ServerContract {

    class NotifyRow {
        public ClientContract client;
        public int i;

        public NotifyRow(ClientContract client, int i) {
            this.client = client;
            this.i = i;
        }

        @Override
        public boolean equals(Object obj) {
//            if (obj instanceof NotifyRow) {
//                System.out.println("--Comparando: ");
//                System.out.println("---- [" + client + " - " + i + "]");
//                System.out.println("---- [" + ((NotifyRow)obj).client + " - " + ((NotifyRow)obj).i + "]");
//                System.out.println("---- ["+(i==((NotifyRow)obj).i)+" - "+(((NotifyRow)obj).client.toString().equals(client.toString()))+"]");
//            }
            return obj instanceof NotifyRow && (i == ((NotifyRow) obj).i && (((NotifyRow)obj).client.toString().equals(client.toString())));
        }
    }

    Tabla t;
    Vector<NotifyRow> observers = new Vector<NotifyRow>();

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

    //--------------------------------------------------------------------------------

    public synchronized void register(ClientContract c, int i) throws RemoteException{
        // store the callback object into the vector
        NotifyRow client = new NotifyRow(c,i);
        if (!(observers.contains(client))) {
            observers.addElement(client);
            System.out.println("-- Registered new client ["+c+"-"+i+"]");
            sendnotify();
        } else {
            System.out.println("** Client was already registered ["+c+"-"+i+"]");
        }
    }

    // This remote method allows an object client to
    // cancel its registration for callback
    // @param id is an ID for the client; to be used by
    // the server to uniquely identify the registered client.
    public synchronized void unregister(ClientContract c, int i) throws RemoteException{
        NotifyRow client = new NotifyRow(c,i);
        if (observers.removeElement(client)) {
            System.out.println("-- Unregistered client ["+c+"-"+i+"]");
        } else {
            System.out.println("** Unregister: client wasn't registered ["+c+"-"+i+"]");
        }
    }

    public synchronized void unregister(ClientContract c) throws RemoteException{
        int j=0;
        for (int i=0; i<observers.size(); i++){
            if (((NotifyRow) observers.get(i)).client.toString().equals(c.toString())) {
                System.out.println("-- Unregistered client "+c+"-"+((NotifyRow) observers.get(i)).i+"]");
                observers.removeElement(i);
                j++;
            }
        }
        if (j==0) {
            System.out.println("** Unregister: client wasn't registered ["+c+"]");
        }
    }

    public synchronized void sendnotify() throws RemoteException{
//        // make callback to each registered client
//        for (int i = 0; i < observers.size(); i++){
//            System.out.println("-- Doing "+ i +"-th callback\n");
//            // convert the vector object to a callback object
//            ClientContract nextClient =
//                    (ClientContract)observers.elementAt(i);
//            // invoke the callback method
//            nextClient.notifyMe("-- Number of registered clients="+observers.size());
//        }
    }

}
