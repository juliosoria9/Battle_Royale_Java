package Herramienta;

import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Herramienta {
    private String nombre;
    private int daño;
    private int distancia_ataque;
    File archivo = new File("src/Herramienta/herramientas.txt");
    Scanner s;

    ArrayList<String> nombres = new ArrayList<String>();
    ArrayList<Integer> danios = new ArrayList<Integer>();
    ArrayList<Integer> distancias = new ArrayList<Integer>();
    
    
    public Herramienta(String nombre, int daño, int distancia_de_ataque) { //constructor si queremos hacer algo por defecto
        this.nombre = nombre;
        this.daño = daño;
        this.distancia_ataque = distancia_de_ataque;
    }
    public Herramienta() {
    	
    	
    	
    	try {
			leerdatos();
		} catch (IOException e) {  //llamamos a la funcion de leer datos 
			System.out.println("error al implementar datos del fichero arma");
		}
    	
    	
    	crear_arma();
    }
    public void leerdatos() throws IOException { //lee el archivo y guarda en los arrayList
    	String linea;
    	try {
    		s = new Scanner(archivo); // creamos el escaner del fichero
    		while(s.hasNextLine()) {
    			
    			//leemos cada linea quitamos posibles espacios y los metemos en elos arraylist
    			 linea = s.nextLine();
    		 	nombres.add(linea.trim());
    			 linea = s.nextLine();
    			 danios.add(Integer.parseInt(linea.trim()));
    			 linea = s.nextLine();
    			 distancias.add(Integer.parseInt(linea.trim()));
    		}
    		s.close();
    		
    		
    		
    	}catch (FileNotFoundException e) {
    		System.out.println("Intentando acceder al archivo en la ruta: " + archivo.getAbsolutePath()); // nos dice la ruta en la que falla
    	    System.out.println("Error: El archivo no se encontro verifica la ruta."); //mensaje de error
     }
    }
    public void mostrartxt() {
    	if (nombres.size() != danios.size() || nombres.size() != distancias.size()) {
            System.out.println("Error: Las listas no tienen el mismo tamaño."); // cada elemento tiene que tener su pareja en su otro array con el indice
            return;
        }

        // Imprimimos los encabezados
        System.out.println("Arma\t\tDaño\tAlcance");
        System.out.println("--------------------------------");

        // Iteramos sobre los elementos
        for (int i = 0; i < nombres.size(); i++) { // nos muestra las armas en forma de columna
            System.out.printf("%-10s\t%-5d\t%-5d\n", nombres.get(i), danios.get(i), distancias.get(i));
        }
    }
    public void crear_arma() { //Selenccionamos un numero aleatorio y nos mete en las stats los valores correspondientes de cada array
    	Random random = new Random();
    	 int numero = random.nextInt(nombres.size());
         this.nombre = nombres.get(numero);
         this.daño = danios.get(numero);
         this.distancia_ataque = distancias.get(numero);
    }
    
    
    
    
    
    
    
    
    public String toString() { //metodo toString
        return ("     arma:\n nombre: "+nombre +"\n el daño es: "+ daño +"\n la distancia de ataque es: "+ distancia_ataque);
    }

    
     //getters no hay setters ya que el arma no deve poder cambiarse
    public String getNombre() {
        return nombre;
    }

    public int getdaño() {
        return daño;
    }

    public int getdistancia_de_ataque() {
    	return distancia_ataque;
    }


}
