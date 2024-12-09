package personajes;

import Herramienta.Herramienta;

public class Enemigo extends Personaje{
	
	private String dificultad;

	public Enemigo(int vida,  Herramienta arma, String nombre, String dificultad)
	{
		super(vida, arma, nombre);
		this.dificultad = dificultad;
	}
}
