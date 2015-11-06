
/**
 * Write a description of class KopiBerkas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileOutputStream;

public class CaesarCipher
{
    private int shift;
    
    public CaesarCipher(int shift){
         this.shift=shift;
    }
    
    public static void main(String []args){
       try {
            CaesarCipher cc = new CaesarCipher(6);
            cc.enkripsi("ujisasaran.txt", "enkripsi.txt");
            cc.dekripsi("ujisasaran.txt", "dekripsi.txt");
        }
       catch (IOException kesalahan) {
            System.out.printf("Terjadi kesalahan: %s", kesalahan);
        }    
    }
     
    public void enkripsi(String sumber, String sasaran) throws IOException{
        FileOutputStream sasaranCC = null;
        FileInputStream  sumberCC = null;
        
        try {         
            sumberCC = new FileInputStream(sumber);
            sasaranCC = new FileOutputStream(sasaran);
            
            int karakter = sumberCC.read();
            
            while (karakter != -1) {
                karakter = karakter+shift;
                sasaranCC.write(karakter);
                karakter = sumberCC.read();
            }
            sasaranCC.flush();
         } 
         //akan menjalankan isi blog(bracket) mau salah atau benar pada kondisi try
         finally {
            // Tutup stream masukan
            if (sumberCC != null){
                sumberCC.close();
            }
            if (sasaranCC != null){
                sasaranCC.close();
            }
         }
    }
    
    public void dekripsi(String sumber, String sasaran) throws IOException{
        FileOutputStream sasaranCC = null;
        FileInputStream  sumberCC = null;
        try {
                        
            sumberCC = new FileInputStream(sumber);
            sasaranCC = new FileOutputStream(sasaran);
            
            int karakter = sumberCC.read();
            
            while (karakter != -1) {
                karakter = karakter-shift;
                sasaranCC.write(karakter);
                karakter = sumberCC.read();
            }
            sasaranCC.flush();
         } 
         //akan menjalankan isi blog(bracket) mau salah atau benar pada kondisi try
         finally {
            // Tutup stream masukan
            if (sumberCC != null){
                sumberCC.close();
            }
            if (sasaranCC != null){
                sasaranCC.close();
            }
         }
    }
        
}
