import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 1234);

            // Create input and output streams
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            while (true) {
                System.out.print("Enter a number (or type 'bye' to exit): ");
                String inp = sc.nextLine();

                // Send the number to the server
                out.writeUTF(inp);

                // Break the loop if the user enters "bye"
                if (inp.equals("bye")) {
                    break;
                }

                // Read the response from the server
                String response = in.readUTF();
                System.out.println("Server response: " + response);
            }

            // Close the streams and the socket
            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
