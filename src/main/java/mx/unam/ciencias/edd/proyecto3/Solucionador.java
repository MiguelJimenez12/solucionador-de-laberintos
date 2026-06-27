package mx.unam.ciencias.edd.proyecto3;

import mx.unam.ciencias.edd.*;

public class Solucionador {
    private Laberinto laberinto;
    private Grafica<Coordenada> grafica;
    private Diccionario<Coordenada, Coordenada> padres;
    private Lista<Coordenada> ruta;

    public Solucionador(Laberinto laberinto) {
        this.laberinto = laberinto;
        this.grafica = construirGrafica();
    }

    private Grafica<Coordenada> construirGrafica() {
        Lista<Coordenada> vertices = new Lista<>();
	Lista<String> lineas = laberinto.getLineas();
	for (int f = 0; f < lineas.getElementos(); f++) {
	    String linea = lineas.get(f);
	    for (int c = 0; c < linea.length(); c++) {
                if (laberinto.esTransitable(f, c)) {
                    vertices.agrega(new Coordenada(f, c));
                }
            }
        }

        Grafica<Coordenada> g = new Grafica<>();
        for (Coordenada v : vertices)
            g.agrega(v);

        for (Coordenada v : vertices) {
            for (Coordenada u : vecinos(v)) {
                if (laberinto.esTransitable(u.fila, u.columna)) {
                    if (!g.sonVecinos(v, u))
                        g.conecta(v, u);
                }
            }
        }

        return g;
    }

    private Lista<Coordenada> vecinos(Coordenada c) {
        Lista<Coordenada> lista = new Lista<>();
        lista.agrega(new Coordenada(c.fila - 1, c.columna));
        lista.agrega(new Coordenada(c.fila + 1, c.columna));
        lista.agrega(new Coordenada(c.fila, c.columna - 1));
        lista.agrega(new Coordenada(c.fila, c.columna + 1));
        return lista;
    }

    public boolean resolver() {
        padres = new Diccionario<>();
        Cola<Coordenada> cola = new Cola<>();
        Diccionario<Coordenada, Boolean> visitados = new Diccionario<>();

        Coordenada inicio = laberinto.getEntrada();
        Coordenada fin = laberinto.getSalida();

        cola.mete(inicio);
        visitados.agrega(inicio, true);

        while (!cola.esVacia()) {
            Coordenada actual = cola.saca();

            if (actual.equals(fin)) {
                reconstruirRuta(fin);
                return true;
            }

            for (VerticeGrafica<Coordenada> vecino : grafica.vertice(actual).vecinos()) {
                Coordenada siguiente = vecino.get();
                if (!visitados.contiene(siguiente)) {
                    visitados.agrega(siguiente, true);
                    padres.agrega(siguiente, actual);
                    cola.mete(siguiente);
                }
            }
        }

        return false;
    }

    private void reconstruirRuta(Coordenada fin) {
        ruta = new Lista<>();
        Coordenada actual = fin;
        while (actual != null) {
            ruta.agregaInicio(actual);
            if (!padres.contiene(actual))
                break;
            actual = padres.get(actual);
        }
    }

    public Lista<Coordenada> obtenerRuta() {
        return ruta;
    }
}
