package RMI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import helper.ExploreThreads;

public class ClientOperation {

	protected ClientOperation() throws RemoteException {
		super();
	}

	private static RMIInterface rmi_interf;
	private static int i ;
	public static interfClient rmi_client;

    public static void registryviewer() {
		    Registry registry = null;
			try {
				registry = LocateRegistry.getRegistry("192.168.6.107", 1097);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		    try {
				for (String name : registry.list()) {
				    System.out.println("nom bindé dans la registry : " + "\"" + name + "\"");
				}
			} catch (AccessException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
	}  
	
	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {
		
		try {			
			//registryviewer() ; 			
			System.out.println("************** Avant binding rmi par le client") ;
			Registry reg = LocateRegistry.getRegistry("192.168.6.107", Integer.parseInt("1097")) ; 
			RMIInterface loRmiInterf = (RMIInterface) reg.lookup("loizrmiserver") ;
			
			System.out.println("************** Après binding rmi par le client") ;
			String shote = null;
			rmi_client = new interfClientImpl();
			try {
				shote = loRmiInterf.StockerEnStatiqueIpClient(rmi_client);
				System.out.println("retour serveur =  " + shote);
				System.exit(0);
			} catch (RemoteException e1) {
				System.out.println("echec envoi : : " + e1.getMessage());
				e1.printStackTrace();
			}
			System.out.println(shote);		
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