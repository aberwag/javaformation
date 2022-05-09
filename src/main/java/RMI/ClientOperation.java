package RMI;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JOptionPane;

public class ClientOperation {

	private static RMIInterface rmi_interf ;

	public static void main(String[] args) 
		throws MalformedURLException, RemoteException, NotBoundException {
		
		try {		
			
			Registry reg=LocateRegistry.getRegistry("127.0.0.1",1099);
			
			//rmi_interf = (RMIInterface) Naming.lookup("//127.0.0.1:1099/loizrmiserver");
			
			rmi_interf=(RMIInterface)reg.lookup("//127.0.0.1/loizrmiserver");
			
			
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
		
//		catch (MalformedURLException e) {
//				
//				System.out.println("Erreur url server (MalformedURLException) : " + e.getLocalizedMessage());
//				e.printStackTrace();
//		}
		

	}

}