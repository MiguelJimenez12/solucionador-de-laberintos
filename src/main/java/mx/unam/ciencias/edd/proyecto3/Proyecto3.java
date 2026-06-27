package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.*;

public class Proyecto3 {
    public static void main(String[] args) {
	if (args.length != 1) {
            System.err.println("Uso: java -jar target/proyecto3.jar <archivo>");
            return;
        }

        String archivo = args[0];
        Laberinto laberinto = Laberinto.leerArchivo(archivo);
        if (laberinto == null) return;

        Solucionador solucionador = new Solucionador(laberinto);
        if (solucionador.resolver())
            laberinto.marcarRuta(solucionador.obtenerRuta());

	    laberinto.imprimir();
    }
}
