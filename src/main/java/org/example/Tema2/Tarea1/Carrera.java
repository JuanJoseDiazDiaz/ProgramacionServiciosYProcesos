package org.example.Tarea1;

public class Carrera {
        public static void main(String[] args) {
            Corredor corredor1 = new Corredor("Corredor 1");
            Corredor corredor2 = new Corredor("Corredor 2");
            Corredor corredor3 = new Corredor("Corredor 3");
            Corredor corredor4 = new Corredor("Corredor 4");
            Corredor corredor5 = new Corredor("Corredor 5");

            corredor1.start();
            corredor2.start();
            corredor3.start();
            corredor4.start();
            corredor5.start();
        }

}
