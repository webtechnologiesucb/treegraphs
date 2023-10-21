class FenwickTree {
    int[] BIT;
    int n;

    public FenwickTree(int size) {
        n = size;
        BIT = new int[n + 1];
    }

    // Actualiza el valor en la posición index del arreglo original.
    public void actualizar(int index, int valor) {
        index++; // Ajuste para la indexación base 1
        while (index <= n) {
            BIT[index] += valor;
            index += index & -index; // Añade el bit menos significativo
        }
    }

    // Consulta la suma acumulativa de elementos desde el índice 0 hasta index del arreglo original.
    public int consulta(int index) {
        index++; // Ajuste para la indexación base 1
        int suma = 0;
        while (index > 0) {
            suma += BIT[index];
            index -= index & -index; // Resta el bit menos significativo
        }
        return suma;
    }
}

public class ArbolesFenwick {
    public static void main(String[] args) {
        int[] arreglo = {1, 2, 3, 4, 5, 6, 7, 8};
        int n = arreglo.length;

        FenwickTree fenwick = new FenwickTree(n);

        // Construir el Árbol de Fenwick con los elementos del arreglo
        for (int i = 0; i < n; i++) {
            fenwick.actualizar(i, arreglo[i]);
        }

        // Consultar la suma acumulativa de elementos en diferentes rangos
        int suma1 = fenwick.consulta(2); // Suma de elementos en el rango [0, 2]
        int suma2 = fenwick.consulta(5); // Suma de elementos en el rango [0, 5]
        int suma3 = fenwick.consulta(7); // Suma de elementos en el rango [0, 7]

        System.out.println("Suma 1: " + suma1); // Debe ser 6
        System.out.println("Suma 2: " + suma2); // Debe ser 21
        System.out.println("Suma 3: " + suma3); // Debe ser 36
    }
}
