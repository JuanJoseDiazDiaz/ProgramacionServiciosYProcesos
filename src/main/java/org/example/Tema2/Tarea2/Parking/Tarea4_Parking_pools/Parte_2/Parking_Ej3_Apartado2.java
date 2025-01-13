package org.example.Tema2.Tarea2.Parking.Tarea4_Parking_pools.Parte_2;

import java.util.Random;

public class Parking_Ej3_Apartado2 {
    private int capacidad;
    private String[] plazas;
    private final Object lock = new Object();

    public Parking_Ej3_Apartado2(int capacidad) {
        this.capacidad = capacidad;
        this.plazas = new String[capacidad];
    }

    public int entrarParking(String matricula, boolean esFurgoneta) {
        synchronized (lock) {
            int inicio = esFurgoneta ? 0 : 1;
            int paso = esFurgoneta ? 2 : 1;

            for (int i = inicio; i < capacidad; i += paso) {
                if (plazas[i] == null && (!esFurgoneta || (i + 1 < capacidad && plazas[i + 1] == null))) {
                    System.out.println("Vehículo con matrícula " + matricula + " entró en la plaza " + i);
                    plazas[i] = matricula;
                    if (esFurgoneta) {
                        plazas[i + 1] = matricula; // Ocupa la siguiente plaza
                    }
                    return i;
                }
            }
            return -1; // No hay plaza disponible
        }
    }

    public void salirParking(int plaza, boolean esFurgoneta) {
        synchronized (lock) {
            System.out.println("Vehículo con matrícula " + plazas[plaza] + " salió de la plaza " + plaza);
            plazas[plaza] = null;
            if (esFurgoneta) {
                plazas[plaza + 1] = null; // Libera la siguiente plaza
            }
        }
    }
}
