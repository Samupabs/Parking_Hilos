import java.util.Random;

/**
 * La clase Coche representa un vehículo que puede solicitar entrar y salir de un estacionamiento (Parking).
 * Cada coche tiene un nombre único y está asociado a un estacionamiento específico.
 * La ejecución de un coche como un hilo (Thread) simula su comportamiento de solicitud de entrada y salida repetitiva.
 */
public class Coche extends Thread {

    /** Nombre único del coche. */
    protected String nombre;

    /** Instancia del estacionamiento al que está asociado el coche. */
    protected Parking parking;

    /**
     * Constructor de la clase Coche.
     *
     * @param nombre  Nombre único del coche.
     * @param parking Instancia del estacionamiento al que está asociado el coche.
     */
    public Coche(String nombre, Parking parking) {
        this.nombre = nombre;
        this.parking = parking;
    }

    /**
     * Método que define el comportamiento del hilo cuando se ejecuta.
     * El coche solicitará entrar y salir del estacionamiento en un bucle infinito.
     */
    @Override
    public void run() {
        boolean bot = true;

        while (bot) {

            solicitaEntrar();

            try {
                Thread.sleep(new Random().nextInt(5000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            solicitaSalir();

            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Método sincronizado que permite al coche solicitar entrar al estacionamiento.
     */
    synchronized void solicitaEntrar() {
        parking.solicitaEntrada(this);
    }

    /**
     * Método sincronizado que permite al coche solicitar salir del estacionamiento.
     */
    synchronized void solicitaSalir() {
        parking.salirDelParking(this);
    }

    // Métodos Getters y Setters...

    /**
     * Obtiene el nombre del coche.
     *
     * @return Nombre del coche.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del coche.
     *
     * @param nombre Nuevo nombre del coche.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
