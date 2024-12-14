package personajes;

import Herramienta.Herramienta;
import Herramienta.menuHerramientas;
import partida.Tablero;

public abstract class Personaje {

	private int vida = 0;
	private Herramienta arma;
	private String nombre;
	private int x = 0;
	private int y = 0;
	protected Tablero tablero; //para poder hacer el override de Die() en enemigos
	
	public Personaje (int vida, Herramienta arma, String nombre, int x, int y, Tablero tablero)
	{
		this.vida = vida;
		this.arma = arma;
		this.nombre = nombre;
		this.x = x;
		this.y = y;
		this.tablero = tablero;
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
		System.out.println("El jugador " + nombre + " ha muerto");	
		tablero.asignarValor(x, y, 0);
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

	//elegir  arma según el tipo (para enemigos)  usando un String
	public static Herramienta setArma(String tipo) {
	    Herramienta armaSet;
	    switch (tipo) { 
	        case "espada":
	            armaSet = new Herramienta("Espada", 10, "corta");
	            break;
	        case "arco":
	            armaSet = new Herramienta("Arco", 5, "larga");
	            break;
	        case "varita":
	            armaSet = new Herramienta("Varita", 8, "corta");
	            break;
	        default:
	            armaSet = new Herramienta("Arma desconocida", 0, "ninguno");
	            System.out.println("Tipo de arma no reconocido");
	            break;
	    }
	    System.out.println("Arma seleccionada para enemigo: " + armaSet);
	    return armaSet;
	}


	 
	//cambio de arma al inicio de la partida (según tipo de jugador)
	public static Herramienta resetArma(String tipo) {
	    Herramienta armaReset;
	    switch (tipo) {
	        case "corta":
	            armaReset = new Herramienta("Espada", 10, "corta");
	            break;
	        case "larga":
	            armaReset = new Herramienta("Arco", 5, "larga");
	            break;
	        case "mágica":
	            armaReset = new Herramienta("Varita", 8, "corta");
	            break;
	        default:
	            armaReset = new Herramienta("Arma desconocida", 0, "ninguno");
	            break;
	    }
	    System.out.println("Arma seleccionada para jugador: " + armaReset);
	    return armaReset;
	}
	 
	// Cambia de arma directamente para enemigo
	public static Herramienta resetArma(int tipo) {
	    Herramienta armaReset;
	    switch (tipo) {
	        case 1: //tipo 1 - Espada
	            armaReset = new Herramienta("Espada", 10, "corta");
	            break;
	        case 2: //tipo 2 - Arco
	            armaReset = new Herramienta("Arco", 5, "larga");
	            break;
	        case 3: //tipo 3 - Varita
	            armaReset = new Herramienta("Varita", 8, "corta");
	            break;
	        default: //no vale
	            armaReset = new Herramienta("Arma desconocida", 0, "ninguno");
	            System.out.println("Tipo de arma no reconocido");//se asigna predeterminadamente
	            break;
	    }
	    System.out.println("El arma seleccionada para el eenemigo:" + armaReset);
	    return armaReset;
	}
	 
	 public void usarArma() {
	        if (arma != null) {
	            arma.usar();
	        } else {
	            System.out.println("no se ha equipado un arma");
	        }
	    }
}
