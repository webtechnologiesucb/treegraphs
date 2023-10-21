public class MinHeap {
    private int[] pila;
    private int longitud;
    private int capacidad;

    public MinHeap(int capacidad) {
        this.capacidad = capacidad;
        this.longitud = 0;
        this.pila = new int[capacidad];
    }

    private int padre(int i) {
        return (i - 1) / 2;
    }

    private int izquierdo(int i) {
        return 2 * i + 1;
    }

    private int derecho(int i) {
        return 2 * i + 2;
    }

    private boolean esHoja(int i) {
        return i >= (longitud / 2) && i <= longitud;
    }

    private void intercambiar(int a, int b) {
        int temp = pila[a];
        pila[a] = pila[b];
        pila[b] = temp;
    }

    public void insertar(int valor) {
        if (longitud >= capacidad) {
            throw new IllegalStateException("El heap está lleno");
        }

        longitud++;
        int i = longitud - 1;
        pila[i] = valor;

        while (i != 0 && pila[padre(i)] > pila[i]) {
            intercambiar(i, padre(i));
            i = padre(i);
        }
    }

    public void eliminarMin() {
        if (longitud <= 0) {
            return;
        }

        if (longitud == 1) {
            longitud--;
            return;
        }

        pila[0] = pila[longitud - 1];
        longitud--;
        heapify(0);
    }

    private void heapify(int i) {
        int izq = izquierdo(i);
        int der = derecho(i);
        int minimo = i;

        if (izq < longitud && pila[izq] < pila[minimo]) {
            minimo = izq;
        }

        if (der < longitud && pila[der] < pila[minimo]) {
            minimo = der;
        }

        if (minimo != i) {
            intercambiar(i, minimo);
            heapify(minimo);
        }
    }

    public void imprimirHeap() {
        for (int i = 0; i < longitud / 2; i++) {
            int izq = izquierdo(i);
            int der = derecho(i);
            System.out.print("Padre: " + pila[i] + " - ");
            if (izq < longitud) {
                System.out.print("Izquierdo: " + pila[izq] + " ");
            }
            if (der < longitud) {
                System.out.print("Derecho: " + pila[der]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MinHeap heap = new MinHeap(10);

        heap.insertar(4);
        heap.insertar(2);
        heap.insertar(6);
        heap.insertar(1);
        heap.insertar(3);
        heap.insertar(5);

        System.out.println("Heap construido:");
        heap.imprimirHeap();

        heap.eliminarMin();
        System.out.println("\nHeap después de eliminar el mínimo:");
        heap.imprimirHeap();
    }
}

