package org.example.Tema2.Tarea2.Examen_De_Hilos.Ej2;

import java.util.concurrent.*;

/**
 * Clase Monitor que gestiona los recursos compartidos mediante semáforos.
 */
class Monitor {

    // Semáforos para gestionar el acceso a los recursos.
    private Semaphore semaforoR1 = new Semaphore(1);
    private Semaphore semaforoR2 = new Semaphore(1);
    private Semaphore semaforoR3 = new Semaphore(1);

    /**
     * Método para requerir el recurso R1.
     * El hilo espera hasta que el recurso esté disponible.
     */
    public void requiereR1() {
        System.out.println(Thread.currentThread().getName() + ": Esperando por R1");
        try {
            semaforoR1.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Obtenido R1");
    }

    /**
     * Método para requerir los recursos R2 y R3.
     * El hilo espera hasta que ambos recursos estén disponibles.
     */
    public void requiereR2_R3() {
        System.out.println(Thread.currentThread().getName() + ": Esperando por R2 y R3");
        try {
            semaforoR2.acquire();
            semaforoR3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Obtenido R2 y R3");
    }

    /**
     * Método para requerir los recursos R1, R2 y R3.
     * El hilo espera hasta que los tres recursos estén disponibles.
     */
    public void requiereR1_R2_R3() {
        System.out.println(Thread.currentThread().getName() + ": Esperando por R1, R2 y R3");
        try {
            semaforoR1.acquire();
            semaforoR2.acquire();
            semaforoR3.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": Obtenido R1, R2 y R3");
    }

    /**
     * Método para liberar el recurso R1.
     */
    public void liberaR1() {
        System.out.println(Thread.currentThread().getName() + ": Libera R1");
        semaforoR1.release();
    }

    /**
     * Método para liberar los recursos R2 y R3.
     */
    public void liberaR2_R3() {
        System.out.println(Thread.currentThread().getName() + ": Libera R2 y R3");
        semaforoR2.release();
        semaforoR3.release();
    }

    /**
     * Método para liberar los recursos R1, R2 y R3.
     */
    public void liberaR1_R2_R3() {
        System.out.println(Thread.currentThread().getName() + ": Libera R1, R2 y R3");
        semaforoR1.release();
        semaforoR2.release();
        semaforoR3.release();
    }
}

/**
 * Clase T1 que representa un hilo que requiere el recurso R1.
 */
class T1 implements Runnable {
    private Monitor monitor;

    /**
     * Constructor de la clase T1.
     *
     * @param monitor instancia de la clase Monitor para gestionar recursos.
     */
    public T1(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        monitor.requiereR1();
        System.out.println(Thread.currentThread().getName() + ": Usa R1");
        // Aquí realizaría las operaciones con R1
        monitor.liberaR1();
    }
}

/**
 * Clase T2 que representa un hilo que requiere los recursos R2 y R3.
 */
class T2 implements Runnable {
    private Monitor monitor;

    /**
     * Constructor de la clase T2.
     *
     * @param monitor instancia de la clase Monitor para gestionar recursos.
     */
    public T2(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        monitor.requiereR2_R3();
        System.out.println(Thread.currentThread().getName() + ": Usa R2 y R3");
        // Aquí realizaría las operaciones con R2 y R3
        monitor.liberaR2_R3();
    }
}

/**
 * Clase T3 que representa un hilo que requiere los recursos R1, R2 y R3.
 */
class T3 implements Runnable {
    private Monitor monitor;

    /**
     * Constructor de la clase T3.
     *
     * @param monitor instancia de la clase Monitor para gestionar recursos.
     */
    public T3(Monitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        monitor.requiereR1_R2_R3();
        System.out.println(Thread.currentThread().getName() + ": Usa R1, R2 y R3");
        // Aquí realizaría las operaciones con R1, R2 y R3
        monitor.liberaR1_R2_R3();
    }
}

/**
 * Clase principal que crea y ejecuta los hilos.
 */
public class Main_Ej2 {
    public static void main(String[] args) {
        Monitor monitor = new Monitor();

        Thread t1 = new Thread(new T1(monitor), "T1");
        Thread t2 = new Thread(new T2(monitor), "T2");
        Thread t3 = new Thread(new T3(monitor), "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
