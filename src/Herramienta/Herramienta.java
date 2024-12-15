package Herramienta;

public class Herramienta {
    private String nombre;
    private int bonus;
    private String tipo; //corta, larga, mágica
    private int tipoEntero; //1=corta, 2=mágica, 3=larga

    public Herramienta(String nombre, int bonus, String tipo, int tipoEntero) {
        this.nombre = nombre;
        this.bonus = bonus;
        this.tipo = tipo;
        this.tipoEntero = tipoEntero;
    }

    // Generar un arma desde un tipo de jugador
    public static Herramienta generarArmaPorTipo(String tipo) {
        switch (tipo.toLowerCase()) {
            case "guerrero":
                return new Herramienta("Espada", 10, "corta", 1);
            case "mago":
                return new Herramienta("Varita", 8, "mágica", 2);
            case "arquero":
                return new Herramienta("Arco", 5, "larga", 3);
            default:
                return new Herramienta("Arma desconocida", 0, "ninguno", 0);
        }
    }

    @Override
    public String toString() {
        return "Herramienta{" +
               "nombre='" + nombre + '\'' +
               ", bonus=" + bonus +
               ", tipo='" + tipo + '\'' +
               ", tipoEntero=" + tipoEntero +
               '}';
    }


    public String getNombre() {
        return nombre;
    }

    public int getBonus() {
        return bonus;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTipoEntero() {
        return tipoEntero;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipoEntero(int tipoEntero) {
        this.tipoEntero = tipoEntero;
    }

   //devuelve el daño 
    public int getDamage() {
        return this.bonus; //el daño es igual al bonus 
    }

    //devuelve la distancia de ataque basada en el tipo de herramienta
    public int getDistanciaAtaque() {
        //la distancia depende de la herramienta 
        switch (this.tipo) {
            case "corta": 
                return 1; //distancia corta devuelve un 1
            case "larga": 
                return 3; //distancia larga devuelve un 3
            case "magica": 
                return 2; //distancia media(magica) devuelve un 2
            default: 
                return 0;
        }
    }

}
