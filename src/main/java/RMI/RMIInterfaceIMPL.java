package RMI;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;


public class RMIInterfaceIMPL extends UnicastRemoteObject  implements RMIInterface {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> objSetJeuSaisies = null ;
	
	//private collection colll ;
	
	protected RMIInterfaceIMPL() throws RemoteException {
		super(1097);
	}

	//ArrayList<String> cars = new ArrayList<String>();
	
	@Override
	public ArrayList<String>  stoquerSaisieClient(String sArgSaisie) throws RemoteException {
		System.out.println("(<stoquerSaisieClient hashcode de l\'instance serveur = "+this.hashCode()+"> Ici serveur) : saisie client vaut \"" + sArgSaisie + "\"");				
		
		if (objSetJeuSaisies == null)
			objSetJeuSaisies = new ArrayList<String>();
		objSetJeuSaisies.add(sArgSaisie);
		
		System.out.println("(<stoquerSaisieClient objSetJeuSaisies membre, son hashcode = "+objSetJeuSaisies.hashCode()+">");

		return objSetJeuSaisies ;
	}

	@Override
	public String helloTo(String name) throws RemoteException {		
		return "reçu : " + name ;
	}

	@Override
	public String StockerEnStatiqueIpClient(interfClient intClient) throws RemoteException {	
		String sHote = intClient.RecupereAdresseIp() ;		 
	    return sHote ; 
	}

}