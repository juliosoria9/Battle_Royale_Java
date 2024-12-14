package partida;

import java.util.Scanner;

import personajes.*;

public class Partida {
	
	//arrays declarados como globales porque todas las funciones tienen que poder acceder a ellos
	

	public void inicio()
	{
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero(10, 10); //pasarle las medidas del tablero
		boolean controlDif = false;
		boolean controlNum = false;
		String strDificultad = "\0";
		int num = 0;
		boolean controlPartida = true;
		
		//PARTIDA
		System.out.println("----------------BIENVENIDO AL BATTLE ROYALE------------------");
		System.out.println("");
		
		//CREACION PERSONAJES
		while(controlNum == false)
		{
			System.out.println("Introduzca el numero de jugardores:");
			num = lector.nextInt();
			System.out.println("");
			if(num >= 1 && num <= 10)
			{
				controlNum = true;
			}
			if(controlNum == false)
			{
				System.out.println("NUMERO DE JUGADORES NO VALIDO");
				System.out.println("");
			}
		}
		
		//Se crean los arrays de personajes y enemigos
		Jugador jugadores[] = new Jugador[num];
		Enemigo enemigos[] = new Enemigo[num];
		
		while(controlDif == false)
		{
			System.out.println("Introduzca la dificultad:");
			System.out.println("Facil: 1\nMedio: 2\nDificil: 3");
			int dificultad = lector.nextInt();
			strDificultad = dificultadToString(dificultad);
			System.out.println("");
			if(strDificultad != null)
			{
				controlDif = true;
			}
			if(controlDif == false)
			{
				System.out.println("VALOR DE DIFICULTAD NO VALIDO");
				System.out.println("");
			}
		}	
		crearPersonajes(num, strDificultad, tablero, jugadores, enemigos); //funcion para inicializar personajes
		tablero.mostrarTablero();//muestra el tablero inicial
		
		while(controlPartida == true)
		{
			turno(num, jugadores, enemigos);
			controlPartida = CheckPersonajes();
		}
	}

	private void turno(int num, Jugador jugadores[], Enemigo enemigos[])
	{
		Personaje oponente; //se declara como tipo personaje para que valga para ambos
		
		for(int i = 0; i < num; i++)
		{
			//turno player i
			tablero.moverse(jugadores[i]);
			oponente = tablero.puedeAtacar(jugadores[i]); //comprueba si ese jugador puede atacar
			if(oponente != null)
			{
				jugadores[i].makeDamage(oponente.getVida()); //si puede atacar ataca
				oponente = null; //se reseta la variable para el siguiente movimiento
			}
			
			//turno enemy i
			tablero.moverse(enemigos[i]);
			oponente = tablero.puedeAtacar(enemigos[i]); //comprueba si ese enemigo puede atacar
			if(oponente != null)
			{
				jugadores[i].makeDamage(oponente.getVida()); //si puede atacar ataca
				oponente = null; //se reseta la variable para el siguiente movimiento
			}
		}
	}

	public void crearPersonajes(int num, String dificultad, Tablero tablero, Jugador jugadores[], Enemigo enemigos[])
	{
		Scanner lectorCrear = new Scanner(System.in);
		
		//Crea jugadores
		for (int i = 0; i < num; i++)
		{
			System.out.println("Elige tu tipo de personaje:");
			System.out.println("Guerrero(por defecto): 1\nMago: 2\nArquero: 3");
			int tipo = lectorCrear.nextInt();
			System.out.println("");
			
			//hacerlo con control de errores para que ponga jugador(i+1) si se pone enter/espacio/etc
			System.out.println("Introudce el nombre de tu personaje:");
			String nombre = lectorCrear.next();
			System.out.println("");
			
			//hacer enum para el control de tipo
			if(tipo == 1)//guerrero
			{
				jugadores[i] = new Jugador(100, nombre, "espada", 0, 0, tablero);
			}else if(tipo == 2)//mago
			{
				jugadores[i] = new Jugador(125, nombre, "varita", 0, 0, tablero);
			}else if(tipo == 3)//arquero
			{
				jugadores[i] = new Jugador(75, nombre, "arco", 0, 0, tablero);
			}else//por defecto
			{
				jugadores[i] = new Jugador(100, nombre, "espada", 0, 0, tablero);
			}
        }
		
		//Crea enemigos
		for(int i = 0; i < num; i++)
		{
			enemigos[i].creaEnemigos(dificultad, tablero, i);
		}
		
		//tablero.setarraypersonajes();
		tablero.meterPersonajes();
	}
	
	//comprueba que aun queden enemigos o jugadores
	private boolean CheckPersonajes()
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	//probablemente esto se podria hacer con un enum
	private String dificultadToString(int dificultad) {
		if(dificultad == 1)
		{
			return "facil";
		}else if(dificultad == 2)
		{
			return "medio";
		}else if(dificultad == 3)
		{
			return "dificil";
		}
		return null;
	}
}
