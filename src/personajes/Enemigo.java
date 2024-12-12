package personajes;

import Herramienta.Herramienta;
import partida.Tablero;

public class Enemigo extends Personaje{
	
	public Enemigo(int vida, Herramienta arma, String nombre, int x, int y, Tablero tablero)
	{
		super(vida, arma, nombre, x, y, tablero);
	}
	
	@Override
	public void Die()
	{
		//tablero.setValorPos(0); //poner metodo de cambiar valores en tablero
		System.out.println("El enemigo " + getNombre() + " ha muerto");	
	}
	
	public Enemigo creaEnemigos (String dificultad, Tablero tablero)
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
