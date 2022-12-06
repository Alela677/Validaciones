package hlc.ud03.relacion03.datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import hlc.ud03.relacion03.datos.csv.ProcesadorLineaCSV;

public class BloqueDatosEnCSV implements BloqueDatos {

  Map<String, String> datos;
  
  public BloqueDatosEnCSV(String linea, String[] campos) {
    datos = new HashMap<>();
    procesaLinea(linea, campos);
  }
  
  @Override
  public boolean contieneDato(String nombre) {
    return datos.containsKey(nombre);
  }

  @Override
  public String getDato(String nombre) {
    return datos.get(nombre);
  }

  private void procesaLinea(String linea, String[] campos) {
    ProcesadorLineaCSV procesador = new ProcesadorLineaCSV(linea);
    int i = 0;
    // Mientras haya campos
    while (procesador.hasNext()) {
      // Obtiene el siguiente campo
      String campo = procesador.next();
      // Si no hemos extraido más campos que los de la lista
      if (i < campos.length) {
        // Se añade al bloque
        datos.put(campos[i++], campo);
      } else {
        // Lanza una excepción
        throw new BloqueDatosException("Se ha detectado una línea con más datos que campos");
      }
    }
  }
}
