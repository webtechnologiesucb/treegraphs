public class AlgoritmoViterbi {
    public static void main(String[] args) {
        // Definir los estados ocultos del HMM
        String[] estados = {"Soleado", "Lluvioso"};

        // Definir las observaciones
        String[] observaciones = {"Paseo", "Comercio", "Limpieza"};

        // Probabilidad inicial de los estados
        double[] inicialProb = {0.6, 0.4};

        // Matriz de transición de estados
        double[][] matrizTransicion = {
                {0.7, 0.3},
                {0.4, 0.6}
        };

        // Matriz de emisión de observaciones
        double[][] matrixEmision = {
                {0.1, 0.4, 0.5},
                {0.6, 0.3, 0.1}
        };

        int numEstados = estados.length;
        int numObservaciones = observaciones.length;

        // Inicializar las matrices de Viterbi
        double[][] viterbi = new double[numEstados][numObservaciones];
        int[][] punteroRetroceso = new int[numEstados][numObservaciones];

        // Paso 1: Inicialización
        for (int estado = 0; estado < numEstados; estado++) {
            viterbi[estado][0] = inicialProb[estado] * matrixEmision[estado][0];
            punteroRetroceso[estado][0] = -1; // No hay retroceso en el primer paso
        }

        // Paso 2: Recursión
        for (int t = 1; t < numObservaciones; t++) {
            for (int estado = 0; estado < numEstados; estado++) {
                double maxProbabilidad = 0.0;
                int mejorEstadoPrevio = 0;

                for (int estadoPrevio = 0; estadoPrevio < numEstados; estadoPrevio++) {
                    double probabilidad = viterbi[estadoPrevio][t - 1] * matrizTransicion[estadoPrevio][estado] * matrixEmision[estado][t];
                    if (probabilidad > maxProbabilidad) {
                        maxProbabilidad = probabilidad;
                        mejorEstadoPrevio = estadoPrevio;
                    }
                }
                viterbi[estado][t] = maxProbabilidad;
                punteroRetroceso[estado][t] = mejorEstadoPrevio;
            }
        }

        // Paso 3: Termination
        double maxProbabilidadFinal = 0.0;
        int mejorEstadoFinal = 0;

        for (int estado = 0; estado < numEstados; estado++) {
            if (viterbi[estado][numObservaciones - 1] > maxProbabilidadFinal) {
                maxProbabilidadFinal = viterbi[estado][numObservaciones - 1];
                mejorEstadoFinal = estado;
            }
        }

        // Paso 4: Reconstruir la secuencia más probable
        int[] mejorRuta = new int[numObservaciones];
        mejorRuta[numObservaciones - 1] = mejorEstadoFinal;
        int estadoActual = mejorEstadoFinal;

        for (int t = numObservaciones - 1; t > 0; t--) {
            estadoActual = punteroRetroceso[estadoActual][t];
            mejorRuta[t - 1] = estadoActual;
        }

        // Imprimir la secuencia más probable de estados
        System.out.println("Secuencia más probable de estados:");
        for (int t = 0; t < numObservaciones; t++) {
            System.out.println(observaciones[t] + " -> " + estados[mejorRuta[t]]);
        }
    }
}
