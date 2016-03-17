package Parser;

import Model.Tabla;
import Model.Valor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;

/**
 * Created by snooze on 3/17/16.
 */
public class IbexParser {

    public static Tabla parse() throws RemoteException {
        Tabla t = new Tabla();

        {
            Document doc;
            try {
                // need http protocol
                doc = Jsoup.connect("http://www.bolsamadrid.es/esp/aspx/Portada/Portada.aspx").get();

                // get values table
                Element table = doc.select("table[class=TblPort TblAccPort]").first();

                // get rows
                Iterator<Element> tr = table.select("tr").iterator();
                tr.next(); //First one is headers one

                // Iterate throw rows
                while(tr.hasNext()){
                    Element row = tr.next();
                    Iterator<Element> rows = row.select("td").iterator();

                    String company, last, dif;
                    company = rows.next().text();
                    last = rows.next().text().replace(",",".");
                    dif = rows.next().text().replace(",",".");

                    // Save value
                    t.add(new Valor(company, Float.parseFloat(last), Float.parseFloat(dif)));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return t;
    }

}
