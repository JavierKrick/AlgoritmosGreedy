
import java.util.Scanner;

public class MaximasActividades {

    public static void algoritmoDeFloyd(int[][] arr, int n, int i) {
        int maxi = i;  // Inicializar el mayor como la raíz
        int NodoIzq = 2 * i + 1;  // hijo izquierdo
        int NodoDer = 2 * i + 2;  // hijo derecho

        // ¿hijo izquierdo es más grande que la raíz?
        if (NodoIzq < n && arr[NodoIzq][1] > arr[maxi][1]) {
            maxi = NodoIzq;
        }

        // ¿erificar si el hijo derecho es más grande el más grande?
        if (NodoDer < n && arr[NodoDer][1] > arr[maxi][1]) {
            maxi = NodoDer;
        }

        // Si el mayor no es la raíz
        if (maxi != i) {
            int[] temp = arr[i];
            arr[i] = arr[maxi];
            arr[maxi] = temp;  // intercambiar

            // Recursivamente aplicar algoritmoDeFloyd en el subárbol afectado
            algoritmoDeFloyd(arr, n, maxi);
        }
    }

    public static int[][] ordenarConMaxHeapInPlace(int[][] arr) {
        int n = arr.length;

        // Construir un max-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            algoritmoDeFloyd(arr, n, i);
        }

        // Extraer elementos uno por uno
        for (int i = n - 1; i > 0; i--) {
            // intercambiar el mayor elemento con el último
            int[] temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;  
            algoritmoDeFloyd(arr, i, 0);  // heapificar el resto de la lista
        }

        return arr;
    }

    // Es un algoritmo greedy, arranco ordenándolos en función del que termina antes, y los voy agregando
    public static int maximasActividades(int[][] actividades) {


        // Ordeno sólo en función de cuando termina de manera ascendente
        actividades = ordenarConMaxHeapInPlace(actividades);


        int ultimaHora = -1;
        int contador = 0;
        

        for (int[] act : actividades) {

            if (act[0] >= ultimaHora) {
                contador = contador + 1;
                ultimaHora = act[1];
            }
        }

        return contador;
    }




    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);


        int T = scanner.nextInt(); // casos de prueba

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt(); // intervalos
            int[][] actividades = new int[N][2];

            for (int i = 0; i < N; i++) {
                actividades[i][0] = scanner.nextInt(); // tiempo de inicio
                actividades[i][1] = scanner.nextInt(); // tiempo de finalización
            }

            int resultado = maximasActividades(actividades);
            System.out.println(resultado);
        }

        scanner.close();
    }
}
