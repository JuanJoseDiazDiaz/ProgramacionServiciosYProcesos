package org.example.Encriptado_Tema5.Tarea3;

import java.io.*;
import java.net.Socket;
import java.security.*;
import javax.crypto.Cipher;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

public class Cliente {
    private static final String ALGORITMO = "RSA";

    public static void main(String[] args) {
        try {
            // Crear socket para conectar con el servidor
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            // Recibir clave pública del servidor
            int publicKeyLength = dis.readInt();
            byte[] publicKeyBytes = new byte[publicKeyLength];
            dis.readFully(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(publicKeyBytes));

            // Leer líneas de texto del usuario y enviarlas encriptadas al servidor
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce varias líneas de texto (escribe 'fin' para terminar):");
            while (true) {
                String linea = scanner.nextLine();
                if (linea.equalsIgnoreCase("fin")) break;

                // Encriptar la línea de texto
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] encryptedBytes = cipher.doFinal(linea.getBytes());

                // Enviar la línea encriptada al servidor
                dos.writeInt(encryptedBytes.length);
                dos.write(encryptedBytes);
            }

            // Cerrar la conexión después de enviar todos los datos
            dos.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

