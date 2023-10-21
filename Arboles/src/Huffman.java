import java.util.PriorityQueue;
import java.util.HashMap;
import java.util.BitSet;
import java.util.Map;

class NodoHuffman implements Comparable<NodoHuffman> {
    char caracter;
    int frecuencia;
    NodoHuffman izq;
    NodoHuffman der;

    public NodoHuffman(char caracter, int frecuencia) {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    public int compareTo(NodoHuffman otro) {
        return this.frecuencia - otro.frecuencia;
    }
}

class ArbolHuffman {
    private NodoHuffman raiz;
    private Map<Character, String> tablaCodificacion;

    public ArbolHuffman(String texto) {
        construirArbol(texto);
        generarTablaCodificacion();
    }

    private void construirArbol(String texto) {
        // Calcular las frecuencias de caracteres en el texto
        Map<Character, Integer> frecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            frecuencias.put(c, frecuencias.getOrDefault(c, 0) + 1);
        }

        // Crear nodos de caracteres y agregarlos a la cola de prioridad
        PriorityQueue<NodoHuffman> colaPrioridad = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entrada : frecuencias.entrySet()) {
            colaPrioridad.add(new NodoHuffman(entrada.getKey(), entrada.getValue()));
        }

        // Construir el árbol de Huffman
        while (colaPrioridad.size() > 1) {
            NodoHuffman izquierdo = colaPrioridad.poll();
            NodoHuffman derecho = colaPrioridad.poll();
            NodoHuffman padre = new NodoHuffman('\0', izquierdo.frecuencia + derecho.frecuencia);
            padre.izq = izquierdo;
            padre.der = derecho;
            colaPrioridad.add(padre);
        }
        raiz = colaPrioridad.poll();
    }

    private void generarTablaCodificacion() {
        tablaCodificacion = new HashMap<>();
        generarTablaCodificacionRec(raiz, "");
    }

    private void generarTablaCodificacionRec(NodoHuffman nodo, String codigo) {
        if (nodo != null) {
            if (nodo.izq == null && nodo.der == null) {
                tablaCodificacion.put(nodo.caracter, codigo);
            }
            generarTablaCodificacionRec(nodo.izq, codigo + "0");
            generarTablaCodificacionRec(nodo.der, codigo + "1");
        }
    }

    public BitSet comprimir(String texto) {
        StringBuilder binario = new StringBuilder();
        for (char c : texto.toCharArray()) {
            binario.append(tablaCodificacion.get(c));
        }
        BitSet bits = new BitSet(binario.length());
        for (int i = 0; i < binario.length(); i++) {
            if (binario.charAt(i) == '1') {
                bits.set(i);
            }
        }
        return bits;
    }

    public String descomprimir(BitSet bits) {
        StringBuilder binario = new StringBuilder();
        for (int i = 0; i < bits.length(); i++) {
            binario.append(bits.get(i) ? '1' : '0');
        }
        StringBuilder texto = new StringBuilder();
        NodoHuffman actual = raiz;
        for (char bit : binario.toString().toCharArray()) {
            if (bit == '0') {
                actual = actual.izq;
            } else {
                actual = actual.der;
            }
            if (actual.izq == null && actual.der == null) {
                texto.append(actual.caracter);
                actual = raiz;
            }
        }
        return texto.toString();
    }
}

public class Huffman {
    public static void main(String[] args) {
        String textoOriginal = "Este es un ejemplo de compresion de datos usando el algoritmo de Huffman.";
        ArbolHuffman arbolHuffman = new ArbolHuffman(textoOriginal);
        BitSet bitsComprimidos = arbolHuffman.comprimir(textoOriginal);
        System.out.println("Datos comprimidos (bits): " + bitsComprimidos);
        String textoDescomprimido = arbolHuffman.descomprimir(bitsComprimidos);
        System.out.println("Texto descomprimido: " + textoDescomprimido);
    }
}