package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import validacion.ValidarUsuario;

public class ProcesadorArchivo {

	private static final String[] NOMBRE_CAMPOS = { "Titulo", "Nombre", "Apellidos", "Telefono", "CP", "Email", "URL",
			"Username", "Password", "Fecha-Registro" };

	private static final String CAMPO_TITULO = "Titulo";
	private static final String CAMPO_NOMBRE = "Nombre";
	private static final String CAMPO_APELLIDOS = "Apellidos";
	private static final String CAMPO_TELEFONO = "Telefono";
	private static final String CAMPO_CP = "CP";
	private static final String CAMPO_EMAIL = "Email";
	private static final String CAMPO_URL = "URL";
	private static final String CAMPO_USERNAME = "Username";
	private static final String CAMPO_PASSWORD = "Password";
	private static final String CAMPO_FECHA_REGISTRO = "Fecha-Registro";

	String archivo;

	public ProcesadorArchivo(String archivo) {
		this.archivo = archivo;
	}

	public List<String> procesarArchivo() {
		List<String> errores = new ArrayList<>();
		int numLinea = 1;
		try {
			// Abrimos el archivo
			BufferedReader reader = new BufferedReader(new FileReader("files/" + archivo));
			reader.readLine();
			// Linea
			String linea;
			// Para cada linea
			while ((linea = reader.readLine()) != null) {
				System.out.println(linea);
				// Crea un procesador para la linea
				BloqueDatos bloqueLinea = new BloqueDatosEnCSV(linea, NOMBRE_CAMPOS);
				// Valida la linea
				validaLinea(bloqueLinea, numLinea, errores);
				numLinea++;
			}
		} catch (FileNotFoundException e) {
			errores.add("El archivo " + archivo + " no se encuentra");
		} catch (IOException e) {
			errores.add("Error leyendo de " + archivo);
		} catch (NoSuchElementException e) {
			errores.add("Error de formato en la línea " + numLinea);
		}
		return errores;
	}

	private void validaLinea(BloqueDatos bloqueLinea, int numLinea, List<String> errores) {

		// Validamos el título
		if (!ValidarUsuario.tituloValido(bloqueLinea.getDato(CAMPO_TITULO))) {
			addError(errores, CAMPO_TITULO, numLinea, ValidarUsuario.getError());
		}
		// Validamos el nombre
		if (!ValidarUsuario.nombreValido(bloqueLinea.getDato(CAMPO_NOMBRE))) {
			addError(errores, CAMPO_NOMBRE, numLinea, ValidarUsuario.getError());
		}
		// Validamos los apellidos
		if (!ValidarUsuario.apellidoValido(bloqueLinea.getDato(CAMPO_APELLIDOS))) {
			addError(errores, CAMPO_APELLIDOS, numLinea, ValidarUsuario.getError());
		}
		// Validamos el telefono
		if (!ValidarUsuario.telefonoValido(bloqueLinea.getDato(CAMPO_TELEFONO))) {
			addError(errores, CAMPO_TELEFONO, numLinea, ValidarUsuario.getError());
		}
		// Validamos el Código Postal
		if (!ValidarUsuario.cpValido(bloqueLinea.getDato(CAMPO_CP))) {
			addError(errores, CAMPO_CP, numLinea, ValidarUsuario.getError());
		}
		// Validamos el Correo Electronico
		if (!ValidarUsuario.emailValido(bloqueLinea.getDato(CAMPO_EMAIL))) {
			addError(errores, CAMPO_EMAIL, numLinea, ValidarUsuario.getError());
		}
		// Validamos la URL
		if (!ValidarUsuario.urlValido(bloqueLinea.getDato(CAMPO_URL))) {
			addError(errores, CAMPO_URL, numLinea, ValidarUsuario.getError());
		}
		// Validamos el nombre de usuario
		if (!ValidarUsuario.usernameValido(bloqueLinea.getDato(CAMPO_USERNAME))) {
			addError(errores, CAMPO_USERNAME, numLinea, ValidarUsuario.getError());
		}
		// Validamos la password
		if (!ValidarUsuario.passwordValido(bloqueLinea.getDato(CAMPO_PASSWORD))) {
			addError(errores, CAMPO_PASSWORD, numLinea, ValidarUsuario.getError());
		}
		// Validamos la Fecha de registro
		if (!ValidarUsuario.esValidoFechaRegistro(bloqueLinea.getDato(CAMPO_FECHA_REGISTRO))) {
			addError(errores, CAMPO_FECHA_REGISTRO, numLinea, ValidarUsuario.getError());
		}
	}

	private void addError(List<String> errores, String campoTitulo, int numLinea, String error) {
		errores.add("Linea " + numLinea + ". Campo: " + campoTitulo + " Error: " + error);
	}

}
