package partida;

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
		//maximos y minimos de personajes al iniciar la partida
		
		
		
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
	
	    
	    
	    //---------------------------------------------------------------------------------------------------------------------------------------------
	    
	    
	    
		//----------------------------------------------------dificultad----------------------------------------------------------------------------------
	    boolean control;
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
		
		
		
	}
	
	//modifican la vida de jugadores y enemigos en funcion de la dificultad
	//se hace un setter de la vida porque cada tipo de personaje tiene una vida base distinta (porque hay que tener 3 tipos de personajes)
	public void dificultad_facil() {
		for(int i = 0; i < numero_de_jugadores; i++)
		{
			int vidaMod = (int) ((int)jugadores[i].getVida()*1.6);
			jugadores[i].setVida(vidaMod);
		}
		
		//No se modifica la vida de los enemigos, se deja la de base
	}
	public void dificultad_media() {
		//TODO llamar a crear_personajes y pasarle el multiplicador de vida para la dificultad
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
		//TODO llamar a crear_personajes y pasarle el multiplicador de vida para la dificultad
		//No se modifica la vida del jugador se deja la de base
		
		for(int i = 0; i < numero_de_bots; i++)
		{
			int vidaEnemyMod = (int) ((int)enemigos[i].getVida()*1.4);
			enemigos[i].setVida(vidaEnemyMod);
		}
	}

	
	
						
							/*crear_personajes(numero_de_personajes, strDificultad, tablero, jugadores, enemigos); //funcion para inicializar personajes
							tablero.mostrarTablero();//muestra el tablero inicial
							
							while(controlPartida == true)
							{
								turno(numero_de_personajes, jugadores, enemigos, lector,tablero);
								controlPartida = CheckPersonajes(jugadores,enemigos);
							}
							
							if (jugadoresVivos(jugadores)) {
					            System.out.println("¡Habéis ganado!");
					        } else if (enemigosVivos(enemigos)) {
					            System.out.println("¡Habéis perdido!");
					        }*/
	

	public void crear_personajes(int dificultad, int vida) { // dificultad puede ser un multiplicador de vida ejemplo 1.2 * 100 = 120 de vida eso en medio en dificil un *1.6 o algo asi
		
		for (int i = 0; i < numero_de_personajes; i++)
		{
			System.out.println("Elige tu tipo de personaje:");
			System.out.println("Guerrero(por defecto): 1\nMago: 2\nArquero: 3");
			int tipo = lector.nextInt();
			System.out.println("");
			
			String nombre=null;
			boolean nombreValido=false;
			
			while(!nombreValido) {
				try {
					System.out.println("Introudce el nombre de tu personaje:");
					nombre = lector.next();
					
					if(nombre==null || nombre.length()==0) {
						throw new Exception("El campo del nombre no puede estar vacio");
					}
					if(nombre.length()>20) {
						throw new Exception("El nombre es muy largo( maximo 20 caracteres)");
					}
					
					nombreValido=true;
				}catch(Exception e) {
					System.out.println("Error");
				}
				
			}
			
			System.out.println("");
			
			//hacer enumero_de_personajes para el control de tipo
			//modificar para pasar el arma directamente en vez del nombre, aunque estaria bien si se puede mantener armas similares a esas para el inicio para diferenciar los 3 tipos de personaje
			if(tipo == 1)//guerrero
			{
				jugadores[i] = new Jugador(100, nombre, "espada", 0, 0, tablero); //corto alcance mucha fuerza, vida estandar
			}else if(tipo == 2)//mago
			{
				jugadores[i] = new Jugador(125, nombre, "varita", 0, 0, tablero); //corto alcance poca fuerza, más vida
			}else if(tipo == 3)//arquero
			{
				jugadores[i] = new Jugador(75, nombre, "arco", 0, 0, tablero); //largo alcance, poca vida
			}else//por defecto
			{
				jugadores[i] = new Jugador(100, nombre, "espada", 0, 0, tablero); //por defecto
			}
        }
		
		//Crea enemigos
		for(int i = 0; i < numero_de_personajes; i++)
		{
			//TODO pasarle arma donde esta null
			enemigos[i] = new Enemigo(100, null, "Enemigo" + (i + 1), 0, 0, tablero);//inicializacion del enemigo
		}
		
		//hacer enmun enum_dificultad para las dificultades
		if(dificultad == 1)
		{
			dificultad_facil();
		}else if(dificultad == 2)
		{
			dificultad_media();
		}
		else if(dificultad == 3)
		{
			dificultad_dificil();
		}else
		{
			dificultad_media();
		}
		
		//tablero.setarraypersonajes();
		tablero.meterPersonajes();
		
	}
	/*
	private void turno(int numero_de_personajes, Jugador jugadores[], Enemigo enemigos[],Scanner lector,Tablero tablero)
	{
		Personaje oponente; //se declara como tipo personaje para que valga para ambos
		
		for(int i = 0; i < numero_de_personajes; i++)
		{
			//turno player i
			moverse(jugadores[i],lector,tablero);
			oponente = tablero.atacar(jugadores[i]); 				//esto va a devolver un array donde estan los jugadores a su alcance
			if(oponente != null)//&& tablero.distancia(jugadores[i],oponente)<=3)
			{
				jugadores[i].makeDamage(oponente.getVida()); //si puede atacar ataca
				//oponente = null; //se reseta la variable para el siguiente movimiento
				oponente.CheckDeath();
			}
			
			//turno enemy i
			moverse(enemigos[i],lector,tablero); //TODO implementar sistemas de bots
			
			oponente = tablero.atacar(enemigos[i]); //esto va a devolver un array donde estan los jugadores a su alcance
			if(oponente != null)//&& tablero.distancia(jugadores[i],oponente)<=3)
			{
				jugadores[i].makeDamage(oponente.getVida()); //si puede atacar ataca
				//oponente = null; //se reseta la variable para el siguiente movimiento
				oponente.CheckDeath();
			}
		}
	}
	
	/*
	//comprueba que aun queden enemigos o jugadores
	private boolean CheckPersonajes(Jugador[] jugadores , Enemigo[] enemigos)
	{
		// TODO Auto-generated method stub
		return jugadoresVivos(jugadores) && enemigosVivos(enemigos);//
	}
	//comprueba si hay jugadores vivos
	private boolean jugadoresVivos(Jugador[] jugadores) {
		for(int i=0;i<jugadores.length;i++) {
			if(jugadores[i] != null && jugadores[i].getVida()>0) {
				return true;
			}
		}
		return false;
	}
	//comprueba si hay enemigos vivs
	private boolean enemigosVivos(Enemigo[] enemigos) {
		for(int i = 0; i < enemigos.length; i++) {
            if (enemigos[i] != null && enemigos[i].getVida()>0) {
				return true;
			}
		}
		return false;
	}
	
	
	//funcion moverse esta llama al tablero y gestiona tmabien la entrada del usuario
	
	private void moverse(Personaje p1,Scanner lector,Tablero tablero) { //pasamos el personaje que queremos que se mueva el escaner y el tablero
		
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
	}*/
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
