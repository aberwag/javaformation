package dto;

public class pojoAdresse {
	
	private String rue ;
	private String ville ;	
	
	public pojoAdresse(String rue, String ville) {
		super();
		this.rue = rue;
		this.ville = ville;
	}
	public pojoAdresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	@Override
	public String toString() {
		return "pojoAdresse [rue=" + rue + ", ville=" + ville + "]";
	}
	
	

}
