package RMI;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface interfClient extends Remote, Serializable {
	
    public String RecupereAdresseIp() throws RemoteException ;

}