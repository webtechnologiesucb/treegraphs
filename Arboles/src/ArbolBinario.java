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

    public void inOrden() {
        recorrerInOrden(raiz);
    }

    public void preOrden() {
        recorrerPreOrden(raiz);
    }

    public void postOrden() {
        recorrerPostOrden(raiz);
    }

    public void recorrerInOrden(Nodo aux) {
        if (aux != null) {
            recorrerInOrden(aux.izq);
            System.out.print(aux.dato + " ");
            recorrerInOrden(aux.der);
        }
    }

    public void recorrerPreOrden(Nodo aux) {
        if (aux != null) {
            System.out.print(aux.dato + " ");
            recorrerPreOrden(aux.izq);
            recorrerPreOrden(aux.der);
        }
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
    }
}


