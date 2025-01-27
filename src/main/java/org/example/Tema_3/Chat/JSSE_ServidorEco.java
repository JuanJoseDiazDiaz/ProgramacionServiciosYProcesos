package org.example.Tema_3.Chat;

import java.net.Socket;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.net.ServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class JSSE_ServidorEco {

    private static final String COD_TEXTO = "UTF-8";

    public static void main(String[] args) {

        if (args.length < 1) {
            System.err.println("ERROR, indicar: puerto.");
            System.exit(1);
        }
        int numPuerto = Integer.parseInt(args[0]);

        // Configurar SSL
        System.setProperty("javax.net.ssl.keyStore", "keystoreServ");
        System.setProperty("javax.net.ssl.keyStorePassword", "pwdkeystoreserv");

        SSLServerSocketFactory sfactSSL = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
        try (ServerSocket socketServidor = sfactSSL.createServerSocket(numPuerto)) {
            System.out.printf("Creado socket de servidor en puerto %d. Esperando conexiones de clientes.%n", numPuerto);

            while (true) {    // Acepta una conexión de cliente tras otra
                try (Socket socketComunicacion = socketServidor.accept()) {
                    System.out.printf("Cliente conectado desde %s:%d.%n",
                            socketComunicacion.getInetAddress().getHostAddress(),
                            socketComunicacion.getPort());

                    try (InputStream isDeCliente = socketComunicacion.getInputStream();
                         OutputStream osACliente = socketComunicacion.getOutputStream();
                         InputStreamReader isrDeCliente = new InputStreamReader(isDeCliente, COD_TEXTO);
                         BufferedReader brDeCliente = new BufferedReader(isrDeCliente);
                         OutputStreamWriter oswACliente = new OutputStreamWriter(osACliente, COD_TEXTO);
                         BufferedWriter bwACliente = new BufferedWriter(oswACliente)) {

                        String lineaRecibida;
                        while ((lineaRecibida = brDeCliente.readLine()) != null && lineaRecibida.length() > 0) {
                            bwACliente.write("#" + lineaRecibida + "#");
                            bwACliente.newLine();
                            bwACliente.flush();
                        }

                    } catch (IOException e) {
                        System.err.println("Error en la comunicación con el cliente.");
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    System.err.println("Error al aceptar la conexión de un cliente.");
                    e.printStackTrace();
                }
                System.out.println("Cliente desconectado.");
            }
        } catch (IOException e) {
            System.err.println("Error al crear el socket del servidor.");
            e.printStackTrace();
            System.exit(1);
        }
    }

}
