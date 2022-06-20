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
	
	private ArrayList<String> objSetJeuSaisies = null ;
	
	private final ArrayList<String> ObjSaisieNulle = null ;
	
	private LinkedHashSet<interfClient> objListClients = null ;
	
	//Tableau des clients à mettre à jour
	private List<Entry<Integer,Boolean>> ListClientToUpdate = null;
	
	
	protected RMIInterfaceIMPL() throws RemoteException {
		super(1097);
	}  
	
	//Ici on dispose de l'échange à jour
	@Override
	public ArrayList<String> stoquerSaisieClient(String sArgSaisie, interfClient intClient, boolean bManuel) throws RemoteException {
		System.out.println("(<stoquerSaisieClient hashcode de l\'instance serveur = "+this.hashCode()+"> Ici serveur) : saisie client vaut \"" + sArgSaisie + "\"");				
		
		
		
		Integer iCode = intClient.hashCode() ;
		if (bManuel) {
		
		if (objSetJeuSaisies == null)
			objSetJeuSaisies = new ArrayList<String>();
		
		objSetJeuSaisies.add(sArgSaisie);
		}
		
		if(objListClients==null)	{	  
			objListClients = new LinkedHashSet<interfClient>() ; 			
			objListClients.add(intClient) ; 
		
		System.out.println("(<stoquerSaisieClient - hashcode client = " + intClient.hashCode() + ">" ) ; 		
		}
		/*
		//ObjSaisieNulle
		if (ListClientToUpdate == null) {
			ListClientToUpdate = new ArrayList<>() ; 			
		}
	   // On initialise avec la première saisie client
		if(ListClientToUpdate.isEmpty())
			ListClientToUpdate.add(new SimpleEntry<>(iCode,false)) ;
		//Liste non vide : des saisies ont été effectuées
		else if (!ListClientToUpdate.contains(iCode)) {			
			 int index = ListClientToUpdate.indexOf(iCode) ;
			 //Le client courant n'éxiste pas et vient d'ajouter une valeur
			 if (index == -1) 
			ListClientToUpdate.add(new SimpleEntry<>(iCode,true));
			 
			 else {
				//Le client courant éxiste et vient d'ajouter une valeur
				//On doit comparer ses données locales avec les données serveur
			  ListClientToUpdate.add(new SimpleEntry<>(iCode,true));
			 }
		}
		else {
		    int index = ListClientToUpdate.indexOf(iCode) ;  
		    ListClientToUpdate.remove(index); 
		    ListClientToUpdate.add(index, new SimpleEntry<>(iCode,true)) ; 		    
		} */
		
		return objSetJeuSaisies ;
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




