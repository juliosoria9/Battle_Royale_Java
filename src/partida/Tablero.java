package partida;

import personajes.*;

public class Tablero {
	private int[][] tablero;
    private Personaje[] personajes;

    //inicializar la partida
    // el tamaño del tablero tiene que ser 9 veces mas grandes que el de los jugadores de forma
    public void meterPersonajes() {
        int fila = 0;
        int columna = 0;

        for (int i = 0; i < personajes.length; i++) {
            personajes[i].setX(fila);
            personajes[i].setY(columna);

           
            if (fila + 3 < tablero.length) { 
                fila += 3; 
            } else {

                fila = 0;
                if (columna + 3 < tablero[0].length) { 
                    columna = columna +3; 
                } else {
  
                    return;
                }
            }
        }
    }

    
    
    // construir de una cantidad especifica de filas y columna
    public Tablero(int numero_personajes) {
        tablero = new int[numero_personajes * 9][numero_personajes * 9];
        
    }

    // cambiar valor en una posicion especifica
    public void asignarValor(int fila, int columna, int valor) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[0].length) { //comprar que este dentro del rango
            tablero[fila][columna] = valor;
        } else {
            System.out.println("indice fuera de rango.");
        }
    }

    // Metodo para obtener un valor de una posicion especifica
    public int obtenerValor(int fila, int columna) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[0].length) {
            return tablero[fila][columna];
        } else {
            System.out.println("indice fuera de rango.");
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
    
    //calcula la distancia de un personaje a otro
    public int distacia(Personaje p1,Personaje p2) {
    	 //esta funcion se basa en las lineas que hay entre jugadores de forma que si esta diagonal cuenta dos 
    	
    	int distanciaX = Math.abs(p1.getX() - p2.getX()); // con el Math.abs hacemos que sea positivo
        int distanciaY = Math.abs(p1.getY() - p2.getY());
        return distanciaX + distanciaY;

    }
    
    
    //misma funcion pero funciona con cordenadas
    public int distacia(int x1,int x2,int y1,int y2) {
   	
   	int distanciaX = Math.abs(x1 - x2); // con el Math.abs hacemos que sea positivo
       int distanciaY = Math.abs(y1 - y2);
       return distanciaX + distanciaY;

   }
    
    public int moverse(Personaje p1, int direccionx , int direcciony) {
    	
    	
    	return 0;
    	
    }
    
    
    public void setarraypersonajes(Personaje []array_jugadores) {
    	personajes = array_jugadores;
    }
}
