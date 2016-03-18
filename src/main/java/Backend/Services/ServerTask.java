package Backend.Services;

import Model.Tabla;
import Parser.ParseTask;

import java.rmi.RemoteException;

/**
 * Created by snooze on 3/18/16.
 */
public class ServerTask extends ParseTask {

    ServerContract s;

    public ServerTask(Tabla t, ServerContract s) {
        super(t);
        this.s = s;
    }

    @Override
    public void doit() {
        super.doit();
    }

    @Override
    public void run() {
        super.run();
        try {
            s.sendnotify();
//            t.print();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
