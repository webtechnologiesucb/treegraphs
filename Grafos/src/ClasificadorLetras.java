import java.util.ArrayList;
import java.util.List;

public class ClasificadorLetras {
    public static void main(String[] args) {
        char[][] matriz = new char[2][26];
        String texto = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] letra;

        List<Character> letrasRectas = new ArrayList<>();
        List<Character> letrasRedondas = new ArrayList<>();

        for (char c : texto.toCharArray()) {
            if (esLetraRecta(c)) {
                letrasRectas.add(c);
            } else if (esLetraRedonda(c)) {
                letrasRedondas.add(c);
            }
        }
        System.out.println("Letras rectas (arriba): " + letrasRectas);
        System.out.println("Letras redondas (abajo): " + letrasRedondas);

        letra = texto.toCharArray();

        for(int i=0; i<letra.length; i++)
        {
            if (esLetraRecta(letra[i])) {
                matriz[0][i] = letra[i];
                matriz[1][i] = ' ';
            } else if (esLetraRedonda(letra[i])) {
                matriz[0][i] = ' ';
                matriz[1][i] = letra[i];
            }
        }

        System.out.println("LISTADO FINAL:");
        for (char[] fila : matriz) {
            for (char elemento : fila) {
                System.out.print(elemento + " ");
            }
            System.out.println(); // Salto de línea entre filas
        }
    }

    public static boolean esLetraRecta(char c) {
        // Define aquí las letras rectas según tus criterios
        String letrasRectas = "AEFHIKLMNTVWXYZ";
        return letrasRectas.contains(Character.toString(c).toUpperCase());
    }

    public static boolean esLetraRedonda(char c) {
        // Define aquí las letras redondas según tus criterios
        String letrasRedondas = "BCDGJOPQRSU";
        return letrasRedondas.contains(Character.toString(c));
    }
}

