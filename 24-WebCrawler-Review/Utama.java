
/**
 * Write a description of class Utama here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Utama
{
    public static void main(String[] args) {
        try {
            WebCrawler kirim = new WebCrawler();            
            kirim.crawler("GET index.html");
        }
        catch (UnknownHostException ex) {
            System.err.println(ex);
        }
        catch (IOException ex) {
            System.err.println(ex);
        }
    }


}
