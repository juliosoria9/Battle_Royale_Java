package Herramienta;

public class Herramienta {
    private String nombre;
    private int daño;
    private int distancia_ataque;

    //constructor
    public Herramienta(String nombre, int daño,int distancia_ataque) {
        this.nombre = nombre;
        this.daño = daño;
        this.distancia_ataque =  distancia_ataque;
        
    }
    public Herramienta() {
    	//TODO metodo por el que al crearse se asigna de forma automatica segun herramientas.txt
    }
    //TODO aqui hay que hacer la lectura del fichero herramientas.txt y guardar el daño el nombre y la distancia de ataque y se asigne de forma aleatoria 
    // de forma que si tenemos 3 array list (o arrays como querais) cojemos la posicion y en cada uno en esa posicion es donde esta el arma ejemplo cojo la posicion 5 de cada array para cojer 
    //	el daño nombre y alcance 
    
    
    
 /*   //metodo para usar la herramienta 
    public void usar() {
        System.out.println("se esta usando la arma: " + nombre + " (Tipo: " + tipo + ", Bonus: " + bonus + ")");
    }

    //este metodo devuelve el bonus que suma cada herramienta 
    public int getDamage() {
        return bonus;
    }

    @Override
    public String toString() {
        return nombre + " (Bonus: " + bonus + ", Tipo: " + tipo + ")";
    }
    
    //getters y setters
    public String getNombre() {
        return nombre;
    }
    public int getdistancia_ataque() {
    	return distancia_ataque;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }^
    */
}
