package personajes;

import java.util.Scanner;

import Herramienta.Herramienta;
import Herramienta.menuHerramientas;
import partida.Tablero;

public class Jugador extends Personaje {
	
	private Herramienta arma;

	public Jugador(int vida, String nombre, Herramienta armaInicial, int x, int y, Tablero tablero) {
		super(vida, armaInicial, nombre, tablero);
		this.arma = armaInicial; //asignar el arma directamente
	}
	
	
	@Override
	
	public void usarArma() {
		Scanner lector = new Scanner(System.in);
		System.out.println("Quieres cambiar de arma?(Responde si o no)");
		String opcion =lector.next()
;
		if(opcion=="si") {
			Herramienta nuevaArma = menuHerramientas.seleccionarHerramienta("herramientas.txt");
			if(nuevaArma!= null) {
				setArma(nuevaArma);
				System.out.println("Se ha equipado:"+nuevaArma);
				
			}
		}
		
		super.usarArma();//

	}
	public void setArma(Herramienta nuevaArma) {
		this.arma=nuevaArma;
	}
	
}
