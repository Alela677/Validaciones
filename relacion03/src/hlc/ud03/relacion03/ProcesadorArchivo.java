package hlc.ud03.relacion03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import hlc.ud03.relacion03.datos.BloqueDatos;
import hlc.ud03.relacion03.datos.BloqueDatosEnCSV;

public class ProcesadorArchivo {

  private static final String[] NOMBRE_CAMPOS = {
    "Titulo",
    "Nombre",
    "Apellidos",
    "Telefono",
    "CP",
    "Email",
    "URL",
    "Username",
    "Password",
    "Fecha-Registro"
  };
  
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
      BufferedReader reader = new BufferedReader(new FileReader(archivo));
      // Linea
      String linea;
      // Para cada linea
      while ((linea = reader.readLine()) != null) {
        // Crea un procesador para la linea
        BloqueDatos bloqueLinea = new BloqueDatosEnCSV(linea, NOMBRE_CAMPOS);
        // Valida la linea
        validaLinea(bloqueLinea, numLinea, errores);
        numLinea++;
      }
    } catch (FileNotFoundException e) {
      errores.add("El archivo " + archivo + " no se encuentra");
    }
    catch (IOException e) {
      errores.add("Error leyendo de " + archivo);
    }
    catch (NoSuchElementException e) {
      errores.add("Error de formato en la línea " + numLinea);
    }
    return errores;
  }

  private void validaLinea(BloqueDatos bloqueLinea, int numLinea, List<String> errores) {
    ValidaUsuario validador = new ValidaUsuario();
    // Validamos el título
    if (!validador.esValidoTitulo(bloqueLinea.getDato(CAMPO_TITULO))) {
      addError(errores, CAMPO_TITULO, numLinea, validador.getError());
    }
    // Validamos el nombre
    if (!validador.esValidoNombre(bloqueLinea.getDato(CAMPO_NOMBRE))) {
      addError(errores, CAMPO_NOMBRE, numLinea, validador.getError());
    }
    // Validamos los apellidos
    if (!validador.esValidoApellidos(bloqueLinea.getDato(CAMPO_APELLIDOS))) {
      addError(errores, CAMPO_APELLIDOS, numLinea, validador.getError());
    }
    // Validamos el telefono
    if (!validador.esValidoTelefono(bloqueLinea.getDato(CAMPO_TELEFONO))) {
      addError(errores, CAMPO_TELEFONO, numLinea, validador.getError());
    }
    // Validamos el Código Postal
    if (!validador.esValidoCP(bloqueLinea.getDato(CAMPO_CP))) {
      addError(errores, CAMPO_CP, numLinea, validador.getError());
    }
    // Validamos el Correo Electronico
    if (!validador.esValidoEmail(bloqueLinea.getDato(CAMPO_EMAIL))) {
      addError(errores, CAMPO_EMAIL, numLinea, validador.getError());
    }
    // Validamos la URL
    if (!validador.esValidoURL(bloqueLinea.getDato(CAMPO_URL))) {
      addError(errores, CAMPO_URL, numLinea, validador.getError());
    }
    // Validamos el nombre de usuario
    if (!validador.esValidoUsername(bloqueLinea.getDato(CAMPO_USERNAME))) {
      addError(errores, CAMPO_USERNAME, numLinea, validador.getError());
    }
    // Validamos la password
    if (!validador.esValidoPassword(bloqueLinea.getDato(CAMPO_PASSWORD))) {
      addError(errores, CAMPO_PASSWORD, numLinea, validador.getError());
    }
    // Validamos la Fecha de registro
    if (!validador.esValidoFechaRegistro(bloqueLinea.getDato(CAMPO_FECHA_REGISTRO))) {
      addError(errores, CAMPO_FECHA_REGISTRO, numLinea, validador.getError());
    }
  }

  private void addError(List<String> errores, String campoTitulo, int numLinea, String error) {
    errores.add("Linea " + numLinea + ". Campo: " + campoTitulo + " Error: " + error);
  }

}
