package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject ;

public class RMIInterfaceIMPL implements RMIInterface {

	public String helloTo(String name) throws RemoteException {
		
		return "reçu : " + name ;
	}	


}