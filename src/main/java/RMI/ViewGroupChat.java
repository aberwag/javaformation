package RMI;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import RMI.helper.AppHelper;

public class ViewGroupChat extends JFrame {

	private static final long serialVersionUID = 1L;
	private JFrame objJFrameChat = null;
	private boolean bthreadStarted = false;
	private static loizThreadUpdateListLibel objloizThreadUpdateListLibel = null;
	private static RMIInterface rmi_interf_server;
	private static int i;
	private static String attrLogin ;
	private JLabel objJLabelWelcome = null;
	public static interfClient rmi_client;
	

	protected ViewGroupChat() throws RemoteException {
		super();
	}

	public ViewGroupChat(String title, String sargLogin) throws HeadlessException {
		super(title);
		objJFrameChat = this;
		attrLogin = sargLogin ;
	}

	public void buildUI(String[] sCLI_Input) throws MalformedURLException, RemoteException, NotBoundException {

		try {

			String sIP = "";
			if (sCLI_Input != null)
				if (sCLI_Input.length > 0)
					sIP = sCLI_Input[0];
			registryviewer(sIP);
			if (sIP.length() == 0)
				sIP = "127.0.0.1";
			System.out.println("************** Avant binding rmi par le client");
			Registry reg = LocateRegistry.getRegistry(sIP, Integer.parseInt("1097"));
			rmi_interf_server = (RMIInterface) reg.lookup("loizrmiserver");

			System.out.println("************** Après binding rmi par le client");
			// String shote = null;
			rmi_client = new interfClientImpl();

			this.setTitle("Swing Hdra");

			objJFrameChat.setDefaultCloseOperation(0);
			objJFrameChat.setBounds(100, 100, 600, 800);

			AppHelper.fermFenetreDispo(objJFrameChat);
			objJLabelWelcome = new JLabel("BIENVENUE à <" + attrLogin + "> sur le *** SwingChat ***");
			objJLabelWelcome.setBounds(100, 20, 450, 50);
			objJLabelWelcome.setFont(new Font("Arial", Font.BOLD, 16));
			
			LzMultilineJLabell objJListedLabel = new LzMultilineJLabell();
			JScrollPane objScrollPane_For_ListedLabel = new JScrollPane(objJListedLabel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			final JTextField objJTextField = new JTextField();
			JButton objJbutton = new JButton("envoi vers le tchat");
			objJTextField.setBounds(10, 150, 150, 20);
			objJbutton.setBounds(170, 150, 120, 20);
			objScrollPane_For_ListedLabel.setBounds(14, 180, 300, 500);
			objJListedLabel.setBackground(Color.WHITE);
			objJListedLabel.setAlignmentX(5);
			objJListedLabel.setVerticalAlignment(JLabel.TOP);

			objJbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> objArraySaisie = null;
					String sSaisie = attrLogin + ">" + objJTextField.getText();
					try {
						objArraySaisie = rmi_interf_server.stoquerSaisieClient(sSaisie, rmi_client, true);

						// on démarre la thread de mise à jour de la liste d'échange
						if (objloizThreadUpdateListLibel == null) {
							objloizThreadUpdateListLibel = new loizThreadUpdateListLibel(sSaisie, rmi_client,
									rmi_interf_server, objJListedLabel);
							objloizThreadUpdateListLibel.start();
						}

					} catch (RemoteException e1) {
						e1.printStackTrace();
					}

					Iterator<String> iterator = objArraySaisie.iterator();
					while (iterator.hasNext()) {
						String element = (String) iterator.next();
						System.out.println("boucle iterator.hasNext() : " + element);
					}
				}
			});
            objJFrameChat.add(objJLabelWelcome) ; 
			objJFrameChat.add(objJbutton) ; 
			objJFrameChat.add(objJTextField) ; 
			objJFrameChat.getContentPane().add(objScrollPane_For_ListedLabel) ;
			objJFrameChat.setLayout(null);
			objJFrameChat.setVisible(true);
		} catch (NotBoundException e) {
			System.out.println("Erreur acces server (NotBoundException) : " + e.getLocalizedMessage());
			e.printStackTrace();
		}

		catch (RemoteException e) {
			System.out.println("Erreur acces server (RemoteException) : " + e.getLocalizedMessage());
			e.printStackTrace();
		}

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

}