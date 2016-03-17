import Model.Tabla;
import Parser.ParseTask;

/**
 * Created by snooze on 3/17/16.
 */
public class Main {



    public static void main (String... params){
        new ParseTask(new Tabla()).doit();
    }

}
