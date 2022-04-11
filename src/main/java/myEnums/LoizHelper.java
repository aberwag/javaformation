package myEnums;

import java.util.function.Predicate;

import static Constantes.LoizConstants.* ;

import dto.pojouser;
public final class LoizHelper {
	
	private static final Predicate<pojouser> predicateUser = eleUser -> eleUser.isOlympic() ;	
	
public enum myLocaleEnum {	
	 ERROR_DT_DEB_PREMIER_TRIM_DROIT_NULL (predicateUser,CONSTANTE_CHAINE_1);  //Si la discipline est olympique alors on informe de l'obligation de saisie du champs
	
	private Predicate<pojouser> attrBolOlymique ;
	private String attrChaine ;
	
	myLocaleEnum(Predicate<pojouser> attrBolOlymique, String argChaine ) {		
		this.attrBolOlymique = attrBolOlymique;
		this.attrChaine = argChaine ;
	}
	
}



}