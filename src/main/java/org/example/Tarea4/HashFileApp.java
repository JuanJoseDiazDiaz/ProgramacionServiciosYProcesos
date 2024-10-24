package org.example.Tarea4;

import java.io.*;

public class HashFileApp {
    public static void main(String[] args) {
        try {
            // Crear el archivo de entrada
            String fileName = "entrada.txt";
            File file = new File(fileName);
            if (!file.exists()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write("Este es un archivo de ejemplo.");
                writer.close();
                System.out.println("Archivo 'entrada.txt' generado.");
            }

            // Calcular el hash del archivo usando sha256sum
            ProcessBuilder processBuilder = new ProcessBuilder("sha256sum", fileName);
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            // Leer y mostrar el hash
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("Hash del archivo: " + line);
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Error al calcular el hash.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
