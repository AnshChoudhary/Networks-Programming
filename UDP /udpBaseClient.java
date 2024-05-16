import java.io.*;
import java.net.*;
import java.util.*;

public class udpBaseClient{
    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);

        // Create the socket object for carrying the data.
        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();
        byte buf[] = null;

        // loop while user not enters "bye"
        while (true) {
            System.out.print("Enter a number: ");
            String inp = sc.nextLine();

            // convert the String input into the byte array.
            buf = inp.getBytes();

            // Create the datagramPacket for sending the data.
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);

            // Invoke the send call to actually send the data.
            ds.send(DpSend);

            if (inp.equals("bye"))
                break;

            // Buffer to receive response
            byte[] receive = new byte[65535];
            DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);
            ds.receive(DpReceive);

            String response = new String(DpReceive.getData(), 0, DpReceive.getLength());
            System.out.println("Server response: " + response);
        }
        ds.close();
    }
}

