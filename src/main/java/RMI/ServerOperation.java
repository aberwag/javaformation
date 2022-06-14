package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerOperation {	
    
    static String DonneeClient ;  
    
    public String helloTo(String dataclient) throws RemoteException {
    	ServerOperation.DonneeClient = dataclient ; 
        System.err.println(dataclient + " : valeur envoyée du client") ; 
        return "Le serveur a bien reçu le mot : " + dataclient ; 
    }
    
    public static void main(String[] args){    	
        try {        	 
        	System.setProperty("java.security.policy","file:java.policy") ; 
        	System.setProperty("java.rmi.server.hostname","192.168.6.107");
        	RMIInterfaceIMPL rmil = new RMIInterfaceIMPL()  ;         	
        	Registry varRegistry = LocateRegistry.createRegistry(1097) ;
        	varRegistry.rebind("loizrmiserver", rmil );
    		System.out.println("jesuis le serveur > après rebind");
         	//varRegistry = java.rmi.registry.LocateRegistry.createRegistry(1097) ;  
         	//varRegistry.bind("loizrmiserver", new ServerOperation());
         	
        } 
        catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

        
    }




}

