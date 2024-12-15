package personajes;

import Herramienta.Herramienta;
import Herramienta.menuHerramientas;
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
	//TODO se le pasa 
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
		System.out.println("El jugador " + nombre + " ha muerto");	
		tablero.asignarValor(x, y, 0);
	}
	
	//Getters de variables locales 
	public Herramienta getarma() {
		return this.arma;
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
	
	//Metodos para arma
		//eleccion de arma al inicio de partida para el jugador 
	public void setArmaDesdeMenu(String rutaArchivo) {
	    Herramienta nuevaArma = menuHerramientas.seleccionarHerramienta(rutaArchivo);
	    if (nuevaArma != null) {
	        this.arma = nuevaArma;
	        System.out.println(nombre + " ha equipado: " + nuevaArma);
	    } else {
	        System.out.println("No se pudo asignar un arma.");
	    }
	}

	
	//TODO aqui hay que cambiarlo todo no es eficiente se deve hacer en el constructor de forma que el arma se mete en el main a la hora de crear los personajes
	//da igual que sean players o enemigos se crean de la misma manera lo unico que los enemigos (bots) tenemos que gestionar nosotros el tema de los inputs
	
	
	// Getter para el arma
    public Herramienta getArma() {
        return arma;
    }

    @Override
    public String toString() {
        return "Personaje{" +
               "nombre='" + nombre + '\'' +
               ", vida=" + vida +
               ", arma=" + arma +
               '}';
    }
	 public void usarArma() {
	        if (arma != null) {
	            //arma.usar(); //TODO es el personaje o enemigo el que deve usar el arma contra otro el arma no se puede usar a si misma de forma que cuando se ataque se coja el daño del arma
	        					// y se quite vida al enemigo seleccionado
	        } else {
	            System.out.println("no se ha equipado un arma");
	        }
	    }
}
