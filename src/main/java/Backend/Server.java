package Backend;

import Backend.Services.ServerServices;
import Backend.Services.ServerTask;
import Model.Tabla;
import Parser.IbexParser;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by snooze on 3/17/16.
 */
public class Server {

    //This method starts a RMI registry on the local host, if
    //it does not already exists at the specified port number.
    private static void startRegistry(int RMIPortNum) throws RemoteException{
        try {
            Registry registry = LocateRegistry.getRegistry(RMIPortNum);
            registry.list( );
            // This call will throw an exception
            // if the registry does not already exist
        } catch (RemoteException e) {
            // No valid registry at that port.
            LocateRegistry.createRegistry(RMIPortNum);
        }
    } // end startRegistry

    public static void main(String... args){
        ServerServices s;
        try {
            Tabla t = IbexParser.parse();
            s = new ServerServices(t);
            new ServerTask(t, s).doit();
            System.out.println("-- Lanzado demonio de actualización de la tabla");

            startRegistry(Configuracion.PORT);
            System.out.println("-- Registrado RMI en el puerto: "+Configuracion.PORT);

            Naming.rebind(Configuracion.URL,s);
            System.out.println("-- Exportado objeto");
            System.out.println("-- Servidor listo");
//            System.out.println("-- Sirviendo <"+s.size()+"> Objetos");

        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
