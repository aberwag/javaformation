package RMI;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class interfClientImpl  extends UnicastRemoteObject implements interfClient {
	
	public interfClientImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;
	
    public String RecupereAdresseIp() throws RemoteException {
		String sHote = null ;
		try {
			sHote = InetAddress.getLocalHost().getHostAddress() ;
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return sHote ;
	}
}