package Herramienta;

public class Herramienta {
    private String nombre;
    private int bonus;
    private String tipo;

    //constructor
    public Herramienta(String nombre, int bonus, String tipo) {
        this.nombre = nombre;
        this.bonus = bonus;
        this.tipo = tipo;
    }

    //metodo para usar la herramienta 
    public void usar() {
        System.out.println("se esta usando la herramienta: " + nombre + " (Tipo: " + tipo + ", Bonus: " + bonus + ")");
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
