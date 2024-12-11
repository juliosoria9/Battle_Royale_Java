package Herramienta;

public class Herramienta {
    private String nombre;
    private int bonus;
    private String tipo;

    // Constructor
    public Herramienta(String nombre, int bonus, String tipo) {
        this.nombre = nombre;
        this.bonus = bonus;
        this.tipo = tipo;
    }

    //método que simula usar la herramienta
    public void usar() {
        System.out.println("Usando la herramienta: " + nombre + " (Tipo: " + tipo + ", Bonus: " + bonus + ")");
    }

    //método para obtener el daño que produce la herramienta
    public int getDamage() {
        //en este caso, el daño es igual al bonus
        return bonus;
    }

    @Override
    public String toString() {
        return nombre + " (Bonus: " + bonus + ", Tipo: " + tipo + ")";
    }
    
    // Getters y setters
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
