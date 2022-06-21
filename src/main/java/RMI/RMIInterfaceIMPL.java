package RMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.javatuples.Pair;


public class RMIInterfaceIMPL extends UnicastRemoteObject  implements RMIInterface {
	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> attrSetJeuSaisies = null ;
	
	private final ArrayList<String> ObjSaisieNulle = null ;
	
	private LinkedHashSet<interfClient> objListClients = null ;
	
	//Tableau des clients à mettre à jour
	private List<Entry<Integer,Boolean>> ListClientToUpdate = null;
	
	
	protected RMIInterfaceIMPL() throws RemoteException {
		super(1097);
	}  
	
	public ArrayList<String> getAttrSetJeuSaisies() {
		return attrSetJeuSaisies;
	}

	public void setAttrSetJeuSaisies(boolean bManuel,String sArgSaisie ) {
		if (bManuel) {
		if (attrSetJeuSaisies == null)
			attrSetJeuSaisies = new ArrayList<String>();
		attrSetJeuSaisies.add(sArgSaisie);
		}
	}

	//Ici on dispose de l'échange à jour
	@Override
	public ArrayList<String> stoquerSaisieClient(String sArgSaisie, interfClient intClient, boolean bManuel) throws RemoteException {
		System.out.println("(<stoquerSaisieClient hashcode de l\'instance serveur = "+this.hashCode()+"> Ici serveur) : saisie client vaut \"" + sArgSaisie + "\"") ; 
		
		setAttrSetJeuSaisies( bManuel, sArgSaisie ) ;         
		
		if(objListClients==null)	{	  
			objListClients = new LinkedHashSet<interfClient>() ; 			
			objListClients.add(intClient) ; 		
		System.out.println("(<stoquerSaisieClient - hashcode client = " + intClient.hashCode() + ">" ) ; 		
		} 
	
		return attrSetJeuSaisies ;
	}

	public Set<interfClient> getObjListClients() {
		return objListClients;
	}

	public void setObjListClients(LinkedHashSet<interfClient> objListClients) {
		this.objListClients = objListClients;
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




