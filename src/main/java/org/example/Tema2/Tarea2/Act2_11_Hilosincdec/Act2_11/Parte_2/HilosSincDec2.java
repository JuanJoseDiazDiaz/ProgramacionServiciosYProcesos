package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_11.Parte_2;

public class HilosSincDec2 {
    public static void main(String[] args) {
        Contador2 c = new Contador2();
        Thread[] hilos = new Thread[10];

        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new HiloInc2(c, 20));
            hilos[i + 5] = new Thread(new HiloDec2(c, 20));
        }

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Cuenta final: " + c.getCuenta());
    }
}
