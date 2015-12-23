import java.net.Socket;
import java.net.InetAddress;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;

import java.util.Calendar;

public class ClientProcess implements Runnable {
    public ClientProcess(Socket koneksi) {
        this.koneksi = koneksi;
    }

    public void run() {        
        if (koneksi != null) {
            // Ambil IP dari koneksi
            ipStr = koneksi.getInetAddress().getHostAddress();
            
            try {
                // Ambil InputStream
                masukan = koneksi.getInputStream();
                masukanReader = new BufferedReader(new InputStreamReader(masukan)); 
                // Ambil OutputStream
                keluaran = koneksi.getOutputStream();
                keluaranWriter = new BufferedWriter(new OutputStreamWriter(keluaran)); 

                if(koneksi!=null){
                    tangani();
                }
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
                    // Tampilkan pesan
                    System.out.println("Tutup: " + ipStr);
                    System.out.println();
                    
                    // Tutup koneksi
                    koneksi.close();
                }
                catch(IOException salah) {
                    System.out.println(salah);
                }                
            }
        }
    }
    
    private void tangani() throws IOException {
        String ip = koneksi.getInetAddress().getHostAddress();
        System.out.println("Dari: " + ip);
        
        // Baca perintah dari socket
        String perintah = masukanReader.readLine();
        
        //membelah string
        String [] hasil = perintah.split(" ");
            
        // Jika tidak ada perintah keluar saja
        if (perintah == null)
            return;
        
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();
            
        // Tangani perintahnya
        if (perintah.compareTo("SIAPA") == 0)
        {
            // Kirim IP client
            System.out.println("IP client adalah " + ipStr);
            keluaranWriter.write(ipStr);
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else if (perintah.compareTo("WAKTU") == 0)
        {
            //konversi waktu ke string
            String waktuStr = kalender.getTime().toString();
            
            //kirim waktu server            
            keluaranWriter.write(waktuStr);            
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else if ((hasil[0].compareTo("WAKTU")==0) && Character.isDigit(hasil[1].charAt(0))) 
        {
          //else if ((hasil[0].compareTo("WAKTU")==0) && Integer.parseInt(hasil[1])==2){

            N = Integer.parseInt(hasil[1]);
            
            //menambah waktu sesuai N
            kalender.add(Calendar.HOUR_OF_DAY, N);
            
            //konversi waktu ke string
            String waktuStr = kalender.getTime().toString();
            
            keluaranWriter.write(waktuStr);            
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else
        {
            String notif = "Perintah tidak dikenal!";
            
            keluaranWriter.write(notif);            
            keluaranWriter.newLine();
            keluaranWriter.flush();      
        }
        
        // Tampilkan perintahnya
        System.out.println("Dari: " + ipStr);
        System.out.println("perintah: " + perintah);
        System.out.println();
    }

    
    //mengetahui waaktu
    Calendar kalender = Calendar.getInstance();
    int N;
    // Koneksi ke Client
    private Socket koneksi; 
    // IP address dari client
    private String ipStr; 
    
    // InputStream untuk baca perintah
    private InputStream masukan = null;
    // Reader untuk InputStream, pakai yang buffer
    private BufferedReader masukanReader = null;
    // OutputStream untuk tulis balasan
    private OutputStream keluaran = null;
    // Writer untuk OutputStream, pakai yang buffer
    private BufferedWriter keluaranWriter = null;
    
    // Jumlah stok barang, harus static karena dipakai bersama semua thread
    static private int jumlah=0;
}
