package org.example.Tarea2;

import java.io.File;
import java.io.IOException;


public class Act_2_Ej1 {
    public static void main(String[] args) {
        // Directorio por defecto de ejecución del proceso
        String directorioDefault = System.getProperty("user.dir");
        System.out.println("Directorio de ejecución por defecto: " + directorioDefault);

        ProcessBuilder pb = new ProcessBuilder("ls");

        // Directorios en los que ejecutaremos el comando 'ls'
        String[] directorios = {directorioDefault, "/home", "/etc"};

        // Ejecutamos el comando 'ls' en cada directorio
        for (String directorio : directorios) {
            pb.directory(new File(directorio));
            try {
                Process proceso = pb.start();
                int resultado = proceso.waitFor();

                if (resultado == 0) {
                    System.out.println("Comando 'ls' ejecutado correctamente en " + directorio);
                } else {
                    System.err.println("Error al ejecutar el comando 'ls' en " + directorio);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
