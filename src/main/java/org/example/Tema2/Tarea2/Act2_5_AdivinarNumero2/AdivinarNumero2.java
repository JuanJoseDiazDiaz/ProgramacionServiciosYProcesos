package org.example.Tema2.Tarea2.Act2_5_AdivinarNumero2;

public class AdivinarNumero2 {
    public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();

        int numHilos = 10; // Número de hilos adivinadores
        AdivinadorNumero[] hilos = new AdivinadorNumero[numHilos];

        for (int i = 0; i < numHilos; i++) {
            hilos[i] = new AdivinadorNumero(numeroOculto);
            hilos[i].start();
        }

        // Esperar a que todos los hilos terminen
        for (int i = 0; i < numHilos; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
