package org.example.Tema2.Tarea2.BeberSidra;

public class Bebiendo_Sidra {
    public static void main(String[] args) {
        MonitorSidra objetoMonitor = new MonitorSidra();
        Camarero2 hilocam = new Camarero2(objetoMonitor);
        Cliente2 hilocli = new Cliente2(objetoMonitor);

        hilocam.start();
        hilocli.start();
    }
}

class Camarero2 extends Thread {
    private final MonitorSidra monitor;

    public Camarero2(MonitorSidra monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            monitor.camareroEscancia(); // Escancia la sidra
            try {
                Thread.sleep(1000); // Simula el tiempo para volver a escanciar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class Cliente2 extends Thread {
    private MonitorSidra monitor;

    public Cliente2(MonitorSidra monitor) {
        this.monitor = monitor;
    }

    public void run() {
        while (true) {
            monitor.beboSidra(); // Bebe la sidra si ya está escanciada
            try {
                Thread.sleep(1500); // Simula el tiempo de espera entre bebidas
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
