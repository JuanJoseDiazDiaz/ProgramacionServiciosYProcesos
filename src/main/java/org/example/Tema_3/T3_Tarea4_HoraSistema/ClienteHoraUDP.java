package org.example.Tema_3.T3_Tarea4_HoraSistema;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ClienteHoraUDP {
    public static void main(String[] args) {
        final String direccionServidor = "localhost";
        final int puertoServidor = 12345;

        try {
            DatagramSocket socket = new DatagramSocket();

            // Enviar un paquete vacío al servidor
            byte[] buffer = new byte[1024];
            DatagramPacket paqueteEnviar = new DatagramPacket(buffer, buffer.length, java.net.InetAddress.getByName(direccionServidor), puertoServidor);
            socket.send(paqueteEnviar);

            // Preparar el paquete para recibir la respuesta
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
            socket.receive(paqueteRecibido);

            // Convertir los datos recibidos a String
            String fechaHora = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("Fecha y Hora del Servidor: " + fechaHora);

            // Cerrar el socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
