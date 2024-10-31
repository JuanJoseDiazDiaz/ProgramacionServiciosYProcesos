package org.example.Tarea1;

import java.util.Random;

public class Corredor extends Thread{
    private String nombre;

    public Corredor(String nombre) {
        this.nombre = nombre;
    }
    @Override
    public void run() {
        try {
            Random random = new Random();
            for (int i = 1; i <= 10; i++) {
                int distancia = random.nextInt(100) + 1;
                System.out.println(nombre + " esta en el tramo " + i );
                Thread.sleep(1000); // Esperar 1 segundo entre los tramos de la carrera
            }
            System.out.println(nombre + " ha terminado la carrera!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


