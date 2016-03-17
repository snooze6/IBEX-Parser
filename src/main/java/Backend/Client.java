package Backend;

import Model.RemoteTabla;
import Model.Tabla;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public class Client {
    public static void main(String... args) {
        try {

            Remote lookup = Naming.lookup(Configuracion.URL);
            System.out.println("Connected to server");

            Tabla t;
            if (lookup instanceof Tabla) {
                 t = (Tabla) lookup;
            } else {
                 t = new Tabla();
            }

            System.out.println(t.toString());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
