package Backend.Services;

import Backend.Configuracion;
import Model.Tabla;
import Parser.IbexParser;
import javafx.scene.control.ComboBox;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by snooze on 3/17/16.
 */
public class ConnectServerTask implements Runnable {

    ServerContract t;
    ClientContract c;
    Boolean done;
    ComboBox<String> cmb;

    public ConnectServerTask(ServerContract t, ClientContract c, Boolean done, ComboBox<String> cmb) {
        this.t = t;
        this.c = c;
        this.done = done;
        this.cmb = cmb;
    }

    public void doit(){
        new Thread(this).start();
    }

    public void run() {
        try {
            ServerContract t = (ServerContract) Naming.lookup(Configuracion.URL);
            System.out.println("Connected to server");

            ClientContract c = new ClientServices();
//            t.register(c, 34, ServerContract.TYPE_MORE_THAN, 8);
//            System.out.println("Tried to register");

            cmb.getItems().addAll(t.getElements());

            done = true;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
