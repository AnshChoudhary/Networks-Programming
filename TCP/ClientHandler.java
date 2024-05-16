import java.io.*;
import java.net.*;

public class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Create input and output streams
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            while (true) {
                // Read the number from the client
                String input = in.readUTF();
                System.out.println("Received from client: " + input);

                // Break the loop if the client sends "bye"
                if (input.equals("bye")) {
                    System.out.println("Client sent bye.....EXITING");
                    break;
                }

                // Process the input (square the number and sum the digits of the squared number)
                int number = Integer.parseInt(input);
                int squared = number * number;
                int sumOfDigits = sumDigits(squared);

                // Send the result back to the client
                out.writeUTF(String.valueOf(sumOfDigits));
            }

            // Close the streams and the socket
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
