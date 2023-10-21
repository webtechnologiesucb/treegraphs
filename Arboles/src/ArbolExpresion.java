class NodoExpresion {
    char operador;
    int dato;
    NodoExpresion izq;
    NodoExpresion der;

    public NodoExpresion(char operador) {
        this.operador = operador;
        this.dato = 0;
        this.izq = null;
        this.der = null;
    }

    public NodoExpresion(int dato) {
        this.operador = ' ';
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}

public class ArbolExpresion {
    private NodoExpresion raiz;

    public ArbolExpresion() {
        raiz = null;
    }

    public int evaluar() {
        return evaluarRec(raiz);
    }

    private int evaluarRec(NodoExpresion nodo) {
        if (nodo.operador == ' ') {
            return nodo.dato;
        }

        int izquierdo = evaluarRec(nodo.izq);
        int derecho = evaluarRec(nodo.der);

        switch (nodo.operador) {
            case '+':
                return izquierdo + derecho;
            case '-':
                return izquierdo - derecho;
            case '*':
                return izquierdo * derecho;
            case '/':
                return izquierdo / derecho;
            default:
                throw new IllegalArgumentException("Operador no válido");
        }
    }

    public static void main(String[] args) {
        ArbolExpresion arbol = new ArbolExpresion();

        // Construir el árbol de expresión para "3 + (4 * 2)"
        System.out.println("Expresion: 3 + (4 * 2)");
        arbol.raiz = new NodoExpresion('+');
        arbol.raiz.izq = new NodoExpresion(3);
        arbol.raiz.der = new NodoExpresion('*');
        arbol.raiz.der.izq = new NodoExpresion(4);
        arbol.raiz.der.der = new NodoExpresion(2);

        // Evaluar la expresión
        int resultado = arbol.evaluar();
        System.out.println("Resultado de la expresión: " + resultado);
    }
}
