package tablero;

public class Tablero {
    private int[][] tablero;
    private int[] personajes;

    // construir de una cantidad especifica de filas y columna
    public Tablero(int filas, int columnas,int []array_jugadores) {
        tablero = new int[filas][columnas];
        personajes = array_jugadores;
    }

    // cambiar valor en una posicion especifica
    public void asignarValor(int fila, int columna, int valor) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[0].length) {
            tablero[fila][columna] = valor;
        } else {
            System.out.println("Indice fuera de rango.");
        }
    }

    // Metodo para obtener un valor de una posicion especifica
    public int obtenerValor(int fila, int columna) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[0].length) {
            return tablero[fila][columna];
        } else {
            System.out.println("Índice fuera de rango.");
            return -1; //si hay error devuelve -1
        }
    }

    //imprime el tablero
    public void mostrarTablero() {
        for (int[] fila : tablero) {
            for (int valor : fila) {
                System.out.print(valor + " ");
            }
            System.out.println();
        }
    }
}

