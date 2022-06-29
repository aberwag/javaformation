package RMI.ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
//import javax.naming.ldap.*;
import javax.naming.directory.*;

public class Ldap {
	
	boolean okConnection = false ;
	
	public boolean initLDAP (String sLogin, String sPassword) {

		Hashtable<String, String> environment = new Hashtable<String, String>();

		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, "ldap://localhost:389");
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, sLogin);
		environment.put(Context.SECURITY_CREDENTIALS, sPassword);		
		
		try {
			@SuppressWarnings("unused")
			DirContext context = new InitialDirContext(environment);
			okConnection = true ;
			//context.close();
		} 
		catch (InvalidNameException e) {
			System.out.println("InvalidNameException : format de login non adapté");
		}
		catch (NamingException e) {
			okConnection = false ;
			System.out.println("NamingException : connexion  non établie");
			//e.printStackTrace();
		}

		return okConnection ;
	}

}