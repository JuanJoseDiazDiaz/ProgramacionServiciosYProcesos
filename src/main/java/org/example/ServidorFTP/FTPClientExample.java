package org.example.ServidorFTP;
import org.apache.commons.net.ftp.*;
import java.io.*;
import java.util.Scanner;

public class FTPClientExample {
    private static FTPClient ftpClient = new FTPClient();
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String server = "192.168.4.101";
        int port = 21;
        String user = "usuario";
        String pass = "usuario";
        connectToServer(server, port, user, pass);

        try {
            boolean exit = false;
            while (!exit) {
                System.out.println("OPCIONES: ");
                System.out.println(" Listar (ls) ");
                System.out.println(" crear directorios (mkdir) ");
                System.out.println(" subir archivo (put) ");
                System.out.println(" descargar archivo (get)");
                System.out.println(" eliminar archivo (delete) ");
                System.out.println(" volver a la raiz (cd ..) ");
                System.out.println(" eliminar directorio (rmdir) ");
                System.out.println(" cerrar sesion (close) ");
                System.out.println(" salir (quit)");
                System.out.println("Introduce el comando a usar:");
                String comando = scanner.nextLine();


                switch (comando) {
                    case "ls":
                        listFiles();
                        break;
                    case "mkdir":
                        System.out.println("Introduce el nuevo directorio");
                        String newDirectory = scanner.next();
                        createAndChangeDirectory(newDirectory);
                        break;
                    case "put":
                        System.out.println("Introduce el nombre archivo local");
                        String localArchivo = scanner.next();
                        System.out.println("Introduce el nombre archivo remoto");
                        String remotoArchivo = scanner.next();
                        uploadFile(localArchivo, remotoArchivo);
                        break;
                    case "get":
                        System.out.println("Introduce el archivo a descargar");
                        String downloadfile = scanner.next();
                        downloadFile(downloadfile, "descargado.txt");
                        break;
                    case "delete":
                        System.out.println("Introduce el archivo a eliminar");
                        String deletefile = scanner.next();
                        deleteFile(deletefile);
                        break;
                    case "cd ..":
                        returnToRoot();
                        break;
                    case "rmdir":
                        System.out.println("Introduce el directorio a eliminar");
                        String deleteDirectory = scanner.next();
                        deleteDirectory(deleteDirectory);
                        break;
                    case "close":
                        closeConnection();
                        break;
                    case "quit":
                        exit = true;
                        closeConnection();
                        System.out.println("Saliendo...");
                        break;
                }
            }

            scanner.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void connectToServer(String server, int port, String user, String pass) throws IOException {
        ftpClient.connect(server, port);
        ftpClient.login(user, pass);
        ftpClient.enterLocalPassiveMode();
        System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
    }

    private static void listFiles() throws IOException {
        FTPFile[] files = ftpClient.listFiles();
        for (FTPFile file : files) {
            System.out.println("Archivo: " + file.getName());
        }
    }

    private static void createAndChangeDirectory(String newDir) throws IOException {
        ftpClient.makeDirectory(newDir);
        System.out.println("Directorio creado: " + newDir);
        ftpClient.changeWorkingDirectory(newDir);
        System.out.println("Directorio actual: " + ftpClient.printWorkingDirectory());
    }

    private static void uploadFile(String localFile, String remoteFile) throws IOException {
        File file = new File(localFile);

        // Verificar si el archivo existe
        if (!file.exists()) {
            System.out.println("El archivo no existe en la ruta: " + file.getAbsolutePath());
            return;
        }

        // Verificar permisos
        if (!file.canRead()) {
            System.out.println("No tienes permisos para leer el archivo.");
            return;
        }

        // Abrir el archivo
        try (InputStream inputStream = new FileInputStream(file)) {
            boolean done = ftpClient.storeFile(remoteFile, inputStream);
            if (done) {
                System.out.println("Archivo subido correctamente.");
            } else {
                System.out.println("Error al subir el archivo: " + ftpClient.getReplyString());
            }
        }
    }

    private static void downloadFile(String remoteFile, String localFile) throws IOException {
        File file = new File(localFile);
        OutputStream outputStream = new FileOutputStream(file);
        boolean success = ftpClient.retrieveFile(remoteFile, outputStream);
        outputStream.close();
        if (success) {
            System.out.println("Archivo descargado correctamente.");
        }
    }

    private static void deleteFile(String fileName) throws IOException {
        ftpClient.deleteFile(fileName);
        System.out.println("Archivo eliminado.");
    }

    private static void returnToRoot() throws IOException {
        ftpClient.changeWorkingDirectory("/");
    }

    private static void deleteDirectory(String dirName) throws IOException {
        ftpClient.removeDirectory(dirName);
        System.out.println("Directorio eliminado: " + dirName);
    }

    private static void closeConnection() throws IOException {
        ftpClient.logout();
        ftpClient.disconnect();
        System.out.println("Conexión cerrada.");
    }
}

