package helper;

public class LoizStreamhelper {

	public  static String Bonjour(String strArg) {
		String strReturn = "Bonjour \"" +strArg+ "\"" ;
		System.out.println(strReturn);
		return strReturn ;
	}

	public static  String modifierString(String strArg) {
		return strArg+"_modified" ;
	}



	public static  void Affiche(String strTitle ) {
		System.out.println("\n"+ strTitle);
	}

	public static  void loizStrSep() {
		System.out.println("-------------------------------");
	}
}