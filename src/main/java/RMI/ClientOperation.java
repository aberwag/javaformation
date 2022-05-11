package RMI;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Enumeration;

import javax.swing.JOptionPane;

public class ClientOperation {

	private static RMIInterface rmi_interf ;

	public static void main(String[] args) 
		throws MalformedURLException, RemoteException, NotBoundException {
        
		try {
			Registry reg=LocateRegistry.getRegistry("0.0.0.0",Integer.parseInt("1097"));			
		    String[] boundNames = reg.list();
		    
		    for(String name : boundNames){
		    	System.out.println("1097 : " + name);
		    }
			//IP : MBP-de-Loizani.localdomain/192.168.6.107
		    reg=LocateRegistry.getRegistry("0.0.0.0",Integer.parseInt("1098"));			
		    boundNames = reg.list();
		    
		    for(String name : boundNames){
		    	System.out.println("1098 : " + name);
		    }	    
		    
			rmi_interf=(RMIInterface)reg.lookup("loizrmiserver");
			
			String txt = JOptionPane.showInputDialog("Entrer un mot a envoyer au serveur :");
			String response = rmi_interf.helloTo(txt);
			JOptionPane.showMessageDialog(null, response);
		}
		      
		catch (NotBoundException e) {
			System.out.println("Erreur acces server (NotBoundException) : " + e.getLocalizedMessage());
			e.printStackTrace();
		}
		
		catch (RemoteException e) {
			System.out.println("Erreur acces server (RemoteException) : " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

}