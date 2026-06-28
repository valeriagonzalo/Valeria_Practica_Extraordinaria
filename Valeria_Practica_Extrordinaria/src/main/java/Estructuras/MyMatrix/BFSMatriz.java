package Estructuras.MyMatrix;

import Estructuras.Interfaces.Iterador;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyQueue.Cola;

public class BFSMatriz<T> {
    //Atributos:
    private MyMatrix<T> matriz;
    private boolean[][] bloqueado;

    //Constructor:
    public BFSMatriz(MyMatrix<T> matriz) {
        //Si no hay matriz, no se puede calcular el BFS, por lo que salta la excepción.
        if (matriz == null) {
            throw new IllegalArgumentException("No existe la matriz");
        }
        this.matriz = matriz;

        int f = matriz.getRows();
        int c = matriz.getCols();
        this.bloqueado = new boolean[f][c]; //Matriz paralela a la que tengamos que nos dice si una casilla está bloqueada (true) o no (false)
    }

    //Setter:
    public void setCeldaBloqueada(int fila, int columna, boolean bloquear) {
        // CORREGIDO: Usando isValid() en lugar de posicionValida()
        if (!matriz.isValid(fila, columna)) {
            throw new IndexOutOfBoundsException("Posición fuera de la matriz");
        }
        if (matriz.isValid(fila, columna)) {
            bloqueado[fila][columna] = bloquear;
        }
    }

    private ListaSE<Coordenada> getVecinos(int fila, int columna) {
        ListaSE<Coordenada> vecinos = new ListaSE<>();
        if (matriz.isValid(fila - 1, columna)) vecinos.add(new Coordenada(fila - 1, columna)); // Arriba
        if (matriz.isValid(fila + 1, columna)) vecinos.add(new Coordenada(fila + 1, columna)); // Abajo
        if (matriz.isValid(fila, columna - 1)) vecinos.add(new Coordenada(fila, columna - 1)); // Izquierda
        if (matriz.isValid(fila, columna + 1)) vecinos.add(new Coordenada(fila, columna + 1)); // Derecha
        return vecinos;
    }

    //Otros métodos:
    public boolean estaBloqueada(int fila, int columna) {
        if (!matriz.isValid(fila, columna)) return true; //Si la casilla está fuera del rango de la matriz, la contamos como bloqueda.
        return bloqueado[fila][columna]; //True si está bloqueada, false si no.
    }

    public ListaSE<Coordenada> casillasAlcanzables(Coordenada inicio, int maxMovimiento) {
        if (maxMovimiento < 0) {
            throw new IllegalArgumentException("El movimiento máximo no puede ser negativo");
        }

        ListaSE<Coordenada> resultado = new ListaSE<>(); //Lista de casillas alcanzables.
        int filas = matriz.getRows();
        int columnas = matriz.getCols();
        boolean[][] visitado = new boolean[filas][columnas]; //Si una casilla ha sido ya visitada true, si no false.
        int[][] distancia = new int[filas][columnas]; //Distancia es la cantidad de movimientos realizados desde la casilla inicial hasta este momento.

        //Casilla inicial.
        if (inicio == null) {
            throw new IllegalArgumentException("La coordenada inicial no puede ser null");
        }
        int fi = inicio.getFila();
        int ci = inicio.getColumna();

        if (!matriz.isValid(fi, ci) || estaBloqueada(fi, ci)) {
            return resultado;
        }

        visitado[fi][ci] = true;
        distancia[fi][ci] = 0;
        Cola<Coordenada> cola = new Cola<>();
        cola.encolar(inicio);

        if (maxMovimiento >= 0) {
            resultado.add(inicio);
        }

        while (cola.getSize() > 0) {
            Coordenada actual = cola.desencolar();
            int d = distancia[actual.getFila()][actual.getColumna()];

            ListaSE<Coordenada> vecinos = getVecinos(actual.getFila(), actual.getColumna());

            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();

                int vf = v.getFila();
                int vc = v.getColumna();

                if (!visitado[vf][vc] && !bloqueado[vf][vc] && d + 1 <= maxMovimiento) {
                    visitado[vf][vc] = true;
                    distancia[vf][vc] = d + 1;
                    cola.encolar(v);
                    resultado.add(v);
                }
            }
        }

        return resultado;
    }

    public int distanciaMinima(Coordenada origen, Coordenada destino) {
        if (origen == null || destino == null) return -1;
        int filas = matriz.getRows();
        int cols = matriz.getCols();
        if (!matriz.isValid(origen.getFila(), origen.getColumna())) return -1;
        if (!matriz.isValid(destino.getFila(), destino.getColumna())) return -1;
        if (estaBloqueada(origen.getFila(), origen.getColumna())) return -1;
        if (estaBloqueada(destino.getFila(), destino.getColumna())) return -1;
        if (origen.equals(destino)) return 0;

        boolean[][] visitado = new boolean[filas][cols];
        int[][] dist = new int[filas][cols];
        Cola<Coordenada> cola = new Cola<>();
        visitado[origen.getFila()][origen.getColumna()] = true;
        cola.encolar(origen);

        while (cola.getSize() > 0) {
            Coordenada actual = cola.desencolar();
            ListaSE<Coordenada> vecinos = getVecinos(actual.getFila(), actual.getColumna());
            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();
                int vf = v.getFila(), vc = v.getColumna();
                if (!visitado[vf][vc] && !bloqueado[vf][vc]) {
                    visitado[vf][vc] = true;
                    dist[vf][vc] = dist[actual.getFila()][actual.getColumna()] + 1;
                    if (v.equals(destino)) return dist[vf][vc];
                    cola.encolar(v);
                }
            }
        }
        return -1;
    }

    public ListaSE<Coordenada> caminoMinimo(Coordenada origen, Coordenada destino) {
        ListaSE<Coordenada> resultado = new ListaSE<>();
        if (origen == null || destino == null) return resultado;
        int filas = matriz.getRows();
        int cols = matriz.getCols();
        if (!matriz.isValid(origen.getFila(), origen.getColumna())) return resultado;
        if (!matriz.isValid(destino.getFila(), destino.getColumna())) return resultado;
        if (estaBloqueada(origen.getFila(), origen.getColumna())) return resultado;
        if (estaBloqueada(destino.getFila(), destino.getColumna())) return resultado;
        if (origen.equals(destino)) { resultado.add(origen); return resultado; }

        boolean[][] visitado = new boolean[filas][cols];
        Coordenada[][] padre = new Coordenada[filas][cols];
        Cola<Coordenada> cola = new Cola<>();
        visitado[origen.getFila()][origen.getColumna()] = true;
        cola.encolar(origen);
        boolean encontrado = false;

        while (cola.getSize() > 0 && !encontrado) {
            Coordenada actual = cola.desencolar();
            ListaSE<Coordenada> vecinos = getVecinos(actual.getFila(), actual.getColumna());
            Iterador<Coordenada> it = vecinos.getIterador();
            while (it.hasNext()) {
                Coordenada v = it.next();
                int vf = v.getFila(), vc = v.getColumna();
                if (!visitado[vf][vc] && !bloqueado[vf][vc]) {
                    visitado[vf][vc] = true;
                    padre[vf][vc] = actual;
                    if (v.equals(destino)) { encontrado = true; break; }
                    cola.encolar(v);
                }
            }
        }

        if (!encontrado) return resultado;
        Coordenada p = destino;
        while (p != null) {
            resultado.addInicio(p);
            if (p.equals(origen)) break;
            p = padre[p.getFila()][p.getColumna()];
        }
        return resultado;
    }
}
