package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerOperation {	
    
    
    public static void main(String[] args){    	
        try {        	 
        	/*System.setProperty("java.security.policy","file:java.policy") ; 
        	System.setProperty("java.rmi.server.hostname","192.168.6.107"); */
        	       	
        	Registry varRegistry = LocateRegistry.createRegistry(1097) ;
        	RMIInterfaceIMPL rmil = new RMIInterfaceIMPL()  ;  
        	varRegistry.rebind("loizrmiserver", rmil );
    		System.out.println("jesuis le serveur > après rebind");
         	
        } 
        catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

        
    }




}

