
/**
 * Write a description of class Gabung here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gabung
{
    private double[] data;
    private Kelompok[] kelompok = new Kelompok[5];
    
    int n, i;
        
    
    public Gabung(double[] data){
        this.data = data;
        n = data.length;

        
        for(i=0;i<5;i++){
            int awal = (n/5)*i;
            int akhir= ((i+1)*n/5)-1;
            kelompok[i]= new Kelompok(awal, akhir, data);
        }        
        
        Thread t1 = new Thread(kelompok[0]);
        Thread t2 = new Thread(kelompok[1]);
        Thread t3 = new Thread(kelompok[2]);
        Thread t4 = new Thread(kelompok[3]);
        Thread t5 = new Thread(kelompok[4]);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        
    }
    
    public double hasil(){
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){
        }
        double hasil=0;
        for(i=0;i<5;i++){
            hasil +=kelompok[i].hasil();
        }
        return hasil;
    }
}
