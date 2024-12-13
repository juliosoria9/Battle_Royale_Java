package partida;

import java.util.Scanner;

import personajes.*;

public class Partida {

	public void inicio()
	{
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero(10, 10); //pasarle las medidas del tablero
		boolean controlDif = false;
		boolean controlNum = false;
		String strDificultad = "\0";
		int num = 0;
		
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
		crearPersonajes(num, strDificultad, tablero); //funcion para inicializar personajes
		tablero.mostrarTablero();//muestra el tablero inicial
	}

	public void crearPersonajes(int num, String dificultad, Tablero tablero)
	{
		Scanner lectorCrear = new Scanner(System.in);
		
		Jugador jugadores[] = new Jugador[num];
		Enemigo enemigos[] = new Enemigo[num];
		
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
				jugadores[i] = new Jugador(100, nombre, "varita", 0, 0, tablero);
			}else if(tipo == 3)//arquero
			{
				jugadores[i] = new Jugador(100, nombre, "arco", 0, 0, tablero);
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
