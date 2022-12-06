package hlc.ud03.relacion03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import hlc.ud03.relacion03.datos.BloqueDatos;
import hlc.ud03.relacion03.datos.BloqueDatosEnCSV;

public class ValidaDatosApp {

  public static void main(String[] args) {
    if (args.length > 0) {
      ProcesadorArchivo procesador = new ProcesadorArchivo(args[0]);
        List<String> errores = procesador.procesarArchivo();
        // Si hubo errores
        if (!errores.isEmpty()) {
          System.out.println("Se encontraron los siguientes errores:");
          for (String error: errores) {
            System.out.println(error);
          }
        } else {
          System.out.println("No se encontraron errores");
        }
    } else {
      System.err.println("Error. Falta proporcionar a ruta al archivo de datos");
    }
  }

}
