package org.example.Tema_3.T3_Tarea4_HoraSistema;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServidorHoraUDP {
    public static void main(String[] args) {
        final int puerto = 12345;

        try {
            DatagramSocket socket = new DatagramSocket(puerto);
            System.out.println("Servidor de Hora UDP iniciado en el puerto " + puerto);

            while (true) {
                // Preparar el buffer para recibir el paquete
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

                // Esperar a recibir un paquete
                socket.receive(paqueteRecibido);
                System.out.println("Cliente conectado desde " + paqueteRecibido.getAddress());

                // Obtener la fecha y hora actual
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaHora = sdf.format(new Date());

                // Enviar la fecha y hora al cliente
                byte[] datos = fechaHora.getBytes();
                DatagramPacket paqueteEnviar = new DatagramPacket(datos, datos.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socket.send(paqueteEnviar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
