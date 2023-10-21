import java.util.*;

class AristaB {
    int origen, destino, peso;

    public AristaB(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

class GrafoB {
    int vertices;
    List<AristaB> aristas;

    public GrafoB(int vertices) {
        this.vertices = vertices;
        aristas = new ArrayList<>();
    }

    public void agregarArista(int origen, int destino, int peso) {
        AristaB arista = new AristaB(origen, destino, peso);
        aristas.add(arista);
    }

    public int[] bellmanFord(int origen) {
        int[] distancias = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }
        distancias[origen] = 0;

        boolean relax;
        for (int i = 0; i < vertices - 1; i++) {
            relax = false;
            for (AristaB arista : aristas) {
                int u = arista.origen;
                int v = arista.destino;
                int peso = arista.peso;
                if (distancias[u] != Integer.MAX_VALUE && distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    relax = true;
                }
            }
            if (!relax) {
                // No se realizaron cambios en esta iteración, salir temprano.
                break;
            }
        }
        return distancias;
    }
}

public class AlgoritmoBellmanFord {
    public static void main(String[] args) {
        int vertices = 6; // Aumenté el número de vértices
        int origen = 0;
        GrafoB grafo = new GrafoB(vertices);
        grafo.agregarArista(0, 1, 2);
        grafo.agregarArista(0, 2, 4);
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 7);
        grafo.agregarArista(2, 4, 3);
        grafo.agregarArista(3, 4, 1);
        grafo.agregarArista(4, 3, -2);
        grafo.agregarArista(3, 5, 5);
        grafo.agregarArista(4, 5, 2);

        int[] distancias = grafo.bellmanFord(origen);

        System.out.println("Las distancias mas cortas desde el origen " + origen + " a los demás vertices:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vértice " + i + ": " + distancias[i]);
        }
    }
}
