package personajes;

import java.util.Random;
import partida.Tablero;

public class CreaEnemigos{
	
	private Random random = new Random();

	public Enemigo crearEnemigo(String dificultad, Tablero tablero)
	{
		if(dificultad == "facil")
		{
			return crearEnemigoFacil(tablero);
			
		}else if(dificultad == "media")
		{
			return crearEnemigoMedio(tablero);
		}
		
		return crearEnemigoDificil(tablero);
	}
	
	//Crea enemigos en posiciones aleatorias 
	private Enemigo crearEnemigoFacil(Tablero tablero)
	{
		int vida=50;
		int arma=1;
		
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoFacil", 0, 0, tablero);
	}

	private Enemigo crearEnemigoMedio(Tablero tablero)
	{
		int vida=75;
		int arma=2;
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoMedio", 0, 0, tablero);
	}
	
	private Enemigo crearEnemigoDificil(Tablero tablero)
	{
		int vida =100;
		int arma=3;
		
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoDificil", 0, 0, tablero);
	}
}
