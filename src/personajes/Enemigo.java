package personajes;

import armas.Arma;

public class Enemigo extends Personaje{
	
	private String dificultad;

	public Enemigo(int vida, Arma arma, String nombre, String dificultad)
	{
		super(vida, arma, nombre);
		this.dificultad = dificultad;
	}
}
