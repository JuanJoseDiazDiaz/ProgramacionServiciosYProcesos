package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_11.Parte_1;

class Contador {
    private int cuenta = 0;

    public synchronized int getCuenta() {
        return cuenta;
    }

    public synchronized void incrementa() {
        cuenta++;
        notifyAll(); // Notifica a los hilos en espera que pueden intentar decrementar
    }

    public synchronized void decrementa() throws InterruptedException {
        while (cuenta < 1) {
            wait(); // Espera no activa si el contador es 0
        }
        cuenta--;
    }
}
