package org.example.Tema_3.T3_Tarea2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEcoTCP {
    public static void main(String[] args) {
        final String direccionServidor = "localhost";
        final int puertoServidor = 12345;

        try {
            Socket socket = new Socket(direccionServidor, puertoServidor);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            String mensaje;
            while (true) {
                System.out.print("Ingrese un mensaje (o 'exit' para salir): ");
                mensaje = scanner.nextLine();
                if ("exit".equalsIgnoreCase(mensaje)) {
                    break;
                }
                out.println(mensaje);
                System.out.println("Echo del servidor: " + in.readLine());
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
