package org.example.Tema2.Tarea2.BeberSidra;


class MonitorSidra2 {
    private boolean sidraEscanciada = false; // Estado para controlar si la sidra está escanciada

    public synchronized void camareroEscancia() {
        // Escancia la sidra y notifica a los clientes que ya pueden beber
        while (sidraEscanciada) {
            try {
                wait(); // Espera hasta que la sidra sea bebida
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Sidra escanciada...");
        sidraEscanciada = true;
        notifyAll(); // Notifica a los clientes que pueden beber
    }

    public synchronized void beboSidra() {
        // El cliente intenta beber solo si la sidra está escanciada
        while (!sidraEscanciada) {
            try {
                wait(); // Espera hasta que la sidra esté escanciada
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Que rica!");
        sidraEscanciada = false;
        notifyAll(); // Notifica al camarero que puede escanciar más sidra
    }
}

