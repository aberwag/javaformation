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

import dto.pojoAdresse;
import dto.pojouser;
import helper.LoizStreamhelper;

/*
 Exploitation des interfaces fonctionnelles :
  	- Predicate;
	- Consumer;
	- BiConsumer;
	- Supplier;
 */

public class PrincipalObjects extends LoizStreamhelper {

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

	Affiche("4 - 1 - Passage d'un stream d\'athlètes à une collection de type set MAIS de type pojoAdresse !!!! ") ;
		objStreamListeAthletes = listeUsers.stream() ;
	    Set<pojouser> objSetAthletes =  objStreamListeAthletes.map(eleUser -> {
	    																	 eleUser.setSportpractice("modif adresse -" + eleUser.getSportpractice()) ;  return eleUser ;
	    				} ).collect(Collectors.toSet()) ;
	    for (pojouser eleAThlete :objSetAthletes ) {
	    	System.out.println(eleAThlete.toString());
	    }
    loizStrSep() ;
    Affiche("4 - 2 - Utilisation de forEach sur le Set Obtenu");
    objSetAthletes.stream().forEach(objPojouser -> {
        System.out.println(objPojouser.toString());
    }) ;
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

	Affiche("10 - Utilisation de java.util.function.BiConsumer interface sur hashMap<String, Object> avec variable objet intermédiaire") ;
		BiConsumer<String, pojouser> objBIconsumerForMap = (eleKey, eleObj) -> System.out.println("\n" + eleKey + "(par objet intermediaire BiConsumer) : " + "\n"+ eleObj.toString()) ;
		objMap.forEach(objBIconsumerForMap);
	loizStrSep() ;

	Affiche("11 - Utilisation de java.util.function.BiConsumer interface sans variable objet intermédiaire - one line code !") ;
		objMap.forEach( (eleKey,eleObj) -> System.out.println("\n - " + eleKey + " - (sans objet intermediaire BiConsumer) : " + "\n"+ eleObj.toString()) );
    loizStrSep() ;

	Affiche("12 - Utilisation de java.util.function.Consumer interface sur Set<Object> avec variable objet intermédiaire") ;
							objStreamListeAthletes = listeUsers.stream() ;
						    Set<pojoAdresse> objSetAdresses =  objStreamListeAthletes.map(eleUser -> eleUser.getPojoadresse() ).collect(Collectors.toSet()) ;
						    for (pojoAdresse eleAddress : objSetAdresses ) {
						    	System.out.println(eleAddress.toString());
						    }
    loizStrSep() ;
    Affiche("12.1") ;
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


	}

	//Fonction qui renvoit un predicate sans variable intermédiare
	private static Predicate<pojouser> givePredicates(String argLettre) {

		return (( Predicate<pojouser>)(eleUser -> eleUser.isOlympic()))
				                  .negate()
				                  .and(eleUser -> ((eleUser.getNom() ).toUpperCase()).indexOf(argLettre) != -1) ;
	}

}
