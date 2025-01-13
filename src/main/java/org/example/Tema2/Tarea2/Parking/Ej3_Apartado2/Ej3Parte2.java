package org.example.Tema2.Tarea2.Parking.Ej3_Apartado2;

public class Ej3Parte2 {
    public static void main(String[] args) {
        int capacidadParking = 20;
        Parking_Ej3_Apartado2 parking = new Parking_Ej3_Apartado2(capacidadParking);

        Vehiculo[] vehiculos = new Vehiculo[50]; // 40 coches + 10 furgonetas

        for (int i = 0; i < 40; i++) {
            String matricula = "ABC-" + (i + 1);
            vehiculos[i] = new Vehiculo(matricula, parking, false);
        }

        for (int i = 40; i < 50; i++) {
            String matricula = "FGH-" + (i - 39);
            vehiculos[i] = new Vehiculo(matricula, parking, true);
        }

        // Iniciar todos los vehículos
        for (Vehiculo vehiculo : vehiculos) {
            vehiculo.start();
        }

        // Esperar a que todos los vehículos terminen
        for (Vehiculo vehiculo : vehiculos) {
            try {
                vehiculo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
