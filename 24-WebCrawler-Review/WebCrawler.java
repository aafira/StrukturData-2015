
/**
 * Write a description of class Chat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;


public class WebCrawler
{
    public void crawler(String pesan) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("google.co.id", 80);

        // Kirim perintah untuk informasi namaDomain
        kirimPesan(pesan);
        
        // Baca balasannya
        bacaBalasan();
        
        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }

    public void kirimPesan(String pesan) throws IOException {
        Writer keluaranWriter = new OutputStreamWriter(koneksi.getOutputStream()); 
        BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
        keluaranBuff.write(pesan);
        keluaranBuff.newLine();
        keluaranBuff.flush();        
    }
    
    public void bacaBalasan() throws IOException {
        Reader masukan = new InputStreamReader(koneksi.getInputStream()); 
        BufferedReader masukanBuff = new BufferedReader(masukan);
        
        System.out.println("Server: ");
       
        
        // Selagi masih ada data baca         
        int data = masukanBuff.read();
        while (data != -1) {
            System.out.write((char) data);            
            data = masukanBuff.read();                       
        }
    }
    
    private Socket koneksi = null;
}
