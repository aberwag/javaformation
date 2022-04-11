package mains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.apache.commons.lang3.tuple.Triple;

import dto.pojoAdresse;
import dto.pojouser;
import helper.LoizStreamhelper;

public class principalObjects extends LoizStreamhelper {

	public static void main(String[] args) {
	
		
	//Initialisation d'une liste d'objets de type athletes : 
    List<pojouser> listeUsers = new ArrayList<pojouser>() ;      
    listeUsers.add( new pojouser("SOTOMAYOR","Carlos","Olympic Jumper",true)) ; 
    listeUsers.add( new pojouser("LEWIS","Carl","Olympic sprinter",true)) ; 
    listeUsers.add( new pojouser("HANEY","Lee","Body builder",false)) ; 
    listeUsers.add( new pojouser("DIAZ","Nick","Mma Fighter",false)) ;
    
    //Création d'une hashmap correspondante : 
    HashMap<String, pojouser> objMap = new HashMap<String, pojouser>() ;    
    for (pojouser poj :listeUsers ) {
    	objMap.put(poj.getNom()+" " + poj.getPrenom(), poj) ;    	
    }
	//------------------------------------------------------
	Affiche("1 - Creation d'un stream d'objets athlètes : "); 
    Stream<pojouser> objStreamListeAthletes = listeUsers.stream() ;
    
    Affiche("2 - Filtrage sur ce stream d'athletes contenant A dans leur nom et récupération d'une collection de type Liste : ");
    String sLetter = "A" ;          
    List<pojouser> objListeAthletesWithA =  objStreamListeAthletes.filter(eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(sLetter) != -1 ).collect(Collectors.toList());     
    for (pojouser eleAThlete :objListeAthletesWithA ) { 
    	System.out.println(eleAThlete.toString()) ;    	
    }  
	loizStrSep() ;  
    
	Affiche("3 - Filtrage sur ce stream d'athletes pratiquant une discipline olympique et récupération d'une collection de type Liste : ");
	objStreamListeAthletes = listeUsers.stream() ;
	List<pojouser> objListeAthletesOlympics =  objStreamListeAthletes.filter(eleUser -> eleUser.isOlympic() ).collect(Collectors.toList()) ;     
    for (pojouser eleAThlete :objListeAthletesOlympics ) {
    	System.out.println(eleAThlete.toString());   	
    }  
	loizStrSep() ;  
	
	Affiche("4 - passage d'un stream d\'athlètes à une collection de type set MAIS de type pojoAdresse !!!! ") ; 
	objStreamListeAthletes = listeUsers.stream() ;
    Set<pojoAdresse> objSetAdresses =  objStreamListeAthletes.map(eleUser -> eleUser.getPojoadresse() ).collect(Collectors.toSet()) ; 
    for (pojoAdresse eleAddress : objSetAdresses ) {
    	System.out.println(eleAddress.toString());
    }
    loizStrSep() ;    
		
	}

}
