
package Herramienta;

import personajes.Personaje;
import java.util.Scanner;

public class menuHerramientas {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

       
        
        Herramienta herramientaSeleccionada = null;

        System.out.println("=== Menú de Herramientas ===");
        System.out.println("1. Espada");
        System.out.println("2. Arco");
        System.out.println("3. Varita");
        System.out.print("Selecciona una herramienta para equipar al personaje: ");
        int opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                herramientaSeleccionada = new Espada("Espada", 20);
                break;
            case 2:
                herramientaSeleccionada = new Arco("Arco", 15);
                break;
            case 3:
                herramientaSeleccionada = new Varita("Varita", 25);
                break;
            default:
                System.out.println("Opción inválida.");
                break;
        }
    }
}
