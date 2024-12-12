package Herramienta;
import Herramienta.Herramienta;
import personajes.Personaje;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class menuHerramientas {
    public static void main(String[] args) {
        //cargar las herramientas desde el archivo de texto
        ArrayList<Herramienta> herramientas = cargarHerramientasDesdeArchivo("herramientas.txt");

        if (herramientas.isEmpty()) {
            System.out.println("No se encontraron herramientas en el archivo.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        //Personaje personaje = new Personaje(100, null, "Héroe");

        //menú de herramientas
        System.out.println("=== Menú de Herramientas ===");
        for (int i = 0; i < herramientas.size(); i++) {
            System.out.println((i + 1) + ". " + herramientas.get(i));
        }

        //selección de la herramienta 
        System.out.print("Selecciona una herramienta para equipar al personaje: ");
        int opcion = scanner.nextInt();

        if (opcion < 1 || opcion > herramientas.size()) {
            System.out.println("Opción inválida.");
        } else {
            //equipar  la herramienta seleccionada al personaje
            Herramienta herramientaSeleccionada = herramientas.get(opcion - 1);
          //  personaje.setArma(herramientaSeleccionada);
          //  System.out.println("El personaje " + personaje.getNombre() + " ha equipado: " + herramientaSeleccionada);

            //usar la herramienta equipada
           // personaje.usarArma();
        }

        scanner.close();
    }

    //método para leer herramientas desde el archivo herremientas.txt
    private static ArrayList<Herramienta> cargarHerramientasDesdeArchivo(String rutaArchivo) {
        ArrayList<Herramienta> herramientas = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                //variables para cada las parte, se lee hasta encontrar un espacio
                Scanner lineaScanner = new Scanner(linea);
                String nombre = "";
                int bonus = 0;
                String tipo = "";

                if (lineaScanner.hasNext()) {
                    nombre = lineaScanner.next(); //la primera palabra que lee es : nombre
                }
                if (lineaScanner.hasNextInt()) {
                    bonus = lineaScanner.nextInt(); //la segunda que lee es : bonus como número
                }
                if (lineaScanner.hasNext()) {
                    tipo = lineaScanner.next(); //la tercera palabra que lee es : tipo e arma(larga o corta )
                }

                //crear y agregar la herramienta
                herramientas.add(new Herramienta(nombre, bonus, tipo));

                lineaScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de herramientas: " + e.getMessage());
        }

        return herramientas;
    }
}
