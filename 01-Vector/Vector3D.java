
/**
 * Write a description of class Vector3D here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Vector3D
{
    // instance variables - replace the example below with your own
    //public double x, y, z;
    private double x, y, z;

    /**
     * Constructor for objects of class Vector3D
     */
    public Vector3D(double x, double y, double z)
    {
       this.x=x;
       this.y=y;
       this.z=z;
       
       getX();
       getY();
       getZ();
       
    }
    
    public double getX(){
        //x=this.x;
        return x;    
    }
    
    public double getY(){
        //y=this.y;
        return y;    
    }
    
    public double getZ(){
        //z=this.z;
        return z;    
    }

    
}
