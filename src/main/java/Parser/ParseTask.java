package Parser;

import Model.Tabla;

import java.rmi.RemoteException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by snooze on 3/17/16.
 */
public class ParseTask implements Runnable {

    protected Tabla t;
    public static int FRECUENCY = 10;
    public static int i = 0;

    public ParseTask(Tabla t){
        this.t = t;
    }

    public void doit(){
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();

        // Do the thing every 10 seconds
        executor.scheduleAtFixedRate(this, 0, FRECUENCY, TimeUnit.SECONDS);
    }

    public void run() {
        try {
            this.t = IbexParser.parse();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
//        t.print();
//        System.out.println(" ---- "+i);
        i++;
    }

}
