package RMI;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerOperation extends UnicastRemoteObject implements RMIInterface {

    private static final long serialVersionUID = 1L;
    static Registry varRegistry ;

    protected ServerOperation() throws RemoteException {

        //super(Registry.REGISTRY_PORT);
    	 super(1097);

    }
    
    public String helloTo(String name) throws RemoteException{

        System.err.println(name + " is trying to contact!");
        return "Le serveur a bien reçu le mot : " + name;

    }

    public static void main(String[] args){
    	
        //System.setProperty("Djava.rmi.server.hostname" , "127.0.0.1" );
        InetAddress ip;
        String hostname = "" ;
        try {
            ip = InetAddress.getLocalHost();           
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);
 
        } catch (UnknownHostException e) {
 
            e.printStackTrace();
        }
        try {
        	String strHost = "0.0.0.0:1097" ;
        	//varRegistry = java.rmi.registry.LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        	varRegistry = java.rmi.registry.LocateRegistry.createRegistry(1097);
        	//varRegistry.rebind("//" + strHost + "/loizrmiserver", new ServerOperation());
        	varRegistry.rebind("//0.0.0.0:1097/loizrmiserver", new ServerOperation());
        	//varReristry.rebind("rmi://" + strHost + ":1099/loizrmiserver", exportObject(new ServerOperation() , 0));
        	
            System.err.println("Server disponible sur le nom d'hote  : " + strHost);

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }

    }

}
