package Example;

import java.rmi.*;
import java.rmi.server.*;

/**
 * This class implements the remote interface 
 * CallbackClientInterface.
 * @author M. L. Liu
 */

public class CallbackCallbackClientImpl extends UnicastRemoteObject implements CallbackClientInterface {
  
   public CallbackCallbackClientImpl() throws RemoteException {
      super( );
   }

   public String notifyMe(String message){
      String returnMessage = "Call back received: " + message;
      System.out.println(returnMessage);
      return returnMessage;
   }      

}// end CallbackCallbackClientImpl class
