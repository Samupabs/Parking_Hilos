# Parking Simulation

Este proyecto simula el funcionamiento de un estacionamiento utilizando hilos en Java. Incluye clases como `Parking`, `Coche`, y `Main` para crear y gestionar la simulación.

## Clases Principales

### Parking

La clase `Parking` representa el estacionamiento y tiene métodos para que los coches soliciten entrar y salir. Además, gestiona una lista de espera para los coches que esperan su turno.

#### Métodos Destacados

- `solicitaEntrada(Coche coche)`: Un coche solicita entrar al estacionamiento.
- `salirDelParking(Coche coche)`: Un coche solicita salir del estacionamiento.
- `aniadirAListaDeEspera(Coche coche)`: Añade un coche a la lista de espera, evitando duplicados.

### Coche

La clase `Coche` representa un vehículo que solicitará entrar y salir del estacionamiento. Cada coche se ejecuta como un hilo independiente.

#### Métodos Destacados

- `solicitaEntrar()`: Un coche solicita entrar al estacionamiento.
- `solicitaSalir()`: Un coche solicita salir del estacionamiento.

### Main

La clase `Main` inicia la simulación, creando un estacionamiento y varios coches que interactúan con él.

## Uso

1. Ejecutar la clase `Main`.
2. Observar la simulación del estacionamiento y los coches en la consola.

## Requisitos

- Java 8 o superior.

## Contribuciones

Las contribuciones son bienvenidas. Siéntase libre de abrir problemas o enviar solicitudes de extracción.

## Licencia

Este proyecto está bajo la [Licencia MIT](LICENSE).
