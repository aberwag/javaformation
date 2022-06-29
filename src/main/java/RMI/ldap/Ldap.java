package RMI.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.NamingException;
//import javax.naming.ldap.*;
import javax.naming.directory.*;

public class Ldap {

	boolean okConnection = false;

	public boolean initLDAP(String sLogin, String sPassword) {
		try {

			Hashtable<String, String> environment = new Hashtable<String, String>();
			environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			environment.put(Context.PROVIDER_URL, "ldap://localhost:389");
			environment.put(Context.SECURITY_AUTHENTICATION, "simple");
			
			sLogin = getFormatedLogin(sLogin);
			environment.put(Context.SECURITY_PRINCIPAL, sLogin);
			environment.put(Context.SECURITY_CREDENTIALS, sPassword);			
			
			new InitialDirContext(environment);
			
			okConnection = true;
			// context.close();
		} catch (InvalidNameException e) {
			System.out.println("InvalidNameException : format de login non adapté");
		} catch (NamingException e) {
			okConnection = false;
			System.out.println("NamingException : connexion  non établie");
		}

		return okConnection;
	}

	//Reformatte le fromat de login saisi pour avoir le bon format de connexion en sortie
	private String getFormatedLogin(String sLoginInput) throws NamingException {
		String sLoginFormatted = "";
		String sPrefix  = "cn=" ;
		System.out.println(sLoginInput.substring(0, 3));
		if (sLoginInput.length() == 0)
			throw new NamingException();
		else if (sLoginInput.substring(0, 3).equals(sPrefix)) {
			sLoginFormatted = sLoginInput;
		}
		else
			sLoginFormatted = "cn=" + sLoginInput + ",dc=ramhlocal,dc=com";

		return sLoginFormatted;
	}

}