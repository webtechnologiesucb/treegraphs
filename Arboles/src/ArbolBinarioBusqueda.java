class Rama {
    int dato;
    Rama izq;
    Rama der;

    public Rama(int dato) {
        this.dato = dato;
        izq = null;
        der = null;
    }
}

public class ArbolBinarioBusqueda {
    private Rama raiz;

    public ArbolBinarioBusqueda() {
        raiz = null;
    }

    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Rama insertarRec(Rama raiz, int valor) {
        if (raiz == null) {
            return new Rama(valor);
        }

        if (valor < raiz.dato) {
            raiz.izq = insertarRec(raiz.izq, valor);
        } else if (valor > raiz.dato) {
            raiz.der = insertarRec(raiz.der, valor);
        }

        return raiz;
    }

    public void eliminar(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Rama eliminarRec(Rama raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.dato) {
            raiz.izq = eliminarRec(raiz.izq, valor);
        } else if (valor > raiz.dato) {
            raiz.der = eliminarRec(raiz.der, valor);
        } else {
            if (raiz.izq == null) {
                return raiz.der;
            } else if (raiz.der == null) {
                return raiz.izq;
            }
            raiz.dato = valorMinimo(raiz.der);
            raiz.der = eliminarRec(raiz.der, raiz.dato);
        }
        return raiz;
    }

    private int valorMinimo(Rama raiz) {
        int valorMin = raiz.dato;
        while (raiz.izq != null) {
            valorMin = raiz.izq.dato;
            raiz = raiz.izq;
        }
        return valorMin;
    }

    public boolean buscar(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Rama raiz, int valor) {
        if (raiz == null) {
            return false;
        }
        if (raiz.dato == valor) {
            return true;
        }
        if (valor < raiz.dato) {
            return buscarRec(raiz.izq, valor);
        }
        return buscarRec(raiz.der, valor);
    }

    public void inOrden() {
        inOrdenRec(raiz);
    }

    private void inOrdenRec(Rama raiz) {
        if (raiz != null) {
            inOrdenRec(raiz.izq);
            System.out.print(raiz.dato + " ");
            inOrdenRec(raiz.der);
        }
    }

    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();
        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Recorrido Inorden del árbol:");
        arbol.inOrden();

        int valorBuscado = 40;
        System.out.println("\n¿El valor " + valorBuscado + " está en el árbol? " + arbol.buscar(valorBuscado));

        arbol.eliminar(40);
        System.out.println("Recorrido Inorden después de eliminar el valor " + valorBuscado + ":");
        arbol.inOrden();
    }
}