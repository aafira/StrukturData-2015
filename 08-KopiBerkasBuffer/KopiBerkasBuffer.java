
/**
 * Tugas programming mata kuliah Komunikasi Data.
 * 
 * @author Aida Afira 
 * @version 8.11.15
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

import java.io.IOException;

public class KopiBerkasBuffer {
    public void kopi(String sumber, String sasaran)throws IOException{
        // Deklarasi variabel
        FileInputStream  sumberKB= null;        //KB=Kopi Buffer
        BufferedInputStream masukanBuffer = null;
        
        FileOutputStream sasaranKB = null;
        BufferedOutputStream keluaranBuffer = null;
       
        try {
            // Object stream untuk masukkan
            sumberKB = new FileInputStream(sumber);
            masukanBuffer = new BufferedInputStream(sumberKB);
            
            sasaranKB = new FileOutputStream(sasaran);
            keluaranBuffer = new BufferedOutputStream(sasaranKB);
            
            // Coba baca  dari stream
            int karakter = masukanBuffer.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan
                keluaranBuffer.write(karakter);
                // Coba baca lagi dari stream
                karakter = masukanBuffer.read();
            }
            // Flush semua
            keluaranBuffer.flush();
        } 
        finally {
            // Tutup stream masukan
            if (masukanBuffer != null && keluaranBuffer != null){
                masukanBuffer.close();
                keluaranBuffer.close();
            }
            
            if (sumberKB != null && sasaranKB != null){
                sumberKB.close();
                sasaranKB.close();
            }
        }
    } 
}
