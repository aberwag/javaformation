package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface{

    private static final long serialVersionUID = 1L;
    static Registry varRegistry ;
    static String DonneeClient ; 
    
    
    protected ServerOperation() throws RemoteException {
    	 super(1097);
    }    
    
	public String StockerEnStatiqueIpClient(interfClient intClient) throws RemoteException {		
		String sHote = intClient.RecupereAdresseIp() ;
		System.out.println("Un\'hote vient de contacter ce serveur, voici son ip : " + sHote);
		return sHote ;
	}
    
    public String helloTo(String dataclient) throws RemoteException {
    	ServerOperation.DonneeClient = dataclient ; 
        System.err.println(dataclient + " : valeur envoyée du client") ; 
        return "Le serveur a bien reçu le mot : " + dataclient ; 
    }
    
    public static void main(String[] args){    	
        try {
         	varRegistry = java.rmi.registry.LocateRegistry.createRegistry(1097) ;      
        	varRegistry.rebind("loizrmiserver", new ServerOperation());        	   

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }




}
