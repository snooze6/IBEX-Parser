package Model;

import com.sun.org.apache.regexp.internal.RE;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.StringTokenizer;

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

    public Valor get(int a) {return arr.get(a);}

    public int size(){
        return arr.size();
    }

    public void print(){
        for (int i=0; i<arr.size(); i++){
            System.out.println(i+" - ["+arr.get(i).toString()+"]");
        }
    }

    @Override
    public String toString() {
        String s = "";
        for (int i=0; i<arr.size(); i++){
            s = i+" - ["+arr.get(i).toString()+"]\n";
        }
        return s;
    }

    public String caca() {
        return "caca";
    }
}
