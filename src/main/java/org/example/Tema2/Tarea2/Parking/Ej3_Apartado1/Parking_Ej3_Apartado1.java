package org.example.Tema2.Tarea2.Parking.Ej3_Apartado1;

import java.util.Random;

public class Parking_Ej3_Apartado1 {
    private int capacidad;
    private String[] plazas;
    private Object lock = new Object();

    public Parking_Ej3_Apartado1(int capacidad) {
        this.capacidad = capacidad;
        this.plazas = new String[capacidad];
    }

    public int entrarParking(String matricula) {
        synchronized (lock) {
            for (int i = 0; i < capacidad; i++) {
                if (plazas[i] == null) {
                    System.out.println("Coche con matrícula " + matricula + " entró en la plaza " + i);
                    plazas[i] = matricula;
                    return i;
                }
            }
            return -1;
        }
    }

    public void salirParking(int plaza) {
        synchronized (lock) {
            System.out.println("Coche con matrícula " + plazas[plaza] + " salió de la plaza " + plaza);
            plazas[plaza] = null;
        }
    }
}

class Coche extends Thread {
    private String matricula;
    private Parking_Ej3_Apartado1 parking;

    public Coche(String matricula, Parking_Ej3_Apartado1 parking) {
        this.matricula = matricula;
        this.parking = parking;
    }

    @Override
    public void run() {
        Random rand = new Random();

        // Circulando
        int tiempoCirculando = rand.nextInt(5000) + 1; // Milisegundos
        try {
            sleep(tiempoCirculando);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Intentando entrar al parking
        int plaza = parking.entrarParking(matricula);

        if (plaza != -1) {
            // Dentro del parking
            int tiempoDentro = rand.nextInt(3000) + 1000; // Milisegundos
            try {
                sleep(tiempoDentro);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Espera antes de abandonar
            int tiempoEsperaAbandono = rand.nextInt(11000) + 10000; // Milisegundos
            try {
                sleep(tiempoEsperaAbandono);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Saliendo del parking
            parking.salirParking(plaza);
        } else {
            System.out.println("Coche con matrícula " + matricula + " no pudo entrar al parking y se va.");
        }
    }
}

