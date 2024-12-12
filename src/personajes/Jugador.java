package personajes;

import Herramienta.Herramienta;

public class Jugador extends Personaje {
	
	private String tipo;
	private Herramienta arma;

	public Jugador(int vida, String nombre, String tipo, int x, int y)
	{
		
		super(vida, setArma(tipo), nombre, x, y);
		this.tipo = tipo;
	}
}
