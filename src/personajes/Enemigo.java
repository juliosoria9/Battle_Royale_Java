package personajes;

import Herramienta.Herramienta;
import partida.Tablero;

public class Enemigo extends Personaje{
	
	public Enemigo(int vida, String nombre,Herramienta arma, Tablero tablero)
	{
		super(vida, arma, nombre, tablero);
	}
	
	@Override
	public void Die()
	{
		
		tablero.asignarValor(getX(), getY(), 0);
		System.out.println("El enemigo " + getNombre() + " ha muerto");	
	}
	
	

	public void resetArma(Herramienta nuevaArma) {
	    if (nuevaArma != null) {
	        this.arma = nuevaArma;
	        System.out.println("El enemigo ha cambiado su arma a: " + nuevaArma.getNombre());
	    } else {
	        System.out.println("No se pudo cambiar a esa arma");
	    }
	}
	
}
