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
		if(vida < 0)
		{
			Die();
		}
	}
	
	public void CheckDeath()
	{
		if(vida < 0)
		{
			Die();
		}
	}
	
	public void Die()
	{
		//tablero.setValorPos(0); //poner metodo de cambiar valores en tablero
		System.out.println("El jugador " + nombre + " ha muerto");	
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
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	//Metodos para arma
	 public static Herramienta setArma(String tipo) {
		 //hacer condiciones para un arma segun tipo de player   
		 //return armaSet;
	 }
	 
	 public static Herramienta setArma(int tipo)
	 {
		 //hacer condiciones para un arma segun tipo de player   
		 //return armaSet; 
	 }
	 
	 public void usarArma() {
	        if (arma != null) {
	            arma.usar();
	        } else {
	            System.out.println("no se ha equipado un arma");
	        }
	    }
}
