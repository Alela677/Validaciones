package hlc.ud03.relacion03;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ValidaUsuario {

	private static final String[] LISTA_TITULOS = { "Doctor", "Señor", "Señora" };

	private String error = null;

	private void setError(String mensaje) {
		error = mensaje;
	}

	public String getError() {
		return error;
	}

	public boolean esValidoTitulo(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.estaEnLista(valor, LISTA_TITULOS, true)) {
				return true;
			} else {
				setError("El valor no está en la lista de valores permitidos");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoNombre(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.longitudEnRango(valor, 1, 50)) {
				return true;
			} else {
				setError("La longitud no está en el rango permitido");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoApellidos(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.longitudEnRango(valor, 1, 100)) {
				return true;
			} else {
				setError("La longitud no está en el rango permitido");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoTelefono(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.esExpresionRegular(valor, "[6-9][0-9]{8}")) {
				return true;
			} else {
				setError("El formato del campo no es correcto");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoCP(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.esExpresionRegular(valor, "[0-9]{5}")) {
				// Intentamos convertir los dos primeros dígitos a entero y ver si está en rango
				int codigoProvincia = Integer.parseInt(valor.substring(0, 2));
				if (Validacion.estaEnRango(codigoProvincia, 1, 52)) {
					return true;
				} else {
					setError("Los dos primeros números del valor deben estar entre 1 y 52");
					return false;
				}
			} else {
				setError("El formato del campo no es correcto");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoEmail(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.esDireccionEmail(valor)) {
				return true;
			} else {
				setError("El campo no es una dirección de correo válida");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoURL(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.esUrl(valor)) {
				return true;
			} else {
				setError("El campo no es una URL válida");
				return false;
			}
		} else {
			// No es obligatorio
			return true;
		}
	}

	public boolean esValidoUsername(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.longitudEnRango(valor, 1, 10)) {
				if (Validacion.esExpresionRegular(valor, "[a-zA-Z0-9_-]{1,10}")) {
					return true;
				} else {
					setError("El campo contiene caracteres no válidos");
					return false;
				}
			} else {
				setError("La longitud del campo no es válida");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoPassword(String valor) {
		if (Validacion.noEsVacio(valor)) {
			if (Validacion.longitudEnRango(valor, 8, 16)) {
				// Contamos los caracteres en cada clase
				int min = 0;
				int max = 0;
				int num = 0;
				int especial = 0;
				for (int i = 0; i < valor.length(); i++) {
					char caracter = valor.charAt(i);
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
				setError("La longitud del campo no es válida");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

	public boolean esValidoFechaRegistro(String dato) {
		if (Validacion.noEsVacio(dato)) {
			if (Validacion.esFechaValidaISO(dato)) {
				return true;
			} else {
				setError("No es una fecha válida");
				return false;
			}
		} else {
			setError("El campo está vacío pero es obligatorio");
			return false;
		}
	}

}
