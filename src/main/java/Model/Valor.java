package Model;

import java.io.Serializable;

/**
 * Created by snooze on 3/17/16.
 */
public class Valor implements Serializable {
    public String company;
    public float last, dif;

    public Valor(String company, float last, float dif) {
        this.company = company;
        this.last = last;
        this.dif = dif;
    }

    @Override
    public String toString() {
        return company+" - "+last+" - "+dif;
    }
}
