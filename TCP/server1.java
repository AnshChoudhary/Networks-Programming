import java.io.*;
import java.net.*;
class server1{
    public static void main(String [] args)
    {
        ServerSocket server = null;
        try{
            server = new ServerSocket(8009);
            server.setReuseAddress(true);
            while (true){
                Socket client = server.accept();
                System.out.println("New Client connected"+ client.getInetAddress().getHostAddress());
                ClientHandler1 clientSock = new ClientHandler1(client);
                new Thread(clientSock).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if (server != null) {
                try{
                    server.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}