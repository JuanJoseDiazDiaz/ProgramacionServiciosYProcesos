package org.example.Tema2.Tarea2.Act2_11_Hilosincdec.Act2_12.Parte_1;

import java.util.Random;

class Puente {
    private static final int MAX_PERSONAS = 3;
    private static final int MAX_PESO = 200;
    private int pesoActual = 0;
    private int personasActuales = 0;

    public synchronized void entrar(Persona persona) throws InterruptedException {
        while (personasActuales >= MAX_PERSONAS || pesoActual + persona.getPeso() > MAX_PESO) {
            wait();
        }
        personasActuales++;
        pesoActual += persona.getPeso();
        System.out.println(persona + " entra al puente. Personas: " + personasActuales + ", Peso total: " + pesoActual);
    }

    public synchronized void salir(Persona persona) {
        personasActuales--;
        pesoActual -= persona.getPeso();
        System.out.println(persona + " sale del puente. Personas: " + personasActuales + ", Peso total: " + pesoActual);
        notifyAll();
    }
}
class Persona implements Runnable {
    private static int contador = 0;
    private final int id;
    private final int peso;
    private final Puente puente;
    private static final Random random = new Random();

    public Persona(Puente puente) {
        this.id = ++contador;
        this.peso = random.nextInt(81) + 40; // 40 a 120 kg
        this.puente = puente;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public void run() {
        try {
            puente.entrar(this);
            Thread.sleep(random.nextInt(41) * 1000 + 10000); // 10 a 50 segundos
            puente.salir(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Persona " + id + " (" + peso + " kg)";
    }
}

public class PasoPorPuente {
    public static void main(String[] args) {
        Puente puente = new Puente();
        Random random = new Random();

        while (true) {
            new Thread(new Persona(puente)).start();
            try {
                Thread.sleep(random.nextInt(30) * 1000 + 1000); // 1 a 30 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}