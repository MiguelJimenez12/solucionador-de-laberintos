package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Laberinto {
    private Lista<String> lineas;
    private int filas, columnas;
    private Coordenada entrada, salida;
    private Diccionario<Coordenada, Character> camino;

    private Laberinto() {
        this.lineas = new Lista<>();
        this.camino = new Diccionario<>();
    }

    public static Laberinto leerArchivo(String ruta) {
        Laberinto laberinto = new Laberinto();
        int longitudEsperada = -1;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(ruta)))) {
            String linea;
            int fila = 0;
            while ((linea = br.readLine()) != null) {
                if (longitudEsperada == -1) {
                    longitudEsperada = linea.length();
                } else if (linea.length() != longitudEsperada) {
                    System.err.println("Error: líneas del archivo con distinta longitud.");
                    return null;
                }

                for (int col = 0; col < linea.length(); col++) {
                    char c = linea.charAt(col);
                    Coordenada coord = new Coordenada(fila, col);
                    if (c == 'E') {
                        if (laberinto.entrada != null) {
                            System.err.println("Error: más de una entrada.");
                            return null;
                        }
                        laberinto.entrada = coord;
                    } else if (c == 'S') {
                        if (laberinto.salida != null) {
                            System.err.println("Error: más de una salida.");
                            return null;
                        }
                        laberinto.salida = coord;
                    } else if (c != ' ' && c != '█' && c != '*') {
                        System.err.println("Error: carácter inválido '" + c + "'.");
                        return null;
                    }
                }

                laberinto.lineas.agrega(linea);
                fila++;
            }

            if (laberinto.entrada == null || laberinto.salida == null) {
                System.err.println("Error: el laberinto debe tener una entrada 'E' y una salida 'S'.");
                return null;
            }

            laberinto.filas = laberinto.lineas.getElementos();
            laberinto.columnas = longitudEsperada;
            return laberinto;

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }
    }

    public boolean esTransitable(int fila, int columna) {
        if (fila < 0 || fila >= filas || columna < 0 || columna >= columnas)
            return false;

        char c = lineas.get(fila).charAt(columna);
        return c == ' ' || c == 'E' || c == 'S';
    }

    public Coordenada getEntrada() {
        return entrada;
    }

    public Coordenada getSalida() {
        return salida;
    }

    public void marcarRuta(Lista<Coordenada> ruta) {
        for (Coordenada c : ruta) {
            char actual = lineas.get(c.fila).charAt(c.columna);
            if (actual == ' ' || actual == 'E') {
                camino.agrega(c, '*');
            }
        }
    }

    public void imprimir() {
        int f = 0;
        for (String linea : lineas) {
            StringBuilder nueva = new StringBuilder(linea);
            for (int c = 0; c < linea.length(); c++) {
                Coordenada coord = new Coordenada(f, c);
                if (camino.contiene(coord)) {
                    nueva.setCharAt(c, '*');
                }
            }
            System.out.println(nueva.toString());
            f++;
        }
    }

    public Lista<String> getLineas() {
	return this.lineas;
    }

}

