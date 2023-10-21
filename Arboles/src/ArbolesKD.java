
class Punto {
    double x;
    double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class NodoKD {
    Punto punto;
    NodoKD izquierdo;
    NodoKD derecho;

    public NodoKD(Punto punto) {
        this.punto = punto;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolKD {
    NodoKD raiz;

    public ArbolKD() {
        raiz = null;
    }

    public void insertar(Punto punto) {
        raiz = insertarRec(raiz, punto, 0);
    }

    private NodoKD insertarRec(NodoKD nodo, Punto punto, int nivel) {
        if (nodo == null) {
            return new NodoKD(punto);
        }

        int dimension = nivel % 2; // Alternar entre dimensiones x e y (k = 2)

        if (dimension == 0) {
            if (punto.x < nodo.punto.x) {
                nodo.izquierdo = insertarRec(nodo.izquierdo, punto, nivel + 1);
            } else {
                nodo.derecho = insertarRec(nodo.derecho, punto, nivel + 1);
            }
        } else {
            if (punto.y < nodo.punto.y) {
                nodo.izquierdo = insertarRec(nodo.izquierdo, punto, nivel + 1);
            } else {
                nodo.derecho = insertarRec(nodo.derecho, punto, nivel + 1);
            }
        }

        return nodo;
    }

    public boolean buscar(Punto punto) {
        return buscarRec(raiz, punto, 0);
    }

    private boolean buscarRec(NodoKD nodo, Punto punto, int nivel) {
        if (nodo == null) {
            return false;
        }
        if (nodo.punto.x == punto.x && nodo.punto.y == punto.y) {
            return true;
        }

        int dimension = nivel % 2; // Alternar entre dimensiones x e y (k = 2)

        if (dimension == 0) {
            if (punto.x < nodo.punto.x) {
                return buscarRec(nodo.izquierdo, punto, nivel + 1);
            } else {
                return buscarRec(nodo.derecho, punto, nivel + 1);
            }
        } else {
            if (punto.y < nodo.punto.y) {
                return buscarRec(nodo.izquierdo, punto, nivel + 1);
            } else {
                return buscarRec(nodo.derecho, punto, nivel + 1);
            }
        }
    }
}

public class ArbolesKD {
    public static void main(String[] args) {
        ArbolKD arbolKD = new ArbolKD();
        arbolKD.insertar(new Punto(2, 3));
        arbolKD.insertar(new Punto(5, 4));
        arbolKD.insertar(new Punto(9, 6));
        arbolKD.insertar(new Punto(4, 7));

        Punto puntoABuscar = new Punto(5, 4);
        boolean encontrado = arbolKD.buscar(puntoABuscar);
        System.out.println("El punto " + puntoABuscar.x + ", " + puntoABuscar.y + " se encuentra en el árbol KD: " + encontrado);
    }
}
