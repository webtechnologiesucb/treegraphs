import java.util.*;

public class GrafoProfundidadAnchura {
    static class GrafoPeso {
        private int V; // Número de vértices
        private ArrayList<int[]>[] adjList; // Lista de adyacencia

        @SuppressWarnings("unchecked")
        GrafoPeso(int v) {
            V = v;
            adjList = new ArrayList[V];
            for (int i = 0; i < V; ++i) {
                adjList[i] = new ArrayList<>();
            }
        }

        // Agregar una arista con peso al grafo
        void agregarArista(int origen, int destino, int peso) {
            adjList[origen].add(new int[]{destino, peso});
        }

        // Búsqueda en profundidad (DFS) con pesos
        void DFS(int v, boolean[] visitado) {
            visitado[v] = true;
            System.out.print(v + " ");

            for (int[] adyacente : adjList[v]) {
                int nodo = adyacente[0];
                if (!visitado[nodo]) {
                    DFS(nodo, visitado);
                }
            }
        }

        // Búsqueda en amplitud (BFS) con pesos
        void BFS(int v) {
            boolean[] visitado = new boolean[V];
            PriorityQueue<int[]> colaPrioridad = new PriorityQueue<>((a, b) -> a[1] - b[1]);

            visitado[v] = true;
            colaPrioridad.add(new int[]{v, 0});

            while (!colaPrioridad.isEmpty()) {
                int[] actual = colaPrioridad.poll();
                v = actual[0];
                int pesoActual = actual[1];
                System.out.print(v + " ");

                for (int[] adyacente : adjList[v]) {
                    int nodo = adyacente[0];
                    int peso = adyacente[1];
                    if (!visitado[nodo]) {
                        visitado[nodo] = true;
                        colaPrioridad.add(new int[]{nodo, peso + pesoActual});
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        GrafoPeso grafo = new GrafoPeso(4);

        grafo.agregarArista(0, 1, 10);
        grafo.agregarArista(0, 2, 20);
        grafo.agregarArista(1, 2, 30);
        grafo.agregarArista(2, 0, 40);
        grafo.agregarArista(2, 3, 50);
        grafo.agregarArista(3, 3, 60);

        System.out.println("Búsqueda en profundidad (DFS) desde el vértice 2:");
        boolean[] visitadoDFS = new boolean[grafo.V];
        grafo.DFS(2, visitadoDFS);

        System.out.println("\nBúsqueda en amplitud (BFS) desde el vértice 2:");
        grafo.BFS(2);
    }
}
