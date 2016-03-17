import Model.Tabla;
import Parser.ParseTask;

import java.rmi.RemoteException;

/**
 * Created by snooze on 3/17/16.
 */
public class Main {



    public static void main (String... params){
        try {
            new ParseTask(new Tabla()).doit();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
