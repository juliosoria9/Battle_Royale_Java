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
}
