package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;

public interface RMIInterface extends Remote {

     String helloTo(String name) throws RemoteException;
    
     String StockerEnStatiqueIpClient(interfClient intClient) throws RemoteException ;

	ArrayList<String> stoquerSaisieClient(String sArgSaisie, interfClient intClient ,boolean bManuel) throws RemoteException;

}