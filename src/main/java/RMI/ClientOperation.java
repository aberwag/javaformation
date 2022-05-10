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
		
		/*Enumeration<NetworkInterface> nis;
		try {
			nis = NetworkInterface.getNetworkInterfaces();
			while (nis.hasMoreElements()) {
			    NetworkInterface ni = nis.nextElement();
			    Enumeration<InetAddress> addrs = ni.getInetAddresses();
			    while (addrs.hasMoreElements()) {
			        InetAddress localAddr = addrs.nextElement();
			        System.out.println("localAddr.getHostName() : " + localAddr.getHostName()) ; 
	            }
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}*/
		
		String userHomeDir = System.getProperty("user.home");
        System.out.println("The User Home Directory is " + userHomeDir);
		String javaHomeDir = System.getProperty("java.home");
        System.out.printf("The Java Home Directory is " + javaHomeDir);
		String javaClassPath = System.getProperty("java.class.path");
        System.out.printf("The java class path is " + javaClassPath);
        
		
		try {
			Registry reg=LocateRegistry.getRegistry("0.0.0.0",1097);			
			//rmi_interf = (RMIInterface) Naming.lookup("//127.0.0.1:1099/loizrmiserver");
			
			rmi_interf=(RMIInterface)reg.lookup("//0.0.0.0:1097/loizrmiserver");
			//rmi_interf=(RMIInterface)reg.lookup   ("loizrmiserver");
			
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