package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfClient extends Remote {
	
    public String RecupereAdresseIp() throws RemoteException ;

}
