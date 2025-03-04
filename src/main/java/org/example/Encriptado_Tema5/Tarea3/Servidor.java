package org.example.Encriptado_Tema5.Tarea3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import javax.crypto.Cipher;
import java.util.Base64;

public class Servidor {
    private static final String ALGORITMO = "RSA";
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    public static void main(String[] args) {
        try {
            // Generar par de claves (pública y privada)
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITMO);
            keyGen.initialize(2048);
            KeyPair pair = keyGen.generateKeyPair();
            publicKey = pair.getPublic();
            privateKey = pair.getPrivate();

            // Crear socket del servidor
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Servidor esperando conexiones...");

            while (true) {
                Socket socket = serverSocket.accept();
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                DataInputStream dis = new DataInputStream(socket.getInputStream());

                // Enviar clave pública al cliente
                byte[] publicKeyBytes = publicKey.getEncoded();
                dos.writeInt(publicKeyBytes.length);
                dos.write(publicKeyBytes);

                // Leer y desencriptar las líneas de texto enviadas por el cliente
                while (dis.available() > 0) {
                    int length = dis.readInt();
                    byte[] encryptedBytes = new byte[length];
                    dis.readFully(encryptedBytes);

                    // Desencriptar la línea de texto
                    Cipher cipher = Cipher.getInstance(ALGORITMO);
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
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

