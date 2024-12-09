package Herramienta;

import personajes.Personaje;
class Varita extends Herramienta {
    public Varita() {
        super("Varita", 7);
    }

    @Override
    public void usar(Personaje personaje) {
       // personaje.ataque += bonus;
        System.out.println(personaje.getNombre()+" equipa un arco, el ataque ha aumentado 7!!!" );
        System.out.println(personaje.getNombre()+" hace " + personaje.makeDamage(bonus) +"daño" );
    }
}