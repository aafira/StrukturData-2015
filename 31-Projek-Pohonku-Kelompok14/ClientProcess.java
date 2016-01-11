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
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.text.*;

public class ClientProcess implements Runnable{
    public ClientProcess(Socket koneksi, Penyimpanan simpan) {
        this.koneksi = koneksi;
        this.simpan = simpan;
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

                // Selama masih terhubung dengan client tangani
                tangani();
            }
            catch(IOException salah) { 
                System.out.println(salah);
            }
            finally {
                try { 
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
        // Baca perintah dari socket
        String perintah = masukanReader.readLine();
        // Jika tidak ada perintah keluar saja
        if (perintah == null)
            return;            
        // Ada perintah, hilangkan spasi depan & belakang serta ubah ke huruf besar semua
        perintah = perintah.trim().toUpperCase();

        // Ambil protokol-nya
        String[] protokol = perintah.split(" ");

        // Tangani perintahnya
        //perintah input data
        if ((protokol.length) == 5) {
            tanganiSensor(protokol);
        }
        //perintah tampilkan data
        else if ((protokol.length) < 5) {
            //data tampil satu jam terakhir
            if((protokol.length) == 2){
                tanganiClient2(protokol);
            }
            //data tampil sesuai permintaan rentang waktu
            else if((protokol.length) == 3){
                tanganiClient3(protokol);
            }
            else {
                perintahTakDikenal();
            }
        }
        else {
            perintahTakDikenal();
        }

        // Tampilkan perintahnya
        System.out.println("Dari: " + ipStr);
        System.out.println("perintah: " + perintah);
        //simpan.showData();
        System.out.println();
    }

    public void tanganiSensor(String [] protokol)
    throws IOException
    {
        //membelah jenis data dengan nilainya
        String [] suhu = protokol[1].split("_");
        String [] lembab = protokol[2].split("_");
        String [] uv = protokol[3].split("_");
        String [] nitrogen = protokol[4].split("_");

        // Ambil waktunya
        Date waktu = new Date();

        //format: ID SUHU LEMBAB UV NITROGEN
        //pengecekan format memasukkan data benar   
        //gak bisa pake isdata langsong, urutan suhu lembab uv nitrogen harus benar
        if(isID(protokol[0]) && isData(suhu,1) && isData(lembab,2) && isData(uv,3) && isData(nitrogen,4)){            
            //memasukkan data ke dalam arrayList <Pohonku> melalui kelas penyimpanan
            simpan.isiData(protokol[0], waktu, suhu, lembab, uv, nitrogen);
            //pemberitahuan kepada client
            keluaranWriter.write("Data telah disimpan");
            keluaranWriter.newLine();
            keluaranWriter.flush();
        }
        else{
            perintahTakDikenal();
        }
    }

    public void tanganiClient2(String [] protokol)
    throws IOException
    {        
        SimpleDateFormat formatt = new SimpleDateFormat("HH:mm");
        
        Date jamTerakhir;
        Date satuJamSebelumnya;
        Calendar kalender = Calendar.getInstance();
        try{
            if(simpan.getLastHour()!=null){
                //simpan.getLastHour returnnya String
                // Ambil waktu terakhir kali data dimasukkan
                jamTerakhir = formatt.parse(simpan.getLastHour());
            }
            else{
                keluaranWriter.write("Data tidak tersedia");
                keluaranWriter.newLine();
                keluaranWriter.flush();
                return;
            }
            satuJamSebelumnya=jamTerakhir;
            //System.out.println("JamGet1 "+simpan.getLastHour());
            jamTerakhir = formatt.parse(formatt.format(jamTerakhir));
            //convert date to calendar
            kalender.setTime(satuJamSebelumnya);
            kalender.add(Calendar.HOUR_OF_DAY, -1); //mengurangi waktu(untuk satu jam sebelumnya)
            //convert calendar to date
            satuJamSebelumnya.setTime(kalender.getTimeInMillis());
            satuJamSebelumnya = formatt.parse(formatt.format(satuJamSebelumnya));

            //System.out.println("JamTerakhir "+formatt.format(jamTerakhir)+" SatuJam "+formatt.format(satuJamSebelumnya));
            System.out.println("JamGet2 "+simpan.getLastHour());
        }catch(ParseException e){
            perintahTakDikenal();
            return;
        }

        //string balasan untuk dikirim ke client
        String data = "";        

        if(isID(protokol[0])){            
            //pilih jenis data yang ingin ditampilkan
            //else: jenis data tidak tersedia
            //data = simpan.getData(protokol[0], protokol[1], sekarang, satuJam);
            String hasil = simpan.getData(protokol[0], protokol[1], jamTerakhir, satuJamSebelumnya);

            if(hasil!=""){
                data = hasil;
            }
            else if(data==""){
                data = "Data tidak tersedia";
            }

        }
        else{
            perintahTakDikenal();
        }

        keluaranWriter.write(data);
        keluaranWriter.newLine();
        keluaranWriter.flush();
    }

    public void tanganiClient3(String [] protokol)
    throws IOException
    {
        String [] waktu = protokol[2].split("-");
        //jika format penulisan waktu salah
        if(waktu.length!=2){perintahTakDikenal(); return;}

        SimpleDateFormat formatt = new SimpleDateFormat("HH:mm");
        
        Date pertama;
        Date kedua;
        Calendar kalender = Calendar.getInstance();;
        try{
            // Ambil waktu sesuai permintaan
            pertama = formatt.parse(waktu[0]);
            kedua = formatt.parse(waktu[1]);

            System.out.println("Sekarag "+waktu[0]+"Kedua "+waktu[1]);
        }catch(ParseException e){
            //karena format penulisan waktu salah
            keluaranWriter.write(PERINTAH_TIDAK_DIKENAL, 0, PERINTAH_TIDAK_DIKENAL.length());
            keluaranWriter.newLine();
            keluaranWriter.flush();
            return;
        }

        //string untuk dikirim ke client
        String data = "";        

        if(isID(protokol[0])){            
            //pilih jenis data yang ingin ditampilkan
            //else: jenis data tidak tersedia
            String hasil = simpan.getDataWaktu(protokol[0], protokol[1], pertama, kedua);
            if(hasil!=""){
                data = hasil;
            }
            else if(hasil==""){
                data = "Data pada waktu tersebut tidak tersedia";
            }
        }
        else{
            perintahTakDikenal();
        }

        keluaranWriter.write(data);
        keluaranWriter.newLine();
        keluaranWriter.flush();
    }

    public void perintahTakDikenal()
    throws IOException
    {
        // Perintah tidak dikenal
        keluaranWriter.write(PERINTAH_TIDAK_DIKENAL, 0, PERINTAH_TIDAK_DIKENAL.length());
        keluaranWriter.newLine();
        keluaranWriter.flush();
    }

    public boolean isID(String id){
        if((id.length())>=3){            
            return true;
        }
        else{
            return false;
        }

        //keluaranWriter.write("ID Pohon Tidak Ada!");
        //  keluaranWriter.newLine();
        //keluaranWriter.flush();

    }

    public boolean isData(String [] data, int i){
        if( data.length == 2){  
            if(i==1){
                if(data[0].equals("SUHU")){return true;}
                else{return false;}
            }
            else
            if(i==2){
                if(data[0].equals("LEMBAB")){return true;}
                else{return false;}
            }
            else
            if(i==3){
                if(data[0].equals("UV")){return true;}
                else{return false;}
            }
            else
            if(i==4){
                if(data[0].equals("NITROGEN")){return true;}
                else{return false;}
            }
            else{return false;}

        }
        else{
            return false;
        }

    }
    //Tempat menyiman data dari sensor
    Penyimpanan simpan = new Penyimpanan();   

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

    private final static String PERINTAH_TIDAK_DIKENAL = "Perintah tidak dikenal!";

}