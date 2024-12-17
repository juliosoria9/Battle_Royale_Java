package personajes;

import Herramienta.Herramienta;
import partida.Tablero;

public abstract class Personaje {

	private int vida = 0;
	protected Herramienta arma;//para que la clase arma pueda acceder
	private String nombre;
	private int x = 0;
	private int y = 0;
	protected Tablero tablero; //para poder hacer el override de Die() en enemigos
	
	public Personaje (int vida, Herramienta arma, String nombre, Tablero tablero)
	{
		this.vida = vida;
		this.arma = arma;
		this.nombre = nombre;
		this.tablero = tablero;
	}
	
	
	public void takeDamage(int enemyDamage) { // aqui se hace daño se le pasa el daño del arma del enemigo de forma que en el main es p1.takeDamage(p2.getarma().getdaño());
	 vida -= enemyDamage;
    CheckDeath();
	}

	public void CheckDeath() {
    if (vida <= 0) {
        Die();
    	}
	}
	
	public void Die()
	{
		System.out.println("El jugador " + nombre + " ha muerto");	
		tablero.asignarValor(x, y, 0); // se pone un 0 que significa que ha muerto
	}
	
	//Getters de variables locales 
	public Herramienta getArma() {
		return this.arma;
	}
	
	public void setArma(Herramienta nuevaArma) {
		this.arma=nuevaArma;
	}
	
	public int getVida()
	{
		return vida;
	}
	
	public int setVida(int valor)
	{
		return valor;
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
	
	
   
    public String toString() {
        return "---------Personaje-----------\n "
        		+ "nombre: " +nombre
        		+ "\n vida: " + vida
        		+ "\n"+ "-------\n"+arma.toString();
        				
    }
}
    
