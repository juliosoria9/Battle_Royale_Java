package partida;

import java.util.ArrayList;
import java.util.Scanner;

import Herramienta.Herramienta;
import partida.Tablero;

import personajes.*;

public class Partida {
	
	//arrays declarados como globales porque todas las funciones tienen que poder acceder a ellos
	
	Scanner lector = new Scanner(System.in);
	int bots_minimos = 2;
	int bots_maximos = 10;
	int players_minimos = 1;
	int players_maximos = 5;
	
	
	Tablero tablero = new Tablero();
	int numero_de_personajes = 0;
	int numero_de_jugadores = 0;
	int numero_de_bots = 0;
	boolean controlPartida = true;
	
	Personaje arraypersonajes[];
	Enemigo enemigos[];
	Jugador jugadores[];
	
	public void inicio()
	{
		
		//PARTIDA
		System.out.println("----------------BIENVENIDO AL BATTLE ROYALE------------------");
		System.out.println("");
		
		//TABELRO
		//Hay que crear el tablero antes que los personajes porque se necesita el tablero en el constructor de personajes
		
		//CREACION PERSONAJES
		
		//----------------------------------------------selenccion de jugadores-------------------------------------------------------------
		
	    numero_de_jugadores = obtenernumero_de_personajeseroValido(lector,"jugadores humanos", players_minimos, players_maximos);
	    jugadores = new Jugador[numero_de_jugadores];//creamos el array de jugadores
	    System.out.println("Se han asignado el número de jugadores de manera correcta.");

	    // Configurar numero_de_personajesero de enemigos (bots)
	    numero_de_bots = obtenernumero_de_personajeseroValido(lector,"jugadores bots", bots_minimos, bots_maximos); //llamamos a la funcion le pasamos el mensaje y los limites
	    enemigos = new Enemigo[numero_de_bots]; //ceamos el array de enemigos
	    System.out.println("Se han asignado el número de bots de manera correcta.");
	    
	    numero_de_personajes = numero_de_jugadores + numero_de_bots; //calculamos el numero total
	    
	    arraypersonajes = new Personaje[numero_de_personajes]; //creacion de el array con el numero de personajes seleccionado
	    System.out.println("el numero total entre jugadores y bots es de "+ numero_de_personajes +" personajes en el tablero");
	
	    
	    
	    //---------------------------------------------------CREACION DE PERSONAJES--------------------------------------------------------------------
	    
	    System.out.println("es hora de crear los personajes");
	    crearpersonajes();
	    System.out.println("personajes creados con exito");
	    System.out.println("\n");
	    
		//----------------------------------------------------dificultad----------------------------------------------------------------------------------
	    boolean control = false;
		do {
			System.out.println("Introduzca la dificultad:");
			System.out.println("Facil: 1\nMedio: 2\nDificil: 3");
			
			int dificultad = lector.nextInt();
			switch(dificultad) {
			case 1:
				dificultad_facil();
				control = true;
				break;
			case 2:
				dificultad_media();
				control = true;
				break;
			case 3:
				dificultad_dificil();
				control = true;
			break;
			
			default:
				control = false;
				System.out.println("nivel incorrecto");
				break;
				
			}
		}while(control == false);
		//---------------------------------------------------- iniciamos el tablero y metemos los personajes ----------------------------------------------------
		
		tablero.inicializa_tablero(numero_de_personajes);
		tablero.setarraypersonajes(arraypersonajes);
		tablero.meterPersonajes();
		System.out.println("-----------normas-----------\n       en tu turno puedes moverte y atacar \n        si la vida llega a 0 muere\n       los 1 son jugadores y los 0 casillas vacias\n         el 2 eres tu ");         
				                                        
		
		//----------------------------------------------------principio partida----------------------------------------------------------------------------------
		turno();
	}
	
	public void turno() {
		ArrayList<Personaje> pesonajes_a_atacar = new ArrayList<Personaje>();
		int elecion_ataque;
		System.out.println("inicio de turno");
		tablero.mostrarTablero();
		//turno de jugadores
		for(int i = 0;i<numero_de_jugadores;i++) {
			//--------------------------movimiento--------------------
			System.out.println("turno del jugador:"+ jugadores[i].getNombre());
			tablero.mostrar_con_2(jugadores[i],2);
			moverse_jugador(jugadores[i]);
			System.out.println("este es el tablero una vez te moviste");
			tablero.mostrar_con_2(jugadores[i],2);
			//-------------------------ataque---------------------------
			pesonajes_a_atacar = tablero.atacar(jugadores[i]); //esto devuelve el arraylist con los personajes a los que su arma tiene rango para atacar
			tablero.mostrarenemigos(pesonajes_a_atacar,jugadores[i]);
			System.out.println("estos son los jugadores que puedes atacar (lo 3 para arriba) escribe el numero del jugador a quien quieras atacar que este en tu rango");
			lector.nextInt();
		}
		
	}
	
	
	public void crearpersonajes() {
		String name;
		int j = 0;
		for(int i = 1; i < numero_de_jugadores+1; i++) //crea los jugadores les asigna un arma automaticamnete he imprime el arma
		{
			System.out.println("introduce el nombre del jugador" + i +":");
			 name = lector.next();
			jugadores[i-1] = new Jugador(100, name, new Herramienta(), tablero); //instanciamos al jugador pasandole vida nombre y la herramienta (que se crea en el propio constructor)
			System.out.println("el arma de "+jugadores[i-1].getNombre()+" es:\n"+jugadores[i-1].getarma().toString());
			arraypersonajes[j] = jugadores[i-1] ;
			j++;
		}
		for(int i = 0; i < numero_de_bots; i++) //crea los bots les asigna un arma automaticamnete he imprime el arma
		{
			 name = ("bot " + i);
			enemigos[i] = new Enemigo(100, name, new Herramienta(), tablero);
			arraypersonajes[j] = enemigos[i] ;
			j++;
		}
	}
	
	
	
	//modifican la vida de jugadores y enemigos en funcion de la dificultad
	//se hace un setter de la vida porque cada tipo de personaje tiene una vida base distinta (porque hay que tener 3 tipos de personajes)
	public void dificultad_facil() {
		for(int i = 0; i < numero_de_jugadores; i++)
		{
			int vidaMod = (int) ((int)jugadores[i].getVida()*1.3);
			jugadores[i].setVida(vidaMod);
		}
		
		//No se modifica la vida de los enemigos, se deja la de base
	}
	public void dificultad_media() {
		//llamar a crear_personajes y pasarle el multiplicador de vida para la dificultad
		for(int i = 0; i < numero_de_jugadores; i++)
		{
			int vidaMod = (int) ((int)jugadores[i].getVida()*1.4);
			jugadores[i].setVida(vidaMod);
		}
		
		for(int i = 0; i < numero_de_bots; i++)
		{
			int vidaEnemyMod = (int) ((int)enemigos[i].getVida()*1.2);
			enemigos[i].setVida(vidaEnemyMod);
		}
	}
	
	public void dificultad_dificil() {
		
		//No se modifica la vida del jugador se deja la de base
		
		for(int i = 0; i < numero_de_bots; i++)
		{
			int vidaEnemyMod = (int) ((int)enemigos[i].getVida()*1.4);
			enemigos[i].setVida(vidaEnemyMod);
		}
	}

	
	
						
							
	
		
	
	
	
	
	
	//funcion moverse esta llama al tablero y gestiona tmabien la entrada del usuario
	
	private void moverse_jugador(Personaje p1) { //pasamos el personaje que queremos que se 
		
		//variables de movimiento en x e y
		int moversex = 0; 
		int moversey = 0;
		do {
		System.out.println("introduce si quieres poverte: derecha (1) izquierda(2) o no moverse lateralmente (0)");
		 switch(lector.nextInt()) { //hacemos un switch para ver a donde se quiere mover
		 case 1:
			 moversex = 1;
			 break;
		 case 2:
			 moversex = -1;
			 break;
		 case 0:
			 moversex = 0;
			 break;
	        default:
	            System.out.println("Opción no reconocida.\n");
	            break;
		 }
		 System.out.println("introduce si quieres moverte: arriba (1) abajo(2) o no moverse verticalmente (0)");
		 switch(lector.nextInt()) { //hacemos un switch para ver a donde se quiere mover
		 case 1:
			 moversey = 1;
			 break;
		 case 2:
			 moversey = -1;
			 break;
		 case 0:
			 moversey = 0;
			 break;
		 default:
	            System.out.println("Opción no reconocida.\n");
	            break;
		 }
		 if(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0){ // comprobamos los valores para soltar el mensaje de error
			 System.out.printf("introduce valores validos para poder moverte");
		 }
		}while(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0); // si los valores no son correctos vulvemos a repetir 
		 tablero.moverse(p1, moversex, moversey); //llamamos a la funcion para moverse de la clase tablero 
	}
	private int obtenernumero_de_personajeseroValido(Scanner lector,String tipo, int min, int max) {
	    int numero_de_personajes;
	    boolean valido = false;

	    do {
	        System.out.printf("Introduzca el número de %s entre %d y %d:%n", tipo, min, max);
	        numero_de_personajes = lector.nextInt();

	        if (numero_de_personajes >= min && numero_de_personajes <= max) {
	            valido = true;
	        } else {
	            System.out.printf("Número de %s no válido. Debe ser entre %d y %d.%n%n", tipo, min, max);
	        }
	    } while (!valido);

	    return numero_de_personajes;
	}
}
