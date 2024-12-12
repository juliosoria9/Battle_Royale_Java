package personajes;

import java.util.Random;
import partida.Tablero;

public class CreaEnemigos{

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
	
	//falta pasarles arma
	private Enemigo crearEnemigoFacil(Tablero tablero)
	{
		int vida 
		bool control = false;
		while(control != true)
		{
			int x
			int y
			control = checkPosition(); //funcion para ver si esta vacia la coordenada
		}
		int arma
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoFacil", x, y);
	}

	private Enemigo crearEnemigoMedio(Tablero tablero)
	{
		return new Enemigo (10, Enemigo.setArma(1), "enemigoFacil", 0, 0);
	}
	
	private Enemigo crearEnemigoDificil(Tablero tablero)
	{
		return new Enemigo (10, Enemigo.setArma(1), "enemigoFacil", 0, 0);
	}
}
