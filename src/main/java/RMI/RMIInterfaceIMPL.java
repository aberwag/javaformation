package RMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class RMIInterfaceIMPL extends UnicastRemoteObject  implements RMIInterface {
	
	private static final long serialVersionUID = 1L;
	
	protected RMIInterfaceIMPL() throws RemoteException {
		super(1097);
	}

	@Override
	public String helloTo(String name) throws RemoteException {		
		return "reçu : " + name ;
	}

	@Override
	public String StockerEnStatiqueIpClient(interfClient intClient) throws RemoteException {
	    System.out.println("≤StockerEnStatiqueIpClient> : dans l'implementation serveur");
		System.out.println("≤StockerEnStatiqueIpClient> intClient.hashCode(): " +  intClient.hashCode()) ;
        System.out.println("≤StockerEnStatiqueIpClient> intClient.RecupereAdresseIp() : " +  intClient.RecupereAdresseIp() ) ;
        System.out.println("≤StockerEnStatiqueIpClient> intClient.getAttAdresseIp() : " +  intClient.getAttAdresseIp()) ;		
		String sHote = intClient.RecupereAdresseIp() ;		 
	    return sHote ; 
		//return "ceci est la réponse du serveur (au niveau du serveur)" ;
	}


}