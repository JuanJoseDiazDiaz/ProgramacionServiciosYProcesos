package org.example.Tema_3.T3_Tarea2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class EcoClienteHandler implements Runnable {
    private Socket clienteSocket;

    public EcoClienteHandler(Socket socket) {
        this.clienteSocket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clienteSocket.getOutputStream(), true);

            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                System.out.println("Recibido: " + mensaje);
                out.println(mensaje); // Enviar de vuelta el mensaje al cliente
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
