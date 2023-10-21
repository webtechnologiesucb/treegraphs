import java.util.ArrayList;
import java.util.List;

//
public class AlgoritmoFoulkes {
    // Problema del viajero comerciante
    private int numCiudades;
    private int[][] grafo;

    public AlgoritmoFoulkes(int numCiudades, int[][] grafo) {
        this.numCiudades = numCiudades;
        this.grafo = grafo;
    }

    public List<Integer> encontrarRutaTSP() {
        List<Integer> ruta = new ArrayList<>();
        boolean[] visitado = new boolean[numCiudades];

        // Comenzar en la ciudad 0
        int ciudadActual = 0;
        ruta.add(ciudadActual);
        visitado[ciudadActual] = true;

        for (int i = 1; i < numCiudades; i++) {
            int ciudadMasCercana = encontrarCiudadMasCercana(ciudadActual, visitado);
            ruta.add(ciudadMasCercana);
            visitado[ciudadMasCercana] = true;
            ciudadActual = ciudadMasCercana;
        }

        // Regresar a la ciudad de origen (ciudad 0)
        ruta.add(0);

        return ruta;
    }

    private int encontrarCiudadMasCercana(int ciudad, boolean[] visitado) {
        int ciudadMasCercana = -1;
        int distanciaMinima = Integer.MAX_VALUE;

        for (int i = 0; i < numCiudades; i++) {
            if (!visitado[i] && i != ciudad && grafo[ciudad][i] < distanciaMinima) {
                ciudadMasCercana = i;
                distanciaMinima = grafo[ciudad][i];
            }
        }

        return ciudadMasCercana;
    }

    public static void main(String[] args) {
        int numCiudades = 4;
        int[][] grafo = {
                {0, 10, 15, 20},
                {10, 0, 35, 25},
                {15, 35, 0, 30},
                {20, 25, 30, 0}
        };

        AlgoritmoFoulkes tsp = new AlgoritmoFoulkes(numCiudades, grafo);
        List<Integer> rutaTSP = tsp.encontrarRutaTSP();

        System.out.println("Ruta TSP encontrada: " + rutaTSP);
    }
}

