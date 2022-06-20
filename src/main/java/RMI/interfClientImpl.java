package RMI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class interfClientImpl  extends UnicastRemoteObject implements interfClient {
	
	private static final long serialVersionUID = 1L;
	
	private String attAdresseIp ; 


	public interfClientImpl() throws RemoteException {
		//super(1098);
		super() ;
	}

	@Override
    public String RecupereAdresseIp() throws RemoteException {
		String sHote = null ;
		try {
			sHote = InetAddress.getLocalHost().getHostAddress() ;
			setAttAdresseIp(sHote);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return sHote ;
	}

    @Override
	public String getAttAdresseIp() throws RemoteException  {
		return attAdresseIp;
	}


	public void setAttAdresseIp(String attAdresseIp) {
		this.attAdresseIp = attAdresseIp;
	}
}