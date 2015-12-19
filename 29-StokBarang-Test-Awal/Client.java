import java.net.Socket;
import java.net.UnknownHostException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.BufferedWriter;

import java.util.Scanner;

public class Client {
    public void chat() 
                throws UnknownHostException, IOException {
        Socket socket = new Socket("192.168.43.13", 33333);
        try {
          String ketikanSatuBaris;
 
          do{  
              // Ketik            
            Scanner keyboard = new Scanner(System.in);
            System.out.print("Perintah: ");
            ketikanSatuBaris = keyboard.nextLine();
            ketikanSatuBaris = ketikanSatuBaris.toUpperCase();
            // Tulis ke socket
            Writer keluaranWriter = new OutputStreamWriter(socket.getOutputStream()); 
            BufferedWriter keluaranBuff = new BufferedWriter(keluaranWriter);
            keluaranBuff.write(ketikanSatuBaris);
            keluaranBuff.write("\n");
            keluaranBuff.flush();
            
            if((ketikanSatuBaris.charAt(0))=='J'){
                System.out.print("Server: ");
                Reader masukan = new InputStreamReader(socket.getInputStream()); 
                BufferedReader masukanBuff = new BufferedReader(masukan);
                String baris = masukanBuff.readLine();
                System.out.println(baris);
            }
            
          }while((ketikanSatuBaris.charAt(0))!='S');
          
          if((ketikanSatuBaris.charAt(0))=='S')
             socket.close();
          
        }
        catch(IOException salah) {
            System.out.println(salah);
        }
        finally {
            if (socket != null)
            socket.close();
        }
    }
}
