package validacion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Validador {

	private static final String EXPRESION_EMAIL = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final String EXPRESION_URL = "(http(?:s)?\\:\\/\\/[a-zA-Z0-9]+(?:(?:\\.|\\-)[a-zA-Z0-9]+)+(?:\\:\\d+)?(?:\\/[\\w\\-]+)*(?:\\/?|\\/\\w+\\.[a-zA-Z]{2,4}(?:\\?[\\w]+\\=[\\w\\-]+)?)?(?:\\&[\\w]+\\=[\\w\\-]+)*)";

	private Validador() {

	}

	public static boolean estaVacio(String dato) {
		return (dato == null) || dato.isEmpty();
	}

	public static boolean noEsVacio(String dato) {
		return !estaVacio(dato);
	}

	public static boolean regex(String dato, String expresion) {
		return dato.matches(expresion);
	}

	public static boolean fechaValida(String dato) {
		return validaFecha(dato, "dd/MM/yyyy");
	}

	public static boolean fechaISOValida(String dato) {
		return validaFecha(dato, "yyyy-MM-dd");
	}

	public static boolean validaFecha(String fecha, String formato) {

		if (noEsVacio(fecha)) {

			try {
				DateTimeFormatter formatoFech = DateTimeFormatter.ofPattern(formato);
				LocalDate fecha1 = LocalDate.parse(fecha, formatoFech);
				if (fecha1.format(formatoFech).equals(fecha)) {
					return true;
				} else {
					return false;
				}
			} catch (Exception e) {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean numeroEntero(String dato) {
		try {
			Integer.parseInt(dato);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean estaEnRango(int dato, Integer maximo, Integer minimo) {
		if ((minimo == null) || (dato >= minimo)) {
			return ((maximo == null) || (dato <= maximo));
		} else {
			return false;
		}

	}

	public static boolean estaEnLista(String dato, String[] lista, boolean esta) {

		if (noEsVacio(dato)) {
			for (String datos : lista) {
				if (esta) {
					dato.equals(datos);
					return true;
				} else {
					return false;
				}
			}

		} else {
			return false;
		}
		return false;
	}

	public static boolean estaEnRangoTexto(String dato, Integer minimo, Integer maximo) {

		if (noEsVacio(dato)) {

			int longitudDato = dato.length();
			if ((minimo == null) || (longitudDato >= minimo)) {
				return (maximo == null) || (longitudDato <= maximo);
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public static boolean hayDuplicados(String[] lista) {

		Set<String> conjuntoDatos = new HashSet<>();
		for (String datosLista : lista) {
			conjuntoDatos.add(datosLista);
		}

		if (conjuntoDatos.size() == lista.length) {
			return true;
		} else {
			return false;
		}

	}
	
	public static boolean esUnEmail(String dato) {
		return dato.matches(EXPRESION_EMAIL);
	}

	public static boolean esUnaURL(String dato) {
		return dato.matches(EXPRESION_URL);
	}
}
