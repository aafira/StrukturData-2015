
/**
 * Write a description of class KopiBerkas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class KopiBerkas
{
   public static void main(String []args){
       try {
            KopiBerkas kopii = new KopiBerkas();
            kopii.kopi("ujisasaran.txt", "kopi.txt");
        }
        catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }    
    }
    
    public void kopi(String sumber, String sasaran) throws IOException{
          FileOutputStream sasaranKopi = null;
          FileInputStream  sumberKopi = null;
          //sumberKopi="ujisumber.txt";
          //sasaranKopi="ujisasaran.txt";
        
         try {
            // Object stream untuk masukkan
            sumberKopi = new FileInputStream(sumber);
            sasaranKopi = new FileOutputStream(sasaran);
            
            // Coba baca  dari stream
            int karakter = sumberKopi.read();
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan
                sasaranKopi.write(karakter);
                // Coba baca lagi dari stream
                karakter = sumberKopi.read();
            }
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
