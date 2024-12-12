package personajes;

import Herramienta.Herramienta;
import partida.Tablero;

public abstract class Personaje {

	protected int vida = 0;
	protected Herramienta arma;
	protected String nombre;
	protected int x = 0;
	protected int y = 0;
	
	
	public Personaje (int vida, Herramienta arma, String nombre, int x, int y)
	{
		this.vida = vida;
		this.arma = arma;
		this.nombre = nombre;
		this.x = x;
		this.y = y;
	}
	
	 public int makeDamage(int enemyVida) {
	        //aplica  el daño usando el bonus de la herramienta equipada
	        if (arma != null) {
	        	usarArma();
	            //reduce la vida del enemigo
	            enemyVida -= arma.getDamage();
	        }
	        return enemyVida;
	    }
	
	public void takeDamage(int enemyDamage) 
	{
		vida -= enemyDamage;
	}
	
	//Getters de variables locales (no esta arma porque se haria un getter a la clase arma)
	public int getVida()
	{
		return vida;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	 public void setArma(Herramienta arma) {
	        return;
	 }
	 
	 public void usarArma() {
	        if (arma != null) {
	            arma.usar();
	        } else {
	            System.out.println("no se ha equipado un arma");
	        }
	    }
}
