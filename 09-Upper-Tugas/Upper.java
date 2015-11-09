
/**
 * Tugas programming mata kuliah Komunikasi Data.
 * 
 * @author Aida Afira 
 * @version 8.11.15
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Upper
{
    public void upper(String sumber, String sasaran)throws IOException {
        FileInputStream  sumberFile = null;
        FileOutputStream  keluaranFile= null;
        
        try {
            // Object stream untuk masukkan
            sumberFile = new FileInputStream(sumber);
            keluaranFile = new FileOutputStream(sasaran);
            
            // Coba baca  dari stream
            int karakter = sumberFile.read();
                     
                     
            // Selama masih ada data yang masih bisa dibaca
            while (karakter != -1) {
                // Lakukan sesuatu dengan data yang dibaca => Tampikan dengan huruf besar
                karakter=Character.toUpperCase(karakter);
                keluaranFile.write(karakter);
                
                // Coba baca lagi dari stream
                karakter = sumberFile.read();
            }
            
            //flush semua
            keluaranFile.flush();
         } 
         //akan menjalankan isi blog(bracket) mau salah atau benar (pada kondisi try).
         finally {
            // Tutup stream masukan
            if (sumberFile != null){
                sumberFile.close();
            }
            // Tutup stream keluaran/sasaran
            if (keluaranFile != null){
                keluaranFile.close();
            }
            
         }                
    }    
    
    
}
