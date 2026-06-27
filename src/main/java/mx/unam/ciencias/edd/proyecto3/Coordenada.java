package mx.unam.ciencias.edd.proyecto3;

public class Coordenada {
    public final int fila;
    public final int columna;

    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Coordenada))
            return false;
        Coordenada c = (Coordenada) o;
        return this.fila == c.fila && this.columna == c.columna;
    }

    @Override
    public int hashCode() {
        return (fila * 31) + columna;
    }

    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}
