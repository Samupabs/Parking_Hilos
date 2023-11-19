import java.util.*;

/**
 * La clase Parking representa un estacionamiento con un número máximo de plazas para coches.
 * Los coches pueden solicitar entrar al estacionamiento, salir del estacionamiento y esperar su turno
 * en una lista de espera cuando no hay plazas disponibles.
 */
public class Parking {

    /** Número máximo de plazas en el estacionamiento. */
    protected int plazasMaximas;

    /** Lista que almacena los coches actualmente en las plazas del estacionamiento. */
    protected List<Coche> plazasDelParking = new ArrayList<>(plazasMaximas);

    /** Cola de espera para los coches que desean entrar al estacionamiento. */
    protected Queue<Coche> listaEspera = new LinkedList<>();

    /**
     * Constructor de la clase Parking.
     *
     * @param plazasDelParking Número máximo de plazas en el estacionamiento.
     */
    public Parking(int plazasDelParking) {

        if (plazasDelParking <= 0){
            System.out.println("Plazas insuficientes. Se establecerá 2 por defecto.");
            plazasDelParking=2;
        }

        setPlazasMaximas(plazasDelParking);

        // Pongo todas las plazas en null para dejarlas disponibles
        for (int i = 0; i < plazasDelParking; i++) {
            this.plazasDelParking.add(null);
        }
    }

    //Métodos-----------------------------------------------------------------------------------------------------------

    /**
     * Método para que un coche solicite entrar al estacionamiento.
     *
     * @param coche Coche que solicita entrar al estacionamiento.
     */
    synchronized void solicitaEntrada(Coche coche) {
        // Entran en la lista de espera
        aniadirAListaDeEspera(coche);
        asignandoPlazas(coche.getNombre());
    }

    /**
     * Método para que un coche salga del estacionamiento.
     *
     * @param coche Coche que solicita salir del estacionamiento.
     */
    synchronized void salirDelParking(Coche coche) {

        Iterator<Coche> iterator = plazasDelParking.iterator();

        while (iterator.hasNext()) {
            Coche i = iterator.next();

            if (i != null && i.getNombre().equals(coche.getNombre())) {
                iterator.remove();
                plazasDelParking.add(null);
                System.out.println(coche.getNombre() + " ha salido del parking");
                break;
            }
        }
        notifyAll();
    }

    /**
     * Método para añadir un coche a la lista de espera, verificando si ya existe un coche con el mismo nombre.
     *
     * @param coche Coche que solicita entrar al estacionamiento.
     */
    synchronized void aniadirAListaDeEspera(Coche coche) {
        // Comprobar si ya existe un coche con el mismo nombre
        boolean cocheExistente = listaEspera.stream().anyMatch(c -> c.getNombre().equals(coche.getNombre()));

        // Si no existe, agregarlo a la lista de espera
        if (!cocheExistente) {
            listaEspera.add(coche);
            System.out.println(coche.getNombre() + " ha solicitado entrar al parking y está en la lista de espera.");
        } else {
            System.out.println("Ya hay un coche con el nombre " + coche.getNombre() + " en la lista de espera.");
        }
    }

    /**
     * Método para asignar plazas en el estacionamiento a los coches de la lista de espera.
     *
     * @param nombreCoche Nombre del coche que solicita entrar al estacionamiento.
     */
    synchronized void asignandoPlazas(String nombreCoche) {

        boolean plazaAsignada = false;
        boolean inf = true;

        while (inf) {

            for (int i = 0; i < plazasDelParking.size(); i++) {
                if (plazasDelParking.get(i) == null) {
                    plazasDelParking.set(i, listaEspera.poll());
                    System.out.println("El " + nombreCoche + " ha entrado al parking.");
                    mostrarCochesEnParking();
                    plazaAsignada = true;
                    inf = false;
                    break;
                }
            }

            if (!plazaAsignada) {

                try {
                    wait();  // Esperamos a que se libere una plaza
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Método para mostrar los coches en espera.
     */
    synchronized void mostrarCochesEsperando() {
        System.out.println("\nCoches esperando:");
        for (Coche i : listaEspera) {
            System.out.println("\t" + i.getNombre());
        }
    }

    /**
     * Método para mostrar los coches en el estacionamiento.
     */
    synchronized void mostrarCochesEnParking() {
        int contador = 1;
        System.out.println("\nCoches en el Parking: [ TOTAL DE PLAZAS: " + plazasMaximas + " ]");

        for (Coche c : plazasDelParking) {
            if (c == null) {
                System.out.println("\tPlaza vacía");
            } else {
                System.out.println("\t" + c.getNombre());
            }
        }
    }

    //G&S---------------------------------------------------------------------------------------------------------------

    public int getPlazasMaximas() {
        return plazasMaximas;
    }

    public void setPlazasMaximas(int plazasMaximas) {
        this.plazasMaximas = plazasMaximas;
    }

    public List<Coche> getPlazasDelParking() {
        return plazasDelParking;
    }

    public void setPlazasDelParking(List<Coche> plazasDelParking) {
        this.plazasDelParking = plazasDelParking;
    }

    public Queue<Coche> getListaEspera() {
        return listaEspera;
    }

    public void setListaEspera(Queue<Coche> listaEspera) {
        this.listaEspera = listaEspera;
    }
}
