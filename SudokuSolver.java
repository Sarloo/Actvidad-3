public class SudokuSolver {
    
    // Tamaño del tablero de Sudoku (4x4 para simplificar)
    private static final int TAMANIO = 4;
    
    /**
     * Método principal para resolver el Sudoku
     * @param tablero matriz que representa el tablero de Sudoku
     * @return true si se encontró una solución
     */
    public static boolean resolverSudoku(int[][] tablero) {
        // Buscar la primera celda vacía (representada por 0)
        int[] celdaVacia = encontrarCeldaVacia(tablero);
        int fila = celdaVacia[0];
        int columna = celdaVacia[1];
        
        // Si no hay celdas vacías, el Sudoku está resuelto
        if (fila == -1 && columna == -1) {
            return true;
        }
        
        // Probar números del 1 al 4 (para un Sudoku 4x4)
        return probarNumeros(tablero, fila, columna, 1);
    }
    
    /**
     * Método recursivo para probar números en una celda
     * @param tablero matriz del Sudoku
     * @param fila fila de la celda actual
     * @param columna columna de la celda actual
     * @param num número a probar (comienza en 1)
     * @return true si se encontró una solución con este número
     */
    private static boolean probarNumeros(int[][] tablero, int fila, int columna, int num) {
        // Caso base: si se probaron todos los números (1-4)
        if (num > TAMANIO) {
            return false;
        }
        
        // Verificar si el número es válido en esta posición
        if (esNumeroValido(tablero, fila, columna, num)) {
            // Colocar el número en el tablero
            tablero[fila][columna] = num;
            
            // Intentar resolver el resto del tablero recursivamente
            if (resolverSudoku(tablero)) {
                return true;
            }
            
            // Backtrack: si no se pudo resolver, deshacer el cambio
            tablero[fila][columna] = 0;
        }
        
        // Probar con el siguiente número
        return probarNumeros(tablero, fila, columna, num + 1);
    }
    
    /**
     * Encuentra la primera celda vacía en el tablero
     * @param tablero matriz del Sudoku
     * @return arreglo con [fila, columna] de la celda vacía, o [-1, -1] si no hay
     */
    private static int[] encontrarCeldaVacia(int[][] tablero) {
        return buscarCeldaRecursivo(tablero, 0, 0);
    }
    
    /**
     * Método recursivo para buscar celdas vacías
     */
    private static int[] buscarCeldaRecursivo(int[][] tablero, int fila, int columna) {
        // Si se recorrieron todas las filas, no hay celdas vacías
        if (fila >= TAMANIO) {
            return new int[]{-1, -1};
        }
        
        // Si se recorrieron todas las columnas de esta fila, pasar a la siguiente fila
        if (columna >= TAMANIO) {
            return buscarCeldaRecursivo(tablero, fila + 1, 0);
        }
        
        // Si la celda actual está vacía, retornar sus coordenadas
        if (tablero[fila][columna] == 0) {
            return new int[]{fila, columna};
        }
        
        // Pasar a la siguiente columna
        return buscarCeldaRecursivo(tablero, fila, columna + 1);
    }
    
    /**
     * Verifica si un número es válido en una posición dada
     */
    private static boolean esNumeroValido(int[][] tablero, int fila, int columna, int num) {
        // Verificar fila
        boolean validoFila = verificarFila(tablero, fila, num, 0);
        // Verificar columna
        boolean validoColumna = verificarColumna(tablero, columna, num, 0);
        // Verificar subcuadrícula 2x2
        boolean validoSubgrid = verificarSubgrid(tablero, fila - fila % 2, columna - columna % 2, num, 0, 0);
        
        return validoFila && validoColumna && validoSubgrid;
    }
    
    /**
     * Verifica si el número ya existe en la fila
     */
    private static boolean verificarFila(int[][] tablero, int fila, int num, int col) {
        if (col >= TAMANIO) return true;
        if (tablero[fila][col] == num) return false;
        return verificarFila(tablero, fila, num, col + 1);
    }
    
    /**
     * Verifica si el número ya existe en la columna
     */
    private static boolean verificarColumna(int[][] tablero, int columna, int num, int fila) {
        if (fila >= TAMANIO) return true;
        if (tablero[fila][columna] == num) return false;
        return verificarColumna(tablero, columna, num, fila + 1);
    }
    
    /**
     * Verifica si el número ya existe en la subcuadrícula 2x2
     */
    private static boolean verificarSubgrid(int[][] tablero, int startFila, int startCol, int num, int i, int j) {
        if (i >= 2) return true;
        if (j >= 2) return verificarSubgrid(tablero, startFila, startCol, num, i + 1, 0);
        if (tablero[startFila + i][startCol + j] == num) return false;
        return verificarSubgrid(tablero, startFila, startCol, num, i, j + 1);
    }
    
    /**
     * Método para imprimir el tablero de Sudoku
     */
    public static void imprimirTablero(int[][] tablero) {
        imprimirFila(tablero, 0);
    }
    
    private static void imprimirFila(int[][] tablero, int fila) {
        if (fila >= TAMANIO) return;
        imprimirColumna(tablero, fila, 0);
        System.out.println();
        imprimirFila(tablero, fila + 1);
    }
    
    private static void imprimirColumna(int[][] tablero, int fila, int col) {
        if (col >= TAMANIO) return;
        System.out.print(tablero[fila][col] + " ");
        imprimirColumna(tablero, fila, col + 1);
    }
    
    public static void main(String[] args) {
        // Tablero de Sudoku 4x4 (0 = celda vacía)
        int[][] tablero = {
            {1, 0, 0, 0},
            {0, 2, 0, 0},
            {0, 0, 3, 0},
            {0, 0, 0, 4}
        };
        
        System.out.println("Tablero inicial:");
        imprimirTablero(tablero);
        System.out.println();
        
        if (resolverSudoku(tablero)) {
            System.out.println("Solución encontrada:");
            imprimirTablero(tablero);
        } else {
            System.out.println("No se encontró solución");
        }
    }
}