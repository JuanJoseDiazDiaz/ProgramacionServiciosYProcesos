package org.example.Tarea4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class PowerShellApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String command;

            System.out.println("PowerShell Shell. Introduce 'exit' para salir.");

            while (true) {
                System.out.print("PS > ");
                command = scanner.nextLine();

                if (command.equalsIgnoreCase("exit")) {
                    System.out.println("Saliendo de PowerShell...");
                    break;
                }

                // Ejecutar el comando en PowerShell
                ProcessBuilder processBuilder = new ProcessBuilder("pwsh", "-Command", command);
                processBuilder.redirectErrorStream(true);
                Process process = processBuilder.start();

                // Leer la salida del comando
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
