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
	
	
	/*//elegir  arma según el tipo (para enemigos)  usando un String
	public static Herramienta setArma(String tipo) {
	    Herramienta armaSet;
	    switch (tipo) { 
	        case "espada":
	            armaSet = new Herramienta("Espada", 10, "corta",1);
	            break;
	        case "arco":
	            armaSet = new Herramienta("Arco", 5, "larga",3);
	            break;
	        case "varita":
	            armaSet = new Herramienta("Varita", 8, "corta",2);
	            break;
	        default:
	            armaSet = new Herramienta("Arma desconocida", 0, "ninguno",0);
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
	            armaReset = new Herramienta("Espada", 10, "corta",1);
	            break;
	        case "larga":
	            armaReset = new Herramienta("Arco", 5, "larga",3);
	            break;
	        case "mágica":
	            armaReset = new Herramienta("Varita", 8, "corta",2);
	            break;
	        default:
	            armaReset = new Herramienta("Arma desconocida", 0, "ninguno",0);
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
	            armaReset = new Herramienta("Espada", 10, "corta",1);
	            break;
	        case 2: //tipo 2 - Arco
	            armaReset = new Herramienta("Arco", 5, "larga",3);
	            break;
	        case 3: //tipo 3 - Varita
	            armaReset = new Herramienta("Varita", 8, "corta",2);
	            break;
	        default: //no vale
	            armaReset = new Herramienta("Arma desconocida", 0, "ninguno",0);
	            System.out.println("Tipo de arma no reconocido");//se asigna predeterminadamente
	            break;
	    }
	    System.out.println("El arma seleccionada para el eenemigo:" + armaReset);
	    return armaReset;
	}
	 */
	 public void usarArma() {
	        if (arma != null) {
	            //arma.usar(); //TODO es el personaje o enemigo el que deve usar el arma contra otro el arma no se puede usar a si misma de forma que cuando se ataque se coja el daño del arma
	        					// y se quite vida al enemigo seleccionado
	        } else {
	            System.out.println("no se ha equipado un arma");
	        }
	    }
}
