class NodoAVL {
    int dato;
    int altura;
    NodoAVL izq;
    NodoAVL der;

    public NodoAVL(int dato) {
        this.dato = dato;
        this.altura = 1; // Inicialmente, la altura de un nuevo nodo es 1
        this.izq = null;
        this.der = null;
    }
}

public class ArbolAVL {
    private NodoAVL raiz;

    public ArbolAVL() {
        raiz = null;
    }

    // Método auxiliar para obtener la altura de un nodo
    private int altura(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.altura;
    }

    // Método auxiliar para calcular el factor de equilibrio de un nodo
    private int factorEquilibrio(NodoAVL nodo) {
        if (nodo == null) {
            return 0;
        }
        return altura(nodo.izq) - altura(nodo.der);
    }

    // Método para rotación a la izquierda
    private NodoAVL rotacionIzquierda(NodoAVL y) {
        NodoAVL x = y.der;
        NodoAVL t2 = x.izq;
        // Realizar la rotación
        x.izq = y;
        y.der = t2;
        // Actualizar alturas
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        return x;
    }

    // Método para rotación a la derecha
    private NodoAVL rotacionDerecha(NodoAVL x) {
        NodoAVL y = x.izq;
        NodoAVL t2 = y.der;
        // Realizar la rotación
        y.der = x;
        x.izq = t2;
        // Actualizar alturas
        x.altura = Math.max(altura(x.izq), altura(x.der)) + 1;
        y.altura = Math.max(altura(y.izq), altura(y.der)) + 1;
        return y;
    }

    // Método para insertar un dato en el árbol AVL
    public void insertar(int dato) {
        raiz = insertarRec(raiz, dato);
    }

    // Método auxiliar para insertar un dato en el árbol AVL
    private NodoAVL insertarRec(NodoAVL nodo, int dato) {
        // Paso 1: Insertar el dato en el árbol de búsqueda binaria
        if (nodo == null) {
            return new NodoAVL(dato);
        }

        if (dato < nodo.dato) {
            nodo.izq = insertarRec(nodo.izq, dato);
        } else if (dato > nodo.dato) {
            nodo.der = insertarRec(nodo.der, dato);
        } else {
            // No se permiten datoes duplicados
            return nodo;
        }

        // Paso 2: Actualizar la altura del nodo actual
        nodo.altura = 1 + Math.max(altura(nodo.izq), altura(nodo.der));
        // Paso 3: Obtener el factor de equilibrio de este nodo
        int factorEquilibrio = factorEquilibrio(nodo);
        // Paso 4: Realizar rotaciones si el nodo no está balanceado
        // Rotación a la izquierda
        if (factorEquilibrio > 1 && dato < nodo.izq.dato) {
            return rotacionDerecha(nodo);
        }
        // Rotación a la derecha
        if (factorEquilibrio < -1 && dato > nodo.der.dato) {
            return rotacionIzquierda(nodo);
        }
        // Rotación doble izquierda-derecha
        if (factorEquilibrio > 1 && dato > nodo.izq.dato) {
            nodo.izq = rotacionIzquierda(nodo.izq);
            return rotacionDerecha(nodo);
        }
        // Rotación doble derecha-izquierda
        if (factorEquilibrio < -1 && dato < nodo.der.dato) {
            nodo.der = rotacionDerecha(nodo.der);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Método para realizar un recorrido inorden del árbol AVL
    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(NodoAVL nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            System.out.print(nodo.dato + " ");
            inOrdenRec(nodo.der);
        }
    }

    public static void main(String[] args) {
        ArbolAVL arbol = new ArbolAVL();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        System.out.println("Recorrido Inorden del árbol AVL:");
        arbol.inOrden();
    }
}
