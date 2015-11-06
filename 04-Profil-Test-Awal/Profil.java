public class Profil
{
    private String nama = new String("Aida Afira");
    private String nim = new String("1408107010046");
    
    public Profil(){}
    
    public Profil(String nama, String nim){
        this.nama=nama;
        this.nim=nim;
    }
    
    public String getNama(){
        return nama;
    }
    
    public String getNim(){
        return nim;
    }
}
