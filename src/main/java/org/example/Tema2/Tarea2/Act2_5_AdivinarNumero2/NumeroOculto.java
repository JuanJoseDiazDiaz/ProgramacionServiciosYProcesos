package org.example.Tema2.Tarea2.Act2_5_AdivinarNumero2;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class NumeroOculto {
    private final int numeroOculto;
    private AtomicInteger partidaActual;

    public NumeroOculto() {
        numeroOculto = new Random().nextInt(101); // Genera un número oculto entre 0 y 100
        partidaActual = new AtomicInteger(1); // Inicializa la partida en 1
    }

    public int obtenerPartidaActual() {
        return partidaActual.get();
    }

    public synchronized int propuestaNumero(int num, int partida) {
        if (partida != partidaActual.get()) {
            // El hilo está tratando de adivinar en una partida que ya ha terminado
            return -1;
        }
        if (num == numeroOculto) {
            // El número ha sido adivinado en esta partida
            partidaActual.incrementAndGet(); // Avanzamos a la siguiente partida
            return 1;
        }
        return 0; // El número no ha sido adivinado aún
    }
}

class AdivinadorNumero extends Thread {
    private NumeroOculto numeroOculto;

    public AdivinadorNumero(NumeroOculto numeroOculto) {
        this.numeroOculto = numeroOculto;
    }

    @Override
    public void run() {
        int partida;
        do {
            partida = numeroOculto.obtenerPartidaActual();
            int num = new Random().nextInt(101); // Genera un número aleatorio
            int resultado = numeroOculto.propuestaNumero(num, partida);
            if (resultado == 1) {
                System.out.println("Hilo " + getId() + " ha adivinado el número en la partida " + partida);
                return; // Termina la ejecución del hilo
            } else if (resultado == -1) {
                System.out.println("Hilo " + getId() + " intentó adivinar en una partida que ya ha terminado.");
                return; // Termina la ejecución del hilo
            }
        } while (true);
    }
}

