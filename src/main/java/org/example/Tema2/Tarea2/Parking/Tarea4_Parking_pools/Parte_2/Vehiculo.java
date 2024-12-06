package org.example.Tema2.Tarea2.Parking.Tarea4_Parking_pools.Parte_2;

import java.util.Random;

public class Vehiculo implements Runnable {
    private String matricula;
    private Parking_Ej3_Apartado2 parking;
    private boolean esFurgoneta;

    public Vehiculo(String matricula, Parking_Ej3_Apartado2 parking, boolean esFurgoneta) {
        this.matricula = matricula;
        this.parking = parking;
        this.esFurgoneta = esFurgoneta;
    }

    @Override
    public void run() {
        Random rand = new Random();

        // Circulando
        int tiempoCirculando = rand.nextInt(5000) + 1; // Milisegundos
        try {
            Thread.sleep(tiempoCirculando);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Intentando entrar al parking
        int plaza = parking.entrarParking(matricula, esFurgoneta);
        if (plaza != -1) {
            // Dentro del parking
            int tiempoDentro = rand.nextInt(3000) + 1000; // Milisegundos
            try {
                Thread.sleep(tiempoDentro);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Espera antes de abandonar
            int tiempoEsperaAbandono = rand.nextInt(11000) + 10000; // Milisegundos
            try {
                Thread.sleep(tiempoEsperaAbandono);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Saliendo del parking
            parking.salirParking(plaza, esFurgoneta);
        } else {
            System.out.println("Vehículo con matrícula " + matricula + " no pudo entrar al parking y se va.");
        }
    }
}
