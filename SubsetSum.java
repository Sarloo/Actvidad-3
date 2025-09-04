import java.util.Arrays;

public class SubsetSum {
    
    /**
     * Método recursivo para verificar si existe un subconjunto que sume el valor objetivo
     * @param conjunto arreglo de enteros
     * @param n tamaño del subconjunto actual
     * @param suma objetivo a alcanzar
     * @return true si existe un subconjunto que sume el valor objetivo
     */
    public static boolean existeSubconjuntoSuma(int[] conjunto, int n, int suma) {
        // Caso base: si la suma es 0, se encontró un subconjunto válido
        if (suma == 0) {
            return true;
        }
        // Caso base: si no hay más elementos y la suma no es 0
        else if (n == 0) {
            return false;
        }
        // Si el último elemento es mayor que la suma, se excluye
        else if (conjunto[n - 1] > suma) {
            return existeSubconjuntoSuma(conjunto, n - 1, suma);
        }
        // Caso recursivo: probar incluyendo o excluyendo el último elemento
        else {
            return existeSubconjuntoSuma(conjunto, n - 1, suma) || 
                   existeSubconjuntoSuma(conjunto, n - 1, suma - conjunto[n - 1]);
        }
    }
    
    public static void main(String[] args) {
        // Prueba del algoritmo
        int[] conjunto = {3, 34, 4, 12, 5, 2};
        int sumaObjetivo = 9;
        boolean resultado = existeSubconjuntoSuma(conjunto, conjunto.length, sumaObjetivo);
        
        System.out.println("Conjunto: " + Arrays.toString(conjunto));
        System.out.println("¿Existe subconjunto que sume " + sumaObjetivo + "? " + resultado);
    }
}