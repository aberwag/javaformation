package RMI;


import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.naming.CommunicationException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import RMI.helper.AppHelper;
import RMI.ldap.Ldap;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel objJLabelLogin = null;
	private JLabel objJLabelMotPasse = null;
	private JTextField objJTextFieldLogin = null;
	private JTextField objJTextFieldMotPasse = null;
	private JFrame objJFrameLogin = null;
	private String attrTitreFenetre = "";
	private String[] attrCLI_Input = null ;
	boolean okConnect = false;

	public LoginUI(String title) throws HeadlessException {
		super(title);
		objJFrameLogin = this;
		attrTitreFenetre = title;
		objJFrameLogin.setTitle(attrTitreFenetre);
		objJFrameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// buildFields() ;
	}

	public void buildFields(String[] sCLI_Input) {
		attrCLI_Input = sCLI_Input ;
		objJFrameLogin.setUndecorated(false);
		objJFrameLogin.setBounds(10, 10, 400, 200);
		objJFrameLogin.setLocationRelativeTo(null);
		objJFrameLogin.setResizable(true);
		objJFrameLogin.setFocusable(true);

		objJLabelLogin = new JLabel("Login");
		objJLabelMotPasse = new JLabel("Mot de passe");
		objJTextFieldLogin = new JTextField();
		objJTextFieldMotPasse = new JTextField();

		objJLabelLogin.setBounds(10, 65, 150, 20);
		objJTextFieldLogin.setBounds(150, 65, 150, 20);
		objJLabelMotPasse.setBounds(10, 90, 150, 20);
		objJTextFieldMotPasse.setBounds(150, 90, 150, 20);

		JButton objJButton = new JButton("Connexion");
		objJButton.addActionListener(addButtonEvent());
		objJButton.setBounds(120, 135, 150, 20);

		AppHelper.fermFenetreDispo(objJFrameLogin);

		objJFrameLogin.add(objJLabelLogin);
		objJFrameLogin.add(objJTextFieldLogin);
		objJFrameLogin.add(objJLabelMotPasse);
		objJFrameLogin.add(objJTextFieldMotPasse);
		objJFrameLogin.add(objJButton);
		objJFrameLogin.setLayout(null);
		objJFrameLogin.validate();
		objJFrameLogin.setVisible(true);
	}



	private ActionListener addButtonEvent() {
		ActionListener actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				try {
					okConnect = checkAccess();
					if (okConnect) {
						System.out.println("Connection établie");
						
						objJFrameLogin.setVisible(false);
						objJFrameLogin.dispose();						
						ViewGroupChat objViewGroupChat = new ViewGroupChat("Groupe de papoteurs",getLogin()) ;						
						objViewGroupChat.buildUI(attrCLI_Input);
					}
					else {
						JOptionPane.showMessageDialog(objJFrameLogin, "Login ou mot de passe incorrect", "Connexion ldap refusée",JOptionPane.ERROR_MESSAGE);
						
						System.out.println("Connection non établie");
					}
				} catch (CommunicationException e) {
					System.out.println("Connection non établie");
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		return actionListener;
	}

	private boolean checkAccess() throws CommunicationException {
		boolean okCon = false;
		String sLoginUser = getLogin();
		String sPassUser = getPassword();
		Ldap objLdap = new Ldap();
		okCon = objLdap.initLDAP(sLoginUser, sPassUser, attrCLI_Input);
		return okCon;
	}

	private String getPassword() {
		return objJTextFieldMotPasse.getText();
	}

	private String getLogin() {
		return objJTextFieldLogin.getText();
	}

}
