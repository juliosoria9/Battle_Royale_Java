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
		//Tablero esta en protected , cambiar
		//tablero.asignarValor(getX(), getY(), 0);
		System.out.println("El enemigo " + getNombre() + " ha muerto");	
	}
	
	public Enemigo creaEnemigos (String dificultad, Tablero tablero, int index)
	{
		if(dificultad == "facil")
		{
			return crearEnemigoFacil(tablero, index);
			
		}else if(dificultad == "media")
		{
			return crearEnemigoMedio(tablero, index);
		}
		
		return crearEnemigoDificil(tablero, index);
	}
	
	//Crea enemigos en posiciones aleatorias 
	private Enemigo crearEnemigoFacil(Tablero tablero, int index)
	{
		int vida=50;
		int arma=1;
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoFacil" + (index+1), 0, 0, tablero);
	}

	private Enemigo crearEnemigoMedio(Tablero tablero, int index)
	{
		int vida=75;
		int arma=2;
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoMedio" + (index+1), 0, 0, tablero);
	}
	
	private Enemigo crearEnemigoDificil(Tablero tablero, int index)
	{
		int vida =100;
		int arma=3;
		
		return new Enemigo (vida, Enemigo.setArma(arma), "enemigoDificil" + (index+1), 0, 0, tablero);
	}
}
