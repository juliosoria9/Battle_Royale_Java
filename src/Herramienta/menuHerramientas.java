package Herramienta;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class menuHerramientas {

    public static Herramienta seleccionarHerramienta(String rutaArchivo) {
        //cargar las herramientas desde el archivo
        ArrayList<Herramienta> herramientas = cargarHerramientasDesdeArchivo(rutaArchivo);

        if (herramientas.isEmpty()) {
            System.out.println("No se encontraron herramientas en el archivo");
            return null;
        }

        //menú de herramientas se imprime desde el archivo 
        System.out.println("=== Menú de Herramientas ===");
        for (int i = 0; i < herramientas.size(); i++) {
            System.out.println((i + 1) + ". " + herramientas.get(i));
        }

        //seleccionar herramienta
        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecciona una herramienta: ");
        int opcion = scanner.nextInt();//se guarda la herramienta seleccionada 

        //verificar si es valido
        if (opcion < 1 || opcion > herramientas.size()) {
            System.out.println("Opción inválida.");
            return null;
        } else {
            return herramientas.get(opcion - 1); //devuelve el herramienta  selecionada 
        }
    }

    private static ArrayList<Herramienta> cargarHerramientasDesdeArchivo(String rutaArchivo) {
        ArrayList<Herramienta> herramientas = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileReader(rutaArchivo))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();

                //lee las partes de la linea que estan separadas por espacios 
                Scanner lineaScanner = new Scanner(linea);
                String nombre = "";
                int bonus = 0;
                String tipo = "";

                if (lineaScanner.hasNext()) {
                    nombre = lineaScanner.next();
                    //se guarda el nombre de la herramienta seleccionada 
                }
                if (lineaScanner.hasNextInt()) {
                    bonus = lineaScanner.nextInt();
                }
                if (lineaScanner.hasNext()) {
                    tipo = lineaScanner.next();
                }

                //se crea y se añade 
                herramientas.add(new Herramienta(nombre, bonus, tipo));
                lineaScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de herramientas: " + e.getMessage());
        }

        return herramientas;
    }
    
    public static int calcularDaño(Herramienta herramienta) {
        if (herramienta == null || herramienta.getTipo() == null) {
            System.out.println("No se ha equipado una herramienta válida.");
            return 0;
        }

        int dañoBase = 10; //daño base por defecto
        String tipo = herramienta.getTipo();

        if (tipo.equals("larga")) {
            dañoBase += 5; //armas largas añaden 5
        } else if (tipo.equals("corta")) {
            dañoBase += 7; //armas cortas añaden 7
        } else {
            System.out.println("Tipo de herramienta desconocido");
        }

        System.out.println("El daño calculado usando " + herramienta.getNombre() + " es: " + dañoBase);
        return dañoBase;
    }

    
}