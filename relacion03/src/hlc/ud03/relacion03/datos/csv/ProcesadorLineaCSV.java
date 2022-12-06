package hlc.ud03.relacion03.datos.csv;

import java.util.NoSuchElementException;

public class ProcesadorLineaCSV {

  private String linea = null;
  
  public ProcesadorLineaCSV(String linea) {
    this.linea = linea;
  }

  public boolean hasNext() {
    return linea.length() > 0;
  }

  public String next() {
    if (hasNext()) {
      String resultado = "";
      boolean comillas = false;
      int i = 0;
      // Si el primer carácter es una comilla
      if (linea.charAt(0) == '"') {
        // La saltamos y pasamos a la siguiente
        comillas = true;
        i = 1;
      }
      // Mientras haya caracteres
      while (i < linea.length()) {
        // Si estamos en modo comillas
        if (comillas) {
          // Si el caracter es una comilla
          if (linea.charAt(i) == '"') {
           // Si no hay siguiente caracter o este no es una comilla
            if (((i + 1) == linea.length()) || (linea.charAt(i + 1) == '"')) {
              // Si hay siguiente caracter y es una coma la consume
              if ((i < linea.length()) && linea.charAt(i) == ',') {
                i++;
              }
              // Recorta la linea y devuelve el resultado
              recortaLinea(i);
              return resultado;
            }
          }  else {
            // Recogemos el carácter y pasamos al siguiente
            resultado += linea.charAt(i++);
          }
        } else {
          // No estamos en modo comillas
          // Si el carácter no es una coma
          if (linea.charAt(i) != ',') {
            // Se recoge y se pasa al siguiente
            resultado += linea.charAt(i++);
          } else {
            // Se recorta
            i++;
            recortaLinea(i);
            return resultado;
          }
        }
      }
      // Se acabó la línea. Si estábamos en comillas
      if (comillas) {
        // Error en la línea
        throw new NoSuchElementException();
      } else {
        // Recortamos la linea
        linea = "";
        // Devuelve el resultado
        return resultado;
      }
    } else { 
      throw new NoSuchElementException();
    }
  }
  
  private void recortaLinea(int posicion) {
    linea = linea.substring(posicion);
  }
  
}
