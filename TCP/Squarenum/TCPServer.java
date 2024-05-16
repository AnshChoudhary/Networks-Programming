import java.io.*;
import java.net.*;

public class TCPServer {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server is listening on port 1234");

            while (true) {
                // Accept a client connection
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Create a new thread for the client
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
