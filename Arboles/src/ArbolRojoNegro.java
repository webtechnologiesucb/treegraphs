enum Color {
    ROJO,
    NEGRO
}
class NodoRojoNegro {
    int dato;
    Color color;
    NodoRojoNegro izq;
    NodoRojoNegro der;
    NodoRojoNegro padre;

    public NodoRojoNegro(int dato) {
        this.dato = dato;
        this.color = Color.ROJO; // Por defecto, un nodo nuevo es rojo
        this.izq = null;
        this.der = null;
        this.padre = null;
    }
}

public class ArbolRojoNegro {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private NodoRojoNegro raiz;

    public ArbolRojoNegro() {
        raiz = null;
    }

    // Método para insertar un valor en el árbol Rojo-Negro
    public void insertar(int valor) {
        raiz = insertarRec(raiz, valor);
        raiz.color = Color.NEGRO; // La raíz debe ser siempre negra
    }

    private NodoRojoNegro insertarRec(NodoRojoNegro nodo, int valor) {
        if (nodo == null) {
            return new NodoRojoNegro(valor);
        }

        if (valor < nodo.dato) {
            nodo.izq = insertarRec(nodo.izq, valor);
            nodo.izq.padre = nodo;
        } else if (valor > nodo.dato) {
            nodo.der = insertarRec(nodo.der, valor);
            nodo.der.padre = nodo;
        }

        // Restaurar las propiedades del árbol Rojo-Negro
        if (nodo.color == Color.ROJO && nodo.izq != null && nodo.izq.color == Color.ROJO) {
            if (nodo.padre != null && nodo.padre.izq == nodo) {
                NodoRojoNegro tio = nodo.padre.der;
                if (tio != null && tio.color == Color.ROJO) {
                    nodo.padre.color = Color.ROJO;
                    tio.color = Color.NEGRO;
                    nodo.color = Color.NEGRO;
                } else {
                    if (nodo.izq == nodo) {
                        nodo = rotacionDerecha(nodo.padre);
                    }
                    nodo.padre.color = Color.NEGRO;
                    nodo.padre.der.color = Color.ROJO;
                    nodo = rotacionIzquierda(nodo.padre.padre);
                }
            } else {
                if (nodo.padre != null) {
                    NodoRojoNegro tio = nodo.padre.izq;
                    if (tio != null && tio.color == Color.ROJO) {
                        nodo.padre.color = Color.ROJO;
                        tio.color = Color.NEGRO;
                        nodo.color = Color.NEGRO;
                    } else {
                        if (nodo.der == nodo) {
                            nodo = rotacionIzquierda(nodo.padre);
                        }
                        nodo.padre.color = Color.ROJO;
                        nodo.padre.izq.color = Color.NEGRO;
                        nodo = rotacionDerecha(nodo.padre.padre);
                    }
                }
            }
        }

        return nodo;
    }

    private NodoRojoNegro rotacionIzquierda(NodoRojoNegro x) {
        NodoRojoNegro y = x.der;
        x.der = y.izq;
        if (y.izq != null) {
            y.izq.padre = x;
        }
        y.padre = x.padre;
        if (x.padre == null) {
            raiz = y;
        } else if (x == x.padre.izq) {
            x.padre.izq = y;
        } else {
            x.padre.der = y;
        }
        y.izq = x;
        x.padre = y;
        return y;
    }

    private NodoRojoNegro rotacionDerecha(NodoRojoNegro y) {
        NodoRojoNegro x = y.izq;
        y.izq = x.der;
        if (x.der != null) {
            x.der.padre = y;
        }
        x.padre = y.padre;
        if (y.padre == null) {
            raiz = x;
        } else if (y == y.padre.izq) {
            y.padre.izq = x;
        } else {
            y.padre.der = x;
        }
        x.der = y;
        y.padre = x;
        return x;
    }

    // Método para realizar un recorrido inorden del árbol Rojo-Negro
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(NodoRojoNegro nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izq);
            if (nodo.color == Color.NEGRO) {
                System.out.print(ANSI_BLACK +"(N)" + nodo.dato + " " + ANSI_RESET);
            } else {
                System.out.print(ANSI_RED + "(R)" + nodo.dato + " " + ANSI_RESET);
            }
            inOrdenRec(nodo.der);
        }
    }

    public static void main(String[] args) {
        ArbolRojoNegro arbol = new ArbolRojoNegro();

        arbol.insertar(50);
        arbol.insertar(30);
        arbol.insertar(70);
        arbol.insertar(20);
        arbol.insertar(40);
        arbol.insertar(60);
        arbol.insertar(80);

        System.out.println("Recorrido Inorden del árbol Rojo-Negro:");
        arbol.inOrden();
    }
}