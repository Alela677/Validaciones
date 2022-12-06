package main;

public class MainApp {

	public static void main(String[] args) {

		System.out.println("Ejercicio1: "+ej1("ab"));
		System.out.println("Ejercicio2: "+ej2("a"));
		System.out.println("Ejercicio3: "+ej3("abbb"));
		System.out.println("Ejercicio4: "+ej4("abb"));
		System.out.println("Ejercicio5: "+ej5("asacsa_sasa_sasa"));
		System.out.println("Ejercicio6: "+ej6("Aasacsa"));
		System.out.println("Ejercicio7: "+ej7("Aasacsa"));
		System.out.println("Ejercicio8: "+ej8("sasas_Aasacsa,"));
		System.out.println("Ejercicio9: "+ej9("sasaszAasacsa"));
		System.out.println("Ejercicio11: "+ej11("2020-12-04"));
		System.out.println("Ejercicio12: "+ej12("190,00"));
		System.out.println("Ejercicio13: "+ej13("190,00e1919"));
		System.out.println("Ejercicio14: "+ej14("alejandroLeal@sasasa.com"));
		System.out.println("Ejercicio15: "+ej15("+123 433232"));
		System.out.println("Ejercicio16: "+ej16("https://alejandro.com/asasa"));
		System.out.println("Ejercicio17: "+ej17("C:\\Program Files\\asasa.s"));
		System.out.println("Ejercicio19: "+ej19("12345678F"));
		System.out.println("Ejercicio20: "+ej20("T12345678F"));
		
		
		
	}

	private static boolean ej1(String valor) {
		if (!valor.isEmpty()) {
			return valor.matches("ab+");
		}
		return false ;
	}
	
	private static boolean ej2(String valor) {
		return valor.matches("ab*");

	}

	private static boolean ej3(String valor) {
		return valor.matches("ab{3}");

	}

	private static boolean ej4(String valor) {
		return valor.matches("ab{2,3}");

	}
	
	private static boolean ej5(String valor) {
		return valor.matches("[a-z][a-z_]*");

	}
	
	private static boolean ej6(String valor) {
		return valor.matches("[A-Z][a-z]*");

	}
	
	private static boolean ej7(String valor) {
		return valor.matches("^[A-Za-z]+.*");

	}
	
	private static boolean ej8(String valor) {
		return valor.matches(".*[A-Z-a-z]+(|.|,|;)?");

	}
	
	private static boolean ej9(String valor) {
		return valor.matches("[A-Za-z]*z[A-Za-z]*");

	}
	
	private static boolean ej11(String valor) {
		return valor.matches("\\d{4}-\\d{2}-\\d{2}");

	}
	
	private static boolean ej12(String valor) {
		return valor.matches("[0-9]+(,[0-9]+)?");

	}
	
	private static boolean ej13(String valor) {
		return valor.matches("[0-9]+(,[0-9]+)?([eE][0-9]+)?");

	}
	
	private static boolean ej14(String valor) {
		return valor.matches("^[a-zA-Z0-9_\\\\-]+@[a-zA-Z0-9]+\\.[a-z]{2,4}");

	}
	
	private static boolean ej15(String valor) {
		return valor.matches("\\+[0-9]{3}( )?[0-9]{1,12}");

	}
	
	private static boolean ej16(String valor) {
		return valor.matches("https?://[A-Za-z0-9-]+(.[A-Za-z-])*(/[A-Za-z_\\-]*|(\\.\\.?))+");

	}
	
	private static boolean ej17(String valor) {
		return valor.matches("([A-Za-z]:)?\\\\?([A-Za-z0-9( )?]+|(..?))(\\\\([0-9A-Za-z.-]+|(\\.\\.?)))*");

	}
	
	private static boolean ej19(String valor) {
		return valor.matches("^[0-9]{8}[A|B|C|D|E|F|G|H|J|K|L|M|N|P|Q|R|S|T|V|W|Y|Z]$");

	}
	private static boolean ej20(String valor) {
		return valor.matches("^(X|T)[0-9]{7,8}[A|B|C|D|E|F|G|H|J|K|L|M|N|P|Q|R|S|T|V|W|Y|Z]$");

	}
}
