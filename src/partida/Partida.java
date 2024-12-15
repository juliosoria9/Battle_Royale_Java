package partida;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import Herramienta.Herramienta;
import partida.Tablero;

import personajes.*;

public class Partida {
	
	//variables declaradas como globales porque todas/varias funciones tienen que poder acceder a ellas
	private Scanner lector = new Scanner(System.in);
	private int bots_minimos = 2;
	private int bots_maximos = 10;
	private int players_minimos = 1;
	private int players_maximos = 5;
	
	
	private Tablero<?> tablero = new Tablero<Object>();
	private int numero_de_personajes = 0;
	private int numero_de_jugadores = 0;
	private int numero_de_bots = 0;
	private boolean controlPartida = true;
	
	private Personaje arraypersonajes[];
	private Enemigo enemigos[];
	private Jugador jugadores[];
	private String nombreGanador;
	
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
		

		int i = 0;
	    while(ultimojugador() == false) {
	    	turno(i);
	    	i++;
	    }
	    System.out.println( "GANA " + nombreGanador);
	    
	   
		
		
	}
	public boolean ultimojugador() {
		int num = 0;
		for(int i = 0; i < arraypersonajes.length; i++) {
			if (arraypersonajes[i].getVida() <= 0) {
				num++;
				nombreGanador = arraypersonajes[i].getNombre();
			}
			if(num >= 2) {
				return false;
			}
		}
		if(num == 1) {
			return true;
		}
		return false;
	}
	public void turno(int ronda) {
		int controlmov= 0;
		ArrayList<Personaje> personajes_a_atacar = new ArrayList<Personaje>();
		int elecion_ataque;
		System.out.println("inicio de turno numero: " + ronda);
		tablero.mostrarTablero();
		//turno de jugadores
		for(int i = 0;i<numero_de_jugadores;i++) {
			
			
			
			//--------------------------movimiento--------------------
			System.out.println("turno del jugador:"+ jugadores[i].getNombre());
			tablero.mostrar_con_2(jugadores[i],2);
			do {
			controlmov = moverse_jugador(jugadores[i]);
			if(controlmov == 0) {
				System.out.printf("error al moverte");
			}
			}while(controlmov == -1);
			
			//esto va segun mi logica porque si no cuando se rompa el bucle una vez no vuelve a entrar
			controlmov = -1; //resetea el valor del controlmov
			
			System.out.println("este es el tablero una vez te moviste");
			System.out.println("-------------------------------------------------");
			tablero.mostrar_con_2(jugadores[i], 2);
			System.out.println("-------------------------------------------------");
			System.out.println("---------------este es el mapa de enemigos---------------");
			
			
			
			//-------------------------ataque---------------------------
			System.out.println("tu arma tiene "+jugadores[i].getArma().getdistancia_de_ataque()+" de distancia de ataque\n\n estos son las posibles victimas");
			
			personajes_a_atacar = tablero.atacar(jugadores[i]); //esto devuelve el arraylist con los personajes a los que su arma tiene rango para atacar
			if(personajes_a_atacar.size() == 0 ) {
				System.out.println("no tienes a nadie a rango por lo que no poedes atacar");
			}else {
			
				/*for(int h = 0; i<= personajes_a_atacar.size();h++) { //mostrar las posibles victimas
					System.out.println("-----------------este jugador esta representado con: "+ (h+3) +" en el tablero de juego---------------------");
					System.out.println(personajes_a_atacar.get(h).toString());
				}*/
				
				do {
				
				tablero.mostrarenemigos(personajes_a_atacar,jugadores[i]); //muestra a los enemigos a los que puedes atacar
				//mayores de 3 porque a los que enemigos a los que no se puede atacar se representan con 1 y los jugadores con 2
				System.out.println("estos son los jugadores que puedes atacar (los de 3 para arriba) escribe el numero del jugador a quien quieras atacar que este en tu rango");
				elecion_ataque = lector.nextInt(); //elige el enemigo al que atacar
	//ataque//	
	//ataque//	//has puesto que todo valga, porque te vale todo lo menor o todo lo mayor, en otras palabras todo menos =
				//esto creo que o es el ultimo o no funciona, porque size te da el tamaño maximo
				if(elecion_ataque -3 >= personajes_a_atacar.size() || elecion_ataque -3 >= personajes_a_atacar.size()) {
					System.out.println("el enemigo introducido no es correcto o no esta disponible");
					System.out.println("seleccionaste"+(elecion_ataque));
				}
				
				
				}while(elecion_ataque -3 >= personajes_a_atacar.size() || elecion_ataque -3 >= personajes_a_atacar.size()); 
				// comprueba que es un valor valido
				personajes_a_atacar.get(elecion_ataque-3).takeDamage(jugadores[i].getArma().getdaño());
				System.out.println("se ha dañado a "+ personajes_a_atacar.get(elecion_ataque-3).getNombre() + " y se le ha aplicado "+jugadores[i].getArma().getdaño()+" de daño, la nueva vida de "+personajes_a_atacar.get(elecion_ataque-3).getNombre()+" es "+personajes_a_atacar.get(elecion_ataque-3).getVida()+" de vida");
			}
		
		
		
		//turno enemigos
		for(int j = 0; j < numero_de_bots; j++)
		{
			
			//--------------------------movimiento--------------------
			System.out.println("turno del enemigo:"+ enemigos[j].getNombre());
			tablero.mostrar_con_2(enemigos[j],2);
			do {
				controlmov = moverse_enemigo(enemigos[j]);
				if(controlmov == 0) {
					System.out.printf("error al moverte");
				}
				}while(controlmov == -1);
				
				controlmov = -1; //resetea el valor del controlmov
				System.out.println("este es el tablero una vez se movio el enemigo");
				System.out.println("-------------------------------------------------");
				tablero.mostrar_con_2(enemigos[j], 2);
				System.out.println("---------------este es el mapa de enemigos---------------");
				
				
				
				//-------------------------ataque---------------------------
				Random random = new Random();
				
				personajes_a_atacar = tablero.atacar(enemigos[j]); //esto devuelve el arraylist con los personajes a los que su arma tiene rango para atacar
				if(personajes_a_atacar.size() == 0 ) {
					System.out.println("no tienes a nadie a rango por lo que no poedes atacar");
				}else {
					
					
					elecion_ataque = random.nextInt(personajes_a_atacar.size()); //elige el enemigo al que atacar
					System.out.print(elecion_ataque);
					System.out.print(personajes_a_atacar.size()); //todo el ataque es automatico el de los bots
					
					
					personajes_a_atacar.get(elecion_ataque).takeDamage(enemigos[j].getArma().getdaño()); //hace daño al enemigo
					System.out.println("se ha dañado a "+ personajes_a_atacar.get(elecion_ataque).getNombre() + " y se le ha aplicado "+enemigos[j].getArma().getdaño()+" de daño, la nueva vida de "+personajes_a_atacar.get(elecion_ataque).getNombre()+" es "+personajes_a_atacar.get(elecion_ataque).getVida()+" de vida");

				}
			}
		}
		
	}
	
	
	public void crearpersonajes() {
		String name;
		int j = 0;
		//-------------------------crea jugadores---------------------------
		for(int i = 1; i < numero_de_jugadores+1; i++) //crea los jugadores les asigna un arma automaticamnete he imprime el arma
		{
			System.out.println("\nintroduce el nombre del jugador" + i +":");
			name = lector.next();
			if(name.isBlank() || name.isEmpty()) //si el nombre esta vacio o no tiene caracteres visibles (es de espacios) le pone al jugador nombre por defecto
			{
				name = "jugador" + i;
			}
			//-----------------eleccion de tipo---------------------
			System.out.println("\nElige el tipo:");
			System.out.println("Guerrero: 1 (por defecto) / Tanque: 2 / Asesino: 3");
			int tipo = lector.nextInt();
			switch(tipo)
			{
			case 1:
				jugadores[i-1] = new Jugador(100, name, new Herramienta(10), tablero); //instanciamos al jugador pasandole vida nombre y la herramienta (que se crea en el propio constructor)
				System.out.println("\nHas elegido guerrero tienes 100 de vida y +10 de daño en tus armas");
				break;
			case 2:
				jugadores[i-1] = new Jugador(150, name, new Herramienta(-10), tablero); //instanciamos al jugador pasandole vida nombre y la herramienta (que se crea en el propio constructor)
				System.out.println("\nHas elegido tanque tienes 150 de vida y -10 de daño en tus armas");
				break;
			case 3:
				jugadores[i-1] = new Jugador(50, name, new Herramienta(30), tablero); //instanciamos al jugador pasandole vida nombre y la herramienta (que se crea en el propio constructor)
				System.out.println("\nHas elegido guerrero tienes 50 de vida y +30 de daño en tus armas");
				break;
			default:
				jugadores[i-1] = new Jugador(100, name, new Herramienta(10), tablero); //instanciamos al jugador pasandole vida nombre y la herramienta (que se crea en el propio constructor)
				System.out.println("\nHas elegido guerrero tienes 100 de vida y +10 de daño en tus armas");
			break;
			}
			
			//--------------imprime info arma-----------------
			System.out.println("el arma de "+jugadores[i-1].getNombre()+" es:\n"+jugadores[i-1].getArma().toString());
			arraypersonajes[j] = jugadores[i-1] ;
			j++;
		}
		
		//-------------------------crea enemigos---------------------------
		for(int k = 0; k < numero_de_bots; k++) //crea los bots les asigna un arma automaticamnete he imprime el arma
		{
			name = ("bot " + (k+1));
			enemigos[k] = new Enemigo(100, name, new Herramienta(), tablero);
			arraypersonajes[j] = enemigos[k] ;
			j++;
		}
	}
	
	
	
	//modifican la vida de jugadores y enemigos en funcion de la dificultad
	//se hace un setter de la vida porque cada tipo de personaje tiene una vida base distinta
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
	private int moverse_jugador(Personaje p1) { //pasamos el personaje que queremos que se 
		
		//variables de movimiento en x e y
		int moversex = 0; 
		int moversey = 0;
		do {
		System.out.println("introduce si quieres moverte: derecha (1) izquierda(2) o no moverse lateralmente (0)");
		 switch(lector.nextInt()) { //hacemos un switch para ver a donde se quiere mover
		 case 1:
			 moversey = 1; //derecha
			 break;
		 case 2:
			 moversey = -1; //izquierda
			 break;
		 case 0:
			 
			 moversey = 0; //nada
			 break;
	        default:
	        	
	            System.out.println("Opción no reconocida.\n");
	            break;
		 }
		 System.out.println("introduce si quieres moverte: arriba (1) abajo(2) o no moverse verticalmente (0)");
		 switch(lector.nextInt()) { //hacemos un switch para ver a donde se quiere mover
		 case 1:
			 moversex = -1; //arriba
			 break;
		 case 2:
			 moversex = 1; //abajo
			 break;
		 case 0:
			 moversex = 0; //nada
			 break;
		 default:
			  
	            System.out.println("Opción no reconocida.\n");
	            break;
		 }
		 if(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0){ // comprobamos los valores para soltar el mensaje de error
			 System.out.printf("introduce valores validos para poder moverte");
		 }
		}while(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0);
		// si los valores no son correctos vulvemos a repetir
		int num = tablero.moverse(p1, moversex, moversey);
		 return(num); //llamamos a la funcion para moverse de la clase tablero y segun lo que devuelva controlamos el error
		 
	}
	
	//funcion moverse esta llama al tablero y mueve a los enemigos de forma aleatoria
		private int moverse_enemigo(Personaje e1) { //pasamos el personaje que queremos que se 
			
			Random numRand = new Random();
			
			//variables de movimiento en x e y
			int moversex = 0; 
			int moversey = 0;
			do {
			 moversex = numRand.nextInt(3); //hace valor random cada vez que entra al bucle entre 0 y 2
			 switch(moversex) { //hacemos un switch para ver a donde se quiere mover
			 case 1:
				 moversey = 1; //derecha
				 break;
			 case 2:
				 moversey = -1; //izquierda
				 break;
			 case 0:
				 
				 moversey = 0; //nada
				 break;
		        default:

		            break;
			 }

			 moversey = numRand.nextInt(3); //hace valor random cada vez que entra al bucle entre 0 y 2
			 switch(moversey) { //hacemos un switch para ver a donde se quiere mover
			 case 1:
				 moversex = -1; //arriba
				 break;
			 case 2:
				 moversex = 1; //abajo
				 break;
			 case 0:
				 moversex = 0; //nada
				 break;
			 default:
		            break;
			 }
			 if(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0){ // comprobamos los valores para soltar el mensaje de error
			 }
			}while(moversex != 1 && moversex != -1 && moversex != 0 && moversey != 1 && moversey != -1 && moversey != 0);
			// si los valores no son correctos vulvemos a repetir
			int num = tablero.moverse(e1, moversex, moversey);
			 return(num); //llamamos a la funcion para moverse de la clase tablero y segun lo que devuelva controlamos el error
			 
		}

		//controla creacion de jugadores y enemigos
	private int obtenernumero_de_personajeseroValido(Scanner lector,String tipo, int min, int max) {
	    int numero_de_personajes;
	    boolean valido = false;

	    do {
	        System.out.printf("Introduzca el número de %s entre %d y %d:%n", tipo, min, max); //tipo = jugador/enemigo
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
