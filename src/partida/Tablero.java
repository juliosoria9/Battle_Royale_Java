package partida;

import java.util.ArrayList;
import java.util.Random;

import personajes.*;

public class Tablero<asignarValor> {
	private int[][] tablero;
    private Personaje[] personajes;

    //metemos los personajes que se nos han paso dentro del tablero cambiando su x e y
    public void meterPersonajes() {
    	Random random = new Random();
    	int fila = 0;
    	int columna = 0;
        
        for(int i = 0;i < personajes.length;i++) {
            
            
            
             fila = random.nextInt(tablero.length); 
             columna = random.nextInt(tablero[0].length); 
        	personajes[i].setX(fila);
            personajes[i].setY(columna);
            
            asignarValor(fila,columna,1);
        	
        }
    }

    //esta funcio solo cambia el valor lo muestra y lo vuelve a cambiar para que sea mas facil a la hora de moverse por el tablero
    public void mostrar_con_2(Personaje p1,int num){
    	asignarValor(p1.getX(),p1.getY(),num);//se asigna el valor num en las coordenadas del personaje
    	mostrarTablero();//muestra tablero
    	asignarValor(p1.getX(),p1.getY(),num);
    }
    
    
    public void mostrarenemigos(ArrayList<Personaje> array_enemigos,Personaje p1) {
    	Personaje enemigo;
    	for(int i = 3;i < array_enemigos.size()+3; i++) { //usamos tres para que no se confunda con jugadores tablero ni tu mismo
    		
    		asignarValor_con_personaje(array_enemigos.get(i-3),i);
    		
    	}
    	asignarValor_con_personaje(p1,2); //cambiamos para ver nuestro personaje
    	mostrarTablero();
    	asignarValor_con_personaje(p1,1); //volvemos a cambiar el valor para que este todo correcto
    	
    	for(int i = 3;i < array_enemigos.size()+3; i++) { //usamos tres para que no se confunda con jugadores tablero ni tu mismo
    		
    		asignarValor_con_personaje(array_enemigos.get(i-3),1);
    		
    	}
    }
    
    
    
    
    
    public void inicializa_tablero(int numero_personajes) {
    	
    	tablero = new int[numero_personajes ][numero_personajes ];
    	
    }
    
    
    
    
    // cambiar valor en una posicion especifica
    public void asignarValor(int fila, int columna, int valor) {
        if (fila >= 0 && fila < tablero.length && columna >= 0 && columna < tablero[0].length) { //comprar que este dentro del rango
            tablero[fila][columna] = valor;
        } else {
            System.out.println("indice fuera de rango.");
        }
    }
    
    
    //se asgina el valor pero solo hace falta pasarle el personaje por lo que es mas sencillo
    public void asignarValor_con_personaje(Personaje p1, int valor) {
        if (p1.getX() >= 0 && p1.getX() < tablero.length && p1.getY() >= 0 && p1.getY() < tablero[0].length) { //comprar que este dentro del rango
            tablero[p1.getX()][p1.getY()] = valor;
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
        int nuevaX = p1.getX() + direccionx;
        int nuevaY = p1.getY() + direcciony;
    	
    	
    	if( nuevaX >= 0 && nuevaX < tablero.length && nuevaY >= 0 && nuevaY < tablero[0].length) {
        		 if (obtenerValor(nuevaX, nuevaY) == 0) {
        	            // Actualizamos la posición del personaje
        			 asignarValor_con_personaje(p1,0);
        	            p1.setX(nuevaX);
        	            p1.setY(nuevaY);
        	            
        	            return 1; // Movimiento exitoso
        	        } else {
        	            System.out.println("Casilla ocupada.");
        	            return -1; // Error: Casilla ocupada
        	        }
        	    } else {
        	        System.out.println("Coordenadas fuera de rango.");
        	        return -1; // Error: Fuera de rango
    	
        	    }
    
    }
    
    
    
    
    
    //se le pasan unas coordenadas y te dice que personaje hay hay o si no hay que devuelve null
    public Personaje cords_a_personaje(int x,int y) {
    	for(int i= 0;i < personajes.length;i++) {//se repite por cada personaje en el array
    		if(personajes[i].getX() == x && personajes[i].getY() == y) { //coje las coordenadas de lo personajes y las compara con las que le pasas
    			return personajes[i];
    		}
    	}
    	return null; // si falla devuele null
    	
    }
    
    public ArrayList<Personaje> atacar(Personaje p1) {
        ArrayList<Personaje> personajes_a_atacar = new ArrayList<Personaje>(); // Lista de personajes a atacar
        int distancia = p1.getArma().getdistancia_de_ataque(); // Distancia de ataque del personaje

        // Recorremos el radio de ataque alrededor del personaje
        for (int i = -distancia; i <= distancia; i++) { 
            for (int j = -distancia; j <= distancia; j++) {
                // Coordenadas de la casilla a inspeccionar
                int nx = p1.getY() + i;
                int ny = p1.getY() + j;

                // Verificar si las coordenadas están dentro de los límites de la matriz
                if (nx >= 0 && ny >= 0 && nx < tablero.length && ny < tablero[0].length) {
                    // Verificamos si hay un personaje en esa casilla
                    if (obtenerValor(nx, ny) == 1 || obtenerValor(nx, ny) == 2) { // Si la casilla contiene un 1 o 2 (personajes) con el 1 basta pero asi nos quitamos de errores
                        Personaje personaje = cords_a_personaje(nx, ny); // Obtener personaje de las coordenadas
                        if (personaje != null) {
                            personajes_a_atacar.add(personaje); // Agregar el personaje a la lista de atacables
                        } else {
                            System.out.println("Error: No se encontró personaje en las coordenadas (" + nx + ", " + ny + ")");
                        }
                    }
                }
            }
        }
        return personajes_a_atacar; // Devolver la lista de personajes a atacar
    }
    
    
    
    public void setarraypersonajes(Personaje []array_jugadores) { // le pasamos los jugadores
    	this.personajes = array_jugadores;
    }
}
