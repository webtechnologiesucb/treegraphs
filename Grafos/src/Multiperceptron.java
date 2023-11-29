import java.util.*;

public class Multiperceptron {
    static class Neurona {
        private double[] pesos;
        private double umbral;

        Neurona(int numEntradas) {
            pesos = new double[numEntradas];
            // Inicializar los pesos con valores aleatorios
            Random rand = new Random();
            for (int i = 0; i < numEntradas; i++) {
                pesos[i] = rand.nextDouble();
            }
            // Asignar un umbral aleatorio
            umbral = rand.nextDouble();
        }

        // Función de activación (en este caso, una función escalón)
        int funcionActivacion(double sumaPonderada) {
            return (sumaPonderada >= umbral) ? 1 : 0;
        }

        // Calcular la salida de la neurona para un conjunto de entradas dados
        int calcularSalida(int[] entradas) {
            if (entradas.length != pesos.length) {
                throw new IllegalArgumentException("Número de entradas incorrecto");
            }

            double sumaPonderada = 0;
            for (int i = 0; i < pesos.length; i++) {
                sumaPonderada += entradas[i] * pesos[i];
            }

            return funcionActivacion(sumaPonderada);
        }
    }

    static class CapaNeuronas {
        private Neurona[] neuronas;

        CapaNeuronas(int numNeuronas, int numEntradasPorNeurona) {
            neuronas = new Neurona[numNeuronas];
            for (int i = 0; i < numNeuronas; i++) {
                neuronas[i] = new Neurona(numEntradasPorNeurona);
            }
        }

        // Calcular la salida de la capa para un conjunto de entradas dados
        int[] calcularSalidaCapa(int[] entradas) {
            int[] salida = new int[neuronas.length];
            for (int i = 0; i < neuronas.length; i++) {
                salida[i] = neuronas[i].calcularSalida(entradas);
            }
            return salida;
        }
    }

    static class RedNeuronal {
        private CapaNeuronas[] capas;

        RedNeuronal(int[] numNeuronasPorCapa, int numEntradas) {
            capas = new CapaNeuronas[numNeuronasPorCapa.length];
            for (int i = 0; i < capas.length; i++) {
                int numEntradasPorNeurona = (i == 0) ? numEntradas : numNeuronasPorCapa[i - 1];
                capas[i] = new CapaNeuronas(numNeuronasPorCapa[i], numEntradasPorNeurona);
            }
        }

        // Calcular la salida de la red neuronal para un conjunto de entradas dados
        int[] calcularSalidaRed(int[] entradas) {
            int[] salida = entradas;
            for (CapaNeuronas capa : capas) {
                salida = capa.calcularSalidaCapa(salida);
            }
            return salida;
        }
    }

    public static void main(String[] args) {
        int[] neuronasPorCapa = {3, 4, 2}; // Ejemplo: 3 neuronas en la capa de entrada, 4 en la capa oculta, 2 en la capa de salida
        int numEntradas = 3; // Ejemplo: 3 entradas para la capa de entrada

        RedNeuronal red = new RedNeuronal(neuronasPorCapa, numEntradas);

        int[] entradas = {1, 0, 1}; // Ejemplo de un conjunto de entradas
        int[] salida = red.calcularSalidaRed(entradas);

        // Mostrar la salida
        System.out.println("Salida: " + Arrays.toString(salida));
    }
}
