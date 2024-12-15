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
	
	
	
	public void setArma(Herramienta nuevaArma) {
		this.arma=nuevaArma;
	}
	public void Die()
	{
		
		tablero.asignarValor(getX(), getY(), 0);
		System.out.println("El enemigo " + getNombre() + " ha muerto");	
	}
}
