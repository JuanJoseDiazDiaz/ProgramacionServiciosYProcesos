package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_11.Parte_2;


public class Contador2 {
    private int cuenta = 0;
    private final int MAX_CUENTA = 100; // Valor máximo del contador

    public synchronized int getCuenta() {
        return cuenta;
    }

    public synchronized void incrementa() throws InterruptedException {
        while (cuenta >= MAX_CUENTA) {
            wait(); // Espera si se alcanza el valor máximo
        }
        cuenta++;
        notifyAll(); // Notifica a los hilos en espera
    }

    public synchronized void decrementa() throws InterruptedException {
        while (cuenta <= 0) {
            wait(); // Espera si el contador es 0
        }
        cuenta--;
        notifyAll(); // Notifica a los hilos en espera
    }
}

