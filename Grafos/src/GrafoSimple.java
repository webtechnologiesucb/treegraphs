import java.util.ArrayList;
import java.util.List;

class Vertice {
    int valor;

    public Vertice(int valor) {
        this.valor = valor;
    }
}

class GrafoNoDirigido {
    private int numVertices;
    private List<Vertice> vertices;
    private List<List<Integer>> listaAdyacencia;

    public GrafoNoDirigido(int numVertices) {
        this.numVertices = numVertices;
        vertices = new ArrayList<>();
        listaAdyacencia = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            vertices.add(new Vertice(i));
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarArista(int v1, int v2) {
        listaAdyacencia.get(v1).add(v2);
        listaAdyacencia.get(v2).add(v1);
    }

    public List<Integer> obtenerVecinos(int vertice) {
        return listaAdyacencia.get(vertice);
    }

    public int obtenerNumeroVertices() {
        return numVertices;
    }

    public Vertice obtenerVertice(int indice) {
        return vertices.get(indice);
    }
}

public class GrafoSimple {
    public static void main(String[] args) {
        GrafoNoDirigido grafo = new GrafoNoDirigido(5);
        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);

        int numVertices = grafo.obtenerNumeroVertices();
        System.out.println("Número de vértices en el grafo: " + numVertices);

        System.out.println("Vecinos del vértice 0:");
        List<Integer> vecinos = grafo.obtenerVecinos(0);
        for (int vecino : vecinos) {
            System.out.print(vecino + " ");
        }
    }
}
