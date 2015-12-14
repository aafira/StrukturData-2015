import java.io.IOException;

import java.net.UnknownHostException;

import java.util.Scanner;

public class UtamaClient {
    public static void main(String[] args)
                  throws UnknownHostException, IOException {
        Client client = new Client();
        
        System.out.print("Masukkan IP Server: ");
        Scanner input = new Scanner(System.in);
        String ip;
        ip = input.nextLine();
        
        client.chat(ip);
    }
}
