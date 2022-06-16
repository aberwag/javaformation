package RMI;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LzMultilineJLabell extends JLabel {

	
	private static final long serialVersionUID = 1L;	
	
	public JLabel addNewLine(String strToAdd) {
		JLabel curJLabel = this;
		String sTextInit  = this.getText() ;
		
		String strFinal = sTextInit ;
		// Si le texte à ajouter, une fois trimé est vide on renvoi directement l'objet courant 
		// Si le texte initial éxiste et si le texte à ajouter éxiste :
				 //On vérifie si le texte initiale commence par le balisage de départ
						//Si ce n'est pas le cas on rajoute la balise de départ
		         //On remplace les \n du texte à ajouter par des <br>
		 		 //On rajoute la balise d'entrée et on remplace les \n par des <br>
		// Si le texte initiale n'éxiste pas : on rajoute la balise d'entrée et on remplace les \n par des <br>
		
		if (sTextInit.length() != 0) {
			sTextInit = sTextInit.replaceAll("\\\\n", "<br />");
			System.out.println(sTextInit.substring(0, 6));
			if (!(sTextInit.substring(0, 6).equals("<html>"))) {
				sTextInit = "<html>" + sTextInit;
				curJLabel.setText(sTextInit);
			
				if (strToAdd.length() == 0) {
					sTextInit = sTextInit + "</html>"  ;
					strFinal = sTextInit ;
				}
				else {
					strToAdd = strToAdd.replaceAll("\\\\n", "<br />");
					strFinal = sTextInit + "<br />" + strToAdd + "</html>" ;
					
				}					
				
			}
			else {
				System.out.println(sTextInit);
				sTextInit = sTextInit.substring(0, sTextInit.length() - 7);
				strToAdd = strToAdd.replaceAll("\\\\n", "<br />");
				strFinal = sTextInit + "<br />" + strToAdd + "</html>" ;
			}
			
			
		}
		
		else {
			if (strToAdd.length() != 0)  { 
				strToAdd = strToAdd.replaceAll("\\\\n", "<br />") ; 
				strFinal = "<html>" + strToAdd + "</html>" ; 
			}
		}
		System.out.println("strFinal : " + strFinal );
		curJLabel.setText(strFinal) ;			
			
		return curJLabel ;	
		
	}
	

}
