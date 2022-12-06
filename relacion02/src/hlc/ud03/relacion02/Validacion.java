package hlc.ud03.relacion02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Set;

public class Validacion {

  private Validacion() {
    
  }
  
  public static boolean esVacio(String valor) {
    return (valor == null) || valor.isEmpty();
  }
  
  public static boolean noEsVacio(String valor) {
    return !esVacio(valor);
  }

  public static boolean esExpresionRegular(String valor, String expresion) {
    return valor.matches(expresion);
  }

  public static boolean esFechaValida(String valor) {
    if (noEsVacio(valor)) {
      try {
        LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return true;
      } catch (DateTimeParseException e) {
        return false;
      }
    } else {
      return false;
    }
  }

  public static boolean esNumeroEntero(String valor) {
    try {
      // Intenta convertirlo a entero
      Integer.parseInt(valor);
      // Si no produce error es un entero
      return true;
    } catch (NumberFormatException e) {
      // Si se produce error. No lo es
      return false;
    }
  }

  public static boolean estaEnRango(int valor, Integer valorMinimo, Integer valorMaximo) {
    // Se realizan dos ifs porque el mecanismo de lazy evaluation no se puede usar al mismo tiempo para dos valores
    // Si el valor es superior (o igual) al limite inferior (en caso de que se proporcione uno
    if ((valorMinimo == null) || (valor >= valorMinimo)) {
      // Devuelve true si el valor es inferior o igual al limite superior (en caso de que se proporcione uno)
      return (valorMaximo == null) || (valor <= valorMaximo);
      } else {
        // No esta en rango
        return false;
    }
  }

  public static boolean estaEnLista(String valor, String[] lista, boolean useCase) {
    if (noEsVacio(valor)) {
      for (String actual: lista) {
        if (useCase) {
          if (valor.equals(actual)) {
            return true;
          }
        } else {
          if (valor.equalsIgnoreCase(actual)) {
            return false;
          }
        }
      }
      // No se encontro
      return false;
    } else {
      return false;
    }
  }

  public static boolean longitudEnRango(String valor, Integer longitudMinima,
    Integer longitudMaxima) {

    int longitud = valor.length();
    // Al igual que está en rango usamos dos expresiones para poder aprovechar lazy evaluation
    if ((longitudMinima == null) || (longitud >= longitudMinima)) {
      return (longitudMaxima == null) || (longitud <= longitudMaxima);
    } else {
      return false;
    }
  }

  public static boolean hayDuplicados(String[] valores) {
    // Crea un conjunto
    Set<String> conjunto = new HashSet<>();
    // Para cada elemento del array
    for (String valor: valores) {
      // Lo añade al conjunto
      conjunto.add(valor);
    }
    // Devuelve true si el conjunto y el array tienen el mismo tamaño
    // Si hay repetidos el conjunto tendrá menos elementos
    return conjunto.size() != valores.length;
  }
}
