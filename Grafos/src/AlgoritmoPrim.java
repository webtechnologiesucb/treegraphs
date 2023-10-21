import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

class ClsArista {
    int origen, destino, peso;

    public ClsArista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

class ClsGrafo {
    int vertices;
    List<List<ClsArista>> listaAdyacencia;

    public ClsGrafo(int vertices) {
        this.vertices = vertices;
        listaAdyacencia = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            listaAdyacencia.add(new ArrayList<>());
        }
    }

    public void agregarVertice(int source, int destination, int weight) {
        ClsArista arista = new ClsArista(source, destination, weight);
        listaAdyacencia.get(source).add(arista);
        arista = new ClsArista(destination, source, weight);
        listaAdyacencia.get(destination).add(arista);
    }

    public List<ClsArista> primMST() {
        List<ClsArista> minimumSpanningTree = new ArrayList<>();
        boolean[] enMST = new boolean[vertices];
        int[] clave = new int[vertices];
        int[] padre = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            clave[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<ClsArista> prioridades = new PriorityQueue<>(vertices, (a, b) -> a.peso - b.peso);
        clave[0] = 0;
        prioridades.add(new ClsArista(-1, 0, 0));

        while (!prioridades.isEmpty()) {
            ClsArista clsArista = prioridades.poll();
            int u = clsArista.destino;
            enMST[u] = true;

            for (ClsArista adjacentClsArista : listaAdyacencia.get(u)) {
                int v = adjacentClsArista.destino;
                int peso = adjacentClsArista.peso;

                if (!enMST[v] && peso < clave[v]) {
                    clave[v] = peso;
                    padre[v] = u;
                    prioridades.add(new ClsArista(u, v, clave[v]));
                }
            }
        }

        for (int i = 1; i < vertices; i++) {
            minimumSpanningTree.add(new ClsArista(padre[i], i, clave[i]));
        }
        return minimumSpanningTree;
    }
}

public class AlgoritmoPrim {
    public static void main(String[] args) {
        int vertices = 5;
        ClsGrafo grafo = new ClsGrafo(vertices);
        grafo.agregarVertice(0, 1, 2);
        grafo.agregarVertice(0, 3, 6);
        grafo.agregarVertice(1, 2, 3);
        grafo.agregarVertice(1, 3, 8);
        grafo.agregarVertice(1, 4, 5);
        grafo.agregarVertice(2, 4, 7);
        grafo.agregarVertice(3, 4, 9);

        List<ClsArista> minimumSpanningTree = grafo.primMST();

        System.out.println("Aristas en Minimum Spanning Tree:");
        for (ClsArista clsArista : minimumSpanningTree) {
            System.out.println(clsArista.origen + " - " + clsArista.destino + " : " + clsArista.peso);
        }
    }
}
