package personajes;

import Herramienta.Herramienta;

public class Enemigo extends Personaje{
	
	public Enemigo(int vida, Herramienta arma, String nombre, int x, int y)
	{
		super(vida, arma, nombre, x, y);
	}
	
	@Override
	public void Die()
	{
		//tablero.setValorPos(0); //poner metodo de cambiar valores en tablero
		System.out.println("El enemigo " + nombre + " ha muerto");	
	}
}
