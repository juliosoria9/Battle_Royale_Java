package personajes;

import java.util.Scanner;

import Herramienta.Herramienta;
import partida.Tablero;

public class Jugador extends Personaje {
	
	private Herramienta arma;

	public Jugador(int vida, String nombre, Herramienta armaInicial, Tablero tablero) {
		super(vida, armaInicial, nombre, tablero);
		this.arma = armaInicial; //asignar el arma directamente
	}
}
