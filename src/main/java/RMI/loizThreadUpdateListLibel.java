package RMI;

import java.rmi.RemoteException;
import java.util.ArrayList;

//Ce thread sera exécuté dès lors qu'une saisie sera effectuée
public class loizThreadUpdateListLibel extends Thread {

	private String attrSaisie = "" ; 
	private RMIInterface attrInterServer ; 
	private interfClient attrInterClient ; 
    private LzMultilineJLabell attrJListedLabel ; 
	
	public loizThreadUpdateListLibel(String argSaisie, interfClient argInterClient, RMIInterface argInterServer, LzMultilineJLabell argJListedLabel) {
		super();
		this.attrSaisie = argSaisie ; 
		this.attrInterServer = argInterServer ; 
		this.attrInterClient = argInterClient ; 
		this.attrJListedLabel = argJListedLabel ; 
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
		ArrayList<String> objArraySaisie;
		try {
			objArraySaisie = attrInterServer.stoquerSaisieClient(attrSaisie,attrInterClient,false);
			attrJListedLabel.setText("");
			for (String val : objArraySaisie) {
				if (val.length() != 0)
					attrJListedLabel.addNewLine(val);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}	

	}

}