import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketPermission;

public class practiceserver {
    public static void main(String [] args) throws IOException{
        DatagramSocket ds = new DatagramSocket(8009);
        byte[] receive = new byte[65535];
        DatagramPacket dpr = null;
        while(true) {
            dpr = new DatagramPacket(receive, receive.length);
            ds.receive(dpr);

            String input = data(receive).toString();
            System.out.println("Client: "+ input);

            if (input.equals("bye")){
                System.out.println("Client sent bye...Exiting");
                break;
            }

            int number = Integer.parseInt(input);
            int squared = number * number;
            int sumOfDigits = sumDigits(squared);

            byte[] sendData = String.valueOf(sumOfDigits).getBytes();
            InetAddress clinetAddress = dpr.getAddress();
            int clientPort =dpr.getPort();
            DatagramPacket dps = new DatagramPacket(sendData,sendData.length,clinetAddress,clientPort);
            ds.send(dps);

            receive = new byte[65535];

        }
        ds.close();
    }
    public static StringBuilder data(byte[] a) {
        if (a == null) 
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
    public static int sumDigits(int number) {
        int sum = 0;
        while (number !=0){
            sum +=number % 10;
            number /=10;
        }
        return sum;
    }
}
