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

public class principal_Java8_Function extends LoizStreamhelper {

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
	    System.out.println("il y a " + objStreamListeAthletes.count() + " athlètes dans la liste");
    
    Affiche("2 - Filtrage sur ce stream d'athletes contenant A dans leur nom et récupération d'une collection de type Liste : ");
	    objStreamListeAthletes = listeUsers.stream() ;
	    final String sLetter = "A" ;         
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
    
    Affiche("5 - Combinaison de plusieurs filtres - par enchainement de filtres (recherche athlète à la fois Olympic et dont le nom contient un \"A\")") ;
		objStreamListeAthletes = listeUsers.stream() ;
		List<pojouser> objListeAthletesFiltreEnChaine_Olympic_Et_A =  objStreamListeAthletes.filter(eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(sLetter) != -1 ).filter(eleUser -> eleUser.isOlympic() ).collect(Collectors.toList()) ;     
	    for (pojouser eleAThlete :  objListeAthletesFiltreEnChaine_Olympic_Et_A ) {
	    	System.out.println(eleAThlete.toString());   	
	    } 
    loizStrSep() ;
    
    Affiche("6 - Combinaison de plusieurs filtres - par filtre complexe (recherche athlète NON Olympic et dont le nom contient un \"Y\")") ;
	    final String sLetterY = "Y" ;
		objStreamListeAthletes = listeUsers.stream() ;
		List<pojouser> objListeAthletesFiltreComplexe_Olympic_Et_A =  objStreamListeAthletes.filter(eleUser -> (((eleUser.getNom() ).toUpperCase()).indexOf(sLetterY) != -1) && (!eleUser.isOlympic()) ).collect(Collectors.toList()) ;     
	    for (pojouser eleAThlete :  objListeAthletesFiltreComplexe_Olympic_Et_A ) {
	    	System.out.println(eleAThlete.toString());   	
	    } 
    loizStrSep() ;	
	
    Affiche("7 - Combinaison de plusieurs filtres - par utilisation de l'instruction Predicate (recherche NON Olympic et dont le nom contient un \"Y\")") ;    
		objStreamListeAthletes = listeUsers.stream() ;
		Predicate<pojouser> objPredicatListeAthletesY = eleUser -> eleUser.isOlympic() ;
		Predicate<pojouser> objPredicatListeAthletesOlympics = eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(sLetterY) != -1 ;	
		List<pojouser> objListeAthletesFiltrePredicates_Olympic_Et_A =  objStreamListeAthletes.filter(objPredicatListeAthletesY.negate().and(objPredicatListeAthletesOlympics) ).collect(Collectors.toList()) ;     
	    for (pojouser eleAThlete :  objListeAthletesFiltrePredicates_Olympic_Et_A ) {
	    	System.out.println(eleAThlete.toString());   	
	    } 
    loizStrSep() ;
    
	Affiche("8 - Combinaison de plusieurs filtres - recupération de predicate par fonction (fonction sans variables intérmédiares = moderne)") ;    
		objStreamListeAthletes = listeUsers.stream() ;
		List<pojouser> objListeAthletesFiltrePredicatesByFunction_Olympic_Et_A =  objStreamListeAthletes.filter(givePredicates(sLetterY) ).collect(Collectors.toList()) ;     
	    for (pojouser eleAThlete :  objListeAthletesFiltrePredicatesByFunction_Olympic_Et_A  ) {
	    	System.out.println(eleAThlete.toString());   	
	    } 
    loizStrSep() ;       
	}		
	
	//Fonction qui renvoit un predicate sans variable intermédiare
	private static Predicate<pojouser> givePredicates(String argLettre) {
		
		return (( Predicate<pojouser>)(eleUser -> eleUser.isOlympic()))
				                  .negate()
				                  .and(eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(argLettre) != -1) ;		
	}

}
