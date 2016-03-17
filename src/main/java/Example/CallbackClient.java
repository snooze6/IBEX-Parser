package Example;

import Example.CallbackServerInterface;

import java.io.*;
import java.rmi.*;

/**
 * This class represents the object client for a
 * distributed object of class CallbackServerImpl,
 * which implements the remote interface
 * CallbackServerInterface.  It also accepts callback
 * from the server.
 *
 * @author M. L. Liu
 */

public class CallbackClient {

    public static void main(String args[]) {
        try {
            // Adquiring data
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Enter the RMIRegistry host namer:");
            String hostName = br.readLine();
            System.out.println("Enter the RMIregistry port number:");
            int RMIPort = Integer.parseInt(br.readLine());
            System.out.println("Enter how many seconds to stay registered:");
            int time = Integer.parseInt(br.readLine());

            String registryURL = "rmi://localhost:" + RMIPort + "/callback";
            System.out.println("--URL: "+registryURL);

            // find the remote object and cast it to an interface object
            CallbackServerInterface h = (CallbackServerInterface) Naming.lookup(registryURL);
            System.out.println("-- Lookup completed ");
            System.out.println("-- Server said " + h.sayHello());
            CallbackClientInterface callbackObj =
                    new CallbackClientImpl();

            // register for callback
            h.registerForCallback(callbackObj);
            System.out.println("-- Registered for callback.");

            // wait for unregister
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException ex) { // sleep over
            }
            h.unregisterForCallback(callbackObj);
            System.out.println("-- Unregistered for callback.");
        } catch (Exception e) {
            System.out.println("++ Exception in CallbackClient: " + e);
        }
    }
}
