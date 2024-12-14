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
		
		tablero.asignarValor(getX(), getY(), 0);
		System.out.println("El enemigo " + getNombre() + " ha muerto");	
	}
	
	public Enemigo creaEnemigos (String dificultad, Tablero tablero, int index)
	{
		if(dificultad == "facil")
		{
			return crearEnemigoFacil(tablero, index);
			
		}else if(dificultad == "media")
		{
			return crearEnemigoMedio(tablero, index);
		}
		
		return crearEnemigoDificil(tablero, index);
	}
	
	//Crea enemigos en posiciones aleatorias 
	private Enemigo crearEnemigoFacil(Tablero tablero, int index)
	{
		int vida=50;
		int arma=1;
		
		return new Enemigo (vida, Enemigo.resetArma(arma), "enemigoFacil" + (index+1), 0, 0, tablero);
		
	}

	private Enemigo crearEnemigoMedio(Tablero tablero, int index)
	{
		int vida=75;
		int arma=2;
		
		return new Enemigo (vida, Enemigo.resetArma(arma), "enemigoMedio" + (index+1), 0, 0, tablero);
	}
	
	private Enemigo crearEnemigoDificil(Tablero tablero, int index)
	{
		int vida =100;
		int arma=3;
		
		return new Enemigo (vida, Enemigo.resetArma(arma), "enemigoDificil" + (index+1), 0, 0, tablero);
	}
	
	@Override
	public void usarArma() {
		Herramienta nuevaArma=buscarArma();
		if(nuevaArma!= null) {
			System.out.println(getNombre()+"cambia de armapor"+nuevaArma.getNombre());
			this.arma=nuevaArma;
		}
		super.usarArma();//despues de cambiar
	}
	
	private Herramienta buscarArma() {
		if(this.arma.getDamage()<10) {
				return resetArma(2);
		}
		return null;
	
	}
	public static Herramienta resetArma(int tipo) {
		Herramienta armaReset;
		switch(tipo) {//segun el tipo de enemigo
			case 1:
				armaReset= new Herramienta("Espada",10,"corta");
				break;
			case 2:
				armaReset= new Herramienta("Arco",5,"corta");
				break;
			case 3:
				armaReset= new Herramienta("Varita",8,"corta");
				break;
			default:
				armaReset=new Herramienta("No se ha equipado arma",0,"");
				break;
		}
		System.out.println("El arma que se ha equipado al enemigo es :"+armaReset);
		return armaReset;
	}
	
}
