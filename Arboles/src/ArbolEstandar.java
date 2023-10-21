class NodoTrie {
    NodoTrie[] hijos = new NodoTrie[26]; // Suponemos caracteres en minúsculas (a-z)
    boolean esFinalPalabra;

    NodoTrie() {
        esFinalPalabra = false;
        for (int i = 0; i < 26; i++) {
            hijos[i] = null;
        }
    }
}

class Trie {
    NodoTrie raiz;

    Trie() {
        raiz = new NodoTrie();
    }

    // Insertar una palabra en el Trie
    void insertar(String palabra) {
        NodoTrie nodo = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            int indice = palabra.charAt(i) - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new NodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esFinalPalabra = true;
    }

    // Buscar si una palabra está en el Trie
    boolean buscar(String palabra) {
        NodoTrie nodo = raiz;
        for (int i = 0; i < palabra.length(); i++) {
            int indice = palabra.charAt(i) - 'a';
            if (nodo.hijos[indice] == null) {
                return false;
            }
            nodo = nodo.hijos[indice];
        }
        return nodo != null && nodo.esFinalPalabra;
    }

    // Eliminar una palabra del Trie
    void eliminar(String palabra) {
        eliminarRec(raiz, palabra, 0);
    }

    private boolean eliminarRec(NodoTrie nodo, String palabra, int profundidad) {
        if (nodo == null) {
            return false;
        }
        if (profundidad == palabra.length()) {
            if (nodo.esFinalPalabra) {
                nodo.esFinalPalabra = false;
                return estaVacio(nodo);
            }
            return false;
        }
        int indice = palabra.charAt(profundidad) - 'a';
        if (eliminarRec(nodo.hijos[indice], palabra, profundidad + 1)) {
            nodo.hijos[indice] = null;
            return !nodo.esFinalPalabra && estaVacio(nodo);
        }
        return false;
    }

    private boolean estaVacio(NodoTrie nodo) {
        for (int i = 0; i < 26; i++) {
            if (nodo.hijos[i] != null) {
                return false;
            }
        }
        return true;
    }
}

public class ArbolEstandar {
    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insertar("manzana");
        trie.insertar("mandarina");
        trie.insertar("pera");

        System.out.println(trie.buscar("manzana")); // true
        System.out.println(trie.buscar("naranja")); // false
        trie.eliminar("manzana");
        System.out.println(trie.buscar("manzana")); // false
    }
}
