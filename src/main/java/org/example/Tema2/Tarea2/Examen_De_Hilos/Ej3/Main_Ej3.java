package org.example.Tema2.Tarea2.Examen_De_Hilos.Ej3;

/**
 * Clase Sincronizador que gestiona la producción y ensamblaje de mesas.
 */
class Sincronizador {
    private int numPatas = 0;
    private int numTableros = 0;
    private int numMesas = 0;
    private final int MAX_NUM_PATAS = 10;
    private final int MAX_NUM_TABLEROS = 5;
    private final int MAX_NUM_MESAS = 5;

    /**
     * Añade una pata si es posible y notifica al ensamblador.
     */
    public synchronized void ponPata() {
        // Si el número de patas llega al máximo, espera.
        while (numPatas == MAX_NUM_PATAS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numPatas++;
        System.out.println("Se ha depositado una pata. Total de patas: " + numPatas);
        // Notifico al ensamblador
        notifyAll();
    }

    /**
     * Añade un tablero si es posible y notifica al ensamblador.
     */
    public synchronized void ponTablero() {
        // Si el número de tableros llega al máximo, espera.
        while (numTableros == MAX_NUM_TABLEROS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numTableros++;
        System.out.println("Se ha depositado un tablero. Total de tableros: " + numTableros);
        // Notifico al ensamblador
        notifyAll();
    }

    /**
     * Ensambla una mesa si hay disponibles 4 patas y 1 tablero.
     * Si llega a 5 mesas, para de ensamblar.
     */
    public synchronized void cogePatasyTablero() {
        while (numPatas < 4 || numTableros < 1 || numMesas == MAX_NUM_MESAS) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numPatas -= 4;
        numTableros--;
        numMesas++;
        System.out.println("Mesa ensamblada. Total de mesas: " + numMesas);
        // Notifico a los fabricantes
        notifyAll();
    }
}

/**
 * Clase que representa un fabricante de patas.
 */
class FabricantePatas implements Runnable {
    private Sincronizador sincronizador;

    /**
     * Constructor de la clase FabricantePatas.
     *
     * @param sincronizador instancia de la clase Sincronizador.
     */
    public FabricantePatas(Sincronizador sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sincronizador.ponPata();
                // Añade 1 segundo al tiempo de ejecución para ver claro la ejecución por consola.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * Clase que representa un fabricante de tableros.
 */
class FabricanteTableros implements Runnable {
    private Sincronizador sincronizador;

    /**
     * Constructor de la clase FabricanteTableros.
     *
     * @param sincronizador instancia de la clase Sincronizador.
     */
    public FabricanteTableros(Sincronizador sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sincronizador.ponTablero();
                // Añade 2 segundos al tiempo de ejecución para ver claro la ejecución por consola.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

/**
 * Clase que representa un ensamblador de mesas.
 */
class EnsambladorMesas implements Runnable {
    private Sincronizador sincronizador;

    /**
     * Constructor de la clase EnsambladorMesas.
     *
     * @param sincronizador instancia de la clase Sincronizador.
     */
    public EnsambladorMesas(Sincronizador sincronizador) {
        this.sincronizador = sincronizador;
    }

    @Override
    public void run() {
        while (true) {
            // Ensambla las mesas en cuanto se cumplan las condiciones.
            sincronizador.cogePatasyTablero();
        }
    }
}

/**
 * Clase principal que crea y ejecuta los hilos.
 */
public class Main_Ej3 {
    public static void main(String[] args) {
        Sincronizador sincronizador = new Sincronizador();

        Thread fabricantePatas = new Thread(new FabricantePatas(sincronizador));
        Thread fabricanteTableros = new Thread(new FabricanteTableros(sincronizador));
        Thread ensambladorMesas = new Thread(new EnsambladorMesas(sincronizador));

        fabricantePatas.start();
        fabricanteTableros.start();
        ensambladorMesas.start();
    }
}
