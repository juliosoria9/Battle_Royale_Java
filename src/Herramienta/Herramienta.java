package Herramienta;

import personajes.Personaje;

 public abstract class Herramienta {
    protected String nombre;
    protected int bonus;

    public Herramienta(String nombre, int bonus) {
        this.nombre = nombre;
        this.bonus = bonus;
    }

    public abstract void usar(Personaje personaje);

    @Override
    public String toString() {
        return nombre + " (+Bonus: " + bonus + ")";
    }
}