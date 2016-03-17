package Model;

import java.util.ArrayList;

/**
 * Created by snooze on 3/17/16.
 */
public class Tabla {
    ArrayList<Valor> arr = new ArrayList<Valor>();

    public Tabla(){}

    public Tabla(ArrayList<Valor> arr) {
        this.arr = arr;
    }

    public void add(Valor valor){
        arr.add(valor);
    }

    public void del(int a){
        arr.remove(a);
    }

    public void print(){
        for (int i=0; i<arr.size(); i++){
            System.out.println(i+" - ["+arr.get(i).toString()+"]");
        }
    }

}
