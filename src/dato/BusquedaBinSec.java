package dato;

public class BusquedaBinSec {
	
    // Método de búsqueda binaria
    public static int busquedaBinaria(int[] arr, int objetivo) {
    	//Se asigna izquierda al principio del array
        int izquierda = 0;
      //Se asigna derecha al final del array
        int derecha = arr.length - 1;
        
        //En un bucle while, calcula el índice medio como la mitad del rango actual
        //Mientras la izquierda sea menor o llegue al final del array
        while (izquierda <= derecha) {
        	
        	//Busca el medio del array
            int medio = izquierda + (derecha - izquierda) / 2;
            
            // Verifica si el objetivo está en el medio
            if (arr[medio] == objetivo) {
                return medio;
            }
            
            // Si el objetivo es mayor, ignora la mitad izquierda
            if (arr[medio] < objetivo) {
                izquierda = medio + 1;
            } else {
                // Si el objetivo es menor, ignora la mitad derecha
                derecha = medio - 1;
            }
        }
        
        // Si llegamos aquí, el elemento no está presente
        return -1;
    }
    
}
