package org.example.Tema2.Tarea2.Parking.Tarea4_Parking_pools.Parte_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Parking_Parte2 {
    private static final int CAPACIDAD_PARKING = 20; // Aumentamos la capacidad para permitir furgonetas
    private static final int NUM_COCHES = 40;
    private static final int NUM_FURGONETAS = 10;

    public static void main(String[] args) {
        Parking_Ej3_Apartado2 parking = new Parking_Ej3_Apartado2(CAPACIDAD_PARKING);
        ExecutorService executor = Executors.newFixedThreadPool(CAPACIDAD_PARKING);

        // Crear coches
        for (int i = 0; i < NUM_COCHES; i++) {
            String matricula = "ABC-" + (i + 1);
            executor.execute(new Vehiculo(matricula, parking, false)); // false indica que es un coche
        }

        // Crear furgonetas
        for (int i = 0; i < NUM_FURGONETAS; i++) {
            String matricula = "FGH-" + (i + 1);
            executor.execute(new Vehiculo(matricula, parking, true)); // true indica que es una furgoneta
        }

        executor.shutdown();
        try {
            // Esperar a que todos los hilos terminen
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }
}
