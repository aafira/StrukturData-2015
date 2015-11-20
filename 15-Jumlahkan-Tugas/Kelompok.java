
/**
 * Write a description of class Kelompok here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Kelompok implements Runnable

{
    private int awal, akhir;
    private double[] data;
    private double nilaiHasil;
    
    public Kelompok(int awal, int akhir, double[] data){
        this.awal = awal;
        this.akhir = akhir;
        this.data = data;
    }
    
    public double hasil(){
        return nilaiHasil;
    }
    
    public void hitung(){
        int i;
        
        for(i=awal; i<=akhir; i++){
            nilaiHasil +=data[i];
        }
    }
    
    public void run(){
        hitung();
        hasil();
    }
}
