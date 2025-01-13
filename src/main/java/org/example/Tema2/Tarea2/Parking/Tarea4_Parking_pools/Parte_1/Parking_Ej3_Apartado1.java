package org.example.Tema2.Tarea2.Parking.Tarea4_Parking_pools.Parte_1;

public class Parking_Ej3_Apartado1 {
    private int capacidad;
    private String[] plazas;
    private final Object lock = new Object();

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
            return -1; // No hay plaza disponible
        }
    }

    public void salirParking(int plaza) {
        synchronized (lock) {
            System.out.println("Coche con matrícula " + plazas[plaza] + " salió de la plaza " + plaza);
            plazas[plaza] = null;
        }
    }
}
