import java.util.*;
public class PuentesKonigsberg {
    private static int numVertices;
    private static List<Set<Integer>> grafo;

    public PuentesKonigsberg(int numVertices) {
        this.numVertices = numVertices;
        grafo = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            grafo.add(new HashSet<>());
        }
    }

    public static void agregarPuente(int inicio, int fin) {
        grafo.get(inicio).add(fin);
        grafo.get(fin).add(inicio);
    }

    public static boolean tieneCaminoEuleriano() {
        int imparVertices = 0;
        for (Set<Integer> vecinos : grafo) {
            if (vecinos.size() % 2 != 0) {
                imparVertices++;
            }
        }
        return imparVertices == 0 || imparVertices == 2;
    }

    public static boolean esCircuitoEuleriano() {
        return tieneCaminoEuleriano() && numVertices > 0;
    }

    public static boolean esCaminoEuleriano() {
        return tieneCaminoEuleriano() && numVertices > 0;
    }

    public static void main(String[] args) {
        PuentesKonigsberg grafoKonigsberg = new PuentesKonigsberg(4);

        // Agregar los puentes del problema de Königsberg al grafo
        grafoKonigsberg.agregarPuente(0, 1);
        grafoKonigsberg.agregarPuente(0, 2);
        grafoKonigsberg.agregarPuente(1, 2);
        grafoKonigsberg.agregarPuente(1, 3);
        grafoKonigsberg.agregarPuente(2, 3);
        grafoKonigsberg.agregarPuente(2, 3);
        grafoKonigsberg.agregarPuente(2, 3);

        boolean esCircuitoEuleriano = grafoKonigsberg.esCircuitoEuleriano();
        boolean esCaminoEuleriano = grafoKonigsberg.esCaminoEuleriano();

        System.out.println("¿El grafo de Königsberg tiene un circuito euleriano? " + esCircuitoEuleriano);
        System.out.println("¿El grafo de Königsberg tiene un camino euleriano? " + esCaminoEuleriano);
    }
}
