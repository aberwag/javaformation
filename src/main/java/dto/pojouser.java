package dto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class pojouser {

	private String nom;
	private String prenom;
	private String sportpractice;
	private boolean Olympic ;
	private pojoAdresse pojoadresse;
	private String attrScope ;

	public String getAttrScope() {
		return attrScope;
	}

	public void setAttrScope(String attrScope) {
		this.attrScope = attrScope;
	}

	@PostConstruct
	public void initbean() {
		System.out.println(String.format("%-15s - %-10s : %-20s  %-20s", sportpractice, nom , "@PostConstruct", this.getClass().getSimpleName()));
	}

	@PreDestroy
	public void javaxannotationdestroy() {
		System.out.println(String.format("\\\\ %-15s - %-10s : %-20s  %-20s", sportpractice, nom , "ANNOTATION @PreDestroy", this.getClass().getSimpleName()));

	}

	public pojouser(String nom, String prenom, String sportpractice, boolean argOlympic) {
		this.nom = nom ;
		this.prenom = prenom ;
		this.sportpractice = sportpractice ;
		this.pojoadresse = new pojoAdresse("rue de " + sportpractice,"ville de " + sportpractice) ;
		this.Olympic = argOlympic ;
	}

	public pojouser() {
		super() ;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSportpractice() {
		return sportpractice;
	}

	public void setSportpractice(String sportpractice) {
		this.sportpractice = sportpractice;
	}

	public boolean isOlympic() {
		return Olympic;
	}

	public void setOlympic(boolean olympic) {
		Olympic = olympic;
	}

	public pojoAdresse getPojoadresse() {
		return pojoadresse;
	}

	public void setPojoadresse(pojoAdresse pojoadresse) {
		this.pojoadresse = pojoadresse;
	}

	@Override
	public String toString() {
		return "pojouser [nom=" + nom + ", prenom=" + prenom + ", sportpractice=" + sportpractice + ", pojoadresse="
				+ pojoadresse + ", attrScope=" + attrScope + "]";
	}





}
