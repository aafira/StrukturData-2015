import java.io.IOException;

import java.net.Socket;
import java.net.ServerSocket;

import java.util.ArrayList;

public class Server {
    public Server() throws IOException {
        serverSocket = new ServerSocket(PORT);
        simpan = new Penyimpanan();
    }
    
    public void dengar() throws IOException {
        while (true) {
            // Tunggu sampai ada koneksi dari client
            Socket koneksi = serverSocket.accept();
                
            // Buat thread untuk tangani client
            ClientProcess clientProcess = new ClientProcess(koneksi, simpan);
            Thread clientProcessThread = new Thread(clientProcess);
            clientProcessThread.start();                
        }
    }
    
    private Penyimpanan simpan=null;
    // Socket server
    private ServerSocket serverSocket = null;
    // Port untuk aplikasi ini
    private static final int PORT = 33333;

}
