package hlc.ud03.relacion02;

import java.util.Map;
import hlc.ud03.relacion02.datos.BloqueDatos;
import hlc.ud03.relacion02.datos.BloqueDatosEnFichero;

public class ValidaApp {
  
  public static void main(String[] args) {
    // Si se proporciono el archivo
    if (args.length > 0) {
      // Crea un bloque de datos basado en archivo y lo procesa
      BloqueDatos datos = new BloqueDatosEnFichero(args[0]);
      // Crea un validador de personas
      ValidaPersona validador = new ValidaPersona();
      // Obtiene una lista de campos del validador y lo va validando uno por uno
      // También se ofrecen métodos específicos por campo
      for (String campo: validador.getCampos()) {
        // Si el campo no valida
        if (!validador.esValidoCampo(campo, datos.getDato(campo))) {
          // Muestra el error
          System.err.println("El campo " + campo + " no es válido. Razón: " + validador.getError());
        }
      }
    } else {
      // Error.
      System.err.println("No se ha proporcionado el archivo a validar");
    }
  }
  
}
