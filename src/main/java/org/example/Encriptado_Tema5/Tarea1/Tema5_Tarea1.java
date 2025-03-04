package org.example.Encriptado_Tema5.Tarea1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Tema5_Tarea1 {
    public static void main(String[] args) {
        try {
            // Pedir líneas de texto por consola y guardarlas en un ArrayList
            Scanner scanner = new Scanner(System.in);
            ArrayList<String> arrayInicial = new ArrayList<>();
            System.out.println("Introduce varias líneas de texto (escribe 'fin' para terminar):");
            while (true) {
                String linea = scanner.nextLine();
                if (linea.equalsIgnoreCase("fin")) break;
                arrayInicial.add(linea);
            }

            // Escribir el ArrayList en un fichero de texto
            FileWriter writer = new FileWriter("archivoOriginal.txt");
            for (String linea : arrayInicial) {
                writer.write(linea + "\n");
            }
            writer.close();

            // Leer el contenido del fichero original
            FileInputStream fis = new FileInputStream("archivoOriginal.txt");
            byte[] inputBytes = new byte[(int) new File("archivoOriginal.txt").length()];
            fis.read(inputBytes);
            fis.close();

            // Generar una clave secreta para encriptar y desencriptar
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            SecretKey secretKey = keyGen.generateKey();
            byte[] keyBytes = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

            // Encriptar el contenido del fichero
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] encryptedBytes = cipher.doFinal(inputBytes);

            // Guardar el contenido encriptado en un nuevo fichero
            FileOutputStream fos = new FileOutputStream("archivo_encriptado.txt");
            fos.write(encryptedBytes);
            fos.close();

            // Leer el contenido del fichero encriptado
            FileInputStream fisEncrypted = new FileInputStream("archivo_encriptado.txt");
            byte[] encryptedInputBytes = new byte[(int) new File("archivo_encriptado.txt").length()];
            fisEncrypted.read(encryptedInputBytes);
            fisEncrypted.close();

            // Desencriptar el contenido del fichero encriptado
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            byte[] decryptedBytes = cipher.doFinal(encryptedInputBytes);

            // Escribir el contenido desencriptado en un nuevo fichero (opcional)
            FileOutputStream fosDecrypted = new FileOutputStream("archivo_desencriptado.txt");
            fosDecrypted.write(decryptedBytes);
            fosDecrypted.close();

            // Comprobar línea a línea que son las mismas que hay en el ArrayList inicial
            BufferedReader reader = new BufferedReader(new FileReader("archivo_desencriptado.txt"));
            String linea;
            int index = 0;
            boolean iguales = true;
            while ((linea = reader.readLine()) != null) {
                if (!linea.equals(arrayInicial.get(index))) {
                    iguales = false;
                    break;
                }
                index++;
            }
            reader.close();

            if (iguales) {
                System.out.println("Las líneas desencriptadas coinciden con el ArrayList inicial.");
            } else {
                System.out.println("Las líneas desencriptadas NO coinciden con el ArrayList inicial.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

