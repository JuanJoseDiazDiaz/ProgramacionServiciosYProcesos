package org.example.Tema2.Tarea2.Parking.Ej3_Apartado2;

import java.util.Random;

public class Parking_Ej3_Apartado2 {
    private int capacidad;
    private String[] plazas;
    private Object lock = new Object();

    public Parking_Ej3_Apartado2(int capacidad) {
        this.capacidad = capacidad;
        this.plazas = new String[capacidad];
    }

    public int entrarParking(String matricula, boolean esFurgoneta) {
        synchronized (lock) {
            int ocupadas = 0;
            int inicio = esFurgoneta ? 0 : 1;
            int paso = esFurgoneta ? 2 : 1;

            for (int i = inicio; i < capacidad; i += paso) {
                if (plazas[i] == null && (esFurgoneta || (i + 1 < capacidad && plazas[i + 1] == null))) {
                    System.out.println("Vehículo con matrícula " + matricula + " entró en la plaza " + i);
                    plazas[i] = matricula;
                    if (esFurgoneta) {
                        plazas[i + 1] = matricula;
                    }
                    return i;
                }
            }
            return -1;
        }
    }

    public void salirParking(int plaza, boolean esFurgoneta) {
        synchronized (lock) {
            System.out.println("Vehículo con matrícula " + plazas[plaza] + " salió de la plaza " + plaza);
            plazas[plaza] = null;
            if (esFurgoneta) {
                plazas[plaza + 1] = null;
            }
        }
    }
}

class Vehiculo extends Thread {
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
            sleep(tiempoCirculando);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Intentando entrar al parking
        int plaza = parking.entrarParking(matricula, esFurgoneta);

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
            parking.salirParking(plaza, esFurgoneta);
        } else {
            System.out.println("Vehículo con matrícula " + matricula + " no pudo entrar al parking y se va.");
        }
    }
}

