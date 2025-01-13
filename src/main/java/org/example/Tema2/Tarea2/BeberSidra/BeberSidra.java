package org.example.Tema2.Tarea2.BeberSidra;

class BebiendoSidra {
    public static void main(String[] args) throws InterruptedException {
        MonitorSidra2 objetoMonitor = new MonitorSidra2();
        Camarero hilocam = new Camarero( );
        Cliente hilocli = new Cliente( );
        hilocli.start();
        hilocam.start();
    }
}
class Camarero extends Thread{
    public void run() {
        System.out.println("Sidra escanciada...");
    }
}
class Cliente extends Thread{
    public void run() {
        System.out.println("Que rica !");
    }
}
