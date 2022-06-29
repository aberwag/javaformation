package RMI;


import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


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