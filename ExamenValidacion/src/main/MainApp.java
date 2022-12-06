package main;

import java.util.List;

import utils.BloqueDatos;
import utils.BloqueDatosEnCSV;
import utils.ProcesadorArchivo;

public class MainApp {

	public static void main(String[] args) {

		if (args.length > 0) {
			ProcesadorArchivo nuevo = new ProcesadorArchivo(args[0]);
			List<String> errores = nuevo.procesarArchivo();

			if (!errores.isEmpty()) {
				for (String string : errores) {
					System.out.println(string);
				}
			} else {
				System.out.println("No hay errores");
			}

		} else {
			System.err.println("No se a proporcionado un fichero");
		}

	}
}
