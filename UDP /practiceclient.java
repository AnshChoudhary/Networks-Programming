import java.io.*;
import java.net.*;
import java.util.*;

public class practiceclient {
    public static void main(String [] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte[] buf = null;
        
        while(true) {
            System.out.print("Enter a number: ");
            String inp = sc.nextLine();

            buf = inp.getBytes();

            DatagramPacket dp = new DatagramPacket(buf, buf.length, ip,8009 );
            ds.send(dp);

            if (inp.equals("bye"))
                break;

            byte[] receive = new byte[65535];
            DatagramPacket dpr = new DatagramPacket(receive, receive.length);
            ds.receive(dpr);

            String response = new String(dpr.getData(),0,dpr.getLength());
            System.out.println("Server response: " + response);
        }
        ds.close();
    }
}
