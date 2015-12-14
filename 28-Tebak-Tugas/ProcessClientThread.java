import java.net.Socket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.util.Scanner;

public class ProcessClientThread implements Runnable {
    public ProcessClientThread(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
        if (koneksi != null)
            prosesPermintaanClient();
    }

    private void prosesPermintaanClient() {
      try {
        String bil = "3";
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        int n=0;
        while(n<3){
            n++;
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            String baris = masukanReader.readLine();
            System.out.println(baris);
            
            if((baris.charAt(0))=='3'){
                // Kirim ke client
                OutputStream keluaran = koneksi.getOutputStream();
                BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                keluaranBuf.write("ANDA BENAR");
                keluaranBuf.newLine();
                keluaranBuf.flush();
                break;
            }
            else if((baris.charAt(0))!='3'){
                // Kirim ke client
                OutputStream keluaran = koneksi.getOutputStream();
                BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                keluaranBuf.write("SALAH");
                keluaranBuf.newLine();
                keluaranBuf.flush();
            }
        }

        // Tunggu 2 detik
        System.out.println("Tunggu...");
        Thread.sleep(2000);
        System.out.println("Selesai tunggu...");

        koneksi.close();
        
        }
        catch(IOException err) {
            System.out.println(err);
        }
        catch(InterruptedException err) {
            System.out.println(err);
        }
    }

    private Socket koneksi; 
}