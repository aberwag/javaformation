package RMI;


import java.rmi.RemoteException;

public class RMIInterfaceIMPL implements RMIInterface {

	public String helloTo(String name) throws RemoteException {		
		return "reçu : " + name ;
	}

	public String StockerEnStatiqueIpClient(interfClient intClient) throws RemoteException {
		System.out.println("≤StockerEnStatiqueIpClient> : dans l'implementation serveur");
		String sHote = intClient.RecupereAdresseIp() ;
		return sHote ;				
	}




}