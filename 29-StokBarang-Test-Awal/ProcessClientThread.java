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
    int jumlah =0;
    public ProcessClientThread(Socket koneksiKiriman) {
        koneksi = koneksiKiriman;
    }

    public void run() {
        if (koneksi != null)
            prosesPermintaanClient();
    }

    private void prosesPermintaanClient() {
      try {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        String baris;
        String angkaPeriksaStr = Integer.toString(jumlah);
            
        do{
            // Ambil dan tampilkan masukan
            InputStream masukan = koneksi.getInputStream();
            BufferedReader masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
            baris = masukanReader.readLine();
            baris = baris.toUpperCase();
            System.out.println(baris);   
                        
            if((baris.charAt(0))=='T'){
                jumlah++;
                System.out.println("jumlah "+jumlah); 
            }
            else if((baris.charAt(0))=='K'){
                jumlah--;
                System.out.println("jumlah "+jumlah); 
            }
            else if((baris.charAt(0))=='J'){
                // Kirim ke client
                OutputStream keluaran = koneksi.getOutputStream();
                BufferedWriter keluaranBuf = new BufferedWriter(new OutputStreamWriter(keluaran)); 
                angkaPeriksaStr = Integer.toString(jumlah);
                //synchronized(angkaPeriksaStr) {
                keluaranBuf.write(angkaPeriksaStr);
                keluaranBuf.newLine();
                keluaranBuf.flush();                
                //}
            }
        }while((baris.charAt(0)!='S'));
       

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