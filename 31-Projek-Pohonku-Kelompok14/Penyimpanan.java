import java.text.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.ArrayList;

public class Penyimpanan 
{
    private ArrayList <Pohonku> simpan;
    private String jamAkhir = null;
    private SimpleDateFormat df = new SimpleDateFormat("HH:mm");

    public Penyimpanan()
    {
        //instansiasi
        simpan = new ArrayList<Pohonku>();
    }

    public void isiData(String id, Date waktu, 
           String[]suhu, String[]lembab, String[]uv, String[]nitrogen)
    {
        simpan.add(new Pohonku(id, waktu, suhu, lembab, uv, nitrogen));
        jamAkhir = df.format(waktu);
    }

    public String getLastHour(){
        return jamAkhir;
    }

    public void showData()
    {
        for(Pohonku pohon : simpan){
            System.out.println(pohon.showData()); 
        }            
    }

    public String getData(String Id, String data, Date jamTerakhir, Date satuJamSebelumnya)
    {
        String hasil = "";
        for(Pohonku pohon:simpan){
            if (Id.equals(pohon.getId())){ 
                if((satuJamSebelumnya.compareTo(pohon.getWaktu())<=0 && jamTerakhir.compareTo(pohon.getWaktu())>=0)){

                    if(data.equals("SUHU")){
                        hasil = hasil + pohon.getSuhu()+"\n";
                    }

                    if(data.equals("LEMBAB")){
                        hasil = hasil + pohon.getLembab()+"\n";
                    }

                    if(data.equals("UV")){
                        hasil = hasil + pohon.getUv()+"\n";
                    }

                    if(data.equals("NITROGEN")){
                        hasil = hasil + pohon.getNitrogen()+"\n";
                    }
                }
            }
        }
        return hasil;
    }

    public String getDataWaktu(String Id, String data, Date pertama, Date kedua)
    {
        String hasil = "";
        for(Pohonku pohon:simpan){
            if (Id.equals(pohon.getId())){

                if((pertama.compareTo(pohon.getWaktu())<=0 && kedua.compareTo(pohon.getWaktu())>=0)){

                    if(data.equals("SUHU")){
                        hasil = hasil + pohon.getSuhu()+"\n";
                    }

                    if(data.equals("LEMBAB")){
                        hasil = hasil + pohon.getLembab()+"\n";
                    }

                    if(data.equals("UV")){
                        hasil = hasil + pohon.getUv()+"\n";
                    }

                    if(data.equals("NITROGEN")){
                        hasil = hasil + pohon.getNitrogen()+"\n";
                    }
                }
            }
        }
        return hasil;
    }

    class Pohonku
    {
        private String waktu;
        private String id;
        private String suhu, lembab, uv, nitrogen;

        private DateFormat df = new SimpleDateFormat("HH:mm",new Locale("ID"));
        private Date time;

        public Pohonku(String id, Date waktu, 
        String [] suhu, String [] lembab, String [] uv, String [] nitrogen)
        {
            this.id = id;
            this.waktu = df.format(waktu);

            this.suhu = suhu[0]+" "+suhu[1];
            this.lembab = lembab[0]+" "+lembab[1];
            this.uv = uv[0]+" "+uv[1].toLowerCase();
            this.nitrogen = nitrogen[0]+" "+nitrogen[1];
        }

        public String getSuhu()
        {
            String tampil = waktu+" "+id+" "+suhu;
            return tampil;
        }

        public String getLembab()
        {
            String tampil = waktu+" "+id+" "+lembab;
            return tampil;
        }

        public String getUv()
        {
            String tampil = waktu+" "+id+" "+uv;
            return tampil;
        }

        public String getNitrogen()
        {
            String tampil = waktu+" "+id+" "+nitrogen;
            return tampil;
        }

        public String getId()
        {
            return id;
        }

        public String showData()
        {
            String tampil = waktu+" "+id+" "+suhu+" "+lembab+" "+uv+" "+nitrogen;
            return tampil;
        }

        public Date getWaktu()
        {
            //DateFormat df = new SimpleDateFormat ("HH:mm");
            //Date time = null;
            try{
                time = df.parse(waktu);
            }catch(ParseException e){
                e.printStackTrace();
            }
            return time;
        }
    }
}