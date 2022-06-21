package RMI;

import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JLabel;

public class LzMultilineJLabell extends JLabel {

	private static final long serialVersionUID = 1L;

	public void builListDialog(ArrayList<String> attrArraySaisie) {

		String sTextInit = this.getText();
		String strFinal = "";

		String sHtmClose = "";
		this.setText("");
		int i = 0;
		for (String val : attrArraySaisie) {
			i++;
			boolean bEndList = (i == attrArraySaisie.size());
			if (val.length() != 0)
				sHtmClose = bEndList ? "</html>" : "";

			if (strFinal.length() != 0) {
				strFinal = strFinal.replaceAll("\\\\n", "<br />");
				System.out.println(strFinal.substring(0, 6));
				if (!(strFinal.substring(0, 6).equals("<html>"))) {
					strFinal = "<html>" + strFinal;

					if (val.length() == 0) {
						strFinal = strFinal + sHtmClose;						
					} else {
						val = val.replaceAll("\\\\n", "<br />");
						strFinal = strFinal + "<br />" + val + sHtmClose;
					}
				} else {
					System.out.println(strFinal);
					// sTextInit = sTextInit.substring(0, sTextInit.length() - 5);
					val = val.replaceAll("\\\\n", "<br />");
					strFinal = strFinal + "<br />" + val + sHtmClose;
				}

			}

			else {
				if (val.length() != 0) {
					val = val.replaceAll("\\\\n", "<br />");
					strFinal = "<html>" + val + sHtmClose;
				}
			}
			System.out.println("strFinal : " + strFinal);
			// curJLabel.setText(strFinal) ;

		}

		this.setText(strFinal);
	}

}
