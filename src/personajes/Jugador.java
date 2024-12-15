package personajes;

import java.util.Scanner;

import Herramienta.Herramienta;
import Herramienta.menuHerramientas;
import partida.Tablero;

public class Jugador extends Personaje {
	
	private Herramienta arma;

	public Jugador(int vida, String nombre, String tipo, int x, int y, Tablero tablero)
	{
		
		super(vida, setArma(tipo), nombre, tablero);
		this.tipo = tipo;
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
	
	//TODO la funcion de abajo no es dinamica hay que quitarla o adaptarla para que funcione objetos ya creados
	/*
	public static Herramienta setArma(String tipo) {
		switch(tipo) {
		case "guerrero":
			return new Herramienta("Espada",10,"corta",1);
		case "mago":
			return new Herramienta("Varita",8,"corta",2);
		case "arquero":
			return new Herramienta("Arco",5,"larga",3);
		default:
			return new Herramienta("No se ha equipado arma",0,"",0);
		}
	}*/
}
