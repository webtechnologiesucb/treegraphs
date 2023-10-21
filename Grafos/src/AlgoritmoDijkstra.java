import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class DArista {
    int destino;
    int peso;

    public DArista(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

class DGrafo {
    int vertices;
    List<List<DArista>> listaAdyacencia;

    public DGrafo(int vertices) {
        this.vertices = vertices;
        listaAdyacencia = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarVertice(int origen, int destino, int peso) {
        DArista arista = new DArista(destino, peso);
        listaAdyacencia.get(origen).add(arista);
    }

    public int[] dijkstra(int origen) {
        int[] distancias = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[origen] = 0;

        PriorityQueue<Integer> cola = new PriorityQueue<>((a, b) -> distancias[a] - distancias[b]);
        cola.add(origen);

        while (!cola.isEmpty()) {
            int u = cola.poll();
            for (DArista DArista : listaAdyacencia.get(u)) {
                int v = DArista.destino;
                int peso = DArista.peso;
                if (distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    cola.add(v);
                }
            }
        }
        return distancias;
    }
}

public class AlgoritmoDijkstra {
    public static void main(String[] args) {
        int vertices = 6;
        int origen = 0;

        DGrafo grafo = new DGrafo(vertices);
        grafo.agregarVertice(0, 1, 2);
        grafo.agregarVertice(0, 2, 4);
        grafo.agregarVertice(1, 2, 1);
        grafo.agregarVertice(1, 3, 7);
        grafo.agregarVertice(2, 4, 3);
        grafo.agregarVertice(3, 4, 1);
        grafo.agregarVertice(4, 3, 2);
        grafo.agregarVertice(3, 5, 5);
        grafo.agregarVertice(4, 5, 2);

        int[] distancias = grafo.dijkstra(origen);
        System.out.println("La mas corta distancia desde origen " + origen + " a los otros vertices:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertice " + i + ": " + distancias[i]);
        }
    }
}