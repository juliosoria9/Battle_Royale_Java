package personajes;

import Herramienta.Herramienta;

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
	
	//funciones de daño (enemy aplica a cualquier ente del bando contrario)
	public int makeDamage(int enemyVida)
	{
		//enemyVida -= Herramienta.getDamage(); //falta hacer los metodos de arma
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
}
