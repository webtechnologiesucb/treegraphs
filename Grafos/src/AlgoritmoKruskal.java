import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Arista {
    int origen, destino, peso;

    public Arista(int origen, int destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
}

class Grafo {
    int vertices;
    List<Arista> aristas = new ArrayList<>();

    public Grafo(int vertices) {
        this.vertices = vertices;
    }

    public void agregarArista(int origen, int destino, int peso) {
        Arista arista = new Arista(origen, destino, peso);
        aristas.add(arista);
    }

    public List<Arista> kruskalMST() {
        List<Arista> minimumSpanningTree = new ArrayList<>();
        Collections.sort(aristas, Comparator.comparingInt(e -> e.peso));
        DisjointSet disjointSet = new DisjointSet(vertices);

        for (Arista arista : aristas) {
            if (disjointSet.encontrar(arista.origen) != disjointSet.encontrar(arista.destino)) {
                minimumSpanningTree.add(arista);
                disjointSet.unir(arista.origen, arista.destino);
            }
        }
        return minimumSpanningTree;
    }
}

// para detectar ciclos creamos la clase DisjointSet
class DisjointSet {
    int[] padre;

    public DisjointSet(int n) {
        padre = new int[n];
        for (int i = 0; i < n; i++) {
            padre[i] = i;
        }
    }

    public int encontrar(int x) {
        if (x == padre[x]) {
            return x;
        }
        return encontrar(padre[x]);
    }

    public void unir(int x, int y) {
        int rootX = encontrar(x);
        int rootY = encontrar(y);
        padre[rootX] = rootY;
    }
}

public class AlgoritmoKruskal {
    public static void main(String[] args) {
        int vertices = 6;
        Grafo grafo = new Grafo(vertices);
        grafo.agregarArista(0, 1, 4);
        grafo.agregarArista(0, 2, 4);
        grafo.agregarArista(1, 2, 2);
        grafo.agregarArista(1, 3, 3);
        grafo.agregarArista(1, 4, 5);
        grafo.agregarArista(2, 4, 7);
        grafo.agregarArista(3, 4, 4);
        grafo.agregarArista(3, 5, 2);
        grafo.agregarArista(4, 5, 5);

        List<Arista> minimumSpanningTree = grafo.kruskalMST();

        System.out.println("Aristas en el Minimum Spanning Tree (Arbol minimo recubridor):");
        for (Arista arista : minimumSpanningTree) {
            System.out.println(arista.origen + " - " + arista.destino + " : " + arista.peso);
        }
    }
}
