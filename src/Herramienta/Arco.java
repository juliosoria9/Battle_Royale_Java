package Herramienta;

import personajes.Personaje;

class Arco extends Herramienta {
    public Arco() {
        super("Arco", 3);
    }

    @Override
    public void usar(Personaje personaje) {

        System.out.println(personaje.getNombre()+" equipa un arco, el ataque ha aumentado 3!!!" );
        System.out.println(personaje.getNombre()+" hace " + personaje.makeDamage(bonus) +"daño" );
    }
}