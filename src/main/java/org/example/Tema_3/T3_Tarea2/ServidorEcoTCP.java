package org.example.Tema_3.T3_Tarea2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEcoTCP {
    public static void main(String[] args) {
        final int puerto = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor Echo TCP iniciado en el puerto " + puerto);

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Cliente conectado desde " + clienteSocket.getInetAddress());

                // Crear un nuevo hilo para manejar al cliente
                new Thread(new EcoClienteHandler(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
