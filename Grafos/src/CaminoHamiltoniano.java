import java.util.ArrayList;
import java.util.List;

public class CaminoHamiltoniano {
    public static List<Integer> encontrarCaminoHamiltoniano(int[][] grafo) {
        int numVertices = grafo.length;
        List<Integer> camino = new ArrayList<>();
        boolean[] visitado = new boolean[numVertices];

        // Comenzar desde el vértice 0 y agregarlo al camino
        camino.add(0);
        visitado[0] = true;

        // Intentar agregar el resto de los vértices al camino
        if (encontrarCaminoRec(grafo, camino, visitado, 1)) {
            return camino;
        }

        return null; // No se encontró un camino hamiltoniano
    }

    private static boolean encontrarCaminoRec(int[][] grafo, List<Integer> camino, boolean[] visitado, int paso) {
        if (paso == grafo.length) {
            // Se ha agregado un vértice a cada paso, por lo que se encontró un camino hamiltoniano
            return true;
        }

        int ultimoVertice = camino.get(camino.size() - 1);

        for (int siguienteVertice = 0; siguienteVertice < grafo.length; siguienteVertice++) {
            if (grafo[ultimoVertice][siguienteVertice] == 1 && !visitado[siguienteVertice]) {
                camino.add(siguienteVertice);
                visitado[siguienteVertice] = true;

                if (encontrarCaminoRec(grafo, camino, visitado, paso + 1)) {
                    return true;
                }

                // Si el camino no lleva a una solución, retroceder
                camino.remove(camino.size() - 1);
                visitado[siguienteVertice] = false;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] grafo = {
                {0, 1, 1, 1, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 0, 1, 1},
                {1, 1, 1, 0, 1},
                {1, 1, 1, 1, 0}
        };

        List<Integer> camino = encontrarCaminoHamiltoniano(grafo);

        if (camino != null) {
            System.out.println("Camino Hamiltoniano encontrado:");
            for (int vertice : camino) {
                System.out.print(vertice + " ");
            }
        } else {
            System.out.println("No se encontró un camino Hamiltoniano en el grafo.");
        }
    }
}