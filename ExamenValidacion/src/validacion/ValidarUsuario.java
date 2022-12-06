package validacion;

import javafx.scene.shape.VLineTo;

public class ValidarUsuario {

	private static final String[] LISTA_TITULOS = { "Doctor", "Señor", "Señora" };
	private static final String NOMBRE = "([A-Z]{1}[a-z]+[ ]?{1}){1,3}";
	private static final String APELLIDOS = "([A-Z]{1}[a-z]+[ ]?{1}){1,2}";
	private static final String TELEFONO = "[6|7|8|9]{1}[0-9]{8}";
	private static final String CP = "[0-9]{5}";
	private static final String USERNAME = "^[a-z0-9_-]{1,10}$";

	private static String error = null;

	private static void setError(String mensaje) {
		error = mensaje;
	}

	public static String getError() {
		return error;
	}

	public static boolean tituloValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.estaEnLista(dato, LISTA_TITULOS, true)) {
				return true;

			} else {
				setError("El dato no esta en lista");
				return false;
			}
		} else {
			setError("El dato dato vacio");
			return false;
		}
	}

	public static boolean nombreValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.estaEnRangoTexto(dato, 1, 50)) {
				if (Validador.regex(dato, NOMBRE)) {
					return true;
				} else {
					setError("El formato del nombre no es el correcto");
					return false;
				}
			} else {
				setError("El dato no esta en el rango permitido");
				return false;
			}
		} else {
			setError("El dato esta vacio");
			return false;
		}

	}

	public static boolean apellidoValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.estaEnRangoTexto(dato, 1, 100)) {
				if (Validador.regex(dato, APELLIDOS)) {
					return true;
				} else {
					setError("El campo apellido no tiene un formato correcto");
				}
			} else {
				setError("El campo apellido esat fuera del rango permitido");
				return false;
			}
		} else {
			setError("El dato esta vacio");
			return false;
		}
		return false;

	}

	public static boolean telefonoValido(String dato) {
		if (Validador.noEsVacio(dato)) {
			if (Validador.regex(dato, TELEFONO)) {
				return true;
			} else {
				setError("El campo telefono no tiene un formato correcto");
			}
		} else {
			setError("El campo telefono esta vacio");
			return false;
		}

		return false;
	}

	public static boolean cpValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.regex(dato, CP)) {
				int dosNumero = Integer.parseInt(dato.substring(0, 2));
				if (Validador.estaEnRango(dosNumero, 52, 1)) {

					return true;
				} else {

					setError("Los dos primeros numeros del cp tiene que estar entre 1 y 52");
				}
			} else {
				setError("El campo cp no tiene un formato correcto");
				return false;
			}
		} else {
			setError("El campo CP esta vacio");
			return false;
		}
		return false;

	}

	public static boolean emailValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.esUnEmail(dato)) {
				return true;
			} else {
				setError("El campo email no tiene un formato correcto");
				return false;
			}
		} else {
			setError("El campo email esat vacio");
			return false;
		}

	}

	public static boolean urlValido(String dato) {
		if (Validador.noEsVacio(dato)) {
			if (Validador.esUnaURL(dato)) {
				return true;
			} else {
				setError("El campo URL no tiene un formato valido");
				return false;
			}
		} else {
			setError("El campo URL esta vacio");
			return false;
		}

	}

	public static boolean usernameValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.estaEnRangoTexto(dato, 1, 10)) {
				if (Validador.regex(dato, USERNAME)) {
					return true;
				} else {
					setError("El campo username no tiene un formato correcto");
				}
			} else {
				setError("El campo username no esta en rango valido");
				return false;
			}
		} else {
			setError("El campo username esat vacio");
			return false;
		}
		return false;
	}

	public static boolean passwordValido(String dato) {

		if (Validador.noEsVacio(dato)) {
			if (Validador.estaEnRangoTexto(dato, 8, 16)) {

				int min = 0;
				int max = 0;
				int num = 0;
				int especial = 0;
				for (int i = 0; i < dato.length(); i++) {
					char caracter = dato.charAt(i);
					if ((caracter >= 'a') && (caracter <= 'z')) {
						min++;
					} else if ((caracter >= 'A') && (caracter <= 'Z')) {
						max++;
					} else if ((caracter >= '0') && (caracter <= '9')) {
						num++;
					} else if ((caracter == '.') || (caracter == ';') || (caracter == ',') || (caracter == ':')
							|| (caracter == '/') || (caracter == '*') || (caracter == '&') || (caracter == '%')
							|| (caracter == '$') || (caracter == '(') || (caracter == ')')) {
						especial++;
					} else {
						// El caracter no está permitido
						setError("El campo contiene caracteres no válidos");
						return false;
					}
				}
				// Comprobamos que hay un caracter de cada clase
				if ((min > 0) && (max > 0) && (num > 0) && (especial > 0)) {
					return true;
				} else {
					setError("El campo no contiene el número de caracteres de cada clase indicado");
					return false;
				}
			} else {
				setError("El campo password no esta en el rango permitido");
			}
		} else {
			setError("El campo password esta vacio");
		}

		return false;

	}

	public static boolean esValidoFechaRegistro(String dato) {
		if (Validador.noEsVacio(dato)) {
			if (Validador.fechaISOValida(dato)) {
				return true;
			} else {
				setError("El campo fecha no tiene un formato correcto");
				return false;
			}
		} else {
			setError("El campo feccha esat vacio");
			return false;
		}
	}
}
