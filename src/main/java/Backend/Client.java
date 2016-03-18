package Backend;

import Backend.Services.ClientContract;
import Backend.Services.ClientServices;
import Backend.Services.ServerContract;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public class Client {
    public static void main(String... args) {
        try {
            ServerContract t = (ServerContract) Naming.lookup(Configuracion.URL);
            System.out.println("Connected to server");

            ClientContract c = new ClientServices();
            t.register(c, 0);
            System.out.println("Tried to register");

            t.register(c, 0);
            System.out.println("Tried to register");

            System.out.println(t.get(0).toString());

            t.unregister(c);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
