package personajes;

import Herramienta.Herramienta;

public abstract class Personaje {

	protected int vida = 0;
	protected Herramienta arma;
	protected String nombre;
	
	public Personaje (int vida, Herramienta arma, String nombre)
	{
		this.vida = vida;
		this.arma = arma;
		this.nombre = nombre;
	}
	
	 public int makeDamage(int enemyVida) {
	        //aplica  el daño usando el bonus de la herramienta equipada
	        if (arma != null) {
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
