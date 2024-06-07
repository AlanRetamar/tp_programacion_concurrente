package test;

import java.util.Random;

import dato.BusquedaBinSec;


public class TestBusquedaBinSecConc extends Thread {

	private static int tam = 1000000; // Tamaño del array
	private int id; // ID del hilo
	private int objetivo; // El valor que se busca en el array
	private int resultado; // Resultado de la búsqueda
	
	// Array de ejemplo busqueda binaria secuencial
    private static int [] array1 = new int[tam]; 
    // Array de ejemplo busqueda binaria concurrente 
    private static int [] array2 = new int[tam]; 
	
    public TestBusquedaBinSecConc(int id, int objetivo) {
		super();
		this.id = id;
		this.objetivo = objetivo;
	}
    
    //Método que se ejecuta cuando el hilo comienza
    public void run() {
    	
    	//System.out.println("\nhola soy el hilo: " + id);
		   		
    	 this.resultado = BusquedaBinSec.busquedaBinaria(array2, objetivo);
    	
    
    }



	public static void main(String[] args) {
		
		
		double tiempoInicio, tiempoFinal;
		//objetivo a busca en busqueda binaria secuencial
		int obje = -1;
		//objetivo a busca en busqueda binaria concurrente
	    int obje2 = -1;
        
        //Random rand = new Random(System.nanoTime());
	    
	    //Se cargan los dos arrays hasta el tamaño determinado
        for (int i = 0; i < tam; i++) {
			 array1[i] = array2[i] = i; //rand.nextInt(0, 1000);
		}
        
        //Mostrar el array para verificar si está ordenado
        System.out.println("\nArray:\n");
        for (int i = 0; i < tam; i++) {
			System.out.print(" " + array1[i] + " ");
		}
        
        //Utiliza milisegundos
        tiempoInicio = System.currentTimeMillis();
        // Verificar si el array está ordenado
		if (estaOrdenado(array1)) {
			int resultado = -1;
		
            resultado = BusquedaBinSec.busquedaBinaria(array1, obje);
	        
            /*Se verifica si el resultado es -1 (lo que significa que el elemento no está presente)
            o imprime el índice donde se encontró el elemento*/
            if (resultado == -1) {
                System.out.println("\nElemento " + obje + " no presente en el array.");
            } else {
                System.out.println("\nElemento " + obje + " encontrado en el índice: " + resultado);
            }
        } else {
            System.out.println("\nEl array no está ordenado.");
        }
		
		tiempoFinal = System.currentTimeMillis();
		
        System.out.println("Duración Secuencial: " + (tiempoFinal - tiempoInicio)  + " ms");
        
        //Obtiene la maxima cantidad de procesos logicos posible
        int tamanioHilos = Runtime.getRuntime().availableProcessors();
        
        System.out.println("numero de hilos: " + tamanioHilos);
        
        tiempoInicio = System.currentTimeMillis();
        
        //Instancio el array de hilos
        TestBusquedaBinSecConc[] hilos = new TestBusquedaBinSecConc[tamanioHilos];

             
	        for (int i = 0; i < tamanioHilos; i++) {
	        	//Instancio cada uno de los hilos en simultaneo
				hilos[i] = new TestBusquedaBinSecConc(i,obje2);

			}
	        
	        for (int i = 0; i < tamanioHilos; i++) {
	        	hilos[i].start(); // Inicia los hilos en simultaneo
	        }
	        
	        for (int i = 0; i < tamanioHilos; i++) {
	        	try {
					hilos[i].join();  // Espera a que cada hilo termine
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
         
        
        
        // Verificar si el array está ordenado
        if(estaOrdenado(array2)) {
        	int resultado = -1;
			for (int i = 0; i < tamanioHilos; i++) {
				//Recorre los resultados de cada hilo
				if (hilos[i].resultado != -1) { // Asume que -1 indica no encontrado
				    resultado = hilos[i].resultado;
			        break; // Sale del bucle al encontrar el primer resultado exitoso
			    }
			}
            /*Se verifica si el resultado es -1 (lo que significa que el elemento no está presente)
            o imprime el índice donde se encontró el elemento*/
	        if (resultado == -1) {
	            System.out.println("\nElemento " + obje2 + " no presente en el array.");
	        } else {
	            System.out.println("\nElemento " + obje2 + " encontrado en el índice: " + resultado);
	        }
        } else {
            System.out.println("\nEl array no está ordenado.");
        }
                
        tiempoFinal = System.currentTimeMillis();
        System.out.println("Duración Concurrente: " + (tiempoFinal - tiempoInicio)  + " ms");
        
    }
	
	//Verifica si el array está ordenado de forma ascendente
    public static boolean estaOrdenado(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

}
