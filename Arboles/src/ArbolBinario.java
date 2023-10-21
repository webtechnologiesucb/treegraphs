import java.util.Scanner;

class Nodo {
    int dato;
    Nodo izq;
    Nodo der;

    public Nodo(int valor) {
        this.dato = valor;
        this.izq = null;
        this.der = null;
    }
}

class Arbol {
    Nodo raiz;
    int cantidadNodos;
    int cantidadNiveles;

    public Arbol() {
        raiz = null;
        cantidadNodos = 0;
        cantidadNiveles = 0;
    }

    public int getCantidadNodos() {
        return cantidadNodos;
    }

    public int getCantidadNiveles() {
        return cantidadNiveles;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor, 1);
    }

    private Nodo insertarRec(Nodo raiz, int valor, int nivel) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            cantidadNodos++;
            cantidadNiveles = Math.max(cantidadNiveles, nivel);
            return raiz;
        }

        if (valor < raiz.dato) {
            raiz.izq = insertarRec(raiz.izq, valor, nivel + 1);
        } else if (valor > raiz.dato) {
            raiz.der = insertarRec(raiz.der, valor, nivel + 1);
        }
        return raiz;
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo nodo, int valor) {
        if (nodo == null) {
            return nodo;
        }

        if (valor < nodo.dato) {
            nodo.izq = eliminarRec(nodo.izq, valor);
        } else if (valor > nodo.dato) {
            nodo.der = eliminarRec(nodo.der, valor);
        } else {
            // Nodo con un solo hijo o sin hijos
            if (nodo.izq == null) {
                return nodo.der;
            } else if (nodo.der == null) {
                return nodo.izq;
            }
            // Nodo con dos hijos, encontrar el sucesor inorden
            nodo.dato = encontrarMinimoValor(nodo.der);
            // Eliminar el sucesor inorden
            nodo.der = eliminarRec(nodo.der, nodo.dato);
        }
        return nodo;
    }

    private int encontrarMinimoValor(Nodo nodo) {
        int minimoValor = nodo.dato;
        while (nodo.izq != null) {
            minimoValor = nodo.izq.dato;
            nodo = nodo.izq;
        }
        return minimoValor;
    }


    public void inOrden() {
        recorrerInOrden(raiz);
    }

    public void recorrerInOrden(Nodo aux) {
        if (aux != null) {
            recorrerInOrden(aux.izq);
            System.out.print(aux.dato + " ");
            recorrerInOrden(aux.der);
        }
    }

    public void preOrden() {
        recorrerPreOrden(raiz);
    }

    public void recorrerPreOrden(Nodo aux) {
        if (aux != null) {
            System.out.print(aux.dato + " ");
            recorrerPreOrden(aux.izq);
            recorrerPreOrden(aux.der);
        }
    }

    public void postOrden() {
        recorrerPostOrden(raiz);
    }

    public void recorrerPostOrden(Nodo aux) {
        if (aux != null) {
            recorrerPostOrden(aux.izq);
            recorrerPostOrden(aux.der);
            System.out.print(aux.dato + " ");
        }
    }
}

public class ArbolBinario {

    public static void main(String[] args) {
        Scanner oLeer = new Scanner(System.in);
        Arbol arbol = new Arbol();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);
        System.out.println("\nIn Orden");
        arbol.inOrden();
        System.out.println("\nPre Orden");
        arbol.preOrden();
        System.out.println("\nPost Orden");
        arbol.postOrden();
        System.out.println("\nCantidad de nodos: " + arbol.getCantidadNodos());
        System.out.println("Cantidad de niveles: " + arbol.getCantidadNiveles());
        int valorAEliminar = 30;
        arbol.eliminar(valorAEliminar);
        System.out.println("Recorrido Inorden del árbol después de eliminar el valor " + valorAEliminar + ":");
        arbol.inOrden();
    }
}


