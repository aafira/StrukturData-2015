
/**
 * Write a description of class AKeAt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class AKeAt
{
    public void aKeAt(String sumber, String sasaran)throws IOException{
        FileOutputStream sasaranKopi = null;
        FileInputStream  sumberKopi = null;
          
         try {
            // Object stream untuk masukkan
            sumberKopi = new FileInputStream(sumber);
            sasaranKopi = new FileOutputStream(sasaran);
            
            // Coba baca  dari stream
            int karakter = sumberKopi.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan
                if(karakter == 'a' || karakter == 'A'){
                    sasaranKopi.write('@');
                }
                else{sasaranKopi.write(karakter);}
                // Coba baca lagi dari stream
                karakter = sumberKopi.read();
            }
            //flush semua
            sasaranKopi.flush();
         } 
         //akan menjalankan isi blog(bracket) mau salah atau benar pada kondisi try
         finally {
            // Tutup stream masukan
            if (sumberKopi != null){
                sumberKopi.close();
            }
            if (sasaranKopi != null){
                sasaranKopi.close();
            }
         }
    }
}
