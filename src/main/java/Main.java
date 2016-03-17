import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by snooze on 3/17/16.
 */
public class Main {

    Tabla t = new Tabla();

    public static void main (String... params){

        Document doc;
        try {

            // need http protocol
            doc = Jsoup.connect("http://www.bolsamadrid.es/esp/aspx/Portada/Portada.aspx").get();

            // get page title
            String title = doc.title();
            System.out.println("title : " + title);

//            // get all links
//            Elements links = doc.select("a[href]");
//            for (Element link : links) {
//                // get the value from href attribute
//                System.out.println("\nlink : " + link.attr("href"));
//                System.out.println("text : " + link.text());
//            }

            Element table = doc.select("table[class=TblPort TblAccPort]").first();

            Iterator<Element> th = table.select("th").iterator();
            int i=0;
            while(th.hasNext()){
                i++;
                System.out.println(i+" - "+th.next().text());
            }

            System.out.println("");
            Iterator<Element> tr = table.select("tr").iterator();
            tr.next(); //First one is headers one
            i=0;
            while(tr.hasNext()){
                i++;

                Element row = tr.next();
                Iterator<Element> rows = row.select("td").iterator();

                String company, last, dif;
                company = rows.next().text();
                last = rows.next().text();
                dif = rows.next().text();

//                while(rows.hasNext()){
//                    System.out.print(rows.next().text());
//                    if (rows.hasNext()){
//                        System.out.print(" - ");
//                    }
//                }

                System.out.println(i+" - <"+company+" - "+last+" - "+dif+">");
            }


//            System.out.println("Tabla "+table.toString());


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
