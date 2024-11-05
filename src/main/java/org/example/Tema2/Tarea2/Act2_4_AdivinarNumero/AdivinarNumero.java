package org.example.Tema2.Tarea2.Act2_4_AdivinarNumero;

public class AdivinarNumero {
    public static void main(String[] args) {
        NumeroOculto numeroOculto = new NumeroOculto();

        Thread generador = new Thread(() -> {
            while (true) {
                // Simplemente espera hasta que un adivinador adivine el número
                if (numeroOculto.propuestaNumero(0) == 1) {
                    System.out.println("El número ha sido adivinado. Juego terminado.");
                    break;
                }
            }
        });

        generador.start();

        Adivinador[] adivinadores = new Adivinador[10];

        for (int i = 0; i < 10; i++) {
            adivinadores[i] = new Adivinador(numeroOculto);
            adivinadores[i].start();
        }

        try {
            generador.join();
            for (Adivinador adivinador : adivinadores) {
                adivinador.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
