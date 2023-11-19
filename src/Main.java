/**
 * La clase Main es la clase principal que inicia la simulación del estacionamiento y los coches.
 */
public class Main {

    /**
     * Método principal que inicia la simulación del estacionamiento y los coches.
     *
     * @param args Argumentos de la línea de comandos (no se utilizan en este programa).
     */
    public static void main(String[] args) {

        // Se crea una instancia de Parking con un límite de plazas modificable. Si se pone negativo pondrá 2 por defecto.
        Parking parking = new Parking(4);

        // Número de coches que se crearán e iniciarán en la simulación.
        int nroCoches = 5;

        // Se crean e inician hilos (coches) que solicitarán entrar y salir del estacionamiento.
        for (int i = 0; i <= nroCoches; i++) {
            new Coche(("Coche" + (i + 1)), parking).start();
        }
    }
}
