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
	
<<<<<<< HEAD
	
=======
	@Override
	public void usarArma() { //TODO esta funcion no hace falta
		Herramienta nuevaArma=buscarArma();
		if(nuevaArma!= null) {
			System.out.println(getNombre()+"cambia de armapor"+nuevaArma.getNombre());
			this.arma=nuevaArma;
		}
		super.usarArma();//despues de cambiar
	}
	
	private Herramienta buscarArma() {
		if(this.arma.getDamage()<10) {
			 Herramienta nuevaArma = null;
			resetArma(nuevaArma);
		        return nuevaArma;
		}
		return null;
	
	}
>>>>>>> a110be02290d583cd2470c73ccffb3f82ef52955

	public void resetArma(Herramienta nuevaArma) {
	    if (nuevaArma != null) {
	        this.arma = nuevaArma;
	        System.out.println("El enemigo ha cambiado su arma a: " + nuevaArma.getNombre());
	    } else {
	        System.out.println("No se pudo cambiar a esa arma");
	    }
	}
	
}
