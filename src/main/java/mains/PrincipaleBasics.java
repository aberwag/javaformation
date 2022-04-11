package mains ; 

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import helper.LoizStreamhelper;



public class PrincipaleBasics extends LoizStreamhelper {

	public static void main (String[] argv)  {
		
		String[] strTab =new String[]  {"a","b","c"} ;				
		
		//------------------------------------------------------
		Affiche("1 - Afficher le premier élément");
		Stream<String> objStream = Arrays.stream(strTab) ;		
		Optional<String> optionnalString = objStream.findFirst() ;
		String strFirstElem = optionnalString.get() ;		
		System.out.println("premier element  : " + strFirstElem);
		objStream.close();
		loizStrSep() ;

		//------------------------------------------------------
		Affiche("2 - Boucle sur les element d'un stream, avec fonction lambda");
		objStream = Arrays.stream(strTab) ;
		objStream.forEach(strElem -> System.out.println(strElem));
		objStream.close();
		loizStrSep() ;

		//------------------------------------------------------
		Affiche("3- Initialisation d'un stream sans tableau ou liste intermédiare et par instruction of");
		objStream = Stream.of("d","e","f") ; 
		Affiche("Avec lambda");
		objStream.forEach(strElem -> System.out.println(strElem));
		
		Affiche("4 - Avec référence de méthode");
		objStream = Stream.of("d","e","f") ;		
		objStream.forEach(System.out::println);	
		loizStrSep() ;

		//------------------------------------------------------
		Affiche("5 - Utilisation du multithreading");
		List<String> strList = Arrays.asList("a", "b", "ck", "ad", "ae","f","g","h","ki","j","ak","l");			
		strList.parallelStream().forEach(element -> Bonjour(element));
		loizStrSep() ;

		//------------------------------------------------------	
		Affiche("6 - Utilisation d'un filtre");		
		strList.parallelStream().filter(element -> element.indexOf("a")!=-1 || element.indexOf("k")!=-1  ).forEach(element -> Bonjour(element));
		loizStrSep() ;
		
		//------------------------------------------------------
		Affiche("7 - Retour sur l'élément mappé pour opération \"foreach\" : Utilisation instruction foreach de mannière séparée (sur deux lignes)") ; 
		objStream = Stream.of("d","e","f") ; 
		Stream<String> strTreamModified = objStream.map( elem -> modifierString(elem) ) ; 
		strTreamModified.forEach(element -> Bonjour(element)) ; 
		
		//------------------------------------------------------
		Affiche("8 - Utilisation instruction foreach de mannière unifiée (sur une ligne)");
		objStream = Stream.of("g","h","i") ;			
		objStream.map( elem -> modifierString(elem) ).forEach(element -> Affiche(element)) ;
		
		System.out.println("\n\nTHE END");
	}	
	
/*	private String concatBonjour(String strArg) {
		String strReturn = "concatBonjour(tring strArg) - Bonjour \"" +strArg+ "\"" ;
		System.out.println(strReturn);
		return strReturn ;
	}
	
	private static void strTitreRub(String strTitle ) {
		System.out.println("\n"+ strTitle);
	}
	
	private static void loizStrSep() {				
		System.out.println("-------------------------------");
	} */
}
