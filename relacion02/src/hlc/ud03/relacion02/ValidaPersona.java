package hlc.ud03.relacion02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ValidaPersona {

  public static final String CAMPO_NOMBRE_APELLIDOS = "nombreApellidos";
  public static final String CAMPO_FECHA_NACIMIENTO = "fechaNacimiento";
  public static final String CAMPO_TELEFONO_FIJO = "telefonoFijo";
  public static final String CAMPO_TELEFONO_MOVIL = "telefonoMovil";
  public static final String CAMPO_NUMERO_HIJOS = "numeroHijos";
  public static final String CAMPO_COMUNIDAD = "comunidad";
  public static final String CAMPO_LOCALIDAD = "localidad";
  public static final String CAMPO_INTERESES = "intereses";
  
  private static final String EXPRESION_NOMBRE_APELLIDOS = "[A-Z][a-z]*( +[A-Z][a-z]*)*";
  private static final String EXPRESION_TELEFONO_FIJO = "9[0-9]{8}";
  private static final String EXPRESION_TELEFONO_MOVIL = "(\\+[0-9]{2,3})?[0-9]{1,12}";
  private static final Integer NUMERO_HIJOS_VALOR_MIN = 0;
  private static final String[] COMUNIDAD_VALORES = {"Andalucía", "Extremadura", "Otra"};
  private static final String EXPRESION_LOCALIDAD = "[A-Z][a-z]*";
  private static final int INTERESES_MIN = 1;
  private static final int INTERESES_MAX = 5;
  private static final String[] INTERESES_VALORES = {
      "Lectura",
      "Videojuegos",
      "Series",
      "Películas",
      "Actividades al aire libre",
      "Deportes",
      "Tecnología",
      "Manualidades",
  };
  
  
  public final String[] CAMPOS = {
    CAMPO_NOMBRE_APELLIDOS,
    CAMPO_FECHA_NACIMIENTO,
    CAMPO_TELEFONO_FIJO,
    CAMPO_TELEFONO_MOVIL,
    CAMPO_NUMERO_HIJOS,
    CAMPO_COMUNIDAD,
    CAMPO_LOCALIDAD,
    CAMPO_INTERESES,
  };
  
  private String error = null;
  
  public String[] getCampos() {
    return CAMPOS;
  }
  
  public boolean esValidoCampo(String campo, String valor) {
    switch (campo) {
      case CAMPO_NOMBRE_APELLIDOS:
        return esValidoNombreApellidos(valor);
      case CAMPO_FECHA_NACIMIENTO:
        return esValidoFechaNacimiento(valor);
      case CAMPO_TELEFONO_FIJO:
        return esValidoTelefonoFijo(valor);
      case CAMPO_TELEFONO_MOVIL:
        return esValidoTelefonoMovil(valor);
      case CAMPO_NUMERO_HIJOS:
        return esValidoNumeroHijos(valor);
      case CAMPO_COMUNIDAD:
        return esValidoComunidad(valor);
      case CAMPO_LOCALIDAD:
        return esValidoLocalidad(valor);
      case CAMPO_INTERESES:
        return esValidoIntereses(valor);
      default:
        setError("El campo " + campo + " no existe");
        return false;
    }
  }

  private void setError(String mensaje) {
    error = mensaje;
  }
  
  public String getError() {
    return error;
  }

  public boolean esValidoNombreApellidos(String valor) {
    if (Validacion.noEsVacio(valor)) {
      if (Validacion.esExpresionRegular(valor, EXPRESION_NOMBRE_APELLIDOS)) {
        return true;
      } else {
        setError("El formato no es válido");
        return false;
      }
    } else {
      setError("El campo está vacío pero es obligatorio");
      return false;
    }
  }

  public boolean esValidoFechaNacimiento(String valor) {
    if (Validacion.esFechaValida(valor)) {
      LocalDate fecha = LocalDate.parse(valor, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
      if (fecha.isBefore(LocalDate.now())) {
        return true;
      } else {
        setError("La fecha debe ser anterior a la fecha actual");
        return false;
      }
    } else {
      setError("La fecha está vacía o no es válida");
      return false;
    }
  }

  public boolean esValidoTelefonoFijo(String valor) {
    if (Validacion.noEsVacio(valor)) {
      if (Validacion.esExpresionRegular(valor, EXPRESION_TELEFONO_FIJO)) {
        return true;
      } else {
        setError("El formato no es correcto");
        return false;
      }
    } else {
      // Es opcional y por lo tanto puede no proporcionarse
      return true;
    }
  }

  private boolean esValidoTelefonoMovil(String valor) {
    if (Validacion.noEsVacio(valor)) {
      if (Validacion.esExpresionRegular(valor, EXPRESION_TELEFONO_MOVIL)) {
        return true;
      } else {
        setError("El formato no es correcto");
        return false;
      }
    } else {
      // El campo es obligatorio
      setError("El campo está vacío y es obligatorio");
      return false;
    }
  }

  private boolean esValidoNumeroHijos(String valor) {
    if (Validacion.noEsVacio(valor)) {
      if (Validacion.esNumeroEntero(valor)) {
        int valorNumerico = Integer.parseInt(valor);
        if (Validacion.estaEnRango(valorNumerico, NUMERO_HIJOS_VALOR_MIN, null)) {
          return true;
        } else {
          // El número no está en rango
          setError("El valor no está en el rango correcto");
          return false;
        }
      } else {
        setError("El formato no es correcto");
        return false;
      }
    } else {
      // El campo es obligatorio
      setError("El campo está vacío y es obligatorio");
      return false;
    }
  }

  private boolean esValidoComunidad(String valor) {
    if (Validacion.noEsVacio(valor)) {
      if (Validacion.estaEnLista(valor, COMUNIDAD_VALORES, true)) {
        return true;
      } else {
        setError("El valor no está en la lista");
        return false;
      }
    } else {
      setError("El campo está vacío y es obligatorio");
      return false;
    }
  }

  private boolean esValidoLocalidad(String valor) {
    // El valor es obligatorio
    if (Validacion.noEsVacio(valor)) {
      // El valor debe tener entre 1 y 50 caracteres
      if (Validacion.longitudEnRango(valor, 1, 50)) {
        // El valor debe cumplir la expresion regular
        if (Validacion.esExpresionRegular(valor, EXPRESION_LOCALIDAD)) {
          return true;
        } else {
          setError("El campo debe comenzar por una letra mayúscula");
          return false;
        }
      } else {
        setError("La longitud del campo no es válida");
        return false;
      }
    } else {
      setError("El campo está vacío y es obligatorio");
      return false;
    }
  }

  private boolean esValidoIntereses(String valor) {
    // Es obligatorio
    if (Validacion.noEsVacio(valor)) {
      // Obtiene una lista con los valores
      String[] valores = procesaIntereses(valor);
      // Si no hay duplicados
      if (!Validacion.hayDuplicados(valores)) {
        // Si hay el número correcto
        if (Validacion.estaEnRango(valores.length, INTERESES_MIN, INTERESES_MAX)) {
          // Recorre los valores
          for (String actual: valores) {
            if (!Validacion.estaEnLista(actual, INTERESES_VALORES, true)) {
              setError("El valor " + actual + " no está permitido");
              return false;
            }
          }
          // La lista es correcta
          return true;
        } else {
          setError("El número de intereses no es correcto");
          return false;
        }
      } else {
        setError("Hay valores duplicados en el campo");
        return false;
        
      }
    } else {
      setError("El campo es obligatorio y es vacío");
      return false;
    }
  }

  private String[] procesaIntereses(String valor) {
    return valor.split(",");
  }

}
