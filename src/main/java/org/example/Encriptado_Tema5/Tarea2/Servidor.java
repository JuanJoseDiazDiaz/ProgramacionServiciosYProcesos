package org.example.Encriptado_Tema5.Tarea2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Servidor {
    private static final String ALGORITMO = "AES";

    public static void main(String[] args) {
        try {
            // Crear socket del servidor
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                // Leer la clave secreta enviada por el cliente
                int keyLength = dis.readInt();
                byte[] keyBytes = new byte[keyLength];
                dis.readFully(keyBytes);
                SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITMO);

                // Leer y desencriptar las líneas de texto enviadas por el cliente
                while (dis.available() > 0) {
                    int length = dis.readInt();
                    byte[] encryptedBytes = new byte[length];
                    dis.readFully(encryptedBytes);

                    // Desencriptar la línea de texto
                    Cipher cipher = Cipher.getInstance(ALGORITMO);
                    cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
                    byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
                    String linea = new String(decryptedBytes);

                    // Mostrar la línea desencriptada
                    System.out.println("Mensaje recibido y desencriptado: " + linea);
                }

                dis.close();
                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


