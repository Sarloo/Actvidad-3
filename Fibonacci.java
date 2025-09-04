public class Fibonacci {
    
    /**
     * Método recursivo para calcular el n-ésimo número de Fibonacci
     * @param n posición en la serie de Fibonacci (debe ser >= 0)
     * @return el n-ésimo número de Fibonacci
     */
    public static int fibonacci(int n) {
        // Caso base: si n es 0, retorna 0
        if (n == 0) {
            return 0;
        }
        // Caso base: si n es 1, retorna 1
        else if (n == 1) {
            return 1;
        }
        // Caso recursivo: fibonacci(n) = fibonacci(n-1) + fibonacci(n-2)
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }
    
    public static void main(String[] args) {
        // Prueba del método fibonacci
        int n = 10;
        System.out.println("Fibonacci(" + n + ") = " + fibonacci(n));
    }
}