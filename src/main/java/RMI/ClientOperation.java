package RMI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
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

	public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException {

		try {
			Registry reg = LocateRegistry.getRegistry("192.168.6.107", Integer.parseInt("1097")) ; 
			rmi_interf = (RMIInterface) reg.lookup("loizrmiserver") ; 
			// String txt = JOptionPane.showInputDialog("Entrer un mot a envoyer au serveur
			// :");
			/*String txt = "Word";
			String response = rmi_interf.helloTo(txt);
			JOptionPane.showMessageDialog(null, response);*/
			rmi_client = new interfClientImpl();
			/*String shote = rmi_interf.StockerEnStatiqueIpClient(rmi_client);
			System.out.println(shote);
			JOptionPane.showMessageDialog(null, shote);*/
			////////////////////////////////////////////
			JFrame f = new JFrame("Button Example");
			
			final JTextField tf2 = new JTextField();
			tf2.setBounds(100, 100, 150, 20);
			JButton b2 = new JButton("loiz click");
			b2.setBounds(100, 150, 95, 30);
			b2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tf2.setText("clique No : " + i++);
				}
			}) ;
			
			
			final JTextField tf = new JTextField();
			tf.setBounds(50, 50, 150, 20);
			JButton b = new JButton("Click Here");
			b.setBounds(50, 100, 95, 30);
			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					tf.setText("Welcome to Javatpoint.");
					String shote = null;
					try {
						shote = rmi_interf.StockerEnStatiqueIpClient(rmi_client);
						JOptionPane.showMessageDialog(null, "envoi effectué");
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "echec envoi : " + e1.getMessage());
						e1.printStackTrace();
					}
					System.out.println(shote);
				}
			});
			f.add(b);
			f.add(tf);
			f.add(b2);
			f.add(tf2);
			f.setSize(400, 400);
			f.setLayout(null);
			f.setVisible(true);

			////////////////////////////////////////////
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