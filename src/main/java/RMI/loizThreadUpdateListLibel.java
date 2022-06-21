package RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

//Ce thread sera exécuté dès lors qu'une saisie sera effectuée
public class loizThreadUpdateListLibel extends Thread {

	private String attrSaisie = "";
	private RMIInterface attrInterServer;
	private interfClient attrInterClient;
	private LzMultilineJLabell attrJListedLabel;
	private ArrayList<String> attrArraySaisie = new ArrayList<>();
	private ArrayList<String> attrArraySaisieLast = new ArrayList<>();
	
	public loizThreadUpdateListLibel(String argSaisie, interfClient argInterClient, RMIInterface argInterServer,
			LzMultilineJLabell argJListedLabel) {
		super();
		this.attrSaisie = argSaisie;
		this.attrInterServer = argInterServer;
		this.attrInterClient = argInterClient;
		this.attrJListedLabel = argJListedLabel;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			updateList();
		}
	}

	public void updateList() {
		try {
			attrArraySaisie = attrInterServer.stoquerSaisieClient(attrSaisie, attrInterClient, false);
			//if (attrArraySaisie != null && attrArraySaisieLast != null) 
			//String sConcat = attrJListedLabel.getText() ;
				if(!attrArraySaisieLast.equals(attrArraySaisie)) {  
						attrJListedLabel.builListDialog(attrArraySaisie );	
						attrArraySaisieLast = attrArraySaisie ;
				}
				
			}		
		 catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}