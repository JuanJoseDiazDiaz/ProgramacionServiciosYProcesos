package org.example.Tema2.Tarea2.Act2_4_AdivinarNumero;

import java.util.Random;

public class NumeroOculto {
    private int numeroOculto;
    private boolean juegoTerminado;

    public NumeroOculto() {
        Random rand = new Random();
        numeroOculto = rand.nextInt(101); // Genera un número aleatorio entre 0 y 100
        juegoTerminado = false;
    }

    public synchronized int propuestaNumero(int num) {
        if (juegoTerminado) {
            return -1; // Juego terminado
        }

        if (num == numeroOculto) {
            juegoTerminado = true;
            return 1; // Número adivinado
        }

        return 0; // Número incorrecto
    }
}

class Adivinador extends Thread {
    private NumeroOculto numeroOculto;

    public Adivinador(NumeroOculto numeroOculto) {
        this.numeroOculto = numeroOculto;
    }

    @Override
    public void run() {
        while (true) {
            int num = new Random().nextInt(101); // Genera un número aleatorio para adivinar
            int resultado = numeroOculto.propuestaNumero(num);

            if (resultado == 1) {
                System.out.println("¡Hilo " + getId() + " adivinó el número!");
                break;
            } else if (resultado == -1) {
                System.out.println("¡Hilo " + getId() + " finaliza porque el juego terminó!");
                break;
            }
        }
    }
}

