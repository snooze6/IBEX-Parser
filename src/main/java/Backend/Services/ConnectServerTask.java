package Backend.Services;

import Backend.Configuracion;
import javafx.scene.control.ComboBox;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import GUI.GUI;

/**
 * Created by snooze on 3/17/16.
 */
public class ConnectServerTask implements Runnable {

    ServerContract t;
    ClientContract c;
    ComboBox<String> cmb;
    Boolean done;
    public GUI gui;

    public ConnectServerTask(GUI gui) {
        this.gui = gui;
        this.t = gui.getT();
        this.c = gui.getC();
        this.cmb = gui.getComboBox();
        this.done = gui.getDone();
    }

    public void doit(){
        new Thread(this).start();
    }

    public void run() {
        try {
            t = (ServerContract) Naming.lookup(Configuracion.URL);
            System.out.println("Connected to server");

            c = (new ClientServices()).setGUI(gui);
//            t.register(c, 34, ServerContract.TYPE_MORE_THAN, 8);
//            System.out.println("Tried to register");

            cmb.getItems().addAll(t.getElements());
            done = true;

            gui.regenerate(c,t,done);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
