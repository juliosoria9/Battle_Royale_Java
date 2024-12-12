package personajes;

import Herramienta.Herramienta;
import partida.Tablero;

public class Jugador extends Personaje {
	
	private String tipo;
	private Herramienta arma;

	public Jugador(int vida, String nombre, String tipo, int x, int y, Tablero tablero)
	{
		
		super(vida, setArma(tipo), nombre, x, y, tablero);
		this.tipo = tipo;
	}
}
