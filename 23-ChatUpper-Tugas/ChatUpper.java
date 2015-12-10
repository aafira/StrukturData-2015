
/**
 * Write a description of class Chat here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.Reader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.BufferedInputStream;

import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class ChatUpper
{
    public void chat(String pesanChat) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("localhost", 33333);

        // Kirim perintah untuk informasi namaDomain
        kirimPesan(pesanChat);
        
        // Baca balasannya
        bacaBalasan();
        
        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }

    public void kirimPesan(String pesan) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran);
        // 3. Selagi ada data kirim
        keluaranWriter.write(pesan);
        // Syarat protokol-nya, semua perintah diakhiri dengan: CR & LF
        keluaranWriter.write("\r\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum terkirim
    }
    
    public void bacaBalasan() throws IOException {
        Reader masukan = new InputStreamReader(koneksi.getInputStream()); 
        BufferedReader masukanBuff = new BufferedReader(masukan);
        String baris = masukanBuff.readLine();
        System.out.println(baris);
        
        String balasan = baris.toUpperCase();
        
        System.out.println("Client: "+balasan+"\nBalasan ke Server Terkirim");
        kirimPesan(balasan);
    }
    
    private Socket koneksi = null;
}
