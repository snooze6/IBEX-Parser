package Parser;

import Model.Tabla;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by snooze on 3/17/16.
 */
public class ParseTask implements Runnable {

    Tabla t;
    public static int FRECUENCY = 10;
    public static int i = 0;

    public ParseTask(Tabla t){
        this.t = t;
    }

    public void doit(){
        ScheduledExecutorService executor =
                Executors.newSingleThreadScheduledExecutor();

        // Do the thing every 10 seconds
        executor.scheduleAtFixedRate(new ParseTask(t), 0, FRECUENCY, TimeUnit.SECONDS);
    }

    public void run() {
        this.t = IbexParser.parse();
        t.print();
        System.out.println(" ---- "+i);
        i++;
    }

}
