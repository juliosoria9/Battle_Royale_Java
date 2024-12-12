package partida;

import java.util.Scanner;

import personajes.*;

public class Partida {

	public void inicio()
	{
		Scanner lector = new Scanner(System.in);
		Tablero tablero = new Tablero(10, 10); //pasarle las medidas del tablero
		
		//PARTIDA
		System.out.println("----------------BIENVENIDO AL BATTLE ROYALE------------------");
		System.out.println("");
		
		//CREACION PERSONAJES
		int num = lector.nextInt();
		String dificultad = lector.next();
		
		crearPersonajes(num, dificultad, tablero); //funcion para inicializar personajes
	}
	
	public void crearPersonajes(int num, String dificultad, Tablero tablero)
	{
		Jugador jugadores[] = new Jugador[num];
		Enemigo enemigos[] = new Enemigo[num];
		
		for(int i = 0; i < num; i++)
		{
			enemigos[i].creaEnemigos(dificultad, tablero, i);
		}
	}
}
