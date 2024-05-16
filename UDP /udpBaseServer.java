import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udpBaseServer {
    public static void main(String[] args) throws IOException {
        // Create a socket to listen at port 1234
        DatagramSocket ds = new DatagramSocket(1234);
        byte[] receive = new byte[65535];

        DatagramPacket DpReceive = null;
        while (true) {
            // Create a DatagramPacket to receive the data.
            DpReceive = new DatagramPacket(receive, receive.length);

            // Receive the data in byte buffer.
            ds.receive(DpReceive);

            String input = data(receive).toString();
            System.out.println("Client: " + input);

            if (input.equals("bye")) {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            // Process the input (square the number and sum the digits of the squared number)
            int number = Integer.parseInt(input);
            int squared = number * number;
            int sumOfDigits = sumDigits(squared);

            // Convert the result to byte array
            byte[] sendData = String.valueOf(sumOfDigits).getBytes();

            // Get the client's address and port
            InetAddress clientAddress = DpReceive.getAddress();
            int clientPort = DpReceive.getPort();

            // Create a DatagramPacket to send the data
            DatagramPacket DpSend = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            ds.send(DpSend);

            // Clear the buffer after every message.
            receive = new byte[65535];
        }
        ds.close();
    }

    // A utility method to convert the byte array data into a string representation.
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

    // A utility method to sum the digits of a number
    public static int sumDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}

