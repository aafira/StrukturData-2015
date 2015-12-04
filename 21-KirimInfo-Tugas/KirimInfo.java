
/**
 * Write a description of class KirimInfo here.
 * 
 * @author Aida Afira 
 * @version 4.12.15
 */

import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.io.OutputStreamWriter;
import java.io.InputStream;


public class KirimInfo
{
    private Socket koneksi = null;
    
    public void kirimTulisan(String namaDomain) 
                throws UnknownHostException, IOException {
        // 0. Buka socket
        koneksi = new Socket("192.168.43.139", 33333);

        // Kirim perintah untuk informasi namaDomain
        kirimPerintah(namaDomain);

        // Tutup socket-nya => dengan sendirinya menutup semua stream
        koneksi.close();
    }
      
    public void kirimPerintah(String namaDomain) throws IOException {
        // 1 & 2. Minta socket untuk ditulis dan Langsung dibuka
        OutputStream keluaran = koneksi.getOutputStream();
        // Note: Karena protokol-nya berbasis teks pakai writer aja.
        Writer keluaranWriter = new OutputStreamWriter(keluaran); 
        // 3. Selagi ada data kirim
        keluaranWriter.write(namaDomain);
        // Syarat protokol-nya, semua perintah diakhiri dengan: CR & LF
        keluaranWriter.write("\r\n"); 
        keluaranWriter.flush(); // Paksa kirim data yang belum terkirim
    }

}
