package Model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * Created by snooze on 3/17/16.
 */
public class Tabla extends UnicastRemoteObject implements RemoteTabla{
    ArrayList<Valor> arr = new ArrayList<Valor>();

    public Tabla() throws RemoteException {
        super();
    }

    public Tabla(ArrayList<Valor> arr) throws RemoteException {
        super();
        this.arr = arr;
    }

    public void add(Valor valor){
        arr.add(valor);
    }

    public void del(int a){
        arr.remove(a);
    }

    public Valor get(int a) throws RemoteException {return arr.get(a);}

    public int size() throws RemoteException {
        return arr.size();
    }

    public void print() throws RemoteException {
        for (int i=0; i<arr.size(); i++){
            System.out.println(i+" - ["+arr.get(i).toString()+"]");
        }
    }

    public String toString(String message) throws RemoteException {
        String s = "";
        for (int i=0; i<arr.size(); i++){
            s = i+" - ["+arr.get(i).toString()+"]\n";
        }
        return s;
    }

}
