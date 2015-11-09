
/**
 * Tugas programming mata kuliah Komunikasi Data.
 * 
 * @author Aida Afira 
 * @version 9.11.15
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Merge
{
    public void merge(String file1, String file2, String file3, String sasaran)throws IOException{
        FileInputStream  sumberFile1 = null;
        FileInputStream  sumberFile2 = null;
        FileInputStream  sumberFile3 = null;
        FileOutputStream  hasilFile= null;
        
        try {
            // Object stream untuk masukkan
            sumberFile1 = new FileInputStream(file1);
            sumberFile2 = new FileInputStream(file2);
            sumberFile3 = new FileInputStream(file3);
            hasilFile = new FileOutputStream(sasaran);
            
            /*
            int karakter = sumberFile1.read()+sumberFile2.read()+sumberFile3.read();        
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan semua isi file dalam satu file
                hasilFile.write(karakter);
                
                // Coba baca lagi dari stream
                karakter = sumberFile1.read()+sumberFile2.read()+sumberFile3.read();
            }*/
            
            // Coba baca  dari stream
            int karakter = sumberFile1.read();
        
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan semua isi file dalam satu file
                hasilFile.write(karakter);
                
                // Coba baca lagi dari stream
                karakter = sumberFile1.read();
            }
            
            
            karakter = sumberFile2.read();
            
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan semua isi file dalam satu file
                hasilFile.write(karakter);
                
                // Coba baca lagi dari stream
                karakter = sumberFile2.read();
            }
            
            
            karakter = sumberFile3.read();
            
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan semua isi file dalam satu file
                hasilFile.write(karakter);
                
                // Coba baca lagi dari stream
                karakter = sumberFile3.read();
            }
            //flush semua
            hasilFile.flush();
         } 
         //akan menjalankan isi blog(bracket) mau salah atau benar (pada kondisi try).
         finally {
            // Tutup stream masukan
            if (sumberFile1 != null && sumberFile2 != null && sumberFile3 != null){
                sumberFile1.close();
                sumberFile2.close();
                sumberFile3.close();
            }
            // Tutup stream keluaran/sasaran
            if (hasilFile != null){
                hasilFile.close();
            }
            
         }                
    }
}