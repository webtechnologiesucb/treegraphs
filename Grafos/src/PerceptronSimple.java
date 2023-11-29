import java.util.*;

class Neurona {
    private int numEntradas;
    private double[] pesos;
    private double umbral;

    Neurona(int numEntradas) {
        this.numEntradas = numEntradas;
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
        if (entradas.length != numEntradas) {
            throw new IllegalArgumentException("Número de entradas incorrecto");
        }

        double sumaPonderada = 0;
        for (int i = 0; i < numEntradas; i++) {
            sumaPonderada += entradas[i] * pesos[i];
        }

        return funcionActivacion(sumaPonderada);
    }
}

public class PerceptronSimple {
    public static void main(String[] args) {
        // Crear una neurona con 3 entradas
        Neurona neurona = new Neurona(3);

        // Definir un conjunto de entradas de ejemplo
        int[] entradas = {1, 0, 1};

        // Calcular la salida de la neurona para las entradas dadas
        int salida = neurona.calcularSalida(entradas);

        // Mostrar la salida
        System.out.println("Salida: " + salida);
    }
}
