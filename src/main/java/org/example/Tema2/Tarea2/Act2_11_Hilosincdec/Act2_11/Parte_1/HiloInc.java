package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_11.Parte_1;

class HiloInc implements Runnable {
    private final Contador cont;
    private final int numIteraciones;

    public HiloInc(Contador c, int numIteraciones) {
        this.cont = c;
        this.numIteraciones = numIteraciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIteraciones; i++) {
            cont.incrementa();
            System.out.println("Incrementado: " + cont.getCuenta());
        }
    }
}
class HiloDec implements Runnable {
    private final Contador cont;
    private final int numIteraciones;

    public HiloDec(Contador c, int numIteraciones) {
        this.cont = c;
        this.numIteraciones = numIteraciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIteraciones; i++) {
            try {
                cont.decrementa();
                System.out.println("Decrementado: " + cont.getCuenta());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}