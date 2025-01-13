package org.example.Tema2.Tarea2.Parking.Tarea4_Parking_pools.Parte_1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Parking {
    private static final int CAPACIDAD_PARKING = 10;
    private static final int NUM_COCHES = 40;

    public static void main(String[] args) {
        Parking_Ej3_Apartado1 parking = new Parking_Ej3_Apartado1(CAPACIDAD_PARKING);
        ExecutorService executor = Executors.newFixedThreadPool(CAPACIDAD_PARKING);

        for (int i = 0; i < NUM_COCHES; i++) {
            String matricula = "ABC-" + (i + 1);
            executor.execute(new Coche(matricula, parking));
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
