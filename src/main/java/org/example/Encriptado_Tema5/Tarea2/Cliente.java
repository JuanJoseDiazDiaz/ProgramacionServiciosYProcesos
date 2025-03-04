package org.example.Encriptado_Tema5.Tarea2;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Cliente {
    private static final String ALGORITMO = "AES";

    public static void main(String[] args) {
        try {
            // Generar clave secreta
            KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITMO);
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, ALGORITMO);

            // Crear socket para conectar con el servidor
            Socket socket = new Socket("localhost", 12345);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            // Enviar la clave secreta al servidor
            dos.writeInt(keyBytes.length);
            dos.write(keyBytes);

            // Leer líneas de texto del usuario y enviarlas encriptadas al servidor
            Scanner scanner = new Scanner(System.in);
            System.out.println("Introduce varias líneas de texto (escribe 'fin' para terminar):");
            while (true) {
                String linea = scanner.nextLine();
                if (linea.equalsIgnoreCase("fin")) break;

                // Encriptar la línea de texto
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
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


