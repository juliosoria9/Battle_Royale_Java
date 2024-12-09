package Herramienta;

import personajes.Personaje;

class Espada extends Herramienta {
    public Espada() {
        super("Espada", 5);
    }

    @Override
    public void usar(Personaje personaje) {
        //personaje.incrementarAtaque(bonus);
        System.out.println(personaje.getNombre() + " equipa una espada, el ataque aumenta 5!!!");
        System.out.println(personaje.getNombre()+" hace " + personaje.makeDamage(bonus) +"daño" );
    }
}
