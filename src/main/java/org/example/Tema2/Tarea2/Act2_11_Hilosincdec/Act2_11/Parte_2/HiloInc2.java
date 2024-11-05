package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_11.Parte_2;

class HiloInc2 implements Runnable {
    private final Contador2 cont;
    private final int numIteraciones;

    HiloInc2(Contador2 c, int numIteraciones) {
        this.cont = c;
        this.numIteraciones = numIteraciones;
    }

    @Override
    public void run() {
        for (int i = 0; i < numIteraciones; i++) {
            try {
                cont.incrementa();
                System.out.println("Incrementado: " + cont.getCuenta());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class HiloDec2 implements Runnable {
    private final Contador2 cont;
    private final int numIteraciones;

    HiloDec2(Contador2 c, int numIteraciones) {
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
