package RMI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.function.Consumer;

import javax.naming.CommunicationException;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import RMI.ldap.Ldap;
import helper.ExploreThreads;

public class ClientOperation {

	protected ClientOperation() {
		super();
	}

	public static void registryviewer(String sIp) {
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry(sIp, 1097);
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

	public static void main(String[] args)  {	
		
		LoginUI objLoginUI = new LoginUI("Fenêtre de connexion LDAP") ; 
		objLoginUI.buildFields(args) ;
		}
	
}