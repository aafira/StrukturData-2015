import java.io.IOException;

import java.util.Scanner;

public class UtamaClient {
    public static void main(String[] args) {
        try {
            // Minta IP dari server
            Scanner keyboard = new Scanner(System.in);
            System.out.print("IP: ");
            String ipStr = keyboard.nextLine();
            
            // Buat client untuk berhubungan dengan server
            Client client = new Client(ipStr, 33333);
            
            // Ulangi pengiriman perintah selama belum disuruh akhiri
            //while (true) {
                // Baca perintah
                System.out.print("Perintah: ");
                String perintah = keyboard.nextLine();
                // Hilang spasi depan & belakang serta ubah ke huruf besar semua
                perintah = perintah.trim().toUpperCase();
   
                // Kirim perintah ke server
                String balasan = client.perintah(perintah);                
                // Jika ada balasan dari server, tampilkan
                if (balasan != null) {
                    System.out.print("Server: ");
                    System.out.println(balasan);
                }
                
                System.out.println();
            //}
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
    }
}