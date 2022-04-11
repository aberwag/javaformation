package mains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.BiConsumer;
import java.util.function.Supplier;


import org.apache.commons.lang3.tuple.Triple;
import org.apache.commons.lang3.tuple.MutableTriple;
import org.apache.commons.lang3.tuple.Pair;

import org.javatuples.Unit;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException ;
import org.javatuples.Decade;
import org.javatuples.Triplet;
import org.javatuples.Quartet ;
import org.javatuples.Quintet;
import org.javatuples.Sextet;
import org.javatuples.Septet;
import org.javatuples.Octet;
import org.javatuples.Ennead;
import org.javatuples.Decade;

import dto.pojoAdresse;
import dto.pojouser;
import gesterreurs.ClientException;
import helper.LoizStreamhelper;


/*
 Exploitation des interfaces fonctionnelles :
  	- Predicate;
	- Consumer;
	- BiConsumer;
	- Supplier;
 */

public class PrincipalObjects extends LoizStreamhelper {

	
	public static void main(String[] args) throws IOException, ArithmeticException  //throws JsonProcessingException 
	{		
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
		
		objStreamListeAthletes = listeUsers.stream() ;
		Predicate<pojouser> objPredIsOlympicUser = eleUser -> eleUser.isOlympic() ;
		objListeAthletesOlympics =  objStreamListeAthletes.filter(objPredIsOlympicUser).collect(Collectors.toList()) ;
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString;
		try {
			jsonString = mapper.writeValueAsString(new pojouser("SOTOMAYOR","Carlos","Olympic Jumper",true));
			jsonString = mapper.writeValueAsString(new pojouser("SOTOMAYOR","Carlos","Olympic Jumper",true));
			jsonString = mapper.writeValueAsString(listeUsers);
			jsonString = mapper.writeValueAsString(objMap);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
	    for (pojouser eleAThlete :objListeAthletesOlympics ) {
	    	System.out.println(eleAThlete.toString());   	
	    }  
	    
	    
	    
//		Consumer<String> objConStr = firstErrorMessageEncountered -> {
//			throw new ClientException(HttpStatus.BAD_REQUEST, firstErrorMessageEncountered);
//		};
//  	objConStr.accept("mon message d'erreur");		    
  
		int i = 1;
		int j = 0;
		try {
		System.out.println(i / j);
		}
		catch (ArithmeticException e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		throw new IOException("test");     
/*	    
	loizStrSep() ;  
	
	Affiche("4 - passage d'un stream de type athlètes à un java.util.Set de type pojoAdresse !!!! ") ; 
		objStreamListeAthletes = listeUsers.stream() ;
	    Set<pojoAdresse> objSetAdresses =  objStreamListeAthletes.map(eleUser -> eleUser.getPojoadresse() ).collect(Collectors.toSet()) ; 
	    for (pojoAdresse eleAddress : objSetAdresses ) {
	    	System.out.println(eleAddress.toString());
	    }	   
	    
      try {
		jsonString = mapper.writeValueAsString(objSetAdresses);
		
	} catch (JsonProcessingException e) {
		String strmsg = e.getMessage() ;
	} 
    loizStrSep() ;
    /* 
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
    
	Affiche("10 - Utilisation de java.util.function.BiConsumer interface sur hashMap<String, Object> avec variable objet intermédiaire") ;  	
		BiConsumer<String, pojouser> objBIconsumerForMap = (eleKey, eleObj) -> System.out.println("\n" + eleKey + "(par objet intermediaire BiConsumer) : " + "\n"+ eleObj.toString()) ;
		objMap.forEach(objBIconsumerForMap);
	loizStrSep() ;
	
	Affiche("11 - Utilisation de java.util.function.BiConsumer interface sans variable objet intermédiaire - one line code !") ;
		objMap.forEach( (eleKey,eleObj) -> System.out.println("\n" + eleKey + "(sans objet intermediaire BiConsumer) : " + "\n"+ eleObj.toString()) );		
    loizStrSep() ;
	
	Affiche("12 - Utilisation de java.util.function.Consumer interface sur Set<Object> avec variable objet intermédiaire") ;
		Consumer<pojoAdresse>  objconsumerForSet = (eleObj) -> System.out.println("\n" + eleObj.toString()) ;
		objSetAdresses.forEach(objconsumerForSet);
	loizStrSep() ;	
    
	Affiche("13 - Exploitation Consommateur sur un seul element : methode \"accept\"") ;	
		pojouser objFirstAthlete = listeUsers.get(1) ;
		pojoAdresse objAdresse = objFirstAthlete.getPojoadresse() ;
		objconsumerForSet.accept(objAdresse);
	loizStrSep() ;
	
	Affiche("14 - Exploitation de plusieurs consommateurs en cascade (sur même type d'objet)") ;	
		Consumer<pojouser>  objconsumerAthlete = (eleObj) -> System.out.println("\n Athlete ->" + eleObj.getNom() +" "+ eleObj.getPrenom()+" : ") ;
		Consumer<pojouser>  objconsumerAdresse = (eleObj) -> System.out.println("\n Adresse -> " + eleObj.getPojoadresse().toString()) ;	
		Consumer<pojouser> consumerWithAndThen = objconsumerAthlete.andThen(objconsumerAdresse) ;	
	    for (pojouser elePojAthlete :listeUsers ) {
	    	consumerWithAndThen.accept(elePojAthlete);     	
	    }
	loizStrSep() ;
	Affiche("15 - Utilisation de java.util.Supplier : il ne consomme pas mais fournit une valeur (pas de valeur en entrée)") ;	
	Supplier<String> objSupplierUser = () -> listeUsers.get(1).toString();
	System.out.println(objSupplierUser.get());		
	loizStrSep() ;	
	
	Affiche("16 - Triplet d'objet IMMUTABLE simples (non ré-instanciables)") ;		
    Triple<String, String, String> objtriple = Triple.of("IMMUTABLE leftElement", "IMMUTABLE middleElement", "IMMUTABLE rightElement");
    String strLeft = objtriple.getLeft() ;
    String strMiddle = objtriple.getMiddle() ;
    String strRight = objtriple.getRight() ;    
    System.out.println(objtriple.toString());
	loizStrSep() ;
	
	Affiche("17 - Triplet d'objets MUTABLE simples (ré-instanciables)") ;	
	MutableTriple<String, String, String> objMutabletriple = MutableTriple.of("MUTABLE leftElement", "MUTABLE middleElement", "MUTABLE rightElement");
    String strMutableLeft = objMutabletriple.getLeft() ;
    String strMutableMiddle = objMutabletriple.getMiddle() ;
    String strMutableRight = objMutabletriple.getRight() ;
    System.out.println(objMutabletriple.toString());
    objMutabletriple.setLeft("modifié 1 : " +  strMutableLeft);
    objMutabletriple.setMiddle("modifié 2 : " +  strMutableMiddle);
    objMutabletriple.setRight("modifié 3 : " +  strMutableRight);
    System.out.println(objMutabletriple.toString());
    loizStrSep() ;    
    */
	}
	
	
	
	//Fonction qui renvoit un predicate sans variable intermédiare
	private static Predicate<pojouser> givePredicates(String argLettre) {
		
		return (( Predicate<pojouser>)(eleUser -> eleUser.isOlympic()))
				                  .negate()
				                  .and(eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(argLettre) != -1) ;		
	}

}
