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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

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
			rmi_interf = (RMIInterface) reg.lookup("loizrmiserver") ;
			
			System.out.println("************** Après binding rmi par le client") ;
			//String shote = null;
			rmi_client = new interfClientImpl();

			JFrame objJframe = new JFrame("Button Example");
			//objJframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//objJframe.setPreferredSize(new Dimension(300, 300));
			objJframe.setDefaultCloseOperation(0);
			objJframe.setBounds(100, 100, 600, 800);
			

			
			/*Some piece of code*/
			objJframe.addWindowListener(new WindowAdapter() {
			    @Override
			    public void windowClosing(WindowEvent windowEvent) {
			        if (JOptionPane.showConfirmDialog(objJframe, 
			            "Etes vous sûr de vouloir fermer ?", "fermer ?", 
			            JOptionPane.YES_NO_OPTION,
			            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
			            System.exit(0);
			        }
			    }
			}); 
			
			LzMultilineJLabell objJListedLabel = new LzMultilineJLabell() ;
			JScrollPane objScrollPane_For_ListedLabel = new JScrollPane(objJListedLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			
			
			JLabel objJLabel = new JLabel("communication rmi") ;
			
			final JTextField objJTextField = new JTextField();
			JButton objJbutton = new JButton("send text with rmi");
			
			objJLabel.setBounds(14, 130, 150, 20);
			objJTextField.setBounds(10, 150, 150, 20);			
			objJbutton.setBounds(170, 150, 95, 20);
			objScrollPane_For_ListedLabel.setBounds(14, 180, 300, 500);
			//Border objBorder = BorderFactory.createLineBorder(Color.black, 1);  ;
			//objJListedLabel.setBorder(objBorder);
			objJListedLabel.setBackground(Color.WHITE);
			objJListedLabel.setAlignmentX(5);
			objJListedLabel.setVerticalAlignment(JLabel.TOP);
			
			objJbutton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> objSetServeur = null ;
					String sSaisie = objJTextField.getText() ;
					try {
						objSetServeur = rmi_interf.stoquerSaisieClient(sSaisie) ;
//						(objSetServeur.stream()).forEach(val -> { objJListedLabel.addNewLine(val) ; }) ;
//						objJListedLabel.addNewLine(sSaisie) ;
						System.out.println("objSetServeur.toString() : " + objSetServeur.toString() ) ;
						//String strValprec = "" ;
						objJListedLabel.setText("");						
						objSetServeur.stream().forEach( val ->  {
							
							 if (val.length()!= 0)
							objJListedLabel.addNewLine (val) ;} );
						
						
						
						System.out.println(" objJListedLabel.getText() : " +  objJListedLabel.getText()) ;						
						
						objJTextField.setText("");
						/*System.out.println("*******************") ; 
						(objSetServeur.stream()).forEach(val -> System.out.println("val : " + val)) ;	*/		
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
					
					Iterator<String> iterator = objSetServeur.iterator();
					while(iterator.hasNext()) {
					    String element = (String) iterator.next();
					    System.out.println("boucle iterator.hasNext() : " + element);
					}					
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
						System.out.println("shote récupéré : " + shote );
						JOptionPane.showMessageDialog(null, "envoi effectué");
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "echec envoi : " + e1.getMessage());
						e1.printStackTrace();
					}
					System.out.println(shote);
				}
			});
			objJframe.add(b);
			objJframe.add(tf);			
			objJframe.add(objJLabel);
			objJframe.add(objJbutton);
			objJframe.add(objJTextField);
			objJframe.getContentPane().add(objScrollPane_For_ListedLabel); 
			//objJframe.add(objJLabel);
			//objJframe.setSize(400, 400);
			objJframe.setLayout(null);
			objJframe.setVisible(true);
					
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