package org.example.Tema2.Tarea2.Parking.Ej3_Apartado1;

public class Ejercicio3_Parte1 {
    public static void main(String[] args) {
        int capacidadParking = 10;
        Parking_Ej3_Apartado1 parking = new Parking_Ej3_Apartado1(capacidadParking);

        Coche[] coches = new Coche[40];

        for (int i = 0; i < 40; i++) {
            String matricula = "ABC-" + (i + 1);
            coches[i] = new Coche(matricula, parking);
        }

        // Iniciar todos los coches
        for (Coche coche : coches) {
            coche.start();
        }

        // Esperar a que todos los coches terminen
        for (Coche coche : coches) {
            try {
                coche.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
