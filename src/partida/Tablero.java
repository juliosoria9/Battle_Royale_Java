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
            asignarValor(fila,columna,1);
           
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

    
    
    public void inicializa_tablero(int numero_personajes) {
    	
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
    
    
    //misma funcion pero funciona con cordenadas para ver distancias
    public int distacia(int x1,int x2,int y1,int y2) {
   	
   	int distanciaX = Math.abs(x1 - x2); // con el Math.abs hacemos que sea positivo
       int distanciaY = Math.abs(y1 - y2);
       return distanciaX + distanciaY;

   }
    //esta funcion le pasamos direcion y nos compueba su podemos movernos si no es asi nos da error y devuelve -1
    //ejemplo de uso me quiero mover uno arriba uno derecha direccionx = 1 direcciony = 1 si queremos abajo izquierda direccionx = -1 direcciony = -1
    
    public int moverse(Personaje p1, int direccionx , int direcciony) {
    	 //comprobamos que la nueva posicion esta dentro del rango
    	if( (tablero.length <= p1.getX() + direccionx && p1.getX() + direccionx >= 0)&& ( tablero[0].length <= p1.getY() + direcciony && p1.getY() + direcciony >= 0)) {
    		if(obtenerValor(p1.getX() + direccionx,p1.getY() + direcciony) == 0) {		//comprovamos que la casilla este libre ( = 0)
    			
    			p1.setX(p1.getX() + direccionx) ;
        		p1.setY(p1.getY() + direcciony) ;
    		}else {
    			System.out.println("casilla ocupada");
        		return -1; //devolvemos -1 ya que dio error
    		}
    		
    	}else {
    		System.out.println("coodenadas fuera de rango");
    		return -1; //devolvemos -1 ya que dio error
    	}
    	return 0;
    	
    	
    }
    
    public Personaje cords_a_personaje(int x,int y) {
    	for(int i= 0;i < personajes.length;i++) {//se repite por cada personaje en el array
    		if(personajes[i].getX() == x && personajes[i].getY() == y) { //coje las coordenadas de lo personajes y las compara con las que le pasas
    			return personajes[i];
    		}
    	}
    	return null; // si falla devuele null
    	
    }
    
    public Personaje[] atacar(Personaje p1) {
    	Personaje array[] = new Personaje[10];
    	int w = 0;//numero del array donde guardar los jugadores
    	int distancia = p1.getarma().getdistancia_ataque();
    	for (int i = -distancia; i <= distancia; i++) { //buscamos en un radio a su alrededor
            for (int j = -distancia; j <= distancia; j++) {
                // Coordenadas de la casilla a inspeccionar
                int nx = p1.getY() + i;
                int ny = p1.getY() + j;

                // Verificar si las coordenadas están dentro de los límites de la matriz
                if (nx >= 0 && ny >= 0 && nx < tablero.length && ny < tablero[0].length) { // que este dentro del campo
                    if (obtenerValor(nx,ny) == 1 || obtenerValor(nx,ny) == 2) {   // Si la casilla contiene un 1 o 2, buscamos el jugador y lo metemos al array que devuelve
                    	if(cords_a_personaje(nx,ny) != null) { //buscamos el personaje
                    		array[w] = cords_a_personaje(nx,ny);
                    	}else {
                    		System.out.println("error al buscar personaje en el tablero"); // error al buscar el personaje ya que no se dieron bien ls coordenadas
                    	}
                       w++;
                    }
                }
            }
        }
    	return array; 
    }
    
    
    
    public void setarraypersonajes(Personaje []array_jugadores) { // le pasamos los jugadores
    	this.personajes = array_jugadores;
    }
}
